package ru.karapetiandav.tinkoffintership

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.karapetiandav.tinkoffintership.features.news.navigation.NewsScreens
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity() {

    private val navigatorHolder: NavigatorHolder by lazy { DependencyInjectorImpl.navigatorHolder() }
    private val router: Router by lazy { DependencyInjectorImpl.router() }

    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator = SupportAppNavigator(this, R.id.container)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)

        router.newRootScreen(NewsScreens.NewsScreen)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
