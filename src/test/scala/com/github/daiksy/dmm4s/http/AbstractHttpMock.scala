package com.github.daiksy.dmm4s.http

import org.apache.http.client.methods.HttpRequestBase
import org.apache.http._
import org.apache.http.params.HttpParams
import org.apache.http.entity.StringEntity
import java.util.Locale

/**
 * ユニットテストのために、Httpの実行結果をモックするためのクラス。
 *
 * ex)
 * {{{
 *   val hoge = new Hoge with AbstractHttpMock {
 *     protected override val statusCode: Int = 200
 *     protected override val responseBodyAsString: String = "hoge"
 *   }
 * }}}
 *
 * と書けば、AbstractHttpクラスを継承しているHogeの中で呼び出されるhttpExecuteの結果が上書きできる
 * (Scalaの言語仕様で、traitをwithで多重ミックスインした場合に後ろで定義されているものが優先されるため)
 *
 */
trait AbstractHttpMock extends AbstractHttp {

  protected val statusCode: Int
  protected val responseBodyAsString: String
  var requestUrl: String = ""

  protected override def httpExecute[A](requestMethod: HttpRequestBase, handler: AbstractResponseHandler[A]): Either[Throwable, A] = {
    try {
      requestUrl = requestMethod.getURI.toString

      val response = new HttpResponse {
        def containsHeader(name: String) = true
        def addHeader(name: String, value: String) {}
        def addHeader(header: Header) {}
        def getLocale = Locale.JAPAN
        def removeHeader(header: Header) {}
        def getAllHeaders = null
        def setEntity(entity: HttpEntity) {}
        def setStatusLine(ver: ProtocolVersion, code: Int, reason: String) {}
        def setStatusLine(ver: ProtocolVersion, code: Int) {}
        def setStatusLine(statusline: StatusLine) {}
        def setParams(params: HttpParams) {}
        def setStatusCode(code: Int) {}
        def removeHeaders(name: String) {}
        def getLastHeader(name: String) = null
        def getHeaders(name: String) = null
        def setReasonPhrase(reason: String) {}
        def getParams = null
        def getEntity = new StringEntity(responseBodyAsString)
        def setHeader(name: String, value: String) {}
        def setHeader(header: Header) {}
        def setHeaders(headers: Array[Header]) {}
        def getStatusLine = new StatusLine {
          def getStatusCode = statusCode
          def getReasonPhrase = null
          def getProtocolVersion = null
        }
        def headerIterator(name: String) = null
        def headerIterator() = null
        def getFirstHeader(name: String) = null
        def setLocale(loc: Locale) {}
        def getProtocolVersion = null
      }

      Right(handler.handleResponse(response))
    } catch {
      case e: Exception => {
        Left(e)
      }
    } finally {
      requestMethod.abort
    }
  }
}
