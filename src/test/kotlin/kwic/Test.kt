package kwic
import junit.framework.TestCase
import kwic.Shift.WordShiftKWIC
import kwic.Shift.WordShiftKWAC
import kwic.Shift.WordShiftKWOC
import kwic.Storage.DBLPStorageManager
import kwic.Storage.DataBaseStoreManager
import kwic.Storage.FileBasedStorageManager

class Test(name: String?) : TestCase(name) {
    fun testDBlP(){
        val dblp =  DBLPStorageManager()
        dblp.init()
        assertEquals(dblp.length(),4)
        assertEquals(dblp.line(0),"A Hardware Implementation of CURUPIRA Block Cipher for Wireless Sensors.")
        assertEquals(dblp.line(1),"The CURUPIRA-2 Block Cipher for Constrained Platforms - Specification and Benchmarking.")
        assertEquals(dblp.line(2),"Curupira - A Functional Parser for Brazilian Portuguese.")
        assertEquals(dblp.name(),"DBLP")
    }

    fun testFile(){
        val file =  FileBasedStorageManager()
        file.init()
        assertEquals(file.length(),2)
        assertEquals(file.line(0),"Essentials of Business Communication")
        assertEquals(file.line(1),"Encyclopedia of research on library science")
        assertEquals(file.name(),"File")
    }

    fun testDataBase(){
        val dataBase =  DataBaseStoreManager()
        dataBase.init()
        dataBase.init()
        assertEquals(dataBase.length(),2)
        assertEquals(dataBase.line(0),"Essentials of Business Communication")
        assertEquals(dataBase.line(1),"Encyclopedia of research on library science")
        assertEquals(dataBase.name(),"DataBase")
    }

    fun testStopWord(){
        val stop = StopWordManager()
        assertEquals(stop.stopWord("of"),true)
        assertEquals(stop.stopWord("a"),true)
    }

    fun testIndexManager(){
        val index = IndexManager()
        assertEquals(index.isEmpty(),true)
    }

    fun testKWIC(){
        val kwic = WordShiftKWIC()
        val test = ("Essentials of Business Communication".split(" ").toList())
        assertEquals(kwic.shift(test,3).toString(),"[of, Business,                                                    , Communication,       , Essentials]")
        assertEquals(kwic.name(),"KWIC")
    }

    fun testKWAC(){
        val kwac = WordShiftKWAC()
        val test = ("Essentials of Business Communication".split(" ").toList())
        assertEquals(kwac.shift(test,3).toString(),"[Communication,                                                    , Essentials, of, Business]")
        assertEquals(kwac.name(),"KWAC")
    }

    fun testKWOC(){
        val kwoc = WordShiftKWOC()
        val test = ("Essentials of Business Communication".split(" ").toList())
        assertEquals(kwoc.shift(test,3).toString(),"[Communication,  , Essentials, of, Business, Communication]")
        assertEquals(kwoc.name(),"KWOC")
    }

}