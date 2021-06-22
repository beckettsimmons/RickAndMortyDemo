package ca.example.rickandmorty.ui.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import ca.example.rickandmorty.R
import ca.example.rickandmorty.databinding.ListItemThreeLineBinding
import ca.example.rickandmorty.entities.Character
import com.squareup.picasso.Picasso

class CharacterAdapter(
    private val context: Context,
    private val items: List<Character>
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
            Picasso.with(context)
                .load(item.image)
                .resize(80, 80)
                .placeholder(R.drawable.ic_outline_account_circle_24)
                .into(binding.image)
            binding.line1.text = item.name
            binding.line2.text = item.species
            binding.line3.text = item.gender

            binding.root.setOnClickListener {
                val bundle = bundleOf("userId" to item.id.toString())
                Navigation.findNavController(binding.root).navigate(
                    R.id.action_characters_to_character, bundle
                )
            }
        }
        return binding.root
    }
}
