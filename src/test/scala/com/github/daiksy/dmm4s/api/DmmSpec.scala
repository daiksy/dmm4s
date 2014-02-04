package com.github.daiksy.dmm4s.api

import org.specs2.mutable.Specification

class DmmSpec extends Specification {

  class Tester extends Dmm {
    override protected val service: String = ""
    override protected val site: Site.Type = Site.DmmDotCom
    override protected val _affiliateId: String = "__affiliated_id___"
    override protected val _apiId: String = "__api__id"
  }

  "URL Build" should {
    "ettp access" in {
      val client = new Tester
      client.itemList(keyword = "hoge") !== ""
    }
  }

}
