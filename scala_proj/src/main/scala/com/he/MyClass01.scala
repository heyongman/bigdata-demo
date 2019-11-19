package com.he

class MyClass01 extends MyClass02 {
  def fun1(): Unit ={
    println("-----")
  }

  override def fun2(name: String): String = {
    "hello:"+name
  }
}
