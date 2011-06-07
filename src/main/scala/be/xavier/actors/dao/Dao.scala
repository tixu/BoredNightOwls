package be.xavier.actors.dao
import akka.actor._
import scala.collection.mutable.HashMap
import akka.http._
import akka.event.EventHandler
import akka.event.EventHandler._
import be.xavier.bean.{ Response, Data,MetaInformation }
import com.mongodb.casbah.Imports._

case class Read(val key: String) {}

object Dao {

 
  val dictionnary = HashMap[String, Response]()
  val meta = MetaInformation(qos = "", quality = "")
  val data = Data(niss="123",content="ddddddddddddddddddddddd")
  val response = Response(metainformation = meta, data = data)
  dictionnary.put("123", response)

  
  
  def get ( key:String) :Option[Response]={
    try {
    	dictionnary.get(key)
    }
    catch  {
      case  e:NoSuchElementException => {
        EventHandler.error(this,"no such element")
        throw e 
      }
      case  e:Exception => {
        EventHandler.error(this,"unknown error")
        throw e 
      }
    }
  } 
 

}