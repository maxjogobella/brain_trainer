package com.example.brain_kid.domain.model

data class Question(
    val sum : Int,
    val visibleNumber : Int,
    val options : List<Int>
)