package com.example.themedialog

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.themedialog.databinding.ItemColorBinding


class ColorAdapter(
    private val context: Context,
    private val colorArray: List<Int>,
    var checkedPosition: Int,
    private val colorOnClick: (value: Int) -> Unit
) : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return colorArray[position].toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemColorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.binding) {
            colorView.setBackgroundColor(ContextCompat.getColor(context, colorArray[position]))
            colorView.setImageResource(if (checkedPosition == position) R.drawable.ic_round_check else 0)
            colorView.setOnClickListener {
                checkedPosition = position
                notifyDataSetChanged()
                colorOnClick(position)
            }
        }
    }

    override fun getItemCount() = colorArray.size

    inner class ViewHolder(val binding: ItemColorBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.colorView.setOnClickListener {
                val lastCheckedPosition: Int = checkedPosition
                checkedPosition = adapterPosition
                binding.colorView.setImageResource(R.drawable.ic_round_check)
                notifyItemChanged(lastCheckedPosition)
            }
        }
    }
}