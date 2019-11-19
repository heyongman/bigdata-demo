package com.he.day01

import org.apache.spark.{SparkConf, SparkContext}


object GetData {


  def main(args: Array[String]): Unit = {

    println("sdfd")
    val conf = new SparkConf().setAppName("n1").setMaster("local[1]")
    //    val conf = new SparkConf().setAppName("n1").setMaster("spark://keduo:7077")

    val sc = new SparkContext(conf)

    //        sc.textFile("D:\\a.txt").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).foreach(println(_))
    sc.textFile("hdfs://192.168.200.200:9000/maiyue/splunk/20180528/dc09c9fca9adda18314e3d05a1dc52f3_1528358400_1528367949_32_0.csv").foreach(println(_))

    //    val sparkConf: SparkConf = new SparkConf().setAppName("get_data").setMaster("local[*]")
//    val sc = new SparkContext(sparkConf)
//    val hiveContext = new HiveContext(sc)

//    val sourceData = getDataFromHdfs()
//    sourceData.take(10).foreach(println(_))

    //    hiveContext.sql("CREATE TABLE IF NOT EXISTS school_data (ip string,product_id string,product_url string) ROW FORMAT DELIMITED FIELDS TERMINATED BY ','")
    //    import hiveContext.implicits._
    //    存储到hive
    //    sourceData.toDF.write.mode(SaveMode.Append).saveAsTable("school_data")
//        sourceData.saveAsTextFile("hdfs://192.168.200.200:9000/maiyue/school_data")

  }

  /**
    * 从hdfs获取原始数据
    */
  def getDataFromHdfs() = {
//    sc.textFile("hdfs://192.168.200.200:9000/maiyue/splunk/20180528/dc09c9fca9adda18314e3d05a1dc52f3_1528358400_1528367949_32_0.csv").map(data => {
//      val dataArr = data.split(",")
//      if (dataArr.length == 3) {
//        (dataArr(0), dataArr(1), dataArr(2))
//      } else {
//        None
//      }
//    })
  }
}
