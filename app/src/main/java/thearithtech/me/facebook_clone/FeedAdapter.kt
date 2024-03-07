package thearithtech.me.facebook_clone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import thearithtech.me.facebook_clone.databinding.ItemFeedBinding

class FeedAdapter : ListAdapter<Item, FeedAdapter.FeedViewHolder>(COMPARE) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = ItemFeedBinding.inflate(inflate, parent, false)
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    inner class FeedViewHolder(private val binding: ItemFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(data: Item) {
            with(binding) {
                setImage(data.profile, imgProfile)
                setImage(data.image, imgFeed)
                tvName.text = data.name
                tvTitle.text = data.title
                tvDuration.text = data.duration
            }
        }

    }

    companion object {

        private val COMPARE = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem

            override fun areContentsTheSame(oldItem: Item, newItem: Item) =
                oldItem.name == newItem.name
        }
    }
}