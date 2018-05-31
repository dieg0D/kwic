package kwic.Shift

import org.springframework.context.support.ClassPathXmlApplicationContext

object SetWordShift {
    var ctx =  ClassPathXmlApplicationContext("application-context.xml")
    var file : kotlin.collections.List<WordShift>  = ctx.getBean("listWordShifters") as kotlin.collections.List<WordShift>
    var file2 = file.toMutableList().toTypedArray()
    fun setWordShift(): WordShift {
        var y : Boolean
        var z : Boolean
        var x : Int
        do{
            x = 0
            y =false
            z = true
            println("Insira o Algoritmo que deve ser utilizado ")
            for(i in 0 until file2.size){
                print(i+1)
                print("-")
                println( file2[i].name())
            }
            print("Opção:")
            try{
                x = readLine()!!.toInt()
                y = true
            }
            catch (e :java. lang.NumberFormatException ){
                y = false
            }
            x--

            if(x <0 || x > file2.size-1){
                z = false
                y = false
            }
            if(z == false){
                println("Opção invalida !! Tente novamente\n")
            }
        }while (y == false)

        return file2[x]
    }
}