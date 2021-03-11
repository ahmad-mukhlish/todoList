package com.ahmadmukhlish.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmadmukhlish.todolist.db.ListDatabase
import com.ahmadmukhlish.todolist.db.Repository
import com.ahmadmukhlish.todolist.db.ToDo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class MainActivity : AppCompatActivity() {

    val vm: MAViewModel by viewModels { MAViewModelFactory(initDb()) }

    private fun initDb(): Repository {
        val db = ListDatabase.getDatabase(this)
        return Repository(db.Dao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.dialog, null)
            AlertDialog.Builder(this).apply {
                setView(view)
                setPositiveButton("Save") { _, _ ->
                    vm.addItem(ToDo(view.item.text.toString()))
                }
                setNegativeButton("Cancel") { _, _ -> }
                setCancelable(false)
                create()
                show()
            }
        }

        todoList.layoutManager = LinearLayoutManager(this)

        vm.items.observe(this, {
            todoList.adapter = ListAdapter(it)
        })



        val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                vm.deleteItem(viewHolder.itemView.textView.text.toString())
            }

        })

        helper.attachToRecyclerView(todoList)
    }
}