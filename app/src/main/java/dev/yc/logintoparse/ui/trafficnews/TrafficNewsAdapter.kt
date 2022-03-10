package dev.yc.logintoparse.ui.trafficnews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.yc.logintoparse.databinding.ItemTrafficNewsBinding
import dev.yc.logintoparse.model.TrafficInfo

class TrafficNewsAdapter :
    ListAdapter<TrafficInfo, TrafficNewsAdapter.TrafficNewsViewHolder>(TrafficNewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TrafficNewsViewHolder.create(parent)

    override fun onBindViewHolder(holder: TrafficNewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TrafficNewsViewHolder(
        private val binding: ItemTrafficNewsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trafficInfo: TrafficInfo) {
            trafficInfo.apply {
                binding.tvMessage.text = chtmessage
                binding.tvContent.text = content
            }
        }

        companion object {
            fun create(parent: ViewGroup) = TrafficNewsViewHolder(
                binding = ItemTrafficNewsBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    class TrafficNewsDiffCallback : DiffUtil.ItemCallback<TrafficInfo>() {
        override fun areItemsTheSame(oldItem: TrafficInfo, newItem: TrafficInfo): Boolean {
            return oldItem.chtmessage == newItem.chtmessage
        }

        override fun areContentsTheSame(oldItem: TrafficInfo, newItem: TrafficInfo): Boolean {
            return oldItem == newItem
        }
    }
}