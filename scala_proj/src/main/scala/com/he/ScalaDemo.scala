package com.he

import scala.io.StdIn
import scala.util.{Random, Try}

object ScalaDemo {
  val a: Int = 1

  def main(args: Array[String]): Unit = {
    //println("Hello World!")
    //println(1.to(10))
    //    var b=if (1>2){
    //      "aaa"
    //    }else{
    //      "bbb"
    //    }
    //println(b)
    //    var i = 0
    //    while (i<10){
    //      println(i)
    //      i+=1
    //    }

    //    for (j <- 1 to 10 reverse;if j%2==0){
    //      println(j)
    //    }

    //    println()
    //    val breaks = new Breaks
    //     breaks.breakable(for (j <- 1 to (10,3)){
    //       if (j>5){
    //         breaks.break()
    //       }
    //       println(j)
    //     })
    //    println()
    //    var a = for (j <- 1 to (10,3)) yield j * 10
    //    println(a)
    //    println(a(1))

    def fun1(s:String*): Unit = {
      println("---->"+s)
    }

    def fun2(): Unit ={
      for (i <- 0 to 10 reverse){
        println(i)
      }
    }
    def fun3(): Unit ={
      val str = StdIn.readLine("请输入：")
      try {
        val int = str.toInt
        for (i <- 0 to int reverse){
          println(i)
        }
      }catch {
        case ex: Exception => println(ex) //捕获异常
      }

    }
//    var a = "1"
//    val triedInt = Try(a.toInt)
//    println(triedInt)
//    fun1("sdfsdf","aas")
//    fun2()
//    fun3()
//    val distinct = "HEllo".distinct.count(_.isUpper)
//    println(distinct)
//    val a = BigInt(2).pow(1024)
//    println(a)
      val bigInt = BigInt.probablePrime(100,Random)
    println(bigInt)
      val str = bigInt.toString(36)
    println(str)
  }
}
