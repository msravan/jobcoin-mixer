package coinMixer.mixer

import org.joda.time.DateTime

case class Transaction(timestamp: String, fromAddress: String, toAddress:String, amount : Double)
