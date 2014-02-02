package com.github.daiksy.dmm4s.util

import org.specs2.mutable.Specification
import com.github.daiksy.dmm4s.util.UrlEncodeUtil._

class UrlEncodeUtilSpec extends Specification {

  "UrlEncodeUtil" should {
    "encode" in {
      "ディーエムエム".encode must beEqualTo("%A5%C7%A5%A3%A1%BC%A5%A8%A5%E0%A5%A8%A5%E0")
    }
    "decode" in {
      "%A5%C7%A5%A3%A1%BC%A5%A8%A5%E0%A5%A8%A5%E0".decode must beEqualTo("ディーエムエム")
    }
  }
}