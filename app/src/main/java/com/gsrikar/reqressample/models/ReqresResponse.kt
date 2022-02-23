package com.gsrikar.reqressample.models

// TODO: Issue at the time of releasing the application
/**
 * API Response from Reqres
 */
data class ReqresResponse(

    /**
     * Current page number
     */
    val page: Int,

    /**
     * Number of items from page number
     */
    val per_page: Int,

    /**
     * Total number of items
     */
    val total: Int,

    /**
     * Total pages
     */
    val total_pages: Int,

    /**
     * List of users
     */
    val data: List<ReqresData>
)

