package com.example.posts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posts.api.Post
import com.example.posts.db.PostDB
import com.example.posts.db.PostsRealm
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import retrofit2.*
import kotlin.random.Random


class MainActivity : InputDialog.DialogListener, AppCompatActivity() {
    var posts: ArrayList<Post> = arrayListOf()
    private val realm: Realm = Realm.getDefaultInstance()
    private lateinit var postAdapter: PostAdapter
    private val postsRealm = PostsRealm(realm)
    private val idSet = HashSet<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFromDB()
        addPost()
        reset()
    }


    private fun getPostsApi() {
        progressBar.visibility = View.VISIBLE
        lifecycle.coroutineScope.launch {
            try {
                val response = ApiApp.instance.jsonPlaceHolderApi.fetchAllPosts()
                if (response.isSuccessful) {
                    posts = ArrayList(response.body()!!)
                    draw(posts)
                    progressBar.visibility = View.GONE
                    makeToast("loading post code: ${response.code()}")
                    postsRealm.updateDB(posts.map { PostDB(it) })
                } else {
                    progressBar.visibility = View.GONE
                    makeToast("error")
                }
            } catch (t: Throwable) {
                progressBar.visibility = View.GONE
                makeToast(t.message.toString())
            }
        }
    }

    private fun postPostApi(title: String, body: String) {
        lifecycle.coroutineScope.launch {
            try {
                val post = Post(23, 256, title, body)
                val response = ApiApp.instance.jsonPlaceHolderApi.createPost(post)
                if (response.isSuccessful)
                    makeToast("posting post code: ${response.code()}")
                else
                    makeToast("error")
            } catch (t: Throwable) {
                makeToast(t.message.toString())
            }
        }
    }

    fun deletePostApi(id: Int?) {
        lifecycle.coroutineScope.launch {
            try {
                val response = ApiApp.instance.jsonPlaceHolderApi.deletePost(id)
                if (response.isSuccessful)
                    makeToast("deleting post code: ${response.code()}")
                else
                    makeToast("error")
            } catch (t: Throwable) {
                makeToast(t.message.toString())
            }
        }
    }



    private fun postPostDB(title: String, body: String) {
        val id = findId()
        val postDB = PostDB(1, id, title, body)
        postsRealm.addPostDB(postDB)
        posts.add(Post(postDB))
        postAdapter.notifyItemInserted(posts.size - 1)
    }

    private fun deletePostBD(id: Int) {
        val postDB = postsRealm.getPostDB(id)
        postDB?.let {
            if (postDB.isNotEmpty()) {
                val post = postDB.first()
                val pos = posts.indexOf(Post(post!!))
                idSet.remove(post.id)
                postsRealm.deletePostDB(postDB)
                posts.removeAt(pos)
                postAdapter.notifyItemRemoved(pos)
            }
        }
    }

    private fun loadFromDB() {
        progressBar.visibility = View.VISIBLE
        val a = postsRealm.getAll()
        posts.clear()
        a.forEach {
            posts.add(Post(it))
            idSet.add(it.id)
        }
        draw(posts)
        progressBar.visibility = View.GONE
    }

    override fun applyData(title: String, body: String) {
        postPostApi(title, body)
        postPostDB(title, body)
    }

    private fun findId(): Int {
        var id = Random.nextInt(0, Int.MAX_VALUE)
        while (idSet.contains(id))
            id = Random.nextInt(0, Int.MAX_VALUE)
        idSet.add(id)
        return id
    }

    private fun drawDialog() {
        val dialog = InputDialog()
        dialog.show(supportFragmentManager, "input dialog")
    }

    private fun addPost() {
        addButton.setOnClickListener {
            drawDialog()
        }
    }

    private fun reset() {
        resetBtn.setOnClickListener {
            getPostsApi()
        }
    }

    private fun draw(posts: ArrayList<Post>) {
        postAdapter = PostAdapter(posts, object : PostAdapter.OnItemClickListener {
            override fun onDeleteClick(id: Int) {
                deletePostApi(id)
                deletePostBD(id)
            }
        })
        postsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }

    private fun makeToast(string: String) {
        Toast.makeText(
            this@MainActivity,
            string,
            Toast.LENGTH_SHORT
        ).show()
    }

}
