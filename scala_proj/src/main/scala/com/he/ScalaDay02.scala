package com.he

import scala.collection.mutable.ArrayBuffer

object ScalaDay02 extends App {
//  println("hello")
  private val arr0: Array[String] = new Array[String](10)
//  arr0.foreach(println(_))
//  arr0.foreach(x => println(x))
  arr0(0)="ssdsdf"
  for (x <- arr0.indices) {
    arr0(x)=x+""
  }

  //  arr0.foreach(println(_))
    private val arr1: Array[Int] = Array(1,2,3)
    private val arr2: Array[Int] = Array(4,5,6)
  import Array._
//  拼接数组
  private val concatArr: Array[Int] = concat(arr1,arr2)
//  concatArr.foreach(println)
  private val arrayTake: Array[Int] = concatArr.take(2)
  private val arrayRightTake: Array[Int] = concatArr.takeRight(3)
//  arrayTake.foreach(println(_))
//  arrayRightTake.foreach(println(_))
  private val ints: Array[Int] = range(220,221)
//  ints.foreach(println(_))
//  转换为字符串，sep：分隔符
  private val str: String = concatArr.mkString(" ")
  private val str1: String = concatArr.mkString("<","/",">")
//  println(str1)
  private val abcdef = "abcdef"
  private val head: Char = abcdef.head
  private val last: Char = abcdef.last
  private val dropStr: String = abcdef.drop(2)
  private val str2: String = abcdef.substring(2,4)
  private val str3: String = abcdef.substring(1)
//  println(head)
//  println(last)
//  println(dropStr)
//  println(str3)
  private val arrayBuffer1 = new ArrayBuffer[String]()
  private val arrayBuffer2: ArrayBuffer[String] = ArrayBuffer[String]()
  private val arr3 = new Array[String](5)
  arr3(0)="al"
  arrayBuffer1+="fdds"
  arrayBuffer2+="sf"

  arrayBuffer1++=arrayBuffer2
  val arr4= arr3++arrayBuffer1
//  不可变数组转可变数组
  arr3.toBuffer

//  arrayBuffer1++=arr3

//  println(arr4.mkString)
//  println(arrayBuffer1.mkString)

  arrayBuffer1.insert(2,"111")
//  println(arrayBuffer1)
  arrayBuffer1.remove(2)
//  println(arrayBuffer1)

  private val intArr = ArrayBuffer(1,2,4,3)
//  求和
  private val sum: Int = intArr.sum
  println(sum)
//  排序
  private val ints1: ArrayBuffer[Int] = intArr.sortWith(_>_)
  println(ints1)
}
