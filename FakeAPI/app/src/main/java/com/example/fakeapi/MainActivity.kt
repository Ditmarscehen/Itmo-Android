package com.example.fakeapi

import android.annotation.SuppressLint
import android.icu.text.CaseMap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.input.*
import kotlinx.android.synthetic.main.input.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var posts: ArrayList<Post> = arrayListOf()
    var dialogShown: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            progressBar.visibility = View.GONE
            draw(savedInstanceState.getParcelableArrayList("posts")!!)
            if (savedInstanceState.getBoolean("dialogShown")) {
                drawDialog()
            }
        } else
            getPosts()
        addPost()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("posts", posts)
        outState.putBoolean("dialogShown", dialogShown)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        posts = savedInstanceState.getParcelableArrayList("posts")!!
        dialogShown = savedInstanceState.getBoolean("dialogShown")

    }

    private fun draw(posts: List<Post>) {
        usersRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(posts, object : PostAdapter.OnItemClickListener {
                override fun onAddClick(id: Int?) {
                    deletePost(id)
                }
            })

        }
    }

    private fun getPosts() {
        val call: Call<List<Post>> = ApiApp.instance.jsonPlaceHolderApi.fetchAllPosts()

        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                posts = ArrayList(response.body()!!)
                draw(response.body()!!)
                progressBar.visibility = View.GONE
                Toast.makeText(
                    this@MainActivity,
                    "loading post code: ${response.code()}",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun postPost(title: String, body: String) {
        val post = Post(23, 256, title, body)
        val call = ApiApp.instance.jsonPlaceHolderApi.createPost(post)
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                Toast.makeText(
                    this@MainActivity,
                    "posting post code: ${response.code()}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun deletePost(id: Int?) {
        val call = ApiApp.instance.jsonPlaceHolderApi.deletePost(id)
        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                Toast.makeText(
                    this@MainActivity,
                    "deleting post $id with code: ${response.code()}",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }


    private fun addPost() {
        addButton.setOnClickListener {
            drawDialog()
        }
    }

    private fun drawDialog() {
        val dialogBuilder = AlertDialog.Builder(this).create()
        val inflater = this.layoutInflater
        val inputView = inflater.inflate(R.layout.input, null)
        val create = inputView.create as Button
        val cancel = inputView.cancel as Button
        val titleText = inputView.input_title as EditText
        val bodyText = inputView.input_body as EditText
        create.setOnClickListener {
            val title = titleText.text.toString()
            val body = bodyText.text.toString()
            dialogBuilder.dismiss()
            dialogShown = false
            postPost(title, body)
        }
        cancel.setOnClickListener {
            dialogBuilder.dismiss()
            dialogShown = false
        }
        dialogBuilder.setView(inputView)
        dialogBuilder.show()
        dialogShown = true
    }

}
