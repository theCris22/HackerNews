package com.app.hackernews.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.hackernews.R
import com.app.hackernews.core.longToDate
import com.app.hackernews.data.network.models.Hit
import com.app.hackernews.databinding.RowNewsBinding

class HackerNewsAdapter(
    private val context: Context,
    private val onItemClick: (hit: Hit) -> Unit,
) : ListAdapter<Hit, HackerNewsAdapterViewHolder>(HitDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HackerNewsAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_news, parent, false)
        return HackerNewsAdapterViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HackerNewsAdapterViewHolder,
        position: Int,
    ) {
        holder.bindData(context, getItem(position), onItemClick)
    }

    fun updateData(newList: List<Hit>) {
        submitList(newList)
    }
}

class HackerNewsAdapterViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {
    private val binding = RowNewsBinding.bind(view)

    fun bindData(
        context: Context,
        hit: Hit,
        onItemClick: (hit: Hit) -> Unit,
    ) = with(binding) {
        textViewTitle.text = hit.storyTitle ?: context.getString(R.string.default_title_text)
        textViewDesc.text = hit.commentText ?: context.getString(R.string.default_description_text)
        textViewAuthor.text = hit.author ?: context.getString(R.string.default_author_text)
        textViewDate.text = hit.date?.longToDate() ?: context.getString(R.string.default_date_text)

        rowLayout.setOnClickListener { onItemClick(hit) }
    }
}

class HitDiffCallback : DiffUtil.ItemCallback<Hit>() {
    override fun areItemsTheSame(
        oldItem: Hit,
        newItem: Hit,
    ): Boolean = oldItem.objectID == newItem.objectID

    override fun areContentsTheSame(
        oldItem: Hit,
        newItem: Hit,
    ): Boolean = oldItem == newItem
}
