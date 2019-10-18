package ru.ifmo.ibragimov.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {

    private fun evaluate(inputText: String): String {
        val result = Expression(inputText).calculate()
        require(!result.isNaN())
        return result.toString()
    }

    private fun tuneButtons() {
        buttonClear.setOnClickListener {
            result.text = ""
        }
        buttonBackspace.setOnClickListener {
            val text = result.text
            if (text.isNotEmpty()) result.text =
                text.substring(0, text.length - 1)
        }
        buttonEquals.setOnClickListener {
            try {
                result.text = evaluate(result.text.toString())
                errorPanel.text = ""
            } catch (_: IllegalArgumentException) {
                errorPanel.text = getString(R.string.error)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        tuneButtons()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putCharSequence("result", result.text)
        outState.putCharSequence("error", errorPanel.text)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        result.text = savedInstanceState.getCharSequence("result")
        errorPanel.text = savedInstanceState.getCharSequence("error")
    }

    fun append(view: View) {
        result.append((view as Button).text)
    }
}
