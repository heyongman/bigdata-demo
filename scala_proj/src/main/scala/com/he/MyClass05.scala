package com.he

object MyClass05{
//  隐式转换 单独 的转换必须写在对象中
  implicit class MyStringClass(str:String){

    def fun1(): String ={
      println("123")
      str.toUpperCase
    }

  }
}