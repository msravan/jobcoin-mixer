package coinMixer.doler

import coinMixer.mixer.Transaction
import org.scalatest._


class DolerSpec extends FlatSpec with Matchers {


  it should "generate natural numbers till n, when 'n'input provided " in {
    Doler.naturalNumbers(10).take(5).mkString("") should be ("12345")
    Doler.naturalNumbers(10).take(7).mkString("") should be ("1234567")
  }

  it should "generate list of transactions when dole is called " in {
    val transaction =  new Transaction("Some Time", "Sender", "Receiver", 20.5656565)
  val listOfTransaactions: List[Transaction] = Doler.dole(transaction)
  val calcAmount =listOfTransaactions.foldLeft(0.00)((acc, transaction: Transaction) =>{
      acc + transaction.amount
    })
    calcAmount should be (transaction.amount)

  }
}