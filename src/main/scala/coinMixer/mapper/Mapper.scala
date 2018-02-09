package coinMixer.mapper

import java.util.UUID

import com.fasterxml.uuid.Generators
import scala.collection.mutable.ListBuffer
import scala.util.Random

class Mapper {
  var map = Map.empty[String,String]
  def generateAddress(addr: String) : String ={
    addr match {
      case "" => "No address will be generated for empty strings"
      case s: String =>{
        val generatedAddress = Generators.timeBasedGenerator.generate().toString
       this.map +=(generatedAddress -> addr)
        generatedAddress
      }
    }
  }
}
