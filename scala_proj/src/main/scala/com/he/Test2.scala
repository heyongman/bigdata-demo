package com.he

object Test2 extends App {
  private val tuple = Tuple2("a", 1)
  //  println(tuple._1)

  def ++++(x: Int): Option[String] = {
    if (x > 10) {
      None
    } else {
      Some("小于")
    }
  }

  private val option: Option[String] = ++++(1)
  //  println(option)

  //  private val myClass = new MyClass
  //  myClass.fun1("asd")
  //  println(MyClass("sss"))
  private val myClass = new MyClass("asd",22)
//  myClass.fun2()

  private val dd = new MyClass("dd", 11)
//  dd.fun2()

  private val asfas0 = MyClass("asfas00")
  asfas0.fun2()

}
