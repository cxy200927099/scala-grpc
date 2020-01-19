package com.cxy.demo
import novelRecommand.userModel.{UpdateUserModelGrpc, UserRawData, UserRawDataRequest, UserRawDataResponse}

import scala.concurrent.{ExecutionContext, Future}


object UserModelServer extends gRPCServer {

  class UpdateUserService extends UpdateUserModelGrpc.UpdateUserModel {

    override def updateUserModel(request: UserRawDataRequest): Future[UserRawDataResponse] = {

      val recvMap = request.usersAttr
      recvMap.foreach((data) => printRequest(data._1, data._2))
      Future.successful(UserRawDataResponse(code=0, errMsg = ""))
    }

    def printRequest(key:String, userData:UserRawData): Unit ={
      println("server recieve key:"+key+" msg:"+userData.userData.size)
    }

  }

  def main(args: Array[String]): Unit = {
    val service = UpdateUserModelGrpc.bindService(new UpdateUserService,ExecutionContext.global)
    runServer(service)
  }

}
