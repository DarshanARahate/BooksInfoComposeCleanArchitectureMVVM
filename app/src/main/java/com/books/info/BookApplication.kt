package com.books.info

import android.app.Application
import android.content.Context

class BookApplication : Application() {
    init {
        app = this
    }

    companion object {
        private lateinit var app: BookApplication
        fun getAppContext(): Context = app.applicationContext
    }
}