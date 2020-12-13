package com.example.posts.db

import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import java.lang.Exception

class PostsRealm(private val realm: Realm) {

    fun addPostDB( postDB: PostDB) {
        realm.executeTransaction {
            realm.insert(postDB)
        }
    }

    private fun addPostsDB(postsDB: List<PostDB>) {
        realm.executeTransaction {
            realm.insert(postsDB)
        }
    }

    fun deletePostDB( postDB: RealmResults<PostDB>) {
        realm.executeTransaction {
            postDB.deleteAllFromRealm()
        }
    }

    fun getPostDB( postId: Int): RealmResults<PostDB>? {
        return realm.where<PostDB>().equalTo("id", postId).findAll()
    }

    private fun clearRealm() {
        realm.executeTransaction {
            realm.deleteAll()
        }
    }
    fun updateDB(posts: List<PostDB>) {
        clearRealm()
        addPostsDB(posts)
    }
    fun getAll(): RealmResults<PostDB> {
        return realm.where<PostDB>().findAll()
    }
}