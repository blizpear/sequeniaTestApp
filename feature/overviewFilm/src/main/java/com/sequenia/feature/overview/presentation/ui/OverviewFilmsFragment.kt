package com.sequenia.feature.overview.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sequenia.component.ui.fragments.BaseFragment
import com.sequenia.feature.overview.databinding.OverviewFilmsFragmentBinding
import com.sequenia.feature.overview.domain.entity.FilmPreview
import com.sequenia.feature.overview.presentation.model.GenreUi
import com.sequenia.feature.overview.presentation.presenter.OverviewFilmsPresenter
import com.sequenia.feature.overview.presentation.view.OverviewFilmsView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get

class OverviewFilmsFragment : BaseFragment<OverviewFilmsFragmentBinding>(), OverviewFilmsView {

	companion object {

		fun getInstance() = OverviewFilmsFragment()
	}

	@InjectPresenter
	lateinit var presenter: OverviewFilmsPresenter

	@ProvidePresenter
	fun provide(): OverviewFilmsPresenter = get()

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): OverviewFilmsFragmentBinding =
		OverviewFilmsFragmentBinding.inflate(inflater, container, false)

	override fun loading() {
		TODO("Not yet implemented")
	}

	override fun error() {
		TODO("Not yet implemented")
	}

	override fun error(msg: String) {
		TODO("Not yet implemented")
	}

	override fun content(genre: List<GenreUi>, films: List<FilmPreview>) {
		TODO("Not yet implemented")
	}
}