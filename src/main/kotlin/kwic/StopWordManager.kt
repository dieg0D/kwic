package kwic

import  java.io.File
import  kotlin.collections.*

class StopWordManager {
    val words : List<String> = File("//home/diego/Documentos/tp2/KWIC/src/main/resources/stop_words.txt").readLines().toList()

    fun stopWord(word : String):Boolean = words.contains(word)
}