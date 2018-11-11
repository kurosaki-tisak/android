package devtee.com.photos.features

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import devtee.com.photos.R
import devtee.com.photos.databinding.ItemMainListBinding
import devtee.com.photos.model.Album

class MainListAdapter(val albumClicked: ((Album)) -> Unit?) : RecyclerView.Adapter<MainListAdapter.MainCardViewHolder>() {

    var items: List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        setHasStableIds(true)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_list, parent, false)
        return MainCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainCardViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MainCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: ItemMainListBinding = DataBindingUtil.bind(itemView)!!

        fun bind(item: Album) {
            binding.item = item
            binding.root.setOnClickListener { albumClicked.invoke(item) }
        }
    }
}