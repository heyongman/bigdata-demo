package com.he

class Person

class SuperMan {
  def fly(): Unit ={
    println("fly")
  }
}


object MyObject05 {
  implicit def person2SuperMan(man:Person):SuperMan=new SuperMan()

  def main(args: Array[String]): Unit = {
    val person = new Person
    person.fly()
  }
}
//隐式参数
class Man{
  def m2(): Unit ={
    println("man.m2")
  }
}

object Man{
  import MyClass05._
  implicit val man = new Man

  def main(args: Array[String]): Unit = {
    val str = "dsf5sdfasDsS".fun1()
    println(str)

    val str1 = m1("aaa") _
    println(str1)

  }

  def m1(name:String)(implicit man: Man):String={
    println(name)
    man.m2()
      "hello"
  }

}

