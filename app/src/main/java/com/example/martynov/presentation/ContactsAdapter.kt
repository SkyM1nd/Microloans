package com.example.martynov.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.martynov.R
import com.example.martynov.domain.entities.ContactEntity

class ContactsAdapter(private val onClick: (ContactEntity) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private companion object {
        const val NAME_VIEW_TYPE = 0
    }

    var contacts: MutableList<ContactEntity> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            NAME_VIEW_TYPE -> NameViewHolder(parent, onClick)
            else -> throw IllegalArgumentException("Wrong viewType: $viewType")
        }

    override fun getItemCount(): Int =
        contacts.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NameViewHolder -> holder.bind(contacts[position])
        }
    }

    override fun getItemViewType(position: Int): Int =
        NAME_VIEW_TYPE

    fun clear() {
        val size: Int = contacts.size
        contacts.clear()
        notifyItemRangeRemoved(0, size)
    }
}

class NameViewHolder(parent: ViewGroup, private val onClick: (ContactEntity) -> Unit) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
    ) {

    fun bind(item: ContactEntity) {
        (itemView as ConstraintLayout).findViewById<TextView>(R.id.idContact).text =
            item.id.toString()
        itemView.findViewById<TextView>(R.id.nameContact).text =
            item.name
        itemView.findViewById<TextView>(R.id.phoneContact).text =
            item.phone
        itemView.setOnClickListener { onClick(item) }
    }
}
