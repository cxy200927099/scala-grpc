package com.cxy.demo

import novelRecommand.userModel.{UpdateUserModelGrpc, UserRawData, UserRawDataRequest, UserRawDataResponse}

import scala.concurrent.Future


object UserModelClient {

  def main(args: Array[String]): Unit = {

    //build connection channel
    val channel = io.grpc.ManagedChannelBuilder
      .forAddress("LocalHost",50051)
      .usePlaintext(true)
      .build()

    println("connect to 127.0.0.1:50051")

    //construct requestHelloService
    val seq = Seq()
    for ( i <- 0 until 10){
      val str: String = "173android_book1577376676998pub_guid=a6e448fcba5af4e700a64ae738a16e79,pub_reliableGuid=1,pub_oldguid=393151a4a19c80e2f2b93c0c1876f5a0,pub_mac=bad25827907cec15ab2a3e223debbcc,pub_imei=3a1bfe47709abaac24896dfbf58db758,pub_oaid=13e13e2f5d541fdb547e1f7ccd9c3833fa31fd5f7bd9073d93148b598a570673,pub_vaid=89519387acd62d50465ad71ef9319dd97789715fba03cc4c429f8366c6e812f0,pub_aaid=ba714db11c30ed964eefdf30ae6ff0480cda08f03684ca6e0d5eecd55c16c74f,pub_os=9,pub_sdkversion=42,pub_phonetype=V1924A,pub_longitude=,pub_dimension=,pub_channelid=0x10810179,pub_ip=2409%3A880c%3A8384%3Ade31%3A52cb%3Ac667%3Ab0fd%3A32be,pub_session_id=1577375676629,pub_pageid=,pub_network=4,pub_sv=6.13.2.6570,sex_type=m,peerid=cff5323624ed20e427f050e0799252f7,gcid=,is_onshelf=0,url=,pluginVersion=31100,partner_id=2,is_recommend=0,attribute1=book_content_click,partner_book_id=265336,partner_book_name=%E5%BA%86%E4%BD%99%E5%B9%B4,from=sl_home_recommend_6_%E7%95%85%E9%94%80%E7%B2%BE%E9%80%89,style=style_3,position=0,is_local=0,shoulei_pub_apilv=28,shoulei_pub_deviceid=89643c5305f7445cb7c08ca5a707f1bf,shoulei_pub_tinkerid=6_13_2_6570_793ddc29fb,shoulei_pub_uuid=f7527cfd3aa0146edc1b2103b3de55c1,shoulei_pub_userid=b2196ede3d222e1739c4d6c72687638c,timestamp=1577376678,client_ip=2409:896c:140:38e:31a1:760c:2fbb:582b"
      val str2: String = "cxy-%d-%s".format(i, str)
      seq :+ str2
    }
    val userRawData = UserRawData().withUserData(seq)
    val mapData = Map("testKey1"-> userRawData)
    val userRawDataRequest = UserRawDataRequest().withUsersAttr(mapData)
//    val resp = UpdateUserModelGrpc.stub(channel).updateUserModel(userRawDataRequest)

    //async call
    val asyncStub: UpdateUserModelGrpc.UpdateUserModelStub = UpdateUserModelGrpc.stub(channel)
    println("send mesage!")
    val futResponse: Future[UserRawDataResponse] = asyncStub.updateUserModel(userRawDataRequest)

    import scala.concurrent.ExecutionContext.Implicits.global
    futResponse.foreach(resp => println("resp code:"+resp.code+" errMsg:"+resp.errMsg))

    println("sleep 2s")
    Thread.sleep(2000)
    println("sleep 2s done")

//    //sync call
    val syncStub: UpdateUserModelGrpc.UpdateUserModelBlockingClient = UpdateUserModelGrpc.blockingStub(channel)
    val response: UserRawDataResponse = syncStub.updateUserModel(userRawDataRequest)
    println(s"code:${response.code} errMsg:${response.errMsg}")
  }

}
