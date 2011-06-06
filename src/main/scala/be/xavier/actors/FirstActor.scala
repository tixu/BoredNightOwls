package be.xavier.actors

import akka.actor._
import akka.http._


class FirstActor extends Actor {
 
  
  def receive = 
  {
    case get:Get => get OK "default : it works"
    case other:RequestMethod => other NotAllowed "unsupported request"
  }
}