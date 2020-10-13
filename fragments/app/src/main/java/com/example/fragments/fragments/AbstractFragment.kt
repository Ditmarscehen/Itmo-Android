package com.example.fragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fragments.MainActivity.*
import kotlinx.android.synthetic.main.dashboard_fragment.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.notifications_fragment.*

import java.lang.StringBuilder

abstract class AbstractFragment(val fragment: Int, val tag: Tags) : Fragment() {
    var counter = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val button: Button = when (tag) {
            Tags.DASHBOARD -> btn_dashboard
            Tags.HOME -> btn_home
            Tags.NOTIFICATIONS -> btn_notifications
        }
        val textView: TextView = when (tag) {
            Tags.DASHBOARD -> text_dashboard
            Tags.HOME -> text_home
            Tags.NOTIFICATIONS -> text_notifications
        }
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.let {
            counter = it.getInt("counter")
            textView.text = it.getCharSequence("text")
        }
        button.setOnClickListener {
            counter++
            val strb = StringBuilder()
            strb.append(textView.text)
            strb.append("->")
            strb.append(counter.toString())
            textView.text = strb.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val textView: TextView = when (tag) {
            Tags.DASHBOARD -> text_dashboard
            Tags.HOME -> text_home
            Tags.NOTIFICATIONS -> text_notifications
        }
        outState.putInt("counter", counter)
        outState.putCharSequence("text", textView.text)
    }

}