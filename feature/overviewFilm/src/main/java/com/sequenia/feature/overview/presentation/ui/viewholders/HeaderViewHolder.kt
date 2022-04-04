package com.sequenia.feature.overview.presentation.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sequenia.feature.overview.databinding.HeaderItemLayoutBinding

class HeaderViewHolder(
	private val binding: HeaderItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun from(parent: ViewGroup): HeaderViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = HeaderItemLayoutBinding.inflate(inflater, parent, false)
			return HeaderViewHolder(binding)
		}
	}

	fun bind(headerItem: String) {
		binding.headerTitle.text = headerItem
	}
}