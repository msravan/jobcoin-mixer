package coinMixer.mixer

import java.util

import coinMixer.Application
import org.apache.http.NameValuePair
import org.apache.http.client.entity.EntityBuilder
import org.apache.http.client.methods.{CloseableHttpResponse, HttpPost}
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.message.BasicNameValuePair
import org.apache.log4j.Logger

import scala.collection.mutable
import scala.util.{Failure, Success, Try}
import scala.util.parsing.json.JSON

class Mixer {

  private val LOGGER: Logger = Logger.getLogger(classOf[Application])
  private val houseAddress = "srvn-a944322d972"
  private val transactionsUrl = "http://jobcoin.gemini.com/starry/api/transactions"
  val httpClient = HttpClientBuilder.create.build
  var lastSizeOfTransaction = 0

  def sendCoins(transaction: Transaction):Transaction = {
    lastSizeOfTransaction=lastSizeOfTransaction+1
    val post = new HttpPost(transactionsUrl)
    val e = EntityBuilder.create()
    val nameValuePairs = new util.ArrayList[NameValuePair]()
    nameValuePairs.add(new BasicNameValuePair("fromAddress", transaction.fromAddress))
    nameValuePairs.add(new BasicNameValuePair("toAddress", transaction.toAddress))
    nameValuePairs.add(new BasicNameValuePair("amount", transaction.amount.toString))
    e.setParameters(nameValuePairs)
    post.setEntity(e.build())
    Try(httpClient.execute(post)) match {
      case Success(s)=> {
        //LOGGER.info(transaction + "Success")
        transaction
      }
      case Failure(f)=>{
        throw new Exception("Transaction failed : "+transaction)
      }
    }
  }
  def normalizedTransaction(transaction: Map[String, String]): Transaction = {
    new Transaction(transaction.get("timestamp").mkString(""), transaction.get("fromAddress").mkString(""), transaction.get("toAddress").mkString(""), transaction.get("amount").mkString("").toDouble)
  }

  def poll(map: Map[String, String]): Unit = {

    val currentTransactions:List[Transaction] = JSON.parseFull(scala.io.Source.fromURL(transactionsUrl).mkString)
      .toStream.head.asInstanceOf[List[Map[String, String]]]
      .foldLeft(List.empty[Transaction])((acc, transaction) =>
        normalizedTransaction(transaction)
          :: acc)

      currentTransactions.take(currentTransactions.length - lastSizeOfTransaction)
        .toStream.filter(e => map.keySet.contains(e.toAddress))
        .map(transaction => sendCoins(transaction.copy(fromAddress = transaction.toAddress,toAddress = houseAddress)))
        .map(transaction => transaction.copy(fromAddress = houseAddress, toAddress = map.get(transaction.fromAddress).mkString("") ))
        .foldLeft(List.empty[Transaction])((acc,transaction: Transaction)=> {
          //Doler.dole(transaction) - Distribution as of smaller amounts - 2nd transaction execution takes long time
           sendCoins(transaction) :: acc
          acc
        })
   }
}
