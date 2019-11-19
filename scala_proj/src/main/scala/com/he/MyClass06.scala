package com.he

import scala.actors.Actor

class MyClass06 extends Actor{
  override def act(): Unit = {
    while (true){
      receive{
        case name=>println(name)
      }
    }
  }
}
object MyObject06{

  def main(args: Array[String]): Unit = {
//    val class0 = new MyClass06
//    class0.start()
//    class0!"hello"


    val reg = "ad+".r
    var str = "adassv asdas"
    println((reg findAllIn str).mkString)

  }
}