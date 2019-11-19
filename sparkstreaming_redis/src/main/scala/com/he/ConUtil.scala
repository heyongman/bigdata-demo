package com.he

import java.sql.{Connection, DriverManager, PreparedStatement}
import java.util.{Properties, UUID}

import org.apache.spark.sql.{DataFrame, Row, SQLContext, SaveMode}


object ConUtil {

  def insertRowData(row: Row,db: String,tb: String): Unit = {
    var con: Connection = null
    var ps: PreparedStatement = null
    val  len = row.length
    try {
      val url = "jdbc:oracle:thin:@172.16.26.35:1521:orcl"
      Class.forName("oracle.jdbc.OracleDriver")
      con = DriverManager.getConnection(url, "guidian", "admin123")
      val sql = s"insert into $db.$tb values(?"+",?"*len+")"
      ps = con.prepareStatement(sql)
      ps.setString(1, UUID.randomUUID().toString)
      for (i <- 0 until len){
        ps.setString(i+2, row.getString(i))
      }
      ps.executeUpdate()

    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      if (ps != null) {
        ps.close()
      }
      if (con != null) {
        con.close()
      }
    }
  }

  /**
    * 将ds数据保存到oracle
    *
    * @param records
    */
  def insertBatchData(records: Iterator[Row],db: String,tb: String): Unit = {
    records.foreach(println)
    var con: Connection = null
    var ps: PreparedStatement = null
    val len = records.length
    try {
      val url = "jdbc:oracle:thin:@172.16.26.35:1521:orcl"
      Class.forName("oracle.jdbc.OracleDriver")
      con = DriverManager.getConnection(url, "guidian", "admin123")
      val sql = s"insert into $db.$tb values(?"+",?"*len+")"
      ps = con.prepareStatement(sql)
//      这里是个transform
      records.foreach(s => {
        ps.setString(1, UUID.randomUUID().toString)
        for (i <- 0 until len) {
          ps.setString(i + 2, s.getString(i))
        }
        ps.addBatch()
      })
      ps.executeBatch()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      if (ps != null) {
        ps.close()
      }
      if (con != null) {
        con.close()
      }
    }
  }

  /**
    * 读取数据
    *
    * @param sQLContext
    * @param db
    * @param tb
    * @return
    */
  def getData(sQLContext: SQLContext, db: String, tb: String) = {
    //数据库参数
    val url = s"jdbc:oracle:thin:@172.16.26.35:1521:orcl"
    val table = s"$db.$tb"
    val prop = new Properties()
    prop.setProperty("user", "guidian")
    prop.setProperty("password", "admin123")
    prop.setProperty("defaultRowPrefetch", "5000")
    prop.setProperty("driver", "oracle.jdbc.OracleDriver")
    println(s"从：${tb}获取数据")

    sQLContext.read.jdbc(url, table, prop)
  }

  /**
    * 保存到oracle
    *
    * @param df
    */
  def save(df: DataFrame, db: String, tb: String): Unit = {
    val url = "jdbc:oracle:thin:@172.16.26.35:1521:orcl"
    val table = s"$db.$tb"

    val prop = new Properties()
    prop.setProperty("user", "guidian")
    prop.setProperty("password", "admin123")
    prop.setProperty("driver", "oracle.jdbc.OracleDriver")

    df.write.mode(SaveMode.Append).jdbc(url, table, prop)
    //    df.write.mode(SaveMode.Overwrite).csv("D:\\maiyue\\h3c_nat")

  }

}
