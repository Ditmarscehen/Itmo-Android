package com.example.posts.db

import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import java.lang.Exception

class PostsRealm : PostsRealmInterface {
    override fun addPostDB(realm: Realm, postDB: PostDB) {
        realm.executeTransaction {
            realm.insert(postDB)
        }
    }

    override fun addPostsDB(realm: Realm, postsDB: List<PostDB>) {
        realm.executeTransaction {
            realm.insert(postsDB)
        }
    }

    override fun deletePostDB(realm: Realm, postDB: RealmResults<PostDB>) {
        realm.executeTransaction {
            postDB.deleteAllFromRealm()
        }
    }

    override fun getPostDB(realm: Realm, postId: Int): RealmResults<PostDB>? {
        return realm.where<PostDB>().equalTo("id", postId).findAll()
    }

    override fun clearRealm(realm: Realm) {
            realm.executeTransaction {
                realm.deleteAll()
            }
    }

    fun getAll(realm: Realm): RealmResults<PostDB> {
        return realm.where<PostDB>().findAll()
    }
}