package com.he

object MyObject01 {
  def main(args: Array[String]): Unit = {
  val myClass0 = new MyClass01
  val zhazhaqian: String = myClass0.fun2("zhazhaqian")
//  println(zhazhaqian)

  val class0 = new MyClass03
//  class0.m1()

  def m1(age:Int): Int ={
    println("这是m1")
    age
  }

  var m2 = (name:String)=>println("hello "+name)
//  m2("qbq")

//  --------------------------------------------------------
//  一 作为变量的函数
  def m3(name:String): String ={
    name
  }
  var vm3 = m3 _
  println(vm3("zzh"))

//  二 匿名函数
  var vm4=(name:String)=>println("hello:"+name)
  vm4("zzh")

//  三 参数为函数的函数
  def m5(x:Int=>Unit): Unit ={
    println("传过来的是："+x)
    x(222)
  }
  var vm5 = (age:Int)=>println("高阶函数传过来的值："+age)
  m5(vm5)
  m5(m1)

//  四 返回一个函数的函数
  def m6(name:String)={
    println("-----")
    (name:String)=>println("hello:"+name)
      m1(1)
  }
  var zzh= m6("zzh")//zzh为一个函数
  println(zzh)
  zzh("zzq")
  m6("asd")
  }
}
