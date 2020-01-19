package com.cxy.demo

import com.cxy.demo.hello.{Greeting, HelloWorldGrpc, ToBeGreeted}

import scala.concurrent._

object HelloServer extends gRPCServer {

  class HelloService extends HelloWorldGrpc.HelloWorld {
    override def sayHello(request: ToBeGreeted): Future[Greeting] = {

      println("server recieve person:"+request.person.get.name+" msg:"+request.msg)
      val greeter = request.person match {
        case Some(p) => p.name
        case None => "friendo"
      }
      Future.successful(Greeting(message = s"Hello $greeter, ${request.msg}"))
    }
  }

  def main(args: Array[String]): Unit = {
    val service = HelloWorldGrpc.bindService(new HelloService,ExecutionContext.global)
    runServer(service)
  }
}