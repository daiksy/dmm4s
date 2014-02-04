package com.github.daiksy.dmm4s.api

import java.util.Date
import scalaj.http.Http

trait Dmm {

  protected val apiVersion = "2.00"
  protected lazy val baseUrl = s"http://affiliate-api.dmm.com/?api_id=${_apiId}&affiliate_id=${_affiliateId}" +
    s"&operation=ItemList&version=${apiVersion}&site=${site}"

  protected val _apiId: String
  protected val _affiliateId: String
  protected val site: Site.Type
  protected val service: String

  var hoge: String = ""

  /**
   *
   * 商品一覧を取得します.
   * 検索条件を名前付き引数で指定してください.
   *
   * (example)
   * {{{
   *   .itemList(hits = 50, keyword = "ディーエムエム")
   * }}}
   *
   * @param hits 検索結果の件数を指定します. 初期値 20, 最小値 1, 最大値 100
   * @param offset 検索結果の先頭位置を指定します. 初期値 1
   * @param sort 検索結果の先頭位置を指定します. 初期値 人気順
   * @param keyword 任意のキーワードを指定します.
   */
  def itemList(hits: Int = 20, offset: Int = 1, sort: SortPattern.Type = SortPattern.Rank, keyword: String = "") {
    if (hits > 100) throw new IllegalArgumentException("Range error: 'hits' must be 1 to 100")

    import com.github.daiksy.dmm4s.util.DateUtil._
    val now = (new Date).toString("yyyy-MM-dd HH:mm:ss")
    val parameter: Map[String, String] = Map("hits" -> hits.toString, "offset" -> offset.toString,
      "sort" -> sort.toString, "keyword" -> keyword, "timestamp" -> now)

    hoge = Http(baseUrl).params(parameter).charset("euc-jp").asString

    //    get[String](url, parameter)(new Handler)
  }

}

/**
 * DMM APIの対象サイト
 */
object Site extends Enumeration {
  type Type = Value

  /**
   * DMM.com
   */
  val DmmDotCom = Value("DMM.com")

  /**
   * DMM.R18
   */
  val DmmR18 = Value("DMM.co.jp")
}

/**
 * 検索時の並び順
 */
object SortPattern extends Enumeration {
  type Type = Value

  /**
   * 人気順
   */
  val Rank = Value("rank")

  /**
   * 価格の降順（高い順）
   */
  val PriceDesc = Value("+price")

  /**
   * 価格の昇順（安い順）
   */
  val PriceAsc = Value("-price")

  /**
   * 日付順（新着）
   */
  val Date = Value("date")

  /**
   * 評価順
   */
  val Review = Value("review")
}