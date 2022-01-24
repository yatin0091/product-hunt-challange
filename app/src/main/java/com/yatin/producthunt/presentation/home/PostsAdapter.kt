package com.yatin.producthunt.presentation.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yatin.producthunt.R
import com.yatin.producthunt.databinding.RowPostBinding
import com.yatin.producthunt.presentation.core.extension.inflate
import com.yatin.producthunt.presentation.core.extension.loadGifFromUrl
import javax.inject.Inject
import kotlin.properties.Delegates

class PostsAdapter
@Inject constructor() : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    internal var collection: List<PostView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (PostView) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.row_post))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = RowPostBinding.bind(itemView)
        fun bind(postView: PostView, clickListener: (PostView) -> Unit) {
            binding.image.loadGifFromUrl(postView.thumbnail)
            binding.name.text = postView.name
            binding.tagline.text = postView.tagline
            binding.voteCount.text = postView.votesCount.toString()
            binding.makers.text = postView.makers.joinToString()
            itemView.setOnClickListener {
                clickListener(postView)
            }
        }
    }
}
