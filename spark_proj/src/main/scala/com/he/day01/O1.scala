package com.he.day01


import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object O1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("n1").setMaster("local[1]")
    //    val conf = new SparkConf().setAppName("n1").setMaster("spark://keduo:7077")

    val sc = new SparkContext(conf)
    var a = 1
    val value: RDD[Int] = sc.makeRDD(Array(1,2))
    value.map(x => {
      a = a+1
      x+1
    }).map(x => {
      print("x->"+x)
      println("a->"+a)
    }).count()

    println(a)
//        sc.textFile("D:\\a.txt").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).foreach(println(_))
//        sc.textFile("hdfs://192.168.200.200:9000/maiyue/splunk/20180528/dc09c9fca9adda18314e3d05a1dc52f3_1528358400_1528367949_32_0.csv").foreach(println(_))
    //    val makeRdd: RDD[Int] = sc.makeRDD(1 to 10, 16)
    //    println(makeRdd.getNumPartitions)

    //    val r1 = sc.makeRDD(1 to 10)
    //    r1.filter(_%2==0).map(_*10).foreach(println(_))

//    val nameList = List(("zs", 10), ("zs", 30), ("ls", 50))
    //    val r2: RDD[(String, Iterable[Int])] = sc.makeRDD(nameList).groupByKey()
    //    r2.map(x=>{
    //      var sum:Int=0
    //      for (v <- x._2){
    //        sum += v
    //      }
    //      x._1+" "+sum
    //    }).foreach(println(_))

//    var r3 = sc.makeRDD(nameList)
//    r3.reduceByKey(_ + _).foreach(println(_))

//    var dataA=List(("zs",30),("zs",50),("ls",30))
//    var dataB=List(("zs",111),("zs",2222),("ls",3333))
//    var dataC=List(("zs",111),("zs",2222),("ls",3333))
//
//    val rddA: RDD[(String, Int)] = sc.makeRDD(dataA)
//    val rddB: RDD[(String, Int)] = sc.makeRDD(dataB)
//    val rddC: RDD[(String, Int)] = sc.makeRDD(dataC)
//
//    println(rddA.union(rddB).union(rddC))

    sc.stop()
  }
}
