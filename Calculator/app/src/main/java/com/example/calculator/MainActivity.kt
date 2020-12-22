package com.example.calculator

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.expression.generic.GenericTabulator.*
import com.example.calculator.expression.generic.operations.IntOperation
import com.example.calculator.expression.parser.ExpressionParser
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

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
        val result: Int
        try {
            result = if (input.text.last().isDigit()) {
                evaluateInt(input.text.toString())
            } else {
              evaluateInt(input.text.toString().substring(0, input.text.length - 1))
            }
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
                if (input.text[i] == '.'||input.text[i]=='E') {
                    t = false
                }
                if (t)
                    input.append(button.text.toString())
            }
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
            }else{
                operatorsCounter = 0
                correct=true
            }
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
        }
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