package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
             }
    }

    fun clickDatePicker (view: View) {

        val myCalnedar = Calendar.getInstance()

        val year = myCalnedar.get(Calendar.YEAR)
        val month = myCalnedar.get(Calendar.MONTH)
        val day = myCalnedar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->

            Toast.makeText(this,
                "The chosen year is $selectedYear, the month is ${selectedMonth +1} and the day is $selectedDayOfMonth"
                , Toast.LENGTH_LONG ).show()

            val selectedDate = " $selectedDayOfMonth/${selectedMonth +1 }/ $selectedYear"
            selected_date.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy" , Locale.ENGLISH)

            val theDAte = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDAte!!.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentdateToMinutes = currentDate!!.time / 60000

            val differenceInMinutes = currentdateToMinutes - selectedDateInMinutes

            selected_date_in_minute.setText(differenceInMinutes.toString())
        }
            ,year
            ,month
            ,day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }


}