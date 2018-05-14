package kwic

import scala.collection.mutable.ListBuffer
import java.awt.Font
/**
  * A helper object to move strings around, until a 
  * desired position. Its implementation is tight to 
  * the KWIC problem.  
  */
object WordShift {
   val maior = new ListBuffer[Int]()
   val maior1 = new ListBuffer[Int]()
   var stop = new StopWordManager
   
  /** Rotate the words in a list of Strings
    * 
    * Move the words to the left or to the right, until 
    * the word at position pos is moved to the target 
    * position in a string that corresponds to joining 
    * all string in the resulting list. .    
    */
  def shift(words: List[String], pos: Int, target: Int) : List[String] = {
    
    val (l, r) = words.splitAt(pos)
    
    var tam = words.length
    var alvo = tam/2
    return shiftFull(words, alvo, pos, tam-1)
    
  }

  private def shiftFull(l : List[String], alvo: Int, pos : Int, tam: Int) : List[String] = {
    var j = 0
    var dps = new ListBuffer[String]()
    var antes = new ListBuffer[String]()
    
    if((tam+1) % 2 == 0){
        var p = 0
        var f = tam-pos
        if(pos > alvo){
          for (j <- 1 to alvo){
            var i = pos-j
            antes += l(i)
          }
          for ( j <- pos+1 to tam){
            dps += l(j)
            p += 1
          }

          if(tam-alvo > p){
            for ( j <- 0 until ((tam-alvo)-p) ){
              dps += l(j)
            }
          }
        }else{
          var y = 0
          if(pos - 0 == 0){
            for (j <- 0 until alvo){
              var i = tam-j
              if(i > (-1) ){
                antes += l(i)
              }
            }
            for (j <- 1 until alvo){
              var i = j
              dps += l(i)
              
            }
            
          }else{
              var b = 0
              for ( j <- pos-1 to 0 by -1){
                if(j < alvo){
                  antes += l(j)
                  b += 1
                }
              }
              var t = alvo - b
              if( t != 0){
                for ( j <- 0 until t ){
                  var i = tam-j
                  antes += l(i)
                }
              }
              for (j <- 1 until alvo){
                var i = pos+j
                if(i<=tam){
                  dps += l(i)
                }
              }

            }
          }
        }else{ ///////////////////////////////////////////////////
            var p = 0
            var f = tam-pos
            if(pos > alvo){
              for (j <- 1 to alvo){
                var i = pos-j
                antes += l(i)
              }
              for ( j <- pos+1 to tam){
                dps += l(j)
                p += 1
              }

              if(tam-alvo > p){
                for ( j <- 0 until ((tam-alvo)-p) ){
                  dps += l(j)
                }
              }
            }else{
              var y = 0
              if(pos - 0 == 0){
                for (j <- 0 until alvo){
                  var i = tam-j
                  if(i > (-1) ){
                    antes += l(i)
                  }
                }
                for (j <- 1 to alvo){
                  var i = j
                  dps += l(i)
                  
                }  
              }else{
                  var b = 0
                  for ( j <- pos-1 to 0 by -1){
                    if(j < alvo){
                      antes += l(j)
                      b += 1
                    }
                  }
                  var t = alvo - b
                  if( t != 0){
                    for ( j <- 0 until t ){
                      var i = tam-j
                      antes += l(i)
                    }
                  }
                  for (j <- 1 to alvo){
                   var i = pos+j
                   dps += l(i)
                  }
            }
          }
        }

    val k3 = dps.toList
    var k5 = antes.toList.reverse
    var word = List(l(pos))
    if( pos == alvo){
      var kwic = l
      return kwic
    }else{
      var kwic = k5 ++ word ++ k3
      if(stop.stopWord(l(pos))){
        return List("")
      }else{
        return kwic
      }
    }
  }
}
