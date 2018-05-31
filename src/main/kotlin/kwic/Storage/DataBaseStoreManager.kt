package kwic.Storage

import kwic.SetDataBase


class DataBaseStoreManager() : DataStorageManager {

    lateinit var lines : List<String>

    override fun init(){
        lines = SetDataBase.listDAtaBase
    }

    override fun line(idx : Int) = lines[idx]

    override fun length() = lines.size

    override fun name() = "DataBase"

}