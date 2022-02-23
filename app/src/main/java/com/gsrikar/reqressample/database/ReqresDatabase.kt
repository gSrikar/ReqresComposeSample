package com.gsrikar.reqressample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gsrikar.reqressample.database.dao.ReqresDao
import com.gsrikar.reqressample.models.ReqresData


@Database(entities = [ReqresData::class], version = 1, exportSchema = true)
abstract class ReqresDatabase : RoomDatabase() {

    abstract fun reqresDao(): ReqresDao

    companion object {
        // Name of the database
        private const val APP_DATABASE_NAME = "reqres-app.db"


        /**
         * return database instance
         */
        // TODO: Improve synchronized logic
        @Synchronized
        fun getdatabase(appContext: Context): ReqresDatabase {
            return Room.databaseBuilder(
                appContext,
                ReqresDatabase::class.java, APP_DATABASE_NAME
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
}