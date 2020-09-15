package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        val numbers: List<Button> = listOf(
            button0,
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9
        )
        val operators: List<Button> = listOf(
            buttonAdd,
            buttonSubtract,
            buttonMultiply,
            buttonDivide
        )

        for (i in numbers) {
            clickNumber(i)
        }

        for (i in operators) {
            clickOperator(i)
        }

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
            output.text = result.toString()
            correct = true
        } catch (e: Exception) {
            output.text = e.message
            correct = false
            Log.d("Exception: ", e.message.toString())
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
                if (operatorsCounter > 0 && !operatorsChar.contains(input.text.last())) {
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
            output.textSize = 60F
        else
            output.textSize = 80F

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString("input", input.text.toString())
            putString("output", output.text.toString())
            putBoolean("correct", correct)
            putInt("operatorsCounter",operatorsCounter)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        input.text=savedInstanceState.getString("input")
        output.text=savedInstanceState.getString("output")
        correct = savedInstanceState.getBoolean("correct")
        operatorsCounter = savedInstanceState.getInt("operatorsCounter")
    }
}