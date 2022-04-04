package com.sequenia.feature.overview.presentation.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sequenia.feature.overview.R
import com.sequenia.feature.overview.databinding.GenreItemLayoutBinding
import com.sequenia.feature.overview.presentation.model.GenreUi

class GenreViewHolder(
	private val binding: GenreItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun from(parent: ViewGroup): GenreViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = GenreItemLayoutBinding.inflate(inflater, parent, false)
			return GenreViewHolder(binding)
		}
	}

	fun bind(
		genreUi: GenreUi,
		onClickAction: (Long) -> Unit
	) {
		binding.genreName.text = genreUi.genre.genre
		binding.genreButton.isChecked = genreUi.isSelected
		binding.genreButton.setBackgroundColor(
			if (!genreUi.isSelected)
				ContextCompat.getColor(binding.root.context, R.color.white)
			else {
				ContextCompat.getColor(binding.root.context, R.color.black)
			}
		)

		binding.root.setOnClickListener {
			onClickAction(genreUi.genre.genreId)
		}
	}
}