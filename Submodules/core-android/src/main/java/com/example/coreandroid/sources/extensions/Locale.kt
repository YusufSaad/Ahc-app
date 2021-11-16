package com.example.coreandroid.sources.extensions

import java.util.*

/**
 * Created by ahmedsaad on 2018-01-18.
 * Copyright © 2017. All rights reserved.
 */
var cachedRegionLocales: MutableMap<Int, Locale> = mutableMapOf()

/*fun localeFrom(region: RegionType): Locale {
    if (Locale.getDefault().country != region.country
            || Currency.getInstance(Locale.getDefault()).currencyCode != region.currency) {
        // Cache locales by region since intense lookup
        return cachedRegionLocales[region.id] ?: {
            // Assuming English available for region's country
            // TODO: Expose locale from region from server if available
            val identifier = "en_${region.country}"
            val match = Locale.getAvailableLocales().firstOrNull { it.displayName == identifier }
                    ?: Locale.getDefault()

            cachedRegionLocales[region.id] = match
            match
        }()
    }

    return Locale.getDefault()
}*/