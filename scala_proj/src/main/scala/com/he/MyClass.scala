package com.he


class MyClass(name: String) {
  //  var name:String=_
  //  var age:Int=
  private var xx:String=_
  def this(name: String,age:Int) {
    this(name)
  }

  def fun2(): Unit = {
    println("name:" + name)
  }

  def fun1(x: String): Unit = {
    println("hello " + x)
  }
}

/**
  * 伴生对象，访问私有的属性
  */
object MyClass{
  var sex:Int=_
  def apply(name: String): MyClass = new MyClass(name)

  private val myClass = new MyClass("5dsd")
  myClass.xx
//  var x:Unit
//  x=y=1
}
