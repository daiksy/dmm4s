package com.github.daiksy.dmm4s.http

import org.slf4j.LoggerFactory
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.methods.{ HttpDelete, HttpPost, HttpRequestBase, HttpGet }
import org.apache.http.HttpResponse
import org.apache.http.util.EntityUtils
import org.apache.http.message.BasicNameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.utils.URIBuilder

/**
 * APIを操作するためのHTTPリクエストを送ったり、
 * レスポンスを取得する。
 *
 */
abstract class AbstractHttp {

  protected lazy val contentCharset = "EUC-JP"

  protected lazy val httpClient = {
    val client = new DefaultHttpClient
    client.getParams.setParameter("http.protocol.content-charset", contentCharset)
    client
  }

  /**
   * httpメソッドの実行
   *
   * @param requestMethod 実行したいhttpメソッド
   * @return left: エラー時の例外, Right: レスポンス
   */
  protected def httpExecute[A](requestMethod: HttpRequestBase,
    handler: AbstractResponseHandler[A]): Either[Throwable, A] = {
    try {
      val response = httpClient.execute(requestMethod, handler)
      Right(response)
    } catch {
      case e: Exception => {
        Left(e)
      }
    } finally {
      requestMethod.abort
    }
  }

  /**
   * GETメソッドの実行
   *
   * AbstractResponseHandlerトレイトのhandlerメソッドを実装し、メソッドのレスポンスを編集できます。
   * handler自体の返り値が当該メソッドのRightの値となります。
   * handler内でthrowされた例外は、当該メソッドのLeftの値として返却されます。
   *
   * (example)
   * {{{
   *   class Handler extends AbstractResponseHandler {
   *     override def handle(response: HttpResponse) = {
   *       statusCode match {
   *         case 200 => "String"
   *         case 500 => throw new HttpException("server error.")
   *       }
   *     }
   *   }
   *
   *   get("http://example.com/path", Map("key" -> "value")) (new Handler)
   * }}}
   *
   * @param url getメソッドを実行するURL
   * @param queryParams GETメソッドに付与するクエリパラメータ
   * @param handler 実行結果に対する処理
   * @return Either[Throwable, handlerの実行結果]
   */
  protected def get[A](url: String, queryParams: Map[String, Seq[String]] = Map.empty)(handler: AbstractResponseHandler[A]): Either[Throwable, A] = {
    try {
      val builder = new URIBuilder(url)
      queryParams.foreach {
        case (key, values) =>
          values.foreach(builder.addParameter(key, _))
      }

      httpExecute(new HttpGet(builder.build), handler)
    } catch {
      case e: Throwable => Left(e)
    }
  }

  /**
   * HttpResponseから直接ステータスコードとレスポンスボディを取る implicit class
   */
  protected implicit class ResponseUtil(response: HttpResponse) {
    lazy val getStatusCode = response.getStatusLine.getStatusCode
    lazy val getResponseBody = EntityUtils.toString(response.getEntity)
  }
}
