package kwic.Shift

import kotlin.collections.*
/**
 * A helper object to move strings around, until a
 * desired position. Its implementation is tight to
 * the KWIC problem.
 */
interface  WordShift {

    fun shift(words: List<String>, pos: Int) : List<String>

    fun name() : String
}