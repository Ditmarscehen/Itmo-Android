package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

var operatorsCounter: Int = 0
val operatorsChar: Set<Char> = setOf('+', '-', '/', '*')
var correct: Boolean = true

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickNumber(button0)
        clickNumber(button1)
        clickNumber(button2)
        clickNumber(button3)
        clickNumber(button4)
        clickNumber(button5)
        clickNumber(button6)
        clickNumber(button7)
        clickNumber(button8)
        clickNumber(button9)

        clickOperator(buttonAdd)
        clickOperator(buttonSubtract)
        clickOperator(buttonMultiply)
        clickOperator(buttonDivide)

        clickDot(buttonDot)

        clickDelete(buttonDelete)

        clickEvaluate(buttonEvaluate)
    }

    private fun evaluate() {
        val result: Double
        try {
            result = if (input.text.last().isDigit()) {
                ExpressionBuilder(input.text.toString()).build().evaluate()
            } else {
                ExpressionBuilder(
                    input.text.toString().substring(0, input.text.length - 1)
                ).build().evaluate()
            }
            if (result.toLong().toDouble().equals(result))
                output.text = result.toLong().toString()
            else
                output.text = result.toString()
            correct = true
        } catch (e: Exception) {
            output.text = e.message
            correct = false

        }
    }


    private fun clickOperator(button: Button) {
        button.setOnClickListener {
            operatorsCounter++

            if (input.text.isNotEmpty() && input.text.last()
                    .isDigit() || input.text.isEmpty() && button.text == "-"
            )
                input.append(button.text.toString())
            ensureLength()
        }

    }

    private fun clickNumber(button: Button) {
        button.setOnClickListener {
            input.append(button.text.toString())
            if (operatorsCounter > 0) {
                evaluate()
            } else {
                output.text = ""
            }
            ensureLength()
        }

    }

    private fun clickDot(button: Button) {
        button.setOnClickListener {
            var i: Int = input.text.length - 1
            if (input.text.isNotEmpty() && !operatorsChar.contains(input.text[i])) {
                var t = true
                while (i > 1 && input.text[i].isDigit()) {
                    i--
                }
                if (input.text[i] == '.') {
                    t = false
                }
                if (t)
                    input.append(button.text.toString())
            }
            ensureLength()
        }
    }

    private fun clickDelete(button: Button) {
        button.setOnClickListener {
            if (input.text.isNotEmpty()) {
                if (operatorsChar.contains(input.text.last()))
                    operatorsCounter--
                input.text = input.text.substring(0, input.text.length - 1)

                if (input.text.isNotEmpty() && operatorsCounter > 0 && !operatorsChar.contains(input.text.last())) {
                    evaluate()
                } else {
                    output.text = ""
                }
            }
            ensureLength()
        }
    }

    private fun clickEvaluate(button: Button) {
        button.setOnClickListener {
            evaluate()
            if (correct) {
                input.text = output.text
                output.text = ""
                operatorsCounter = 0
            }
            ensureLength()
        }
    }

    private fun ensureLength() {
        if (input.text.length > 9)
            input.textSize = 60F
        else
            input.textSize = 80F
        if (output.text.length > 9)
            output.textSize = 40F
        else
            output.textSize = 60F

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString("input", input.text.toString())
            putString("output", output.text.toString())
            putBoolean("correct", correct)
            putInt("operatorsCounter", operatorsCounter)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        input.text = savedInstanceState.getString("input")
        output.text = savedInstanceState.getString("output")
        correct = savedInstanceState.getBoolean("correct")
        operatorsCounter = savedInstanceState.getInt("operatorsCounter")
    }
}