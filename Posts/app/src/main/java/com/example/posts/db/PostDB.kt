package com.example.posts.db


import com.example.posts.api.Post
import io.realm.RealmObject

open class PostDB(
    var userId: Int = 0,
    var id: Int = 0,
    var title: String = "",
    var body: String = ""
) : RealmObject() {
    constructor(post: Post) : this() {
        userId = post.userId
        id = post.id
        title = post.title
        body = post.body
    }
}
