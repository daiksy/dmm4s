package com.github.daiksy.dmm4s.api

import java.util.Date
import scalaj.http.Http
import com.github.daiksy.dmm4s.entities.Item
import scala.xml.XML

trait Dmm {

  protected val apiVersion = "2.00"
  protected lazy val baseUrl = s"http://affiliate-api.dmm.com/?api_id=${_apiId}&affiliate_id=${_affiliateId}" +
    s"&operation=ItemList&version=${apiVersion}&site=${site}"

  protected val _apiId: String
  protected val _affiliateId: String
  protected val site: Site.Type
  protected val service: String

  /**
   *
   * 商品一覧をXML形式で取得します.
   * 検索条件を名前付き引数で指定してください.
   *
   * (example)
   * {{{
   *   .itemListXml(hits = 50, keyword = "ディーエムエム")
   * }}}
   *
   * @param hits 検索結果の件数を指定します. 初期値 20, 最小値 1, 最大値 100
   * @param offset 検索結果の先頭位置を指定します. 初期値 1
   * @param sort 検索結果の先頭位置を指定します. 初期値 人気順
   * @param keyword 任意のキーワードを指定します.
   */
  def itemListXml(hits: Int = 20, offset: Int = 1, sort: SortPattern.Type = SortPattern.Rank, keyword: String = ""): String = {
    if (hits > 100) throw new IllegalArgumentException("Range error: 'hits' must be 1 to 100")

    buildHttpRequest(hits, offset, sort, keyword).asString
  }

  /**
   *
   * 商品一覧をItemのcase classで取得します.
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
  def itemList(hits: Int = 20, offset: Int = 1, sort: SortPattern.Type = SortPattern.Rank, keyword: String = ""): List[Item] = {
    val xmlString = itemListXml(hits, offset, sort, keyword)
    (stringToXml(xmlString) \ "result" \ "items" \ "item").map { n =>
      Item(n)
    }.toList
  }

  /** Http Requestの組み立て **/
  protected def buildHttpRequest(hits: Int, offset: Int, sort: SortPattern.Type, keyword: String) = {
    val parameter: Map[String, String] = Map("hits" -> hits.toString, "offset" -> offset.toString,
      "sort" -> sort.toString, "keyword" -> keyword, "timestamp" -> timestamp)

    Http(baseUrl).params(parameter).charset("euc-jp")
  }

  /** 現在時刻をフォーマットして取得 */
  protected val timestamp = {
    import com.github.daiksy.dmm4s.util.DateUtil._
    (new Date).toString("yyyy-MM-dd HH:mm:ss")
  }

  /** 取得したXML文字列をXml nodeに変換する */
  protected def stringToXml(xmlString: String): xml.Node = {
    val target = xmlString.lines.map { line =>
      if (line.startsWith("<?xml")) "" else line
    }.mkString

    XML.loadString(target)
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