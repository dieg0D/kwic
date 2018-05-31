package kwic.Storage


import org.json.JSONObject
import java.net.URL


class DBLPStorageManager : DataStorageManager {
    lateinit var lines:List<String>

    override fun init()  {
//        print("Enter the DBLP search criteria (such as the author name) ")
        val query = "curupira"
        val url   = "http://dblp.org/search/publ/api?q=$query&format=json"
        lines     = makeRequest(url)
    }

    private fun makeRequest(urll: String ): List<String>  {
        val url = URL(urll)
        val json  = JSONObject(url.readText())
        val array  = json.getJSONObject("result").getJSONObject("hits").getJSONArray("hit")
        val lista = mutableListOf("")
        var i = 0
        while(i < array.length()){
            lista.add(i, array.getJSONObject(i).getJSONObject("info").getString("title").toString())
            i++
        }
        return lista
    }

    override fun line(idx : Int) = lines[idx]

    override fun length() = lines.size

    override fun name() = "DBLP"

}
