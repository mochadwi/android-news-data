package io.mochadwi.mobilenews.util

import io.realm.DynamicRealm
import io.realm.RealmMigration

/**
 * Created by mochadwi on 3/15/18.
 */
class RealmMigrations : RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema

        if (oldVersion == 2L) {
            TODO("Do the things you want to imigrate here")
        }
    }
}
