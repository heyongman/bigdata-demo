package com.he

object MyObject02 {
  def main(args: Array[String]): Unit = {
    fun1((name:String)=>println(name),"hym")

    println(fun2("a")(_:String))
    println(fun2("a")("b"))

  }

  def fun1(fun:String=>Unit,name:String)={
    fun(name)
  }

  def fun2(s1:String)(s2:String)=s1+s2


}
