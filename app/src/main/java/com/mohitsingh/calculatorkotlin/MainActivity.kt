package com.mohitsingh.calculatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isLastDigitNumber = false
    var isLastDigitDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Calculator"

    }

    fun onDigitClicked(view: View) {
        outputTV.append((view as Button).text)
        isLastDigitNumber = true
    }

    fun onClearClicked(view: View) {
        outputTV.text = ""
        isLastDigitNumber = false
        isLastDigitDot = false
    }

    fun onDotClicked(view: View) {
        if (isLastDigitNumber && !isLastDigitDot) {
            outputTV.append(".")
            isLastDigitNumber = false
            isLastDigitDot = true
        }
    }

    fun onOperatorClicked(view: View) {
        if (isLastDigitNumber && !isOperatorAdded(outputTV.text.toString())) {
            outputTV.append((view as Button).text)
            isLastDigitNumber = false
            isLastDigitDot = false
        }
    }

    private fun isOperatorAdded(text: String): Boolean {
        return if (text.startsWith("-"))
            false
        else text.contains("+") || text.contains("-") || text.contains("*") || text.contains("/")
    }

    fun onEqualClicked(view: View) {
        var text = outputTV.text.toString()
        var prefix = ""
        if (text[0] == '-') {
            prefix = "-"
            text = text.substring(1)
        }
        if (text.contains("-")) {
            val split = text.split("-")
            var one = split[0]
            var two = split[1]

            if (prefix.isNotEmpty()) {
                one = prefix + one
            }

            outputTV.text = (one.toDouble() - two.toDouble()).toString()
        } else if (text.contains("+")) {
            val split = text.split("+")
            var one = split[0]
            var two = split[1]

            if (prefix.isNotEmpty()) {
                one = prefix + one
            }

            outputTV.text = (one.toDouble() + two.toDouble()).toString()
        } else if (text.contains("*")) {
            val split = text.split("*")
            var one = split[0]
            var two = split[1]

            if (prefix.isNotEmpty()) {
                one = prefix + one
            }

            outputTV.text = (one.toDouble() * two.toDouble()).toString()
        } else if (text.contains("/")) {
            val split = text.split("/")
            var one = split[0]
            var two = split[1]

            if (prefix.isNotEmpty()) {
                one = prefix + one
            }

            outputTV.text = (one.toDouble() / two.toDouble()).toString()
        }
    }

}
