package com.example.posts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posts.api.Post
import com.example.posts.db.PostDB
import com.example.posts.db.PostsRealm
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random


class MainActivity : InputDialog.DialogListener, AppCompatActivity() {
    var posts: ArrayList<Post> = arrayListOf()
    val realm: Realm = Realm.getDefaultInstance()
    private lateinit var postAdapter: PostAdapter
    private val postsRealm = PostsRealm()
    private val idSet = HashSet<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFromDB(realm)
        addPost()
        reset()

    }


    private fun getPostsApi() {
        val call: Call<List<Post>> = ApiApp.instance.jsonPlaceHolderApi.fetchAllPosts()
        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                posts = ArrayList(response.body()!!)
                draw(posts)
                progressBar.visibility = View.GONE
                makeToast("loading post code: ${response.code()}")
                updateDB(realm, posts.map { PostDB(it) })
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                makeToast(t.message.toString())
            }
        })

    }

    private fun postPostApi(title: String, body: String) {
        val post = Post(23, 256, title, body)
        val call = ApiApp.instance.jsonPlaceHolderApi.createPost(post)
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                makeToast("posting post code: ${response.code()}")
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                makeToast(t.message.toString())
            }
        })
    }

    fun deletePostApi(id: Int?) {
        val call = ApiApp.instance.jsonPlaceHolderApi.deletePost(id)
        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                makeToast("deleting post code: ${response.code()}")
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                makeToast(t.message.toString())
            }
        })
    }


    private fun updateDB(realm: Realm, posts: List<PostDB>) {
        postsRealm.clearRealm(realm)
        postsRealm.addPostsDB(realm, posts)
    }

    private fun postPostDB(realm: Realm, title: String, body: String) {
        val id = findId()
        val postDB = PostDB(1, id, title, body)
        postsRealm.addPostDB(realm, postDB)
        posts.add(Post(postDB))
        postAdapter.notifyItemInserted(posts.size - 1)
    }

    private fun deletePostBD(realm: Realm, id: Int) {
        val postDB = postsRealm.getPostDB(realm, id)
        postDB?.let {
            if (postDB.isNotEmpty()) {
                val post = postDB.first()
                val pos = posts.indexOf(Post(post!!))
                idSet.remove(post.id)
                postsRealm.deletePostDB(realm, postDB)
                posts.removeAt(pos)
                postAdapter.notifyItemRemoved(pos)
            }
        }
    }

    private fun loadFromDB(realm: Realm) {
        val a = postsRealm.getAll(realm)
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
        postPostDB(realm, title, body)
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
                deletePostBD(realm, id)
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
