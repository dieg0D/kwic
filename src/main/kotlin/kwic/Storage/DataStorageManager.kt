package kwic.Storage

interface  DataStorageManager{

    fun init()

    fun line(idx : Int): String

    fun length():Int

    fun name() : String
}
