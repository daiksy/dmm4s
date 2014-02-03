package com.github.daiksy.dmm4s.util

import org.specs2.mutable.Specification
import com.github.daiksy.dmm4s.util.UrlEncodeUtil._

class UrlEncodeUtilSpec extends Specification {

  "UrlEncodeUtil" should {
    "encode" in {
      "ディーエムエム".urlEncode must beEqualTo("%A5%C7%A5%A3%A1%BC%A5%A8%A5%E0%A5%A8%A5%E0")
    }
    "decode" in {
      "%A5%C7%A5%A3%A1%BC%A5%A8%A5%E0%A5%A8%A5%E0".urlDecode must beEqualTo("ディーエムエム")
    }
    "date encode" in {
      "2014-02-04 00:00:00".urlEncode must beEqualTo("2014-02-04%2000%3A00%3A00")
    }
    "date decode" in {
      "2014-02-04%2000%3A00%3A00".urlDecode must beEqualTo("2014-02-04 00:00:00")
    }
  }
}