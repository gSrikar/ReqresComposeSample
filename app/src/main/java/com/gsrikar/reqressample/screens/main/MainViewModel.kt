package com.gsrikar.reqressample.screens.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.gsrikar.reqressample.models.ReqresData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * View model takes commands from [MainActivity] and returns the data
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    /**
     * Contains the data loading state
     */
    private val loading = mutableStateOf(false)

    /**
     * Set the state to loading
     */
    private fun startLoading() {
        loading.value = true
    }

    /**
     * Set the state to not loading
     */
    private fun stopLoading() {
        loading.value = false
    }

    /**
     * Return the loading status
     */
    fun getLoading(): Boolean {
        return loading.value
    }

    /**
     * Fetch list of users
     */
    suspend fun fetchUsers(): List<ReqresData> {
        // Show loading progress
        startLoading()

        // Fetch the list of user
        val users = repository.fetchUsers()

        // Stop loading progress
        stopLoading()

        // Return the list of users
        return users
    }

}