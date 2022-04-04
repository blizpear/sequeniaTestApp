package com.sequenia.sequenia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

	private val navigatorHolder: NavigatorHolder by inject()
	private val navigator = AppNavigator(this, R.id.fragment_container)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	override fun onStart() {
		super.onStart()
		navigatorHolder.setNavigator(navigator)
	}

	override fun onStop() {
		super.onStop()
		navigatorHolder.removeNavigator()
	}
}