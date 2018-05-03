package kwic

import  kotlin.collections.*

object WordShift {
    fun shift(words : List<String> , pos : Int , target : Int) : List<String> {
        val l = words.subList(0,pos)
        val r =  words.subList(pos,words.size)

        if(l.joinToString(separator = " ").length < (target -5)){
            return shiftRight(l,r,target)
        }
        else{
            return  shiftLeft(l,r,target)
        }
    }


    private fun  shiftRight(l : List<String>, r : List<String>, target : Int) : List<String> {
        if(r.size == 0) {
            return l
        }
        val r1 = r.subList(0,r.size-1)
        val r2 =  r.subList(r.size-1,r.size)
        val l1 = r2 + l

        if(l1.joinToString(separator = " ").length > (target - 5)) {
            return l + r
        }

        return shiftRight(l1, r1, target)
    }

    private fun shiftLeft(l : List<String>, r : List<String>, target : Int) : List<String>{
        if(l.size == 0) {
            return r
        }

        val l1 = l.subList(0,1)
        val l2 =  l.subList(1,l.size)
        val r1 = r + l1

        if(l2.joinToString(separator = " ").length < (target - 5)) {
            return l2 + r1
        }

        return shiftLeft(l2, r1, target)
    }

}