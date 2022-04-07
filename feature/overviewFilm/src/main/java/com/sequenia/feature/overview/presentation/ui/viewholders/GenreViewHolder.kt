package com.sequenia.feature.overview.presentation.ui.viewholders

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

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
		binding.genreName.setTextColor(
			getColorFromAttr(
				binding.root.context,
				if (genreUi.isSelected)
					com.google.android.material.R.attr.colorOnPrimary
				else {
					com.google.android.material.R.attr.colorOnSurface
				}
			)
		)

		binding.root.setOnClickListener {
			onClickAction(genreUi.genre.genreId)
		}
	}
}

private fun getColorFromAttr(context: Context, @AttrRes attr: Int): Int = TypedValue().run {
	context.theme.resolveAttribute(attr, this, true)
	data
}