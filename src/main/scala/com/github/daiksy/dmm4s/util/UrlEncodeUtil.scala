package com.github.daiksy.dmm4s.util

import java.net.URLEncoder
import java.net.URLDecoder

object UrlEncodeUtil {

  lazy val charaSet = "euc-jp"

  implicit class EncoderAndDecoder(self: String) {
    def encode: String = {
      URLEncoder.encode(self, charaSet)
    }
    def decode: String = {
      URLDecoder.decode(self, charaSet)
    }
  }

}
