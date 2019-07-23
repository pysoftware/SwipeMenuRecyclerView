package com.magere.rvtest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.magere.rvtest.R
import com.magere.rvtest.models.Person
import kotlinx.android.synthetic.main.item_left_menu.view.*
import kotlinx.android.synthetic.main.swipe_content.view.*
import java.util.*

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {
    private var mPersonsList: MutableList<Person> = LinkedList()

    private var removedPosition = 0
    private lateinit var removedPerson: Person

    fun setData(personsList: List<Person>) {
        mPersonsList.clear()
        mPersonsList.addAll(personsList)

        notifyDataSetChanged()
    }

    fun addPerson(person: Person) {
        mPersonsList.add(person)

        notifyItemInserted(mPersonsList.size - 1)
    }

    fun deletePersonForSwipe(viewHolder: ViewHolder) {
        val position = viewHolder.adapterPosition
        removedPosition = position
        removedPerson = mPersonsList[position]
        mPersonsList.removeAt(position)

        notifyItemRemoved(position)

        Snackbar.make(viewHolder.itemView, "${removedPerson.name} deleted.", Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.undo) {
                mPersonsList.add(removedPosition, removedPerson)
                notifyItemInserted(removedPosition)
            }.show()
    }

    fun deletePerson(position: Int) {
        mPersonsList.removeAt(position)

        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_left_menu, parent, false))
    }

    override fun getItemCount(): Int {
        return mPersonsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(model = mPersonsList[position])
        holder.leftMenu.setOnClickListener {
            deletePersonForSwipe(holder)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val leftMenu: TextView = itemView.left_menu

        fun bind(model: Person) {
            itemView.swipe_layout.isSwipeEnable = true
            itemView.tv_content.text = model.name
        }
    }
}