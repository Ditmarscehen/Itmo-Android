package com.example.posts.api


import android.os.Parcelable
import com.example.posts.db.PostDB
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable
@Parcelize
@Serializable
data class Post(
    var userId: Int=0,
    var id: Int=0 ,
    var title: String="",
    var body: String=""
) : Parcelable{
    constructor(postDB: PostDB):this(){
        userId= postDB.userId
        id= postDB.id
        title= postDB.title
        body= postDB.body
    }
}




