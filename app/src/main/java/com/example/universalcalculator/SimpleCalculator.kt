package com.example.universalcalculator

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.universalcalculator.databinding.ActivitySimpleCalculatorBinding
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.sqrt

class SimpleCalculator : AppCompatActivity() {

    lateinit var binding: ActivitySimpleCalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        onClick()
    }

    private fun onClick() {
        // Set click listeners for the Buttons
        binding.btnC.setOnClickListener { clear() }
        binding.btnAC.setOnClickListener { allClear() }
        binding.btnDelete.setOnClickListener { backspace() }
        binding.btnPlusByMinus.setOnClickListener { negate() }
        binding.btnPercent.setOnClickListener { percent() }
        binding.btnDivide.setOnClickListener { append("/") }
        binding.btnMultiply.setOnClickListener { append("*") }
        binding.btnMinus.setOnClickListener { append("-") }
        binding.btnPlus.setOnClickListener { append("+") }
        binding.btnEqualTo.setOnClickListener { equalTo() }
        binding.btnPoint.setOnClickListener { append(".") }
        binding.btnZero.setOnClickListener { append("0") }
        binding.btnOne.setOnClickListener { append("1") }
        binding.btnTwo.setOnClickListener { append("2") }
        binding.btnThree.setOnClickListener { append("3") }
        binding.btnFour.setOnClickListener { append("4") }
        binding.btnFive.setOnClickListener { append("5") }
        binding.btnSix.setOnClickListener { append("6") }
        binding.btnSeven.setOnClickListener { append("7") }
        binding.btnEight.setOnClickListener { append("8") }
        binding.btnNine.setOnClickListener { append("9") }
        binding.btnOneByx.setOnClickListener { oneByX() }
        binding.btnXSq.setOnClickListener { xSq() }
        binding.btnTwoRootx.setOnClickListener { twoRootX() }
    }

    private fun twoRootX() {
        val expression = binding.tvInput.text.toString()
        if (expression.isNotEmpty()){
            val value = sqrt(expression.toDouble())
            val finalValue = formatNumber(value)
            binding.tvInput.text = "√($expression)"
            binding.tvOutput.text = finalValue
        } else {
            binding.tvInput.text = "√(0)"
        }


    }

    private fun oneByX() {
        val expression = binding.tvInput.text.toString()
        if (expression.isNotEmpty()){
            val value = 1 / expression.toDouble()
            binding.tvInput.text = "1/($expression)"
            binding.tvOutput.text = value.toString()
        } else {
            binding.tvInput.text = "Press any Digit First"
        }
    }

    private fun xSq() {
        val expression = binding.tvInput.text.toString()
        if (expression.isNotEmpty()){
            val value = expression.toDouble() * expression.toDouble()
            binding.tvInput.text = "$expression²"
            binding.tvOutput.text = value.toString()
        } else {
            binding.tvInput.text = "0²"
            binding.tvOutput.text = "0"
        }
    }

    private fun formatNumber(number: Double): String {
        val formatted = number.toString()
        return if (formatted.endsWith(".0")) {
            formatted.replace(".0", "")
        } else {
            formatted
        }
    }

    private fun equalTo() {
        binding.tvInput.text = binding.tvOutput.text.toString()
        binding.tvOutput.text = ""
    }

    private fun clear() {
        binding.tvInput.text = ""
        binding.tvOutput.text = ""
    }

    private fun allClear() {
        binding.tvInput.text = ""
        binding.tvOutput.text = ""
    }

    private fun backspace() {
        val expression = binding.tvInput.text.toString()
        if (expression.isNotEmpty()) {
            binding.tvInput.text = expression.substring(0, expression.length - 1)
            calculate()
        }
    }

    private fun negate() {
        val expression = binding.tvInput.text.toString()
        if (expression.isNotEmpty()) {
            if (expression.startsWith("-")) {
                binding.tvInput.text = expression.substring(1)
            } else {
                binding.tvInput.text = "-$expression"
            }
        }
    }

    private fun percent() {
        val expression = binding.tvInput.text.toString()
        if (expression.isNotEmpty()) {
            val value = expression.toDouble() / 100.0
            binding.tvInput.text = value.toString()
        }
    }

    private fun append(value: String) {
        binding.tvInput.append(value)
        calculate()
    }

    private fun calculate() {
        val expression = binding.tvInput.text.toString()
        if (expression.isNotEmpty()) {
            try {
                val result = ExpressionBuilder(expression).build().evaluate()
                val finalResult = formatNumber(result)
                binding.tvOutput.text = finalResult
            } catch (e: Exception) {
                //Toast.makeText(this, "Invalid expression", Toast.LENGTH_SHORT).show()
            }
        } else {
            binding.tvOutput.text = ""
        }
    }

}


