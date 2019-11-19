package com.he.day01

import org.apache.spark.sql.catalyst.expressions.Second
import org.apache.spark.sql.{DataFrame, Row, SQLContext}
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object O2 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("hello_df")
    val sc = new SparkContext(conf)
    val sQLContext = new SQLContext(sc)

    import sQLContext.implicits._
    //    val df: DataFrame = sQLContext.read.json("d://user.json")

    //    df.show()

    //    df.select("name").show()
    //    df.select(df("age")>16).show()

    //    df.filter(df("age")>16).show()

    //    反射推断模式

    //    将文件转换为df
    val userDF = sc.textFile("d://user.txt").map(_.split("\t")).map(x => User(x(0), x(1).toInt)).toDF()
    //    创建临时表
    userDF.registerTempTable("user")
    val userFrame = sQLContext.sql("select name from user where age > 20")
    userFrame.show()

    //    用户自定义模式
    //    val schemaString:String = "name age"
    //    import org.apache.spark.sql.types.{StringType, StructField, StructType}
    //    val structType = StructType(schemaString.split(" ").map(field=>StructField(field,StringType,true)))
    //    val rowRdd = sc.textFile("d://user.txt").map(_.split("\t")).map(x=>Row(x(0),x(1)))
    //    val frame = sQLContext.createDataFrame(rowRdd,structType)
    //    frame.registerTempTable("user")
    //    sQLContext.sql("select * from user").show()

    //    读取json
    //    val df = sQLContext.read.format("json").load("d://user.json")
    //    df.write.format("parquet").save("d://newFile.parquet")

    //    连接jdbc
    sQLContext.read.format("jdbc").options(Map("url" -> "jdbc:mysql://192.168.200.10/hive_class?user=root&password=1234", "dbtable" -> "DBS")).load().show()


    //-----sparkStreaming-----
    val streamingContext = new StreamingContext(conf, Seconds(1))
    val dStream: ReceiverInputDStream[String] = streamingContext.socketTextStream("keduo", 9999)
    dStream.flatMap(_.split(" ")).map(word => (word, 1)).reduceByKey(_ + _).print()
    //
    streamingContext.start()
    streamingContext.awaitTermination()
    //
    //    sc.stop()
  }


}

case class User(name: String, age: Int)
