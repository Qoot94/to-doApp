package com.example.to_doapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var clMainK: ConstraintLayout
    private var myItems = ArrayList<TodoList>()
    lateinit var myRecycle: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //link var to ui
        val floatingButton = findViewById<ImageButton>(R.id.ibFloat)
        myRecycle = findViewById<RecyclerView>(R.id.rvMain)
        myRecycle.adapter = RVAdapter(myItems)
        myRecycle.layoutManager = LinearLayoutManager(this)

        clMainK = findViewById(R.id.clMain)


        floatingButton.setOnClickListener {
            customAlert()
        }
    }

    //functions
    private fun customAlert() {
        // first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)
        // then we set up the input
        val input = EditText(this)
        // here we set the message of our alert dialog
        dialogBuilder.setMessage("Enter your message:")
            // positive button text and action
            .setPositiveButton("Submit", DialogInterface.OnClickListener { _, id ->
                myItems.add(TodoList(input.text.toString()))
                myRecycle.adapter!!.notifyDataSetChanged()
            })
            // negative button text and action
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("add new")
        // add the Edit Text
        alert.setView(input)
        // show alert dialog
        alert.show()
    }

    fun deleteItems() {
        var size = myItems.size
        myItems.removeAll { it -> it.isFilled }
        size = size - myItems.size
        myRecycle.adapter!!.notifyDataSetChanged()
        Snackbar.make(clMainK, "$size item(s) deleted", Snackbar.LENGTH_SHORT).show()

    }

    //main menu configuration
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.miDelete -> {
                deleteItems()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}