package com.he.day01

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils

object O4 {
  def main(args: Array[String]): Unit = {

    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)

    //使用sparkStreaming至少设置2核
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("hello_ds")
    val streamingContext = new StreamingContext(conf, Seconds(1))
    //如果使用updateStateByKey的话，就必须指定checkPoint
    streamingContext.checkpoint("hdfs://keduo:9000/spark_checkPoint")

    val (zookeeper, groupId, topics) = ("keduo:2181", "spark-kafka", Map("sparkTopic" -> 1))

    val value = KafkaUtils.createStream(streamingContext, zookeeper, groupId, topics)

    value.map(_._2).flatMap(_.split(" ")).map((_,1)).updateStateByKey(updateFun).print()

    streamingContext.start()
    streamingContext.awaitTermination()

    /**
      * 根据相同的key进入嘎方法
      * @param currValues 之前已经进入到该序列的所有的值
      * @param prevValueState 表示当前进入的值
      * @return
      */
    def updateFun(currValues: Seq[Int], prevValueState: Option[Int]) = {
      //通过Spark内部的reduceByKey按key规约，然后这里传入某key当前批次的Seq/List,再计算当前批次的总和
      val currentCount = currValues.sum
      //拿到options中的值,使用getOrElse的目的是万一传过来的值，不是一个int类型的，那么Option会返回Nono，但是Nono无法也int进行相加，就
      //会出现异常，采用getOrElse就是为了解决这个问题
      val previousCount = prevValueState.getOrElse(0)
      // 已累加的值
      Some(currentCount + previousCount)
      // 返回累加后的结果，是一个Option[Int]类型
    }


  }
}
