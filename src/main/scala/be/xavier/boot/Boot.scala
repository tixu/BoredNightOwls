package be.xavier.boot


import akka.actor._
import akka.http._
import akka.config._
import akka.config.Supervision._
import akka.util._
import be.xavier.services.FirstService
import akka.event.EventHandler
import akka.event.EventHandler._


class Boot 
{
  val factory = SupervisorFactory(SupervisorConfig(OneForOneStrategy(List(classOf[Exception]), 1, 100),
                  Supervise(Actor.actorOf[RootEndpoint], Permanent) ::
                  Supervise(Actor.actorOf[FirstService], Permanent) :: Nil))
  EventHandler.notify(Info(this,("starting kluis service")))
  factory.newInstance.start
}