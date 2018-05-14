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
    //var k = words(pos)
    //println("\nk = " + k + "\n")
    val (l, r) = words.splitAt(pos)
    //println("r = " + r)
    //println("l = " + l)
    if(l.mkString(" ").length < (target -5)) {
      return shiftRight(l, r, target)
    }
    var tam = words.length
    var alvo = tam/2
    return shiftLeft(words, alvo, pos, tam-1)
    
  }

  /*
   * Move the entries of the second list to the beginning of 
   * the first list, until the total length of the strings     
   * of the first list is "smaller enough" than the target parameter. 
   */
  private def shiftRight(l : List[String], r : List[String], target : Int) : List[String] = {
    if(r.length == 0) {
      return l
    }

    val (r1, r2) = r.splitAt(r.length-1)
    val l1 = r2 ++ l

    if(l1.mkString(" ").length > (target - 5)) {
      return l ++ r
    }

    return shiftRight(l1, r1, target)
  }

  /*
   * Move the entries of the first list to the end of 
   * the second list, until the length of the strings 
   * of the first list is "smaller enough" than the target parameter. 
   */
  private def shiftLeft(l : List[String], alvo: Int, pos : Int, tam: Int) : List[String] = {
    /*if(l.length == 0) {
      return r
    }*/

    
    //println(alvo)


    //val (l1, l2) = l.splitAt(1)
    //println("l1 = " + l1)
    //val r1 = r ++ l1
    //println("r1 = " + r1)
    var j = 0
    var dps = new ListBuffer[String]()
    var antes = new ListBuffer[String]()
   
    println("alvo = " + alvo + " pos = " + pos)
    println(l)
    
    if((tam+1) % 2 == 0){
      //println("PAR")
        var p = 0
        var f = tam-pos
        //println(f)
        if(pos > alvo){
          for (j <- 1 to alvo){
            var i = pos-j
            antes += l(i)
          }
          for ( j <- pos+1 to tam){
            dps += l(j)
            p += 1
            //println("aqui = " + (pos+1))
          }

          if(tam-alvo > p){
            for ( j <- 0 until ((tam-alvo)-p) ){
              dps += l(j)
            }
          }
        }else{
          /*println("tam = " + (tam))
          println("tam-alvo = " + (tam-alvo))
          println("tam-pos = " + (tam-pos))*/
          
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
                //println("aqui = " + (pos-1))
              }
              var t = alvo - b
              //println("tesaoo= " + t)
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
          //println("IMPAR")
            var p = 0
            var f = tam-pos
            //println(f)
            if(pos > alvo){
              for (j <- 1 to alvo){
                var i = pos-j
                antes += l(i)
              }
              for ( j <- pos+1 to tam){
                dps += l(j)
                p += 1
                //println("aqui = " + (pos+1))
              }

              if(tam-alvo > p){
                for ( j <- 0 until ((tam-alvo)-p) ){
                  dps += l(j)
                }
              }
            }else{
              /*println("tam = " + (tam))
              println("tam-alvo = " + (tam-alvo))
              println("tam-pos = " + (tam-pos))*/
              
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
                    //println("aqui = " + (pos-1))
                  }
                  var t = alvo - b
                  //println("tesaoo= " + t)
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
    println("dps = " + k3)

    
    
    var k5 = antes.toList.reverse
    println("antes = " + k5)
    /*var x = k5.mkString(" ")
    maior += x.length
    var y = x.length
    val space = new ListBuffer[String]()
    /*println(y)
    println(maior.max)*/
    if(y==maior.max){
      space += ""
    }else{
      for(j <- y to (maior.max) ){
          space += ""
      }
    }
    val k9 = k5 ++ space.toList
    //println(k9.length)

    maior1 += l(pos).length
    var y1 = l(pos).length
    val space1 = new ListBuffer[String]()
    if(y1==maior1.max){
      space1 += ""
    }else{
      for(j <- y1 to (maior1.max) ){
          space1 += ""
      }
    }*/
    var word = List(l(pos))
    if( pos == alvo){
      var kwic = l
      return kwic
    }else{
      var kwic = k5 ++ word ++ k3
      if(stop.stopWord(l(pos))){
        return List("\n")
      }else{
        return kwic
      }
    }
    
  

    /*val diff = 
    if(l2.mkString(" ").length < (target-5)) {
      //return l2 ++ r1
      println("vai tomar no cu")
    }*/

    
  }
}
