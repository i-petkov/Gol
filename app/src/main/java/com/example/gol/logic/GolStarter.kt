package com.example.gol.logic

data class GolStarter(val name:String , val nubRows: Int, val nubCols: Int, val data: List<List<Boolean>>) {
    companion object {
        val infinite_glider = GolStarter("Infinite Glider",26, 36, listOf(
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,true,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,false,false,false,false,false,true,true,false,false,false,false,false,false,false,false,false,false,false,false,true,true),
            listOf(false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,true,false,false,false,false,true,true,false,false,false,false,false,false,false,false,false,false,false,false,true,true),
            listOf(true,true,false,false,false,false,false,false,false,false,true,false,false,false,false,false,true,false,false,false,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(true,true,false,false,false,false,false,false,false,false,true,false,false,false,true,false,true,true,false,false,false,false,true,false,true,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,true,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
            listOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false),
        ))

        val blinker = GolStarter("Blinker", 5,5, listOf(
            listOf(false, false, false, false, false),
            listOf(false, false, true, false, false),
            listOf(false, false, true, false, false),
            listOf(false, false, true, false, false),
            listOf(false, false, false, false, false)
        ))

        val glider = GolStarter("Glider", 7, 7, listOf(
            listOf(false, false, false, false, false, false, false),
            listOf(false, false, false, false, false, false, false),
            listOf(false, false, false, true, false, false, false),
            listOf(false, false, false, false, true, false, false),
            listOf(false, false, true, true, true, false, false),
            listOf(false, false, false, false, false, false, false),
            listOf(false, false, false, false, false, false, false)
        ))

        val pulsar = GolStarter("Pulsar", 17, 17, listOf(
            listOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false),
            listOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false),
            listOf(false, false, false, false, true, true, true, false, false, false, true, true, true, false, false, false, false),
            listOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false),
            listOf(false, false, true, false, false, false, false, true, false, true, false, false, false, false, true, false, false),
            listOf(false, false, true, false, false, false, false, true, false, true, false, false, false, false, true, false, false),
            listOf(false, false, true, false, false, false, false, true, false, true, false, false, false, false, true, false, false),
            listOf(false, false, false, false, true, true, true, false, false, false, true, true, true, false, false, false, false),
            listOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false),
            listOf(false, false, false, false, true, true, true, false, false, false, true, true, true, false, false, false, false),
            listOf(false, false, true, false, false, false, false, true, false, true, false, false, false, false, true, false, false),
            listOf(false, false, true, false, false, false, false, true, false, true, false, false, false, false, true, false, false),
            listOf(false, false, true, false, false, false, false, true, false, true, false, false, false, false, true, false, false),
            listOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false),
            listOf(false, false, false, false, true, true, true, false, false, false, true, true, true, false, false, false, false),
            listOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false),
            listOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false)
        ))

        val starters = listOf(
            infinite_glider,
            blinker,
            glider,
            pulsar
        )
    }
}



