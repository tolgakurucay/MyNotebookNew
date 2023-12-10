package com.tolgakurucay.mynotebooknew.util

import android.content.Context
import android.content.Intent
import com.tolgakurucay.mynotebooknew.R

fun Context.share(title: String, description: String, onShared: () -> Unit) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(
            Intent.EXTRA_SUBJECT,
            title
        )
        putExtra(
            Intent.EXTRA_TEXT,
            description
        )
    }

    val chooserIntent =
        Intent.createChooser(sendIntent, getString(R.string.action_select_the_share_app))
    if (sendIntent.resolveActivity(packageManager).isNotNull()) {
        startActivity(chooserIntent)
        onShared.invoke()
    }

}
