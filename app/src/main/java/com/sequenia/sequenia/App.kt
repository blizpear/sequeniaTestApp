package com.sequenia.sequenia

import android.app.Application
import com.sequenia.sequenia.di.appModule
import com.sequenia.sequenia.di.navigationModule
import com.sequenia.sequenia.di.routersModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger(Level.ERROR)
			androidContext(this@App)

			modules(
				appModule,
				navigationModule,
				//routersModule,
			)
		}
	}
}