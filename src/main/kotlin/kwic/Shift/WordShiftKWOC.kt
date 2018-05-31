package kwic.Shift

import kwic.StopWordManager
import kotlin.collections.*

/**
 * A helper object to move strings around, until a
 * desired position. Its implementation is tight to
 * the KWIC problem.
 */
class WordShiftKWOC : WordShift {
    var stop = StopWordManager()

    override fun shift(words: List<String>, pos: Int) : List<String> {
        var x = listOf<String>()
        ///////////////////////////////////////////////////////////
        var y = words.joinToString(" ")
        var max = 0
        var n = 0
        for( j in 0 until y.length){
            if(y[j] != ' '){
                n += 1
            }else{
                n = 0
            }
            if(n > max){
                max = n
            }
        }
        var b = words[pos].length
        var f = 0
        var space : MutableList<String> = mutableListOf()
        if(b < max){
            f = max-b
            for (i in 0 until f){
                space.add(" ")
            }
        }
                space.add(" ")
        var g = space.toList().joinToString("")
        ///////////////////////////////////////////////////////////
        if(!stop.stopWord(words[pos]))
            x = listOf(words[pos]) + listOf(g) + words
        return x
    }
    override fun name() = "KWOC"
}