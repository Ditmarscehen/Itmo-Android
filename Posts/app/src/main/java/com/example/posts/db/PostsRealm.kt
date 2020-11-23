package com.example.posts.db

import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import java.lang.Exception

class PostsRealm : PostsRealmInterface {
    override fun addPostDB(realm: Realm, postDB: PostDB): Boolean {
        return try {
            realm.executeTransaction { _ ->
                realm.insert(postDB)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun addPostsDB(realm: Realm, postsDB: List<PostDB>): Boolean {
        return try {
            realm.executeTransaction { _ ->
                realm.insert(postsDB)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun deletePostDB(realm: Realm, postDB: RealmResults<PostDB>) {
        try {
            realm.executeTransaction { _ ->
                postDB.deleteAllFromRealm()
            }
        } catch (e: Exception) {

        }
    }

    override fun getPostDB(realm: Realm, postId: Int): RealmResults<PostDB>? {
        return try {
            realm.where<PostDB>().equalTo("id", postId).findAll()
        } catch (e:Exception){
            null
        }
    }

    override fun clearRealm(realm: Realm): Boolean {
        return try {
            realm.executeTransaction { _ ->
                realm.deleteAll()
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAll(realm: Realm): RealmResults<PostDB> {
        return realm.where<PostDB>().findAll()
    }
}