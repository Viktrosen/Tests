package com.geekbrains.tests

import org.koin.android.ext.android.startKoin
import android.app.Application
import com.geekbrains.tests.di.appModule
import com.geekbrains.tests.di.detailsModule
import com.geekbrains.tests.di.mainModule
import com.geekbrains.tests.model.room.database.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.create(this)
        startKoin(this, listOf(appModule, mainModule, detailsModule))
    }
}