package com.example.cnexia_challenge.models

import java.io.Serializable

data class Car(
    val consList: List<String>,
    val customerPrice: Long,
    val make: String,
    val marketPrice: Long,
    val model: String,
    val prosList: List<String>,
    val rating: Long
) : Serializable
