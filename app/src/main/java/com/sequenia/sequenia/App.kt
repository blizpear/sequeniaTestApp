package com.sequenia.sequenia

import android.app.Application
import com.sequenia.feature.detailsfilm.di.filmDetailsModule
import com.sequenia.feature.overview.di.overviewModule
import com.sequenia.network.di.networkModule
import com.sequenia.sequenia.di.appModule
import com.sequenia.sequenia.di.navigationModule
import com.sequenia.sequenia.di.routersModule
import com.sequenia.shared.filmsdatabase.di.filmDatabaseModule
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
				routersModule,
				networkModule,
				filmDatabaseModule,
				overviewModule,
				filmDetailsModule
			)
		}
	}
}