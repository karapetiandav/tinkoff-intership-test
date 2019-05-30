package ru.karapetiandav.tinkoffintership

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.karapetiandav.tinkoffintership.di.module.Modules

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(Modules.appModule)
        }
    }
}