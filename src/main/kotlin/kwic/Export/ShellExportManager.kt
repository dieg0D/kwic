package kwic.Export

import kwic.IndexManager
import kwic.Shift.SetWordShift
import kwic.Storage.SetStorageManager

class ShellExportManager  : ExportManager {
    override fun print(){
        val WordShift = SetWordShift.setWordShift()
        val dsm = SetStorageManager.setStorage()
        dsm.init()

        val im  = IndexManager()

        for(lineNumber in 0 until dsm.length()) {
            val line = dsm.line(lineNumber)
            val words = line.split(' ')
            for(pos in 0 until words.size) {
                im.map(words[pos], line, pos)
            }
        }
        lateinit var x : String
        for(w in im.sortedWords()){
            im.occurrencesOfWord(w)!!.forEach ({
                when(it){
                    it -> x = (WordShift.shift(it.first.split(" ").toList(),it.second ).joinToString(separator = " " ))
                }
                if(x.isNotEmpty()) {
                    println(x)
                }
            })
        }
    }

    override fun name() = "Terminal"
}