package tk.aizydorczyk.wykop.restclient

import java.net.ConnectException

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import play.api.libs.json.{JsArray, JsValue}
import play.api.libs.ws.JsonBodyReadables._
import play.api.libs.ws.ahc.StandaloneAhcWSClient
import tk.aizydorczyk.wykop.restclient.Converters._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class WykopRestClient {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  private val url = "http://a.wykop.pl/stream/index/appkey,x3oGAOodPw,page,%s,output,clear"

  private val apiSignHeader = "apisign"
  private val sign = "430118267fd511f315a2378f2166b790"
  private val contentTypeHeader = "Content-Type"
  private val json = "application/json"

  private val client = StandaloneAhcWSClient()

  def supplyOfWykopEntire(supplyFunction: (Seq[Entry]) => Unit,
                          pageSelectedIndexFunction: () => Int): Unit = {
    client
      .url(url.format(pageSelectedIndexFunction.apply()))
      .withHttpHeaders(
        (apiSignHeader, sign),
        (contentTypeHeader, json)
      )
      .get()
      .map(_.body[JsValue])
      .onComplete {
        case Success(value) =>
          supplySource(value, supplyFunction)
        case Failure(e) => throw e
      }
  }

  def supplySource(response: JsValue,
                   supplyFunction: (Seq[Entry]) => Unit): Unit = {
    Option(response.asInstanceOf[JsArray].value)
      .orElse(Option.empty)
      .map(_.map(_.as[Entry]))
      .fold(throw new AssertionError()())(supplyFunction.apply(_))
  }
}
