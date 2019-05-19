package ru.karapetiandav.tinkoffintership

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.karapetiandav.tinkoffintership.features.news.ui.fragments.NewsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, NewsFragment())
            .commit()
    }
}
