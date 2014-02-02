package com.github.daiksy.dmm4s.util

import java.text.SimpleDateFormat
import org.specs2.mutable.Specification
import com.github.daiksy.dmm4s.util.DateUtil._

class DateUtilSpec extends Specification {

  val sdf = new SimpleDateFormat("yyyyMMdd")
  val apr_1st = sdf.parse("20130401")
  val apr_2nd = sdf.parse("20130402")
  val apr_3rd = sdf.parse("20130403")

  "DateUtil" should {
    "toDate" in {
      "20130401".toDate("yyyyMMdd") must beEqualTo(sdf.parse("20130401"))
    }
    "toDate parse Error" in {
      "hoge".toDate("yyyyMMdd") must throwA[java.text.ParseException]
    }
    "toDate format Error" in {
      "20130401".toDate("hoge") must throwA[IllegalArgumentException]
    }

    "toString" in {
      apr_1st.toString("yyyyMMdd") must beEqualTo("20130401")
    }
    "toString format error" in {
      apr_1st.toString("hoge") must throwA[IllegalArgumentException]
    }

    "Compare > " in {
      (apr_1st > apr_2nd) must beEqualTo(false)
      (apr_1st > apr_1st) must beEqualTo(false)
      (apr_2nd > apr_1st) must beEqualTo(true)
    }

    "Compare >= " in {
      (apr_1st >= apr_2nd) must beEqualTo(false)
      (apr_2nd >= apr_2nd) must beEqualTo(true)
      (apr_2nd >= apr_1st) must beEqualTo(true)
    }

    "Compare < " in {
      (apr_2nd < apr_1st) must beEqualTo(false)
      (apr_2nd < apr_2nd) must beEqualTo(false)
      (apr_1st < apr_2nd) must beEqualTo(true)
    }
    "Compare <= " in {
      (apr_2nd <= apr_1st) must beEqualTo(false)
      (apr_1st <= apr_1st) must beEqualTo(true)
      (apr_1st <= apr_2nd) must beEqualTo(true)
    }

    "Compare Range 'true' " in {
      apr_1st < apr_2nd &&
        apr_2nd < apr_3rd must beEqualTo(true)

      apr_1st <= apr_2nd &&
        apr_2nd <= apr_3rd must beEqualTo(true)

      apr_3rd > apr_2nd &&
        apr_2nd > apr_1st must beEqualTo(true)

      apr_3rd >= apr_2nd &&
        apr_2nd >= apr_1st must beEqualTo(true)
    }
  }

}