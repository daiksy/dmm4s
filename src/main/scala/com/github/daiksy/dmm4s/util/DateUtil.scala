package com.github.daiksy.dmm4s.util

import java.util.Date
import java.text.SimpleDateFormat

object DateUtil {
  implicit class StringToDate(self: String) {
    /**
     * フォーマット指定された形式でDate型に変換する
     *
     * @param format 変換フォーマット
     * @return
     */
    def toDate(format: String): Date = {
      val sdf = new SimpleDateFormat(format)
      sdf.parse(self)
    }
  }

  implicit class DateToString(self: Date) {
    /**
     * フォーマット指定された形式でString型に変換する
     *
     * @param format
     */
    def toString(format: String): String = {
      val sdf = new SimpleDateFormat(format)
      sdf.format(self)
    }
  }

  implicit class DateCompare(self: Date) {
    def >(that: Date): Boolean = {
      self.after(that)
    }
    def >=(that: Date): Boolean = {
      self.after(that) || self == that
    }
    def <(that: Date): Boolean = {
      self.before(that)
    }
    def <=(that: Date): Boolean = {
      self.before(that) || self == that
    }
  }
}
