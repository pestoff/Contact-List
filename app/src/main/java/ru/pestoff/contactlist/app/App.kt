package ru.pestoff.contactlist.app

import android.app.Application
import ru.pestoff.contactlist.component.AppComponent
import ru.pestoff.contactlist.component.DaggerAppComponent
import ru.pestoff.contactlist.module.AppModule

class App : Application() {

    private lateinit var instance: App


    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(instance))
            .build()
    }

    companion object {
        private lateinit var appComponent: AppComponent

        fun getAppComponent() = appComponent
    }
}