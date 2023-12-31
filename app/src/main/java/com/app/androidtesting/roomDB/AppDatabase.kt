package com.app.androidtesting.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.app.androidtesting.roomDB.userlistVM.UserProfileDetailsData

// Define the entities (tables) and their version
@Database(entities = [UserProfileDetailsData::class],  version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // Define an abstract method for each DAO (Data Access Object) in the database
    abstract fun userDao(): UserListDao



    // Create a companion object for database creation
    companion object {
        //DataBase Name
        private const val DATABASE_NAME = "app_database"

        // Singleton instance of the database
        @Volatile
        private var instance: AppDatabase? = null

        //Get the database instance
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        //Create DataBase instance
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME,
            )
                //.addMigrations(MIGRATION_1_2) //Thi code is for migration
                .fallbackToDestructiveMigration() // Use this only during development
                .build()
        }


    }


}
