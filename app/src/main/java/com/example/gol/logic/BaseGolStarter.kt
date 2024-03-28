package com.example.gol.logic


sealed interface GolStarter {
    val name:String
    val numRows: Int
    val numCols: Int
    val data: List<List<Boolean>>
}

fun GolStarter.toIntermediate(intermediate: List<List<Boolean>>): IntermediateGolStarter {
    return IntermediateGolStarter(findBase(), intermediate)
}

fun GolStarter.findBase(): BaseGolStarter {
    return when (this) {
        is BaseGolStarter -> { this }
        is IntermediateGolStarter -> { base.findBase() }
    }
}
data class BaseGolStarter(override val name:String, override val numRows: Int, override val numCols: Int, override val data: List<List<Boolean>>): GolStarter {
    companion object {
        val infinite_glider = BaseGolStarter("Infinite Glider",26, 36, listOf(
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

        val blinker = BaseGolStarter("Blinker", 5,5, listOf(
            listOf(false, false, false, false, false),
            listOf(false, false, true, false, false),
            listOf(false, false, true, false, false),
            listOf(false, false, true, false, false),
            listOf(false, false, false, false, false)
        ))

        val glider = BaseGolStarter("Glider", 7, 7, listOf(
            listOf(false, false, false, false, false, false, false),
            listOf(false, false, false, false, false, false, false),
            listOf(false, false, false, true, false, false, false),
            listOf(false, false, false, false, true, false, false),
            listOf(false, false, true, true, true, false, false),
            listOf(false, false, false, false, false, false, false),
            listOf(false, false, false, false, false, false, false)
        ))

        val pulsar = BaseGolStarter("Pulsar", 17, 17, listOf(
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

data class IntermediateGolStarter(val base: BaseGolStarter, val intermediate: List<List<Boolean>>): GolStarter {
    override val name: String
        get() = "Intermediate::${base.name}"
    override val numRows: Int
        get() = base.numRows
    override val numCols: Int
        get() = base.numCols
    override val data: List<List<Boolean>>
        get() = intermediate
}



