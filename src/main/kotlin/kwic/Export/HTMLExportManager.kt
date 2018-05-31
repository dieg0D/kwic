package kwic.Export


import kwic.IndexManager
import kwic.Storage.SetStorageManager
import kwic.Shift.SetWordShift
import spark.Spark.*


class HTMLExportManager : ExportManager {
    override fun print() {
        val WordShift = SetWordShift.setWordShift()
        var listinha = mutableListOf<String>()
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
                    it -> x = (WordShift.shift(it.first.split(" ").toList(),it.second).joinToString(separator = " " ))
                }
                if(x.isNotEmpty()) {
                  listinha.add(x)
                }
            })
        }
        var oi =""
        for(i in 0 until  listinha.size){
            oi = when (i) {
                0 -> "<p style ='text-align:center;font-size:200%;'>" + oi + listinha[i]+"<br>"
                listinha.size -> oi + listinha[i]+"<br>"+"</p>"
                else -> oi + listinha[i]+"<br>"
            }
        }
        get("/hello") { req, res ->
           oi
        }

    }

    override fun name() = "HTML"
}