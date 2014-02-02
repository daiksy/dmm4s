package com.github.daiksy.dmm4s.http

import org.apache.http.HttpResponse
import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

class AbstractHttpSpec extends Specification with Mockito {

  "httpAbstract#get" should {

    "get success" in {
      val testApi = new AbstractHttp with AbstractHttpMock {
        protected override val statusCode: Int = 200
        protected override val responseBodyAsString: String = ""
        class TestHandler extends AbstractResponseHandler[Int] {
          override def handle(response: HttpResponse) = {
            response.getStatusLine.getStatusCode
          }
        }

        val getStatusCode = {
          val handler = new TestHandler
          get("http://www.example.com")(handler) match {
            case Right(statusCode) => statusCode
            case _ => 0
          }
        }
      }

      testApi.getStatusCode must beEqualTo(200)
    }

    "get fail" in {
      val testApi = new AbstractHttp {
        class TestHandler extends AbstractResponseHandler[String] {
          override def handle(response: HttpResponse) = {
            throw new Exception
          }
        }

        val getResult = {
          val handler = new TestHandler
          get("http://www.example.com")(handler)
        }
      }

      testApi.getResult match {
        case Left(e) => e.isInstanceOf[Throwable] must beTrue
        case _ => 1 === 2 // こっちの結果が返ってきたらわざとテストこかす
      }
    }
  }

}