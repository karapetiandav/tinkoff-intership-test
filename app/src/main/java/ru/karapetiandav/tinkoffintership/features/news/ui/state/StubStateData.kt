package ru.karapetiandav.tinkoffintership.features.news.ui.state

import androidx.annotation.DrawableRes
import com.redmadrobot.lib.sd.StubState

data class StubStateData(
    @DrawableRes
    val iconResId: Int? = null,
    val titleResId: String? = null,
    val description: String? = null
) : StubState