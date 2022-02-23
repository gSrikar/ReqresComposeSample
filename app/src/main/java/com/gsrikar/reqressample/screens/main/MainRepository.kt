package com.gsrikar.reqressample.screens.main

import com.gsrikar.reqressample.api.UserInterface
import com.gsrikar.reqressample.database.ReqresDatabase
import com.gsrikar.reqressample.models.ReqresData
import javax.inject.Inject


/**
 * Repository class takes requests from [MainViewModel] and returns the data
 */
class MainRepository @Inject constructor(
    private val database: ReqresDatabase,
    private val userInterface: UserInterface
) {

    /**
     * Fetch list of users
     */
    suspend fun fetchUsers(): List<ReqresData> {
        // Query the user table
        val userList = database.reqresDao().queryAll()
        println("User List Database Size: ${userList.size}")

        return userList.ifEmpty {
            // List is empty

            // TODO: Error Handling
            // Fetch the list of users
            val userResponseList = userInterface.getUserList().data

            // Insert the data in the database
            database.reqresDao().insertAll(userResponseList)

            // Return the response
            userResponseList
        }
    }


}