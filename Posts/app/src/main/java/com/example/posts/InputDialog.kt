package com.example.posts

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import kotlinx.android.synthetic.main.input.view.*

class InputDialog :
    AppCompatDialogFragment() {
    private lateinit var editTextTitle: EditText
    private lateinit var editTextBody: EditText
    private lateinit var dialogListener: DialogListener
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = activity?.let { AlertDialog.Builder(it) }
        val inflater = activity?.layoutInflater
        val inputView = inflater?.inflate(R.layout.input, null)
        dialogBuilder?.setView(inputView)?.setTitle("Input")

        dialogBuilder?.setPositiveButton("post") { _, _ ->
            val title = editTextTitle.text.toString()
            val body = editTextBody.text.toString()
            dialogListener.applyData(title, body)

        }
        dialogBuilder?.setNegativeButton(
            "cancel"
        ) { _, _ -> }
        editTextTitle = inputView?.input_title as EditText
        editTextBody = inputView.input_body as EditText
        return dialogBuilder!!.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dialogListener = context as DialogListener
    }

    interface DialogListener {
        fun applyData(title: String, body: String)
    }
}