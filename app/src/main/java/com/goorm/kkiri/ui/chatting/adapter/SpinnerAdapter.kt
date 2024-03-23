package com.goorm.kkiri.ui.chatting.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.goorm.kkiri.R
import com.goorm.kkiri.databinding.SpinnerItemCustomBinding

class SpinnerAdapter(private val context: Context,
                     private val data: List<String>
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int = data.size

    override fun getItem(position: Int): Any = data[position]

    override fun getItemId(position: Int): Long = position.toLong()

    // 처음에 클릭 전에 보여지는 레이아웃
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: inflater.inflate(R.layout.spinner_item_custom, parent, false)

        val text = data[position]
        view.findViewById<TextView>(R.id.spinnerText).text = text

        return view
    }

    // 클릭 후 보여지는 레이아웃
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val dropDownView = convertView ?: inflater.inflate(R.layout.spinner_get_view, parent, false)

        val text = data[position]
        dropDownView.findViewById<TextView>(R.id.spinnerText).text = text

        return dropDownView
    }
}