import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object CountDownExample extends App {

  case class StartCount(n:Int, other:ActorRef)
  case class CountDown(n:Int)

  class CountDownActor extends Actor{
      def receive = {
        case StartCount(n,other) =>
          println(self+">>>>>>"+n)
          other ! CountDown(n-1)

        case CountDown(n) =>
          if (n>0){
            println(self+"-----"+n)
            sender ! CountDown(n-1)
          }else{
            context.system.terminate()
          }
      }
  }

  val system = ActorSystem("System")
  val  actor1 = system.actorOf(Props[CountDownActor],"CountDownActor1")
  val  actor2 = system.actorOf(Props[CountDownActor],"CountDownActor2")

  actor1 ! StartCount(10,actor2)

}
