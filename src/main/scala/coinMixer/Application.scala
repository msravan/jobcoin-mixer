package coinMixer

import javax.annotation.PostConstruct

import mapper.Mapper
import mixer.{Mixer, Transaction}
import org.apache.log4j.Logger
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application {
  private val LOGGER: Logger =Logger.getLogger(classOf[Application])
  val mapper = new Mapper
  val mixer =  new Mixer

  @PostConstruct
  def init(): Unit = {
    LOGGER.info("App initialized")
    val transactionsThread = new Thread {
      override def run {
        while (true) {
          mixer.poll(mapper.map)
          Thread.sleep(5000)
        }
      }
    }
    transactionsThread.start
    while (true) {
      val inputAddress = readLine("Enter your address:")
      println("Generated Address:" + mapper.generateAddress(inputAddress))
    }
  }
}
object Application extends App{
  SpringApplication.run(classOf[Application])
}