package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val number_txt = findViewById<TextView>(R.id.number_txt)
        val btn_Start = findViewById<Button>(R.id.btn_mainActivity_start)
        val btn_Stop = findViewById<Button>(R.id.btn_mainActivity_stop)
        val number_input = findViewById<EditText>(R.id.number_input)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


        viewModel.seconds().observe(this, Observer {
            number_txt.text = it.toString()
        })

        viewModel.finished.observe(this, Observer {
            if (it){
                Toast.makeText(this,"the end",Toast.LENGTH_LONG).show()
            }
        })


        btn_Start.setOnClickListener {
            if (number_input.text.isEmpty() || number_input.text.length < 4){
                Toast.makeText(this,"Invalid number",Toast.LENGTH_LONG).show()
            }else{
                viewModel.timerValue.value = number_input.text.toString().toLong()
                viewModel.startTimer()
            }
        }

        btn_Stop.setOnClickListener {
            number_txt.text = "0"
            viewModel.stopTimer()
        }
    }
}