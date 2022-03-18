package android.example.bodegaversionone

import android.example.bodegaversionone.ItemListAdapter.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ItemListAdapter() : ListAdapter<Item, ItemViewHolder>(ItemsComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType : Int) : ItemViewHolder {
        return ItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        // fix later to grab correct text view element
        private val itemItemView : TextView = itemView.findViewById(R.id.text)

        fun bind(text: Item) {

            itemItemView.text = text.toString()
        }

        companion object {
            fun create(parent : ViewGroup) : ItemViewHolder {
                val view : View = LayoutInflater.from(parent.context).inflate(R.layout.row_items, parent, false)
                return ItemViewHolder(view)
            }
        }
    }

    class ItemsComparator(): DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.data == newItem.data
        }
    }

}
