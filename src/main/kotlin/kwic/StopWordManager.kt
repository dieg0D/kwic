package kwic

import  java.io.File
import  kotlin.collections.*

class StopWordManager() {
    val words : List<String> = File("/home/diego/IdeaProjects/src/main/resources/stop_words.txt").readLines().toList()

    fun stopWord(word : String):Boolean = words.contains(word)
}