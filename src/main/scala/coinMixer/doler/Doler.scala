package coinMixer.doler

import coinMixer.mixer.Transaction

import scala.util.Random

object Doler {
  var listOfNumbers: List[Int] = List()
  var target: Int = 0

  def naturalNumbers(i:  Int) = ( 1 to i).toStream

  def randomAmounts(amount: Int): List[Int] = {
    val amountSumCombinations =combinationSum(naturalNumbers(amount))
    amountSumCombinations(new Random().nextInt(amountSumCombinations.length))
  }
  def dole(transaction: Transaction) : List[Transaction] = {
    val precision:List[Transaction] = (transaction.amount - transaction.amount.toInt) match {
      case 0.0 => List.empty[Transaction]
      case a: Double => List(new Transaction(transaction.timestamp,transaction.fromAddress,transaction.toAddress,BigDecimal(a).setScale(4, BigDecimal.RoundingMode.HALF_UP).toDouble
      ))
    }
    randomAmounts(transaction.amount.toInt).map( e=> {
      new Transaction(transaction.timestamp, transaction.fromAddress, transaction.toAddress,e)
    }).foldLeft(precision)((acc,transaction: Transaction)=> {
      transaction :: acc
    })

  }

  def possibleNumbers(result: List[Int]): List[Int] = result match {
    case Nil => listOfNumbers
    case _ =>  listOfNumbers.filter(x => ((x<= (result.head)) && (x<= (target-(result.sum)))))
  }

  def nextResult(prevResult: List[Int]): List[List[Int]] = prevResult match {
    case x if (x.sum==target) => List(x)
    case _ => for {
      possibleNumber <- possibleNumbers(prevResult)
      result <- nextResult(possibleNumber :: prevResult)
    } yield result
  }

  def combinationSum(candidates: Stream[Int]): List[List[Int]] = {
    this.listOfNumbers = candidates.toList
    this.target = candidates.length
    nextResult(List())
  }
}
