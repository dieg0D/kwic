package kwic

import  kotlin.collections.*

class IndexManager() {
    private val map : HashMap<String,MutableList<Pair<String,Int>>> = HashMap()

    fun isEmpty () : Boolean = map.isEmpty()

    fun map(word: String, line: String, pos : Int) {
        val tuple = Pair(line, pos)

        if(map.contains(word)) {
            map[word] = mutableListOf(tuple)
        }
        else {
            map += hashMapOf(word to  mutableListOf(tuple))
        }
    }

    fun occurrencesOfWord(word: String) : MutableList<Pair<String,Int>>?= map[word]

    fun sortedWords() : List<String> = map.keys.toList().sorted()
}