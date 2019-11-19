package com.he

import java.util.UUID

import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}


object StreamingRedis {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("StreamingRedis").setMaster("yarn-cluster")
    val ssc = new StreamingContext(conf, Seconds(5))
    val sQLContext = new SQLContext(ssc.sparkContext)

    //    获取base_customers数据
    ConUtil.getData(sQLContext, "guidian", "base_customers").registerTempTable("base_customers")
    //    获取base_term数据
    ConUtil.getData(sQLContext,"guidian","base_term").registerTempTable("base_term")
    //    获取base_dept数据
    ConUtil.getData(sQLContext,"guidian","base_dept").registerTempTable("base_dept")

//    ssc.checkpoint("/Users/he/sparkstreaming")
    ssc.checkpoint("/tmp/sparkstreaming")
//        获取redis订阅消息
    val ds = ssc.receiverStream(new RedisReceiver("202.193.72.250", 6379, "Ecard_rec", 2000, "GuetRedis123"))
    val winDS = ds.window(Seconds(60),Seconds(10))

    //    格式化一个窗口的数据，得到所有value
    val kv = winDS.map(line => Format.formatRca(line))

    /**
      * 针对ds中每一个rdd进行操作
      */
    kv.foreachRDD(rdd => {
//      val sqLContext = SQLContextSingleton.getInstance(rdd.sparkContext)
//      一个窗口时间内的访问总人数
      if (!rdd.isEmpty()) {
        val t = rdd.first().getString(3).replace("T"," ")
        val count = rdd.count().toString
//        println(s"当前时间：$t 访问总人数：$count")
        ConUtil.insertRowData(Row(t,count),"guidian_lib","test2")
      }

      sQLContext.createDataFrame(rdd, streamStruct).registerTempTable("rec_cust_acc")
      sQLContext.udf.register("getUUID", () => UUID.randomUUID().toString.replaceAll("-", ""))

//      学生消费详细信息
      val stuCusInfoDF = sQLContext.sql(
        "select getUUID() as ID,rca.OPDT,rca.DSCRP,rca.OPFARE,bc.OUTID,bc.NAME,bt.TERMNAME,bd.DPTCODE,bd.DPTNAME " +
          "from rec_cust_acc rca " +
          "left join base_customers bc on bc.CUSTOMERID = rca.CUSTOMERID  " +
          "left join base_term bt on bt.TERMID = rca.TERMID " +
          "left join base_dept bd on bd.DPTCODE = bt.DPTCODE ")

//      各部门访问数据统计
      val countDptDF = sQLContext.sql(
        "select getUUID() as ID,bd.DPTCODE,bd.DPTNAME,count(bd.DPTCODE) C " +
          "from rec_cust_acc rca " +
          "left join base_customers bc on bc.CUSTOMERID = rca.CUSTOMERID  " +
          "left join base_term bt on bt.TERMID = rca.TERMID " +
          "left join base_dept bd on bd.DPTCODE = bt.DPTCODE " +
          "GROUP BY bd.DPTCODE,DPTNAME ")
//      stuCusInfoDF.show()
//      countDptDF.show()
//      保存
      ConUtil.save(stuCusInfoDF, "guidian_lib", "test")
      ConUtil.save(countDptDF,"guidian_lib", "test1")

    })


    //    计算累计数据
    //    val res = ds.flatMap(_.split(" ")).map((_, 1))
    //      .updateStateByKey(updateFun, new HashPartitioner(ssc.sparkContext.defaultParallelism), true)

    //    res.print()
    //    统计窗口时间内的pv

    //    kv.foreachRDD(rdd => {
    //      rdd.map(l => (l._1,1)).reduceByKey(_+_)
    //      rdd.foreachPartition(partRecords => {
    //        val records: Iterator[(String, String)] = partRecords
    //
    //        partRecords.foreach(println)
    ////        ConUtil.insertBatchData(partRecords)
    //      })
    //    })

    //    println(res)
    //    kv.print()
    //    res.saveAsTextFiles("/Users/he/sparkstreaming/res")


    ssc.start()
    ssc.awaitTermination()

  }

  //定义的输出的字段
  val streamStruct = StructType(
    Array(
      StructField("CSTACCFC",StringType),
      StructField("CUSTOMERID",StringType),
      StructField("CARDNO",StringType),
      StructField("OPDT",StringType),
      StructField("SUMFARE",StringType),
      StructField("ODDFARE",StringType),
      StructField("OPFARE",StringType),
      StructField("ACCCODE",StringType),
      StructField("DSCRP",StringType),
      StructField("CARDSN",StringType),
      StructField("TERMID",StringType)
    )
  )

  val updateFun: Iterator[(String, Seq[Int], Option[Int])] => Iterator[(String, Int)] = (it: Iterator[(String, Seq[Int], Option[Int])]) => {
    it.map(x => {
      (x._1, x._2.sum + x._3.getOrElse(0))
    })
  }
}

case class Stream(s:String)

object SQLContextSingleton {
  @transient  private var instance: SQLContext = _
  def getInstance(sparkContext: SparkContext): SQLContext = {
    if (instance == null) {
      instance = new SQLContext(sparkContext)
    }
    instance
  }
}