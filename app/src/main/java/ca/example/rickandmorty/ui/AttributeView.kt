package ca.example.rickandmorty.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import ca.example.rickandmorty.R
import ca.example.rickandmorty.databinding.ViewAttributeBinding

/** Display a character attribute with a label and icon. */
class AttributeView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    private var binding: ViewAttributeBinding

    init {
        inflate(context, R.layout.view_attribute, this)
        binding = ViewAttributeBinding.bind(this)
    }

    fun setup(
        value: String,
        label: String,
        iconResourceId: Int
    ) {
        binding.attributeValue.text = value
        binding.attributeLabel.text = label
        binding.attributeIcon.setImageResource(iconResourceId)
    }
}