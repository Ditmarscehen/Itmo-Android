package com.example.posts.db

import io.realm.Realm
import io.realm.RealmResults

interface PostsRealmInterface {
    fun addPostDB(realm: Realm,postDB: PostDB)
    fun addPostsDB(realm: Realm,postsDB: List<PostDB>)
    fun deletePostDB(realm: Realm,postDB: RealmResults<PostDB>)
    fun getPostDB(realm: Realm,postId: Int):RealmResults<PostDB>?
    fun clearRealm(realm: Realm)
}