package ru.karapetiandav.tinkoffintership.features.news.ui.layout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.redmadrobot.lib.sd.StubState
import com.redmadrobot.lib.sd.ZeroStubView
import kotlinx.android.synthetic.main.layout_news_stub.view.*
import ru.karapetiandav.tinkoffintership.R
import ru.karapetiandav.tinkoffintership.features.news.ui.state.StubStateData

class NewsStubLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), ZeroStubView {

    init {
        View.inflate(context, R.layout.layout_news_stub, this)
    }

    override fun setUpZero(state: StubState) {
        state as StubStateData
        state.iconResId?.let { zero_stub_icon.setImageResource(it) }
        state.titleResId?.let { zero_stub_title.text = it }
        state.description?.let { zero_stub_description.text = it }
    }
}