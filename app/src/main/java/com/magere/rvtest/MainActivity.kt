package com.magere.rvtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.magere.rvtest.adapters.PersonAdapter
import com.magere.rvtest.models.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var persons = mutableListOf<Person>()
    private val mAdapter = PersonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..3) {
            persons.add(Person(name = "Vitya", age = i))
        }

        setupAdapter()
//        setupItemTouchHelper()

        button_add.setOnClickListener {
            mAdapter.addPerson(Person(name = "Dimasta", age = 19))
        }

        button_delete.setOnClickListener {
            mAdapter.deletePerson(persons.size - 1)
        }
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        rv_persons.layoutManager = layoutManager
        rv_persons.adapter = mAdapter
//        rv_persons.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
        mAdapter.setData(persons)
    }

//    private fun setupItemTouchHelper() {
//        val itemTouchHelperCallback =
//            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
//                override fun onMove(
//                    recyclerView: RecyclerView,
//                    viewHolder: RecyclerView.ViewHolder,
//                    target: RecyclerView.ViewHolder
//                ): Boolean {
//                    return false
//                }
//
//                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
//                    mAdapter.deletePersonForSwipe(viewHolder as PersonAdapter.ViewHolder)
//                }
//
//                override fun onChildDraw(
//                    c: Canvas,
//                    recyclerView: RecyclerView,
//                    viewHolder: RecyclerView.ViewHolder,
//                    dX: Float,
//                    dY: Float,
//                    actionState: Int,
//                    isCurrentlyActive: Boolean
//                ) {
//                    val itemView = viewHolder.itemView
//                    if (dX > 0) {
//                        swipeBackground.setBounds(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
//                    } else {
//                        swipeBackground.setBounds(
//                            itemView.right + dX.toInt(),
//                            itemView.top,
//                            itemView.right,
//                            itemView.bottom
//                        )
//                    }
//
//                    swipeBackground.draw(c)
//
//                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//                }
//            }
//
//        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
//        itemTouchHelper.attachToRecyclerView(rv_persons)
//    }
}
