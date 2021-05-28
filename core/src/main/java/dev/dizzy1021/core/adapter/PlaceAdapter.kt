package dev.dizzy1021.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.dizzy1021.core.R
import dev.dizzy1021.core.adapter.event.OnItemClickCallback
import dev.dizzy1021.core.databinding.ItemListPlaceBinding
import dev.dizzy1021.core.domain.model.Place

class PlaceAdapter : PagingDataAdapter<Place, PlaceAdapter.ListViewHolder>(DiffCallback) {

    private var onItemClickCallback: OnItemClickCallback<Place>? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback<Place>) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListPlaceBinding.bind(itemView)

        fun bind(item: Place) {
            with(binding) {
                this.placeName.text = item.name
                this.placeRating.text = item.rating.toString()

                Glide.with(itemView.context)
                    .load(item.poster)
                    .error(R.drawable.ic_no_image)
                    .placeholder(R.drawable.ic_no_image)
                    .into(this.posterPlace)
            }

            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceAdapter.ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_place, parent, false)
        )

    override fun onBindViewHolder(holder: PlaceAdapter.ListViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    object DiffCallback : DiffUtil.ItemCallback<Place>() {

        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem == newItem
        }
    }

}