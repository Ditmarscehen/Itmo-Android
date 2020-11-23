package com.example.posts.db

import android.os.Parcelable
import com.example.posts.api.Post
import io.realm.RealmObject
import kotlinx.android.parcel.Parcelize

@Parcelize
open class PostDB(
    var userId: Int = 0,
    var id: Int = 0,
    var title: String = "",
    var body: String = ""
) : RealmObject(), Parcelable {
    constructor(post: Post) : this() {
        userId = post.userId
        id = post.id
        title = post.title
        body = post.body
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other.javaClass != javaClass) return true
        other as PostDB
        return (other.id == id && other.userId == userId && other.body == body && other.title == title)
    }

    override fun toString(): String {
        return "id $id title $title"
    }
    override fun hashCode(): Int {
        var result = userId
        result = 31 * result + id
        result = 31 * result + title.hashCode()
        result = 31 * result + body.hashCode()
        return result
    }
}
