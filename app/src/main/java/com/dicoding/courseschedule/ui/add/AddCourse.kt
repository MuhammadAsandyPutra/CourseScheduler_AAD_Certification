package com.dicoding.courseschedule.ui.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.util.TimePickerFragment
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddCourse : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

    private lateinit var startTime: String
    private lateinit var endTime: String
    private lateinit var newStarttime: TextView
    private lateinit var newEndtime: TextView
    private lateinit var vModel: AddCourseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        val factory = AddCourseViewModelFactory.AddFactory(this)
        vModel = ViewModelProvider(this, factory)[AddCourseViewModel::class.java]

        newStarttime = findViewById<EditText>(R.id.edit_start_time)
        newEndtime = findViewById<EditText>(R.id.edit_end_time)
        val timePickerFragment = TimePickerFragment()
        val startPk = findViewById<ImageButton>(R.id.clock)
        val endPk = findViewById<ImageButton>(R.id.clock_end)
        startPk.setOnClickListener{
            timePickerFragment.show(supportFragmentManager, "start")

        }
        endPk.setOnClickListener{
            timePickerFragment.show(supportFragmentManager, "end")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_insert -> {
                val EdCourseName= findViewById<TextInputEditText>(R.id.edit_coursename)
                val courseName = EdCourseName.text.toString().trim()

                val EdLecturer = findViewById<TextInputEditText>(R.id.Ed_lecturer)
                val lecturer = EdLecturer.text.toString().trim()

                val EdNote = findViewById<TextInputEditText>(R.id.Ed_note)
                val note = EdNote.text.toString().trim()

                val dayChoose = findViewById<Spinner>(R.id.day_spinnerss)
                val day = dayChoose.selectedItemPosition

                if (courseName.isEmpty() || note.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || lecturer.isEmpty()){
                    Toast.makeText(applicationContext, "Fill the form first !", Toast.LENGTH_SHORT).show()
                }else {
                    vModel.insertCourse(courseName, day, startTime, endTime, lecturer, note)
                    finish()
                    Toast.makeText(applicationContext,"Course Added Successfully !", Toast.LENGTH_SHORT)
                        .show()

                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)

        }
        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
        if (tag == "start"){
            newStarttime.text = format.format(calendar.time)
            startTime = format.format(calendar.time)
        }else {
            newEndtime.text = format.format(calendar.time)
            endTime = format.format(calendar.time)
        }
    }

}