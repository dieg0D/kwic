package kwic

import kotlin.collections.*

fun main(args: Array<String>){

        val dsm = FileBasedStorageManager()
        dsm.init()

        val im = IndexManager()

        for (lineNumber in 0 until dsm.length()) {
            val line = dsm.line(lineNumber)
            val words = line.split(' ')
            for (pos in 0 until words.size) {
                im.map(words[pos], line, pos)
            }
        }
        for (w in im.sortedWords()) {

            for (lineNumber in 0 until dsm.length()) {
                var line = dsm.line(lineNumber)
                var words = line.split(' ')
                for (pos in 0 until words.size) {
                    im.occurrencesOfWord(w)!!.forEach {
                        println(WordShift.shift(line.split(' ').toList(), pos, 0).joinToString(separator = " "))
                    }
                }
            }
        }
}

