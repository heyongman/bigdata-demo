package com.he

import scala.collection.{SortedMap, mutable}
import scala.collection.mutable.ListBuffer

object Test1 extends App {
  private val list1 = List("aaa","bbb","ccc","ddd")
//  list1.foreach(println(_))
//  println(list1.head)
//  println(list1.tail)
//  println(list1.contains("aaa"))
//  println(list1.filter(x => x > "aaa"))

  private val listBuffer = ListBuffer(1,2,3,4)

  listBuffer+=1
  listBuffer.append(2)

//  println(listBuffer)

  private var map1 = Map(("k1", "v1"), ("k2", "v2"))
  map1+=(("k3","v3"))
  map1+=(("k3","v3"))

  private val str: String = map1.getOrElse("ds",null)
//  println(str)

  for (k <- map1.keySet){
//    println(k)
  }

  for (v <- map1.values){
//    println(v)
  }

  for ((k,v) <- map1){
    println(k+":"+v)
  }
  //key不能重复
  private val linkedHashMap = mutable.LinkedHashMap((1,"as"),(2,"sd"),(4,"55"),(3,"sd"),(2,"sd"))
  for ((k,v) <- linkedHashMap){
//    println(k+":"+v)
  }



}
