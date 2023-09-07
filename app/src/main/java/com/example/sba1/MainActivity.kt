package com.example.sba1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {


    private val category =
        arrayOf("Citizen", "Senior Citizen", "Studies Citizen", "Disabled Citizen")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val spinner = findViewById<Spinner>(R.id.categorySpinner)
        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, category)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }

        val calculateButton = findViewById<Button>(R.id.calculateButton)

        calculateButton.setOnClickListener {

            calculateFee();
        }

        val contactButton = findViewById<Button>(R.id.contactButton)


        contactButton.setOnClickListener {

            contactJPN();
        }

    }
   private fun calculateFee() {


       val ageInput = findViewById<EditText>(R.id.ageEditText)

       val getAge = ageInput.text.toString()
       val result = findViewById<TextView>(R.id.resultTextView)

       val selectedCategory = findViewById<Spinner>(R.id.categorySpinner).selectedItem.toString()
       val studyingAbroad = findViewById<CheckBox>(R.id.studyAbroadCheckBox).isChecked

       if (getAge.isNotEmpty() && getAge.length <= 2) {
           val intAge = getAge.toInt()
           var fee = when (selectedCategory) {
               "Senior Citizen" -> 100
               "Studies Citizen" -> if (intAge <= 21 && studyingAbroad) 100 else 200
               "Disabled Citizen" -> 0
               else -> 200
           }



           result.text = "Passport Fee: RM $fee"
       }else Toast.makeText(this,"PLEASE ENTER YOUR VALID AGE !",Toast.LENGTH_SHORT).show()


   }
    private fun contactJPN() {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jpn.gov.my/my/"))
        startActivity(intent)
    }

}
