package com.he

//隐式转换
object MyObject04 {
  def main(args: Array[String]): Unit = {
    val ct = new CaseClass("zs",11)
    ct match {
      case CaseClass("zs",111)=>println("名字zs，年龄11")
      case CaseClass(name,age)=>println(name+":"+age)
    }

    val c1 = new Child1("zzz")
    fun(c1)


  }

  implicit def obj2Parent(obj:Object):Parent={
    if (obj.getClass==classOf[Child0]){
      val c1 = obj.asInstanceOf[Child0]
      new Parent(c1.name)
    } else if (obj.getClass==classOf[Child1]){
      val c2 = obj.asInstanceOf[Child1]
      new Parent(c2.name)
    } else null
  }

  def fun(p:Parent) ={
    println(p.name + "点击成功了")
  }

}

case class CaseClass(name:String,age:Int)

class Parent(val name:String)
class Child0(val name:String)
class Child1(val name:String)
class Child2(val name:String)