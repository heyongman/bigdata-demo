package com.he

import java.io.{File, FileWriter}

import scala.io.Source

object MyObject03 {
  def main(args: Array[String]): Unit = {
    val file = new File("d:a.txt")
    val writer = new FileWriter(file,true)

    writer.write("hello scala\n")
    writer.close()

//    Source.fromFile("d:a.txt").foreach(print(_))
//    Source.fromURL("https://www.baidu.com/","utf-8").foreach(print(_))


//    異常處理
    try{
      println(1 / 0)
    } catch {
      case ex:NullPointerException=>println(ex.getMessage)
      case ex:Exception=>println(ex.getMessage)
      case _=>println("不知道是什麽異常")
    } finally {
      println("不管怎么回事，我都会出现!")
    }


    var m = 3
    m match {
      case 1 => println("1")
      case _ => println(null)
    }

    val array = Array("b","a","c")
    array match {
      case Array(_,_,"c")=>println("匹配到了")
      case _=>println("未匹配到！")
    }




  }


}
