import akka.actor.{Actor, ActorSystem, Props}

object HelloAkkaWorld extends App {
  class SimpleActor extends Actor {
    def receive ={
      case str:String => println(s"String: $str")
      case itr:Int =>println(s"Int: $itr")

    }
  }
  val system = ActorSystem("System")
  val actor = system.actorOf(Props[SimpleActor],"SimpleActor")

  println("Before messages")
  actor ! "Hello World"
  actor ! 34
  println("After messages")

  system.terminate()
}
