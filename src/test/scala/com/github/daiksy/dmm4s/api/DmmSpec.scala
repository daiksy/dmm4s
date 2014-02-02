package com.github.daiksy.dmm4s.api

import org.specs2.mutable.Specification
import com.github.daiksy.dmm4s.http.{ AbstractResponseHandler, AbstractHttpMock }
import org.apache.http.HttpResponse

class DmmSpec extends Specification {

  class Tester extends Dmm {
    override protected val service: String = ""
    override protected val site: Site.Type = Site.DmmDotCom
    override protected val _affiliateId: String = "__affiliate_id__"
    override protected val _apiId: String = "__api_id__"

    protected override def timestamp: String = "2014-01-01 00:00:00"
  }

  "URL Build" should {
    "no keyword" in {
      val client = new Tester with AbstractHttpMock {
        protected override val statusCode: Int = 200
        protected override val responseBodyAsString: String = ""
        class TestHandler extends AbstractResponseHandler[Int] {
          override def handle(response: HttpResponse) = {
            response.getStatusLine.getStatusCode
          }
        }
      }
      client.itemList()
      client.requestUrl must beEqualTo("http://affiliate-api.dmm.com/?api_id=__api_id__&affiliate_id=__affiliate_id__&" +
        "operation=ItemList&version=2.00&site=DMM.com&timestamp=2014-01-01%2B00%253A00%253A00&" +
        "keyword=&offset=1&hits=20&sort=rank")
    }

    "exists keyword" in {
      val client = new Tester with AbstractHttpMock {
        protected override val statusCode: Int = 200
        protected override val responseBodyAsString: String = ""
        class TestHandler extends AbstractResponseHandler[Int] {
          override def handle(response: HttpResponse) = {
            response.getStatusLine.getStatusCode
          }
        }
      }
      client.itemList(keyword = "ディーエムエム")
      client.requestUrl must beEqualTo("http://affiliate-api.dmm.com/?api_id=__api_id__&affiliate_id=__affiliate_id__&" +
        "operation=ItemList&version=2.00&site=DMM.com&timestamp=2014-01-01%2B00%253A00%253A00&" +
        "keyword=%25A5%25C7%25A5%25A3%25A1%25BC%25A5%25A8%25A5%25E0%25A5%25A8%25A5%25E0&offset=1&hits=20&sort=rank")
    }
  }

}
