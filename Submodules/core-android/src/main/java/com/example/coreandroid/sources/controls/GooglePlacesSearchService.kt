package com.example.coreandroid.sources.controls

import android.net.Uri
import com.example.coreandroid.sources.ConstantsType
import com.example.coreandroid.sources.common.Result
import com.example.coreandroid.sources.extensions.callInBackgroundWithCompletionOnUi
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList

/**
 * Created by ahmedsaad on 2018-01-24.
 * Copyright © 2017. All rights reserved.
 */
class GooglePlacesSearchService(val constants: ConstantsType) {
    companion object {
        private const val PLACES_PREDICTION_ENDPOINT = "https://maps.googleapis.com/maps/api/place/autocomplete/json"
        private const val PLACES_DETAILS_ENDPOINT = "https://maps.googleapis.com/maps/api/place/details/json"
    }

    fun request(urlString: String, parameters: Map<String, String>): JSONObject? {
        var conn: HttpURLConnection? = null
        val jsonResults = StringBuilder()

        try {
            val uri = Uri.parse(urlString).buildUpon()

            parameters.forEach {
                uri.appendQueryParameter(it.key, it.value)
            }

            val url = URL(uri.toString())

            conn = url.openConnection() as HttpURLConnection
            val inputStream = InputStreamReader(conn.inputStream)

            // Load the results into a StringBuilder
            val buff = CharArray(1024)
            var read: Int = inputStream.read(buff)
            while (read != -1) {
                jsonResults.append(buff, 0, read)
                read = inputStream.read(buff)
            }
        } catch (e: MalformedURLException) {
            return null
        } catch (e: IOException) {
            return null
        } finally {
            if (conn != null) {
                conn.disconnect()
            }
        }

        return try {
            // Log.d(TAG, jsonResults.toString());
            // Create a JSON object hierarchy from the results
            JSONObject(jsonResults.toString())
        } catch (e: JSONException) {
            null
        }
    }

    fun getPlaceDetails(place: Place?, completion: Result<PlaceDetails, Void>) {
        if (place?.googlePlaceID == null) { return }

        callInBackgroundWithCompletionOnUi(completion) {

            val value = request(PLACES_DETAILS_ENDPOINT, getParameters(id = place.googlePlaceID))

            if (value == null) {
                it.setError(null)
                return@callInBackgroundWithCompletionOnUi
            }

            val placeDetails = PlaceDetails(json = value)

            // Determine if Google Place returned inaccurate detail from ID
            // Place ID will be different from request and result for this case
            // https://issuetracker.google.com/issues/35823492
            if (placeDetails.googlePlaceID != place.googlePlaceID) {
                placeDetails.formattedAddress = "${place.mainAddress}, ${place.secondaryAddress}"

                if (placeDetails.streetNumber == null) {
                    placeDetails.streetNumber = place.mainAddress.split(" ").firstOrNull()
                }
            }

            it.setResult(placeDetails)
        }
    }

    fun getPlaces(text: String, coordinate: String? = null, radius: Double = 100_000.0): ArrayList<Place>? {
        val value  = request(PLACES_PREDICTION_ENDPOINT,
                getParameters(text = text, coordinate = coordinate, radius = radius)) ?: return null

        val predicitionsJSON = value.optJSONArray("predictions")
        val predicitions = arrayListOf<JSONObject>()

        (0 until predicitionsJSON.length()).mapTo(predicitions) {
            predicitionsJSON.optJSONObject(it)
        }

        return  ArrayList(predicitions.map { Place(prediction = it) })
    }

    private fun getParameters(text: String? = null, coordinate: String? = null,
                              radius: Double = 100_000.0, id: String? = null): Map<String, String> {
        val parameters: MutableMap<String, String> = mutableMapOf()

        parameters["key"] = constants.googlePlacesAPIKey

        if (text != null) {
            parameters["types"] = "address"
            parameters["components"] = "country:ca|country:us"
            parameters["input"] = text

            if (coordinate != null) {
                parameters["location"] = coordinate
                parameters["radius"] = radius.toString()
            }
        }

        if (id != null) {
            parameters["placeid"] = id
        }

        return parameters
    }
}

data class Place(
        var mainAddress: String = "",
        var secondaryAddress: String = "",
        var googlePlaceID: String? = null) {

    constructor(prediction: JSONObject): this() {
        val structuredFormatting = prediction.optJSONObject("structured_formatting")

        this.mainAddress = structuredFormatting?.optString("main_text") ?: ""
        this.secondaryAddress = structuredFormatting?.optString("secondary_text") ?: ""

        this.googlePlaceID = prediction.optString("place_id") ?: ""
    }
}

enum class ComponentType(val raw: String) {
    Short("short_name"),
    Long("long_name")
}

data class PlaceDetails(
         var formattedAddress: String = "",
         var streetNumber: String? = null,
         var route: String? = null,
         var postalCode: String? = null,
         var city: String? = null,
         var state: String? = null,
         var country: String? = null,
         var ISOcountryCode: String? = null,
         var coordinate: Pair<Double, Double>? = null,
         var googlePlaceID: String? = null) {

    constructor(json: JSONObject?): this() {
        val result = json?.optJSONObject("result")
        val formattedAddress = result?.optString("formatted_address")

        if (result == null || formattedAddress == null) {
            return
        }

        this.formattedAddress = formattedAddress
        this.googlePlaceID = result.optString("place_id")

        val addressComponentsJSON = result.optJSONArray("address_components")
        val addressComponents = arrayListOf<JSONObject>()

        for (i in 0 until addressComponentsJSON.length()) {
            addressComponents.add(addressComponentsJSON.optJSONObject(i))
        }

        if (!addressComponents.isEmpty()) {

            /// Parses the element value with the specified type from the array or components.
            /// Example: `{ "long_name" : "90", "short_name" : "90", "types" : [ "street_number" ] }`
            fun get(component: String, array: ArrayList<JSONObject>, ofType: ComponentType): String? {
                return array.firstOrNull { it.optString("types")?.contains(component) == true }?.optString(ofType.raw)
            }

            this.streetNumber = get("street_number", addressComponents, ComponentType.Short)
            this.route = get("route", addressComponents, ComponentType.Long)
            this.postalCode = get("postal_code", addressComponents, ComponentType.Short)
            this.city = get("locality", addressComponents, ComponentType.Long)
            this.state = get("administrative_area_level_1",  addressComponents, ComponentType.Short)
            this.country = get("country", addressComponents, ComponentType.Long)
            this.ISOcountryCode = get("country", addressComponents, ComponentType.Short)
        }

        val geometry = result.optJSONObject("geometry")
        val location = geometry?.optJSONObject("location")
        val latitude = location?.optDouble("lat")
        val longitude = location?.optDouble("lng")

        if (latitude != null && longitude != null) {
            this.coordinate = Pair(latitude, longitude)
        }
    }
}