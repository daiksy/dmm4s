package com.github.daiksy.dmm4s.util

import java.net.URLEncoder
import java.net.URLDecoder

object UrlEncodeUtil {

  lazy val urlEncodeChar = "euc-jp"

  implicit class EncoderAndDecoder(self: String) {
    def urlEncode: String = {
      URLEncoder.encode(self, urlEncodeChar).replace("+", "%20")
    }
    def urlDecode: String = {
      URLDecoder.decode(self, urlEncodeChar)
    }
    def utf8ToEucJp: String = {
      new String(self.getBytes("euc-jp"), "euc-jp")
    }
    def eucJpToUtf8: String = {
      new String(self.getBytes("utf-8"), "utf-8")
    }
  }

}
