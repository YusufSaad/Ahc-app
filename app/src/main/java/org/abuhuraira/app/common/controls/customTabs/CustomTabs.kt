package org.abuhuraira.app.common.controls.customTabs

import android.widget.Toast
import android.app.PendingIntent
import android.support.customtabs.CustomTabsIntent
import android.graphics.BitmapFactory
import android.content.*
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.app.Activity
import org.abuhuraira.app.R


/**
 * Created by ahmedsaad on 2018-02-26.
 * Copyright © 2018. All rights reserved.
 */
class CustomTabs {

    private val TOOLBAR_SHARE_ITEM_ID = 1

    fun openTab(activity: Activity, url: String) {
        val builder = CustomTabsIntent.Builder()

        enableUrlBarHiding(builder)
        setToolbarColor(activity, builder)
        setCloseButtonIcon(activity, builder)
        setShowTitle(builder)
        addShareMenuItem(builder)
        addCopyMenuItem(activity, builder)

        val customTabsIntent = builder.build()

        CustomTabActivityHelper.openCustomTab(
                activity, customTabsIntent, Uri.parse(url), WebviewFallback())
    }

    /* Enables the url bar to hide as the user scrolls down on the page */
    private fun enableUrlBarHiding(builder: CustomTabsIntent.Builder) {
        builder.enableUrlBarHiding()
    }

    /* Sets the toolbar color */
    private fun setToolbarColor(context: Context, builder: CustomTabsIntent.Builder) {
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
    }

    /* Sets the secondary toolbar color */
    private fun setSecondaryToolbarColor(context: Context, builder: CustomTabsIntent.Builder) {
        builder.setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
    }

    /* Sets the Close button icon for the custom tab */
    private fun setCloseButtonIcon(context: Context, builder: CustomTabsIntent.Builder) {
        builder.setCloseButtonIcon(BitmapFactory.decodeResource(context.resources, R.drawable.abc_ic_ab_back_material))
    }

    /* Sets whether the title should be shown in the custom tab */
    private fun setShowTitle(builder: CustomTabsIntent.Builder) {
        builder.setShowTitle(true)
    }

    /* Sets animations */
    private fun setAnimations(context: Context, builder: CustomTabsIntent.Builder) {
        builder.setStartAnimations(context, R.anim.right_enter, R.anim.left_exit)
        builder.setExitAnimations(context, R.anim.left_enter, R.anim.right_exit)
    }

    /* Sets share action button that is displayed in the Toolbar */
    private fun setShareActionButton(context: Context, builder: CustomTabsIntent.Builder, url: String) {
        val icon = BitmapFactory.decodeResource(context.resources, android.R.drawable.ic_menu_share)
        val label = "Share via"

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT, url)
        shareIntent.type = "text/plain"

        val pendingIntent = PendingIntent.getActivity(context, 0, shareIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        builder.setActionButton(icon, label, pendingIntent)
    }

    /* Adds share item that is displayed in the secondary Toolbar */
    private fun addToolbarShareItem(context: Context, builder: CustomTabsIntent.Builder, url: String) {
        val icon = BitmapFactory.decodeResource(context.resources, android.R.drawable.ic_menu_share)
        val label = "Share via"

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT, url)
        shareIntent.type = "text/plain"

        val pendingIntent = PendingIntent.getActivity(context, 0, shareIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        builder.addToolbarItem(TOOLBAR_SHARE_ITEM_ID, icon, label, pendingIntent)

    }

    /* Adds a default share item to the menu */
    private fun addShareMenuItem(builder: CustomTabsIntent.Builder) {
        builder.addDefaultShareMenuItem()
    }

    /* Adds a copy item to the menu */
    private fun addCopyMenuItem(context: Context, builder: CustomTabsIntent.Builder) {
        val label = "Copy"
        val intent = Intent(context, CopyBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        builder.addMenuItem(label, pendingIntent)
    }

    class CopyBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val url = intent.dataString

            val clipboardManager: ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE)
                    as ClipboardManager
            val data = ClipData.newPlainText("Link", url)
            clipboardManager.primaryClip = data

            Toast.makeText(context, "Copied " + url!!, Toast.LENGTH_SHORT).show()
        }
    }
}