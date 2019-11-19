package com.he

import org.apache.spark.sql.Row

import scala.util.matching.Regex

object Format {
  def formatRca(line: String): Row ={
    //          s为一行消息
    val re = new Regex("""^\{?"\w+":"?(.*?)"?\}?$""")
    val s = line.split(",")

    val re(cstaccfc) = s(0)
    val re(customerid) = s(1)
    val re(cardno) = s(2)
    val re(opdt) = s(3)
    val re(sumfare) = s(4)
    val re(oddfare) = s(5)
    val re(opfare) = s(8)
    val re(acccode) = s(10)
    val re(dscrp) = s(11)
    val re(cardsn) = s(12)
    val re(termid) = s(14)
    Row(cstaccfc, customerid, cardno, opdt, sumfare, oddfare, opfare, acccode,dscrp, cardsn, termid)

  }

}
