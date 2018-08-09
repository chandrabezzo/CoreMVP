package com.bezzo.coreandroid

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.bezzo.coreandroid.di.component.ApplicationComponent
import com.bezzo.coreandroid.di.component.DaggerApplicationComponent
import com.bezzo.coreandroid.di.module.ApplicationModule
import com.bezzo.coreandroid.util.AppLogger
import com.bezzo.coreandroid.util.LocaleHelper
import com.orhanobut.hawk.Hawk

/**
 * Created by bezzo on 11/01/18.
 */
class MvpApp : Application() {
    // Needed to replace the component with a test specific one
    var component: ApplicationComponent? = null

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(base, LocaleHelper.getLanguage(base)))

        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()

        component!!.inject(this)

        AppLogger.init()
        Hawk.init(this).build()
    }
}