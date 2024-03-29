package com.example.gol.logic

import org.junit.Test
import kotlin.random.Random

class GameOfLifeKtNeighboursTest {

    companion object {
        val seed = System.currentTimeMillis()
    }

    @Test
    fun neighbours() {
        val random = Random(seed)

        /*
         * EXAMPLE SPREAD for maxRow = 10, maxCols = 10
         *
         *  0, 1, 2, 3, 4, 5, 6, 7, 8, 9
         * 10,11,12,13,14,15,16,17,18,19
         * 20,21,22,23,24,25,26,27,28,29
         * 30,31,32,33,34,35,36,37,38,39
         * 40,41,42,43,44,45,46,47,48,49
         * 50,51,52,53,54,55,56,57,58,59
         * 60,61,62,63,64,65,66,67,68,69
         * 70,71,72,73,74,75,76,77,78,79
         * 80,81,82,83,84,85,86,87,88,89
         * 90,91,92,93,94,95,96,97,98,99
         */

        repeat(1000) { repetition: Int ->
            val maxRow = random.nextInt(100)
            val maxCols = random.nextInt(100)

            val array = List(maxRow) { row: Int ->
                List(maxCols) { col :Int ->
                    row * maxCols + col
                }
            }

            // going a bit out of hand and should probably be extracted in separate test cases,
            // but this is exhausting avery possible permutation
            fun expectedNeighbours(rol: Int, col: Int): List<Int> {
                val x = array[rol][col]
                return when {
                    maxRow == 1 && maxCols == 1 -> { // single value, cannot have neighbours
                        listOf()
                    }
                    maxRow == 1 || maxCols == 1 -> { // only 3 values, regardless if the span is on the column or on the row side there can only be two neighbours (prev and next)
                        listOf(x - 1, x + 1)
                    }
                    rol == maxRow - 1 && col == maxCols - 1 -> { // bottom-right edge value
                        listOf(x - 1, x - maxCols, x - maxCols - 1)
                    }
                    rol == 0 && col == maxCols -1 -> { // top-right edge value
                        listOf(x - 1, x + maxCols - 1, x + maxCols)
                    }
                    rol == maxRow - 1 && col == 0 -> { // bottom-left edge value
                        listOf(x + 1, x - maxCols + 1, x - maxCols)
                    }
                    rol == 0 && col == 0 -> { // top-left edge value
                        listOf(x + 1, x + maxCols, x + maxCols + 1)
                    }
                    rol == 0 -> { // top-middle value
                        listOf(x - 1, x + 1, x + maxCols - 1, x + maxCols, x + maxCols + 1)
                    }
                    rol == maxRow -1 -> { // bottom-middle value
                        listOf(x - 1, x + 1, x - maxCols - 1, x - maxCols, x - maxCols + 1)
                    }
                    col == 0 -> { // center-left value
                        listOf(x - maxCols, x - maxCols + 1, x + 1, x + maxCols, x + maxCols + 1)
                    }
                    col == maxCols - 1 -> { // center-right value
                        listOf(x - maxCols, x - maxCols - 1, x - 1, x + maxCols, x + maxCols - 1)
                    }
                    else -> { // center-mid value
                        listOf(x - maxCols, x - maxCols - 1, x - maxCols + 1, x - 1, x + 1, x + maxCols, x + maxCols - 1, x + maxCols + 1)
                    }
                }.filter {// filter accidental out of range values
                    it in 0..array[maxRow -1][maxCols -1] && it != x
                }
            }

            for (row in array.indices) {
                for (col in array[row].indices) {
                    val expectedNeighbours = expectedNeighbours(row, col).sorted()
                    val actualNeighbours = array.neighbours(row, col).sorted()

                    assert(expectedNeighbours == actualNeighbours) {
                        """
                            #seed: $seed, repetition: $repetition, row: $row, col: $col, maxRow: $maxRow, maxCols: $maxCols
                            #
                            #expected: ${expectedNeighbours.joinToString(prefix = "[", postfix = "]")}
                            #
                            #actual: ${actualNeighbours.joinToString(prefix = "[", postfix = "]")}
                        """.trimMargin("#")
                    }
                }
            }
        }
    }

}