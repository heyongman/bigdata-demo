package com.he


import org.apache.spark.sql.Row
import org.apache.spark.{SparkConf, SparkContext}

object Test1 {
  def main(args: Array[String]): Unit = {
//    Class.forName("oracle.jdbc.OracleDriver")
//    val url = "jdbc:mysql://172.20.14.198/gui_electric?autoReconnect=true&failOverReadOnly=false"
//    val url1 = "jdbc:oracle:thin:@172.20.14.198:1521:orcl"
//    val con = DriverManager.getConnection(url1, "guidian", "admin123")
//    print(con)
//    val conf = new SparkConf().setAppName("Test1").setMaster("local[1]")
//    val sc = new SparkContext(conf)
//    val srcRDD = sc.makeRDD(Array("a","b","c"))
//    val value = srcRDD.map((_,1)).reduceByKey(_+_)
//    val aa: Unit = value.foreachPartition(it => {
////      it.foreach(println)
////      ConUtil.func(it)
//    })
//    println(aa)
//    value.foreachPartition(ConUtil.func)

    println(Row(1, 2).get(0))
    println(",?"*2)
    val len = 2
    val sql = s"insert into values(?"+",?"*len+")"
    println(sql)
    for (i <- 0 until len){
      println((i + 2, i))
    }


  }

}
