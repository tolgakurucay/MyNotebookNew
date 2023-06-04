package com.tolgakurucay.mynotebooknew.services

data class TestResponse(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<TestResults>,
    val totalCount: Int,
    val totalPages: Int
)

data class TestResults(
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<String>
)