package kwic.Shift

import kwic.StopWordManager
import kotlin.collections.*
/**
 * A helper object to move strings around, until a
 * desired position. Its implementation is tight to
 * the KWIC problem.
 */
class WordShiftKWAC() : WordShift {
    val maior : MutableList<Int> = mutableListOf()
    val maior1 : MutableList<Int> = mutableListOf()
    var stop = StopWordManager()
    var oneshot = 0
    var alin = 0
    var alin2 = 0


  override fun shift(words: List<String>, pos: Int) : List<String> {
        var tam = words.size
        var alvo = 0
        ///////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////
        return shiftFull(words, alvo, pos, tam-1)

    }

    private fun shiftFull(l : List<String>, alvo: Int, pos : Int, tam: Int) : List<String> {
        var j = 0
        var dps : MutableList<String> = mutableListOf()
        var antes : MutableList<String> = mutableListOf()
        if((tam+1) % 2 == 0){
            var p = 0
            var f = tam-pos
            if(pos > alvo || alvo==0){
                for (j in 1 .. alvo){
                    var i = pos-j
                    antes.add(l[i])
                }
                for ( j in pos+1 .. tam){
                    dps.add(l[j])
                    p += 1
                }

                if(tam-alvo > p){
                    for ( j in 0 until ((tam-alvo)-p) ){
                        dps.add(l[j])
                    }
                }
            }else{
                var y = 0
                if(pos - 0 == 0){
                    for (j in 0 until alvo){
                        var i = tam-j
                        if(i > (-1) ){
                            antes.add(l[i])
                        }
                    }
                    for (j in 1 until alvo){
                        var i = j
                        dps.add(l[i])

                    }

                }else{
                    var b = 0
                    for ( j in pos-1 downTo  0){
                        if(j < alvo){
                            antes.add(l[j])
                            b += 1
                        }
                    }
                    var t = alvo - b
                    if( t != 0){
                        for ( j in 0 until t ){
                            var i = tam-j
                            antes.add(l[i])
                        }
                    }
                    for (j in 1 until alvo){
                        var i = pos+j
                        if(i<=tam){
                            dps.add(l[i])
                        }
                    }
                }
            }
        }else{ ///////////////////////////////////////////////////
            var p = 0
            var f = tam-pos
            if(pos > alvo || alvo==0){
                for (j in 1 .. alvo){
                    var i = pos-j
                    antes.add(l[i])
                }
                for ( j in pos+1 .. tam){
                    dps.add(l[j])
                    p += 1
                }

                if(tam-alvo > p){
                    for ( j in 0 until ((tam-alvo)-p) ){
                        dps.add(l[j])
                    }
                }
            }else{
                var y = 0
                if(pos - 0 == 0){
                    for (j in 0 until alvo){
                        var i = tam-j
                        if(i > (-1) ){
                            antes.add(l[i])
                        }
                    }
                    for (j in 1 .. alvo){
                        var i = j
                        dps.add(l[i])
                    }
                }else{
                    var b = 0
                    for ( j in pos-1 downTo 0){
                        if(j < alvo){
                            antes.add(l[j])
                            b += 1
                        }
                    }
                    var t = alvo - b
                    if( t != 0){
                        for ( j in 0 until t ){
                            var i = tam-j
                            antes.add(l[i])
                        }
                    }
                    for (j in 1 .. alvo){
                        var i = pos+j
                        dps.add(l[i])
                    }
                }
            }
        }
        val k3 = dps.toList()
        var k5 = antes.toList().reversed()
        var word = listOf(l[pos])
        var x = k5.joinToString(" ").length
        var x2 = l[pos].length
        var space : MutableList<String> = mutableListOf()
        var space2 : MutableList<String> = mutableListOf()
        if (oneshot == 0){
            alin = k5.joinToString(" ").length
            alin += 50
            alin2 = l[pos].length
            alin2 += 50
            oneshot = 1
        }
        for ( i in x .. alin){
            space.add(" ")
        }
        var alinhar = space.joinToString("")
        for ( i in x2 .. alin2){
            space2.add(" ")
        }
        var alinhar2 = space2.joinToString("")
        var kwic = listOf<String>()
        if(alvo == 0){
            kwic = word + listOf(alinhar2) + k3
        }else{
            kwic = k5 + listOf(alinhar) + word + listOf("      ") + k3
        }
        if(stop.stopWord(l[pos])){
            return listOf()
        }else{
            return kwic
        }
    }

    override fun name() = "KWAC"
}
