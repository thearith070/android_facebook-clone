package thearithtech.me.facebook_clone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import thearithtech.me.facebook_clone.databinding.ItemFirstStoryBinding
import thearithtech.me.facebook_clone.databinding.ItemStoryBinding

class StoryAdapter : ListAdapter<Item, ViewHolder>(COMPARE) {

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) FIRST_STORY else STORY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val firstStoryBinding = ItemFirstStoryBinding.inflate(inflater, parent, false)
        val storyBinding = ItemStoryBinding.inflate(inflater, parent, false)
        return if (viewType == FIRST_STORY) FirstStoryViewHolder(firstStoryBinding) else StoryViewHolder(
            storyBinding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is StoryViewHolder) {
            holder.bindItem(getItem(position))
        }
    }

    inner class FirstStoryViewHolder(binding: ItemFirstStoryBinding) : ViewHolder(binding.root)

    inner class StoryViewHolder(private val binding: ItemStoryBinding) : ViewHolder(binding.root) {

        fun bindItem(data: Item) {
            with(binding) {
                setImage(data.profile, imgProfile)
                setImage(data.image, imgStory)
                tvName.text = data.name
            }
        }

    }

    companion object {

        private const val FIRST_STORY = 1
        private const val STORY = 2

        private val COMPARE = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem

            override fun areContentsTheSame(oldItem: Item, newItem: Item) =
                oldItem.name == newItem.name
        }
    }
}