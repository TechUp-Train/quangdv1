//package com.example.kmptraining.kmp_session4.data.mapper
//
//import com.example.kmptraining.kmp_session4.data.remote.dto.SearchResultDto
//import com.example.kmptraining.kmp_session4.domain.model.SearchResult
//
//fun SearchResultDto.toDomain() = SearchResult(
//    totalCount = totalCount ?: 0,
//    items = items?.map { it.toDomain() } ?: emptyList()
//)
