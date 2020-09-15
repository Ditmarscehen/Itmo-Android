package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        click(button0)
        click(button1)
        click(button2)
        click(button3)
        click(button4)
        click(button5)
        click(button6)
        click(button7)
        click(button8)
        click(button9)
        click(buttonAdd)
        click(buttonSubtract)
        click(buttonDivide)
        click(buttonMultiply)
        click(buttonDot)
        buttonDelete.setOnClickListener {
            if (input.text.isNotEmpty()) {
                input.text = input.text.substring(0, input.text.length - 1)
            }
        }
        buttonEvaluate.setOnClickListener {
            try {
                val result: Double = ExpressionBuilder(input.text.toString()).build().evaluate()
                output.text = result.toString()
            } catch (e: Exception) {
                Log.d("Exception: ", e.message.toString())
            }
        }
        input.movementMethod = ScrollingMovementMethod()
        input.setHorizontallyScrolling(true)
    }

    fun click(button: Button) {
        button.setOnClickListener { inputAdd(button.text.toString()) }
    }

    fun inputAdd(str: String) {
        input.append(str)
    }


}