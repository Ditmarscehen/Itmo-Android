package com.example.imagenetwork

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.telecom.Call
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.ImageAdapter
import kotlinx.android.synthetic.main.activity_main.*
import net.kodehawa.lib.imageboards.DefaultImageBoards
import net.kodehawa.lib.imageboards.ImageBoard
import net.kodehawa.lib.imageboards.entities.BoardImage
import net.kodehawa.lib.imageboards.entities.exceptions.QueryFailedException
import java.io.IOException
import java.util.function.Consumer
import javax.security.auth.callback.Callback
import kotlin.Int as Int1


class MainActivity : AppCompatActivity() {
    private var imageList: List<BoardImage> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(
            this@MainActivity,
            "sasai kudasai",
            Toast.LENGTH_SHORT
        ).show()
        val i= ImageLoader(this)
        i.execute()
        drawRecycleView()
    }


    class ImageLoader(var activity: MainActivity?) :
        AsyncTask<String, Int1, List<BoardImage>>() {
        private var result: List<BoardImage>? = null
        override fun doInBackground(vararg p0: String?): List<BoardImage> {
            return DefaultImageBoards.KONACHAN.get(10).blocking()
        }

    }

    private fun drawRecycleView() {
        val viewManager = LinearLayoutManager(this)
        recycler_view.apply {
            layoutManager = viewManager
            setHasFixedSize(true)
            adapter = ImageAdapter(imageList) {
                val intent = Intent(Intent.ACTION_DIAL)
                startActivity(intent)
            }
        }
    }
}
