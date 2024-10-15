package com.example.mycaculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var resultTextView: TextView

    private var op1: Double = 0.0
    private var op2: Double = 0.0
    private var operation: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        // Gán sự kiện cho các nút
        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)

        findViewById<Button>(R.id.btnAdd).setOnClickListener(this)
        findViewById<Button>(R.id.btnSubtract).setOnClickListener(this)
        findViewById<Button>(R.id.btnMultiply).setOnClickListener(this)
        findViewById<Button>(R.id.btnDivide).setOnClickListener(this)
        findViewById<Button>(R.id.btnEqual).setOnClickListener(this)
        findViewById<Button>(R.id.btnClear).setOnClickListener(this)
        findViewById<Button>(R.id.btnBackspace).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn0 -> appendToResult("0")
            R.id.btn1 -> appendToResult("1")
            R.id.btn2 -> appendToResult("2")
            R.id.btn3 -> appendToResult("3")
            R.id.btn4 -> appendToResult("4")
            R.id.btn5 -> appendToResult("5")
            R.id.btn6 -> appendToResult("6")
            R.id.btn7 -> appendToResult("7")
            R.id.btn8 -> appendToResult("8")
            R.id.btn9 -> appendToResult("9")
            R.id.btnAdd -> setOperation("+")
            R.id.btnSubtract -> setOperation("-")
            R.id.btnMultiply -> setOperation("x")
            R.id.btnDivide -> setOperation("/")
            R.id.btnEqual -> calculateResult()
            R.id.btnClear -> clearResult()
            R.id.btnBackspace -> backspace()
        }
    }

    private fun appendToResult(value: String) {
        // Thêm ký tự vào TextView
        val currentText = resultTextView.text.toString()
        if (currentText == "0") {
            resultTextView.text = value
        } else {
            resultTextView.text = currentText + value
        }
    }

    private fun setOperation(op: String) {
        operation = op
        op1 = resultTextView.text.toString().toDoubleOrNull() ?: 0.0
        resultTextView.text = ""
    }

    private fun calculateResult() {
        op2 = resultTextView.text.toString().toDoubleOrNull() ?: 0.0
        val result = when (operation) {
            "+" -> op1 + op2
            "-" -> op1 - op2
            "x" -> op1 * op2
            "/" -> if (op2 != 0.0) op1 / op2 else "Error"
            else -> 0.0
        }
        resultTextView.text = result.toString()
        operation = null
    }

    private fun clearResult() {
        // Xóa kết quả
        resultTextView.text = "0"
        op1 = 0.0
        op2 = 0.0
        operation = null
    }

    private fun backspace() {
        // Xóa ký tự cuối cùng
        val currentText = resultTextView.text.toString()
        if (currentText.isNotEmpty()) {
            resultTextView.text = if (currentText.length == 1) {
                "0"
            } else {
                currentText.substring(0, currentText.length - 1)
            }
        }
    }
}
