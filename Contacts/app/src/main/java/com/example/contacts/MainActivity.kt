package com.example.contacts

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var myRequestId: Int = 1
    private var contactsList: List<Contact> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPermission()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            myRequestId -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    drawRecycleView()
                    val count = contactsList.size
                    val contactsAdded =
                        resources.getQuantityString(R.plurals.numberOfAddedContacts, count, count)
                    Toast.makeText(
                        this@MainActivity,
                        contactsAdded,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.permisson_not_gained),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    private fun getPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                myRequestId
            )
        } else {
            drawRecycleView()
        }
    }

    private fun drawRecycleView() {
        contactsList = fetchAllContacts()
        val viewManager = LinearLayoutManager(this)
        contactsRecyclerView.apply {
            layoutManager = viewManager
            setHasFixedSize(true)
            adapter = ContactAdapter(contactsList) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${it.phoneNumber}")
                startActivity(intent)
            }
        }
    }

}