package com.example.trueage

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var myDatePickerBtn : Button? = null
    private var tvSelectedDate : TextView? = null
    private var tvAgeInYears : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myDatePickerBtn = findViewById(R.id.myDatePickerBtn)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInYears = findViewById(R.id.tvAgeInYears)
        myDatePickerBtn?.setOnClickListener {
            myDatePickerFun()
        }
    }
    private fun myDatePickerFun() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener{_, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate?.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val selectedDateInDateFormat = sdf.parse(selectedDate)
                val currDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val diff = currDate.time - selectedDateInDateFormat.time
                val years = diff/(1000*60*60*24*365.25)
                tvAgeInYears?.text = years.toInt().toString()
            },
            year,
            month,
            dayOfMonth
        ).show()
    }
}