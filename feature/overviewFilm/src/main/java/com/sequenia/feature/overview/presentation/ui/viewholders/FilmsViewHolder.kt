package com.sequenia.feature.overview.presentation.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sequenia.feature.overview.R
import com.sequenia.feature.overview.databinding.FilmItemLayoutBinding
import com.sequenia.feature.overview.domain.entity.FilmPreview

class FilmsViewHolder(
	private val binding: FilmItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun from(parent: ViewGroup): FilmsViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = FilmItemLayoutBinding.inflate(inflater, parent, false)
			return FilmsViewHolder(binding)
		}
	}

	fun bind(
		film: FilmPreview,
		onClickAction: (film: Long) -> Unit,
		loadImageAction: (view: ImageView, url: String?) -> Unit
	) {
		binding.filmName.text = film.localizedName

		loadImageAction(binding.filmImage, film.imageUrl)

		binding.root.setOnClickListener {
			onClickAction(film.filmId)
		}
	}
}