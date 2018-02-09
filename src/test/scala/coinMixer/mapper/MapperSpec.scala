import coinMixer.mapper.Mapper

import collection.mutable.{ListBuffer, Stack}
import org.scalatest._

class MapperSpec extends FlatSpec with Matchers {
  val mapper = new Mapper

  it should "generate an address when provided an input address" in {
    val generatedAddress = mapper.generateAddress("abcd")
    println(generatedAddress)
    generatedAddress.isEmpty should be (false)
  }

  it should "generate an address when provided an input address and store inside a map" in {
    val inputAddress = "efgh"
    val generatedAddress = mapper.generateAddress("efgh")
    println(generatedAddress)
    mapper.map.get(generatedAddress).mkString("") should be (inputAddress)
  }
}
