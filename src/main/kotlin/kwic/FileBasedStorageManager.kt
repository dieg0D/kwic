package kwic

import java.io.File
import kotlin.collections.*

class FileBasedStorageManager() : DataStorageManager {

    lateinit var lines : List<String>

    override fun init(){
        val fileName = ("/home/lucas/kwic/src/test/resources/papers.txt")
        lines = File(fileName).readLines().toList()
    }

    override fun line(idx: Int): String {
        return lines[idx]
    }

    override fun length(): Int {
        return lines.size
    }
}
