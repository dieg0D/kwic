package kwic

import scala.io.Source
/**
  * Models the content of a file.  
  */ 
class FileBasedStorageManager() extends DataStorageManager {
  private var lines: List[String] = _

  def init() : Unit = {
    println("Enter the name of the input file: ")
    val fileName = ("/home/lucas/kwic/src/test/resources/papers.txt")
    lines = Source.fromFile(fileName).getLines.toList
    println
  }

  /**
    * Get a specific line of the file. 
    * @param the number of the line within the file
    * @return a specific line of the file.      
    */
  def line(idx : Int) = lines(idx)

  /**
    * The total number of lines of a file. 
    */  
  def length() = lines.length
}
