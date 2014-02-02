package com.github.daiksy.dmm4s.http

import org.apache.http.client.ResponseHandler
import org.apache.http.HttpResponse

/**
 * ResponseHandlerから、個別にステータスコードとレスポンスボディを
 * 取得するためのResponseHandlerの拡張Trait
 *
 * レスポンスの編集と戻り値のセットはhandleを実装してください。
 */
trait AbstractResponseHandler[A] extends ResponseHandler[A] {

  final def handleResponse(response: HttpResponse): A = {
    handle(response)
  }

  def handle(response: HttpResponse): A = ???
}
