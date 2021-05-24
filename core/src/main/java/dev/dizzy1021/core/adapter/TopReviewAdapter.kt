package dev.dizzy1021.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.dizzy1021.core.R
import dev.dizzy1021.core.adapter.event.OnItemClickCallback
import dev.dizzy1021.core.databinding.ItemListTopReviewBinding
import dev.dizzy1021.core.domain.model.Review

class TopReviewAdapter : RecyclerView.Adapter<TopReviewAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback<Review>? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback<Review>) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListTopReviewBinding.bind(itemView)

        fun bind(items: Review) {
            with(binding) {

            }

            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(items) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopReviewAdapter.ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_top_review, parent, false)
        )

    override fun onBindViewHolder(holder: TopReviewAdapter.ListViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val diffCallback = object : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Review>) = differ.submitList(list)
}