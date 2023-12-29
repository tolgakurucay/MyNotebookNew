package com.tolgakurucay.mynotebooknew.util

import android.content.Context
import android.content.Intent
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.tolgakurucay.mynotebooknew.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.util.Locale

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


 fun Context.setCurrentLanguage(appLanguage: AppLanguage) {
    val config = resources.configuration
    val locale = Locale(appLanguage.name.lowercase())
    Locale.setDefault(locale)
    config.setLocale(locale)

    createConfigurationContext(config)
    resources.updateConfiguration(config, resources.displayMetrics)
}



enum class AppLanguage {
    TR,
    EN
}
