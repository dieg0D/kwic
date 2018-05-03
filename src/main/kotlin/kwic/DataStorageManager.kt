package kwic

interface  DataStorageManager{

    fun init()

    fun line(idx : Int): String

    fun length():Int
}
