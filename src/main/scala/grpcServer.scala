package com.cxy.demo
import io.grpc.{ServerBuilder,ServerServiceDefinition}
import io.grpc.netty.NettyServerBuilder

trait gRPCServer {
  def runServer(service: ServerServiceDefinition): Unit = {
    val server = NettyServerBuilder
      .forPort(50051)
      .addService(service)
      .build
      .start

    println("listen: port:"+50051)
    // make sure our server is stopped when jvm is shut down
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run(): Unit = server.shutdown()
    })

    server.awaitTermination()
  }

}