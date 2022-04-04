package com.sequenia.component.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import moxy.MvpAppCompatFragment

abstract class BaseFragment<T : ViewBinding> : MvpAppCompatFragment() {

	private var _binding: T? = null
	val binding: T
		get() = _binding ?: throw IllegalStateException("Binding must be not null")

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_binding = getBinding(inflater, container)
		return binding.root
	}

	abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): T

	override fun onDestroyView() {
		_binding = null
		super.onDestroyView()
	}
}