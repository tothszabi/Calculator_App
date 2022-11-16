package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var result: EditText
    private lateinit var newNum: EditText
    private val diasplayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.tvOperation) }

    // Variables to hold the operands and type of calculation
    private var operand1: Double? = null
    private var operand2: Double = 0.0
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.edResult)
        newNum = findViewById(R.id.edNewNum)

        // Data input buttons
        val button0: Button = findViewById(R.id.bt0)
        val button1: Button = findViewById(R.id.bt1)
        val button2: Button = findViewById(R.id.bt2)
        val button3: Button = findViewById(R.id.bt3)
        val button4: Button = findViewById(R.id.bt4)
        val button5: Button = findViewById(R.id.bt5)
        val button6: Button = findViewById(R.id.bt6)
        val button7: Button = findViewById(R.id.bt7)
        val button8: Button = findViewById(R.id.bt8)
        val button9: Button = findViewById(R.id.bt9)
        val buttonDot: Button = findViewById(R.id.btdot)

        // Operation buttons
        val buttonEquals = findViewById<Button>(R.id.btEqual)
        val buttonDivide = findViewById<Button>(R.id.btDiviider)
        val buttonMultiply = findViewById<Button>(R.id.btMultipluer)
        val buttonMinus = findViewById<Button>(R.id.btMinus)
        val buttonPlus = findViewById<Button>(R.id.btPlus)

        val listner = View.OnClickListener { v->
            val b = v as Button
            newNum.append(b.text)
        }
        button0.setOnClickListener(listner)
        button1.setOnClickListener(listner)
        button2.setOnClickListener(listner)
        button3.setOnClickListener(listner)
        button4.setOnClickListener(listner)
        button5.setOnClickListener(listner)
        button6.setOnClickListener(listner)
        button7.setOnClickListener(listner)
        button8.setOnClickListener(listner)
        button9.setOnClickListener(listner)
        buttonDot.setOnClickListener(listner)

        val opListner = View.OnClickListener { v->
            val operation = (v as Button).text.toString()
            val value = newNum.text.toString()
            if(value.isNotEmpty()){
                performOperation(value , operation)
            }
            pendingOperation = operation
            diasplayOperation.text = pendingOperation
        }
        buttonEquals.setOnClickListener(opListner)
        buttonPlus.setOnClickListener(opListner)
        buttonMinus.setOnClickListener(opListner)
        buttonMultiply.setOnClickListener(opListner)
        buttonDivide.setOnClickListener(opListner)
    }

    private fun performOperation(value: String , operation: String){
        if(operand1 == null){
            operand1 = value.toDouble()
        } else {
            operand2 = value.toDouble()
            if(pendingOperation == "="){
                pendingOperation = operation
            }
            when(pendingOperation){
                "="-> operand1 = operand2
                "/"-> if(operand2 == 0.0){
                    operand1 = Double.NaN  // handle to divide by 0
                } else {
                    operand1 = operand1!! /operand2
                }
                "*" -> operand1 = operand1!! * operand2
                "-" -> operand1 = operand1!! - operand2
                "+" -> operand1 = operand1!! + operand2
            }
        }
        result.setText(operand1.toString())
        newNum.setText("")
    }
}