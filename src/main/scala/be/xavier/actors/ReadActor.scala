package be.xavier.actors

import akka.actor._
import akka.http._
import be.xavier.bean.{ Response, MetaInformation, ResourcesUtil }
import javax.servlet.http.HttpServletRequest
import sjson.json.Serializer
import scala.util.matching.Regex
import akka.event.EventHandler
import akka.event.EventHandler._
import be.xavier.actors.dao.Dao

class ReadActor extends Actor with ResourcesUtil {

  //to check oif this one is multithreaded
  val readrexexp = """/kluis/read/([0-9]*)""".r

  def receive = {
    case httpget: Get =>
      try {
        EventHandler.notify(Info(this, ("reading " + httpget.request.getRequestURI)))
        val readrexexp(niss) = httpget.request.getRequestURI
        EventHandler.notify(Info(this, ("niss: " + niss)))
        //need to make  a method in a fake dao
        
        httpget.response.setContentType("application/text")
        val response = Dao.get(niss)

        response match {
         
          case Some(x) => {
            val json = serializeAsJSON(x)
            httpget OK json
          }
           case None => {
            EventHandler.notify(Info(this, "not found"))      
            httpget NotFound "not found : "+niss
          }
        }

        
        
      } catch {
        case e: MatchError => {
          EventHandler.error(e, "exception reading url")
          httpget Error "unable to read request url"
        }
        case e: Exception => {
          EventHandler.error(e, "exception reading")
          httpget Error "unable to read request"
        }
      }
    case other: RequestMethod =>
      other NotAllowed "unsupported request"
  }

  override def preStart() = {
    EventHandler.debug(this, "starting a read actor")
  }
}