package com.he.day01

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.slf4j.Logger

object O3 {
  /**
    * 使用sparkStreaming连接kafka
    * @param args
    */
  def main(args: Array[String]): Unit = {
    //使用sparkStreaming至少设置2核
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("hello_ds")

    val streamingContext = new StreamingContext(conf, Seconds(1))

    val (zookeeper,groupId,topics)=("keduo:2181","spark-kafka",Map("sparkTopic"->1))//1为分区数

    val value = KafkaUtils.createStream(streamingContext,zookeeper,groupId,topics)

    value.print()

    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
