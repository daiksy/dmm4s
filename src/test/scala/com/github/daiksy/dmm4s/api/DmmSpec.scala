package com.github.daiksy.dmm4s.api

import org.specs2.mutable.Specification

class DmmSpec extends Specification {

  class TesterDotComAll extends DmmDotComAll("__api__id", "__affiliated_id___") {
    override val timestamp = "2014-01-01 00:00:00"
  }
  class TesterR18 extends DmmR18All("__api__id", "__affiliated_id___") {
    override val timestamp = "2014-01-01 00:00:00"
  }

  "DmmDotComAll" should {
    "URL Build" in {
      val client = new TesterDotComAll {
        val httpRequest = buildHttpRequest(1, 1, SortPattern.Rank, "ディーエムエム")
      }
      client.httpRequest.getUrl.toString must beEqualTo("http://affiliate-api.dmm.com/?api_id=__api__id&affiliate_id=__affiliated_id___&" +
        "operation=ItemList&version=2.00&site=DMM.com&timestamp=2014-01-01+00%3A00%3A00&" +
        "keyword=%A5%C7%A5%A3%A1%BC%A5%A8%A5%E0%A5%A8%A5%E0&offset=1&hits=1&sort=rank")
    }
  }

  "DmmR18" should {
    "URL Build" in {
      val client = new TesterR18 {
        val httpRequest = buildHttpRequest(1, 1, SortPattern.Rank, "ディーエムエム")
      }
      client.httpRequest.getUrl.toString must beEqualTo("http://affiliate-api.dmm.com/?api_id=__api__id&affiliate_id=__affiliated_id___&" +
        "operation=ItemList&version=2.00&site=DMM.co.jp&timestamp=2014-01-01+00%3A00%3A00&" +
        "keyword=%A5%C7%A5%A3%A1%BC%A5%A8%A5%E0%A5%A8%A5%E0&offset=1&hits=1&sort=rank")
    }
  }

}
