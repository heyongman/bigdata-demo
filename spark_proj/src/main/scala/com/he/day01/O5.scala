package com.he.day01

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils

object O5 {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)

    //使用sparkStreaming至少设置2核
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("hello_ds")

    val streamingContext = new StreamingContext(conf, Seconds(5))

    val (zookeeper,groupId,topics)=("keduo:2181","spark-kafka",Map("sparkTopic"->1))

    val value = KafkaUtils.createStream(streamingContext,zookeeper,groupId,topics,StorageLevel.MEMORY_ONLY)

    value.map(_._2).flatMap(_.split(" ")).map((_,1)).reduceByKeyAndWindow((x:Int,y:Int)=>x+y,Seconds(15),Seconds(10)).print()

    streamingContext.start()
    streamingContext.awaitTermination()


  }
}
