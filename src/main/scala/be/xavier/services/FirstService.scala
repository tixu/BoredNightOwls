package be.xavier.services

import akka.actor._
import akka.http._
import be.xavier.actors._
import akka.event.EventHandler
import akka.event.EventHandler._

class FirstService extends Actor with Endpoint	 {
  final val ServiceRoot = "/kluis/"
  final val ReadActor = ServiceRoot + "read"
  final val NewActor = ServiceRoot + "new"
   
  lazy val reader = Actor.actorOf[ReadActor].start
  lazy val default = Actor.actorOf[FirstActor].start
  
  def isReadEndpoint( str : String) :Boolean = {
    EventHandler.notify(Info(this,("analysing " + str)))
    str.contains(ReadActor)
  }
  
   def isNewEndpoint( str : String) :Boolean = {
     EventHandler.notify(Info(this,("analysing " + str)))
     str.contains(NewActor)
  }
  
   self.dispatcher = Endpoint.Dispatcher;

  def hook(uri:String) = isNewEndpoint(uri)|| isReadEndpoint(uri)
  
  def provide(uri:String) :ActorRef = {
    if (isReadEndpoint(uri)) {
      EventHandler.notify(Info(this,("going to read")))
      reader
    } else {
      EventHandler.notify(Info(this,("going to default")))
      default
    }
  }

  override def preStart = {
    EventHandler.debug(this,"starting a service actor")
    Actor.registry.actorsFor(classOf[RootEndpoint]).head ! Endpoint.Attach(hook, provide)
  }
  def receive = handleHttpRequest
}