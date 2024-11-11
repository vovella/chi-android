import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.ItemModel
import com.example.recyclerview.R


class ItemAdapter(private val items: MutableList<ItemModel>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val switchButton: Switch = itemView.findViewById(R.id.switchButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]

        holder.textView.text = if (item.isChecked) "ON" else "OFF"
        holder.switchButton.isChecked = item.isChecked

        holder.itemView.setBackgroundColor(
            if (item.isChecked) Color.GREEN else Color.RED
        )

        holder.switchButton.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
            holder.textView.text = if (isChecked) "ON" else "OFF"
            holder.itemView.setBackgroundColor(
                if (isChecked) Color.GREEN else Color.RED
            )
        }
    }

    override fun getItemCount(): Int = items.size
}
