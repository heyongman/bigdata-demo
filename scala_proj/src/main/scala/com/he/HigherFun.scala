package com.he

import scala.annotation.tailrec

object HigherFun {
  def main(args: Array[String]): Unit = {
//    在传递函数时，如果为匿名函数在函数参数的位置上，可以不用写上类型
//      m1(name=>println(name))
//    几种写法
      m0(_*_)
      m0((x,y)=>x*y)
      m0((x:Int,y:Int)=>x*y)
      val unit = m1(_+"22")
//      println(unit)
//    柯里化函数
    val vm2 = m2("aa") _
    val vm_1: String => String = m2("cc")
//    println(vm2("bb"))
//    println(vm_1("dd"))

    val vm3 = m3(_:Int, 3:Int, _:Int)
//    println(vm3(1, 2))

//    println(m4(5))

//    println(m5(5))

//    (1 to 10).map("*"* _).foreach(println(_))
    val strings = for (x <- 1 to 10) yield "*" * x
//    strings.foreach(println(_))
//    List("asda asdsa","aw f").flatMap(x=>x.split(" ")).foreach(println(_))
//    List("asda asdsa","aw f").map(x=>x.split(" ")).foreach(println(_))

//    (1 to 100).filter(x=>x%4==0).foreach(println(_))

//    println((1 to 100).reduce(_ + _))
//    "A type parameter for the binary operator, a supertype of `A`.".split(" ").sortWith(_.length>_.length).foreach(println(_))

//    List("hello java","hello scala").flatMap(_.split(" ")).map((_,1)).foreach(println(_))  //(hello,1)
    val i = List("hello java","hello scala").flatMap(_.split(" ")).map((_,1)).map(_._2).reduce(_+_)
    println(i)

  }


//  多个参数简写调用
  def m0(x:(Int,Int)=>Int): Unit ={
//    println(x(5,10))
  }

  def m1(x:String=>String)={
    x("111")
  }

//  柯里化函数
  def m2(s1:String)(s2:String)=s1+s2

//  偏函数 所有参数指定才会执行
  def m3(x:Int,y:Int,z:Int):Int={
    x+y+z
  }

//  递归函数
  def m4(x:Int): BigInt ={
    if (x<=1){
      1
    } else {
      x*m4(x-1)
    }
  }

//  嵌套函数
  def m5(x:Int):BigInt={
    @tailrec //尾递归
    def m6(a:Int,b:Int):BigInt={
      if (a<=0){
        b
      } else {
        m6(a-1,a*b)
      }
    }
    m6(x,1)
  }


}