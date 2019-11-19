package com.he


import scala.actors.Actor
import scala.util.Random
import scala.util.matching.Regex

object Test2 {
  def main(args: Array[String]): Unit = {
    val re = new Regex("""^\{?"(\w+)":"?(.*?)"?\}?$""")
    val l = "\"OPDT\":\"2018/11/8 18:24:00\""
    val re(k, v) = l
    println(k,v)


    println(Random.nextInt(2))
    Actor
  }

}
