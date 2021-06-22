package ca.example.rickandmorty.ui.locations

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import ca.example.rickandmorty.R
import ca.example.rickandmorty.databinding.ListItemThreeLineBinding
import ca.example.rickandmorty.entities.Location

class LocationsAdapter(
    private val context: Context,
    private val items: List<Location>
) : BaseAdapter() {
    override fun getCount(): Int = this.items.size
    override fun getItem(i: Int): Any = i
    override fun getItemId(i: Int): Long = i.toLong()

    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup): View {
        val binding = if(convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            ListItemThreeLineBinding.inflate(inflater)
        } else {
            ListItemThreeLineBinding.bind(convertView)
        }
        items.getOrNull(i)?.let { item ->
            binding.image.setImageResource(R.drawable.ic_location)
            binding.line1.text = item.name
            binding.line2.text = item.dimension
            binding.line3.text = item.type
        }
        return binding.root
    }
}
