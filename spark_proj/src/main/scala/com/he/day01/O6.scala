package com.he.day01

object O6 {
  def main(args: Array[String]): Unit = {
    def m1(age:Int): Int ={
//      println("这是m1")
      age
    }
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
