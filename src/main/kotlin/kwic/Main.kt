package kwic

import kwic.Export.SetExportManager
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.drop

// Cria a tabela no banco de dados e invoca o SetExport manager para execução do progama
fun main(args: Array<String>){
    Database.connect(url="jdbc:mysql://localhost:3306/title?autoReconnect=true&useSSL=false",user = "root",password = "root", driver = "com.mysql.jdbc.Driver")

    transaction {
        create(titles)
        val t1 = titles.insert {
            it[name] = "Essentials of Business Communication"
        }get titles.id

        val t2 = titles.insert {
            it[name] = "Encyclopedia of research on library science"
        }get titles.id

        for (i in titles.selectAll()) {
            SetDataBase.listDAtaBase.add(i[titles.name])
        }
        drop(titles)
    }
    var a = SetExportManager.setExport()
   a.print()

}

//Definição da tabela do banco de dados
object titles : Table(){
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", length = 200)
}

//Lista que será utilizada pelo DataBaseStoreManager
object SetDataBase{
    val listDAtaBase : MutableList<String> = mutableListOf()
}
