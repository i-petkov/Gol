package com.example.gol.logic

fun <E> List<List<E>>.contentDeepEquals(other: List<List<E>>): Boolean {
    if(this.size != other.size) {
        return false
    } else {
        for (index in indices) {
            if (this[index] != other[index]) {
                return false
            }
        }
        return true
    }
}
