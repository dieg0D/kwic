package kwic.Storage

import java.io.File
import kotlin.collections.*

class FileBasedStorageManager : DataStorageManager {

    lateinit var lines : List<String>

    override fun init(){
        val fileName = ("/home/diego/Documentos/tp2/KWIC/src/test/resources/papers.txt")
        lines = File(fileName).readLines().toList()
    }

    override fun line(idx : Int) = lines[idx]

    override fun length() = lines.size

    override fun name() = "File"
}
