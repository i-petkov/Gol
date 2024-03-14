package com.example.gol.logic

interface TileFactory<T> {
    fun createAliveTile(): T
    fun createDeadTile(): T
    fun isTileAlive(tile:T): Boolean
}

fun <T> List<List<T>>.evolve(factory: TileFactory<T>): List<List<T>> {
    return List(size) { row: Int ->
        List(this[row].size) { col: Int ->
            val isAlive = factory.isTileAlive(this[row][col])
            val livingNeighbours = this.neighbours(row,col).count { factory.isTileAlive(it) }

            if (livingNeighbours == 2 && isAlive) {
                factory.createAliveTile()
            } else if (livingNeighbours == 3) {
                factory.createAliveTile()
            } else {
                factory.createDeadTile()
            }
        }
    }
}

/** enumerates the values of neighbouring, Above, Below, on Each Side and Diagonal */
fun <T> List<List<T>>.neighbours(row:Int, col:Int): List<T> {
    val buffer = ArrayList<T>()

    // neighbours can only exist on rows below, above or same row as index. Filter items out of array range
    val validRows: Sequence<Int> = (row - 1 .. row + 1).asSequence().filter { it in 0..lastIndex }

    // neighbours can only exist on columns left, right or same same as index,
    // Filter items out of ROW range and current checked index(cannot neighbour itself)
    fun validColsAsSeq(r:Int) = (col - 1 .. col + 1).asSequence().filter {
        it in 0..this[r].lastIndex && !(r == row && it == col)
    }

    for(r in validRows) {
        for (c in validColsAsSeq(r)) {
            buffer.add(this[r][c])
        }
    }

    return buffer
}

fun <T> String.parsAs2DList(transformation: (Char) -> T): List<List<T>> =
    split('\n').fold(ArrayList()) { acc, s ->
        acc.apply { add(s.toList().map(transformation)) }
    }