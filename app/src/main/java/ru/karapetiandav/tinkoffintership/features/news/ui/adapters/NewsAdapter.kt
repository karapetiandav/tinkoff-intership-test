package ru.karapetiandav.tinkoffintership.features.news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import kotlinx.android.synthetic.main.item_news.view.*
import ru.karapetiandav.tinkoffintership.R
import ru.karapetiandav.tinkoffintership.ui.models.News

class NewsAdapter(private val news: List<News>, private val onClickAction: (Int) -> Unit) : Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflatedView = parent.inflate(R.layout.item_news)
        return NewsViewHolder(inflatedView, onClickAction)
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = news[position]
        holder.bind(currentNews)
    }

    inner class NewsViewHolder(itemView: View, private val onClickAction: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onClickAction.invoke(adapterPosition)
        }

        fun bind(news: News) {
            with(itemView) {
                newsText.text = news.text
            }
        }
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}