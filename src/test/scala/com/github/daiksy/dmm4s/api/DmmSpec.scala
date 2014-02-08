package com.github.daiksy.dmm4s.api

import org.specs2.mutable.Specification

class DmmSpec extends Specification {

  // テスト用のxmlはDMM APIリファレンスのサンプルを使用
  val fullXml =
    """
      |<?xml version="1.0" encoding="euc-jp"?>
      |<response>
      |  <request>
      |    <parameters>
      |      <parameter name="api_id" value="apiaccount" />
      |      <parameter name="affiliate_id" value="affiliate-999" />
      |      <parameter name="operation" value="ItemList" />
      |      <parameter name="version" value="2.00" />
      |      <parameter name="timestamp" value="2012-12-01 00:00:00" />
      |      <parameter name="site" value="DMM.co.jp" />
      |      <parameter name="keyword" value="巨乳" />
      |    </parameters>
      |  </request>
      |  <result>
      |    <result_count>20</result_count>
      |    <total_count>151087</total_count>
      |    <first_position>1</first_position>
      |    <items>
      |      <item>
      |        <service_name>動画</service_name>
      |        <floor_name>ビデオ</floor_name>
      |        <category_name>ビデオ (動画)</category_name>
      |        <content_id>kawd00409</content_id>
      |        <product_id>kawd00409</product_id>
      |        <title>新人！kawaii*専属デビュ→ 原石美少女☆ココチイイ親近感 野宮さとみ</title>
      |        <URL>http://www.dmm.co.jp/digital/videoa/-/detail/=/cid=kawd00409/</URL>
      |        <affiliateURL>http://www.dmm.co.jp/digital/videoa/-/detail/=/cid=kawd00409/affiliate-999</affiliateURL>
      |        <URLsp>http://www.dmm.co.jp/digital/videoa/-/detail/=/cid=kawd00409/</URLsp>
      |        <affiliateURLsp>http://www.dmm.co.jp/digital/videoa/-/detail/=/cid=kawd00409/affiliate-999</affiliateURLsp>
      |        <imageURL>
      |          <list>http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409pt.jpg</list>
      |          <small>http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409ps.jpg</small>
      |          <large>http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409pl.jpg</large>
      |        </imageURL>
      |        <sampleImageURL>
      |          <sample_s>
      |            <image>http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409-1.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409-2.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409-3.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409-4.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409-5.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409-6.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409-7.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409-8.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409-9.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409-10.jpg</image>
      |          </sample_s>
      |        </sampleImageURL>
      |        <prices>
      |          <price>1980〜</price>
      |          <deliveries>
      |            <delivery>
      |              <type>stream</type>
      |              <price>1980</price>
      |            </delivery>
      |            <delivery>
      |              <type>download</type>
      |              <price>1980</price>
      |            </delivery>
      |            <delivery>
      |              <type>hd</type>
      |              <price>2480</price>
      |            </delivery>
      |            <delivery>
      |              <type>toaster</type>
      |              <price>2100</price>
      |            </delivery>
      |            <delivery>
      |              <type>androiddl</type>
      |              <price>1980</price>
      |            </delivery>
      |          </deliveries>
      |        </prices>
      |        <date>2012-11-22 10:01:00</date>
      |        <iteminfo>
      |          <keyword>
      |            <name>DVDトースター</name>
      |            <id>6529</id>
      |          </keyword>
      |          <keyword>
      |            <name>ハイビジョン</name>
      |            <id>6533</id>
      |          </keyword>
      |          <keyword>
      |            <name>独占配信</name>
      |            <id>6548</id>
      |          </keyword>
      |          <keyword>
      |            <name>巨乳</name>
      |            <id>2001</id>
      |          </keyword>
      |          <keyword>
      |            <name>パイズリ</name>
      |            <id>5019</id>
      |          </keyword>
      |          <keyword>
      |            <name>美少女</name>
      |            <id>1027</id>
      |          </keyword>
      |          <keyword>
      |            <name>単体作品</name>
      |            <id>4025</id>
      |          </keyword>
      |          <keyword>
      |            <name>デビュー作品</name>
      |            <id>6006</id>
      |          </keyword>
      |          <series>
      |            <name>新人！kawaii*専属デビュ→</name>
      |            <id>9271</id>
      |          </series>
      |          <maker>
      |            <name>kawaii</name>
      |            <id>4469</id>
      |          </maker>
      |          <actor>
      |            <name>ほげ</name>
      |            <id>999</id>
      |          </actor>
      |          <actress>
      |            <name>野宮さとみ</name>
      |            <id>1017973</id>
      |          </actress>
      |          <actress>
      |            <name>のみやさとみ</name>
      |            <id>1017973_ruby</id>
      |          </actress>
      |          <actress>
      |            <name>av</name>
      |            <id>1017973_classify</id>
      |          </actress>
      |          <director>
      |            <name>妻夫木翔太</name>
      |            <id>106183</id>
      |          </director>
      |          <director>
      |            <name>つまぶきしょうた</name>
      |            <id>106183_ruby</id>
      |          </director>
      |          <author>
      |            <name>ふが</name>
      |            <id>999</id>
      |          </author>
      |          <label>
      |            <name>kawaii</name>
      |            <id>6567</id>
      |          </label>
      |        </iteminfo>
      |        <stock />
      |      </item>
      |      <item>
      |        <service_name>動画</service_name>
      |        <floor_name>ビデオ</floor_name>
      |        <category_name>ビデオ (動画)</category_name>
      |        <content_id>bbi00128</content_id>
      |        <product_id>bbi00128</product_id>
      |        <title>保健室の先生-学校に潜む白衣のド淫乱養護教論- 蓮実クレア</title>
      |        <URL>http://www.dmm.co.jp/digital/videoa/-/detail/=/cid=bbi00128/</URL>
      |        <affiliateURL>http://www.dmm.co.jp/digital/videoa/-/detail/=/cid=bbi00128/affiliate-999</affiliateURL>
      |        <URLsp>http://www.dmm.co.jp/digital/videoa/-/detail/=/cid=bbi00128/</URLsp>
      |        <affiliateURLsp>http://www.dmm.co.jp/digital/videoa/-/detail/=/cid=bbi00128/affiliate-999</affiliateURLsp>
      |        <imageURL>
      |          <list>http://pics.dmm.co.jp/digital/video/bbi00128/bbi00128pt.jpg</list>
      |          <small>http://pics.dmm.co.jp/digital/video/bbi00128/bbi00128ps.jpg</small>
      |          <large>http://pics.dmm.co.jp/digital/video/bbi00128/bbi00128pl.jpg</large>
      |        </imageURL>
      |        <sampleImageURL>
      |          <sample_s>
      |            <image>http://pics.dmm.co.jp/digital/video/bbi00128/bbi00128-1.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/bbi00128/bbi00128-2.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/bbi00128/bbi00128-3.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/bbi00128/bbi00128-4.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/bbi00128/bbi00128-5.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/bbi00128/bbi00128-6.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/bbi00128/bbi00128-7.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/bbi00128/bbi00128-8.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/bbi00128/bbi00128-9.jpg</image>
      |            <image>http://pics.dmm.co.jp/digital/video/bbi00128/bbi00128-10.jpg</image>
      |          </sample_s>
      |        </sampleImageURL>
      |        <prices>
      |          <price>1980〜</price>
      |          <deliveries>
      |            <delivery>
      |              <type>stream</type>
      |              <price>1980</price>
      |            </delivery>
      |            <delivery>
      |              <type>download</type>
      |              <price>1980</price>
      |            </delivery>
      |            <delivery>
      |              <type>hd</type>
      |              <price>2480</price>
      |            </delivery>
      |            <delivery>
      |              <type>androiddl</type>
      |              <price>1980</price>
      |            </delivery>
      |          </deliveries>
      |        </prices>
      |        <date>2012-11-22 10:00:57</date>
      |        <iteminfo>
      |          <keyword>
      |            <name>痴女</name>
      |            <id>1031</id>
      |          </keyword>
      |          <keyword>
      |            <name>巨乳</name>
      |            <id>2001</id>
      |          </keyword>
      |          <keyword>
      |            <name>女教師</name>
      |            <id>1016</id>
      |          </keyword>
      |          <keyword>
      |            <name>淫乱・ハード系</name>
      |            <id>4030</id>
      |          </keyword>
      |          <keyword>
      |            <name>単体作品</name>
      |            <id>4025</id>
      |          </keyword>
      |          <keyword>
      |            <name>独占配信</name>
      |            <id>6548</id>
      |          </keyword>
      |          <keyword>
      |            <name>ハイビジョン</name>
      |            <id>6533</id>
      |          </keyword>
      |          <maker>
      |            <name>美</name>
      |            <id>5552</id>
      |          </maker>
      |          <actress>
      |            <name>蓮実クレア</name>
      |            <id>1017139</id>
      |          </actress>
      |          <actress>
      |            <name>はすみくれあ</name>
      |            <id>1017139_ruby</id>
      |          </actress>
      |          <actress>
      |            <name>av</name>
      |            <id>1017139_classify</id>
      |          </actress>
      |          <director>
      |            <name>ザック荒井</name>
      |            <id>290</id>
      |          </director>
      |          <director>
      |            <name>ざっくあらい</name>
      |            <id>290_ruby</id>
      |          </director>
      |          <label>
      |            <name>美</name>
      |            <id>20012</id>
      |          </label>
      |        </iteminfo>
      |        <stock />
      |      </item>
      |    </items>
      |  </result>
      |</response>
    """.stripMargin

  class TesterDmm extends Dmm {
    override protected val service: String = ""
    override protected val site: Site.Type = Site.DmmDotCom
    override protected val _affiliateId: String = "__affiliated_id___"
    override protected val _apiId: String = "__api__id"
  }

  class TesterDotComAll extends DmmDotComAll("__api__id", "__affiliated_id___") {
    override val timestamp = "2014-01-01 00:00:00"
  }
  class TesterR18 extends DmmR18All("__api__id", "__affiliated_id___") {
    override val timestamp = "2014-01-01 00:00:00"
  }

  "Dmm" should {
    "parameter range error" in {
      val client = new TesterDmm
      client.itemListXml(hits = 101) must throwA[IllegalArgumentException]
    }

    "xml deserialize" in {
      val client = new TesterDmm {
        override def itemListXml(hits: Int, offset: Int, sort: SortPattern.Type, keyword: String) = fullXml
      }
      val itemList = client.itemList()

      itemList.size === 2
      itemList.head.serviceName === "動画"
      itemList.head.floorName === "ビデオ"
      itemList.head.categoryName === "ビデオ (動画)"
      itemList.head.title === "新人！kawaii*専属デビュ→ 原石美少女☆ココチイイ親近感 野宮さとみ"
      itemList.head.url === "http://www.dmm.co.jp/digital/videoa/-/detail/=/cid=kawd00409/"
      itemList.head.affiliateURL === "http://www.dmm.co.jp/digital/videoa/-/detail/=/cid=kawd00409/affiliate-999"
      itemList.head.urlForSmartPhone === "http://www.dmm.co.jp/digital/videoa/-/detail/=/cid=kawd00409/"
      itemList.head.affiliateUrlForSmartPhone === "http://www.dmm.co.jp/digital/videoa/-/detail/=/cid=kawd00409/affiliate-999"
      itemList.head.imageUrlForList === "http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409pt.jpg"
      itemList.head.imageUrlSmall === "http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409ps.jpg"
      itemList.head.imageUrlLarge === "http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409pl.jpg"
      itemList.head.sampleImageUrls.size === 10
      itemList.head.sampleImageUrls.head === "http://pics.dmm.co.jp/digital/video/kawd00409/kawd00409-1.jpg"
      itemList.head.itemInfo.keyword.size === 8
      itemList.head.itemInfo.keyword.head === "DVDトースター"
      itemList.head.itemInfo.series.size === 1
      itemList.head.itemInfo.series.head === "新人！kawaii*専属デビュ→"
      itemList.head.itemInfo.maker.size === 1
      itemList.head.itemInfo.maker.head === "kawaii"
      itemList.head.itemInfo.actress.size === 3
      itemList.head.itemInfo.actress.head === "野宮さとみ"
      itemList.head.itemInfo.actor.head === "ほげ"
      itemList.head.itemInfo.author.head === "ふが"
      itemList.head.itemInfo.director.size === 2
      itemList.head.itemInfo.director.head === "妻夫木翔太"
      itemList.head.itemInfo.label.size === 1
      itemList.head.itemInfo.label.head === "kawaii"
    }
  }

  "DmmDotComAll" should {
    "URL Build" in {
      val client = new TesterDotComAll {
        val httpRequest = buildHttpRequest(1, 1, SortPattern.Rank, "ディーエムエム")
      }
      client.httpRequest.getUrl.toString must beEqualTo("http://affiliate-api.dmm.com/?api_id=__api__id&affiliate_id=__affiliated_id___&" +
        "operation=ItemList&version=2.00&site=DMM.com&timestamp=2014-01-01+00%3A00%3A00&" +
        "keyword=%A5%C7%A5%A3%A1%BC%A5%A8%A5%E0%A5%A8%A5%E0&offset=1&hits=1&sort=rank")
    }
  }

  "DmmR18" should {
    "URL Build" in {
      val client = new TesterR18 {
        val httpRequest = buildHttpRequest(1, 1, SortPattern.Rank, "ディーエムエム")
      }
      client.httpRequest.getUrl.toString must beEqualTo("http://affiliate-api.dmm.com/?api_id=__api__id&affiliate_id=__affiliated_id___&" +
        "operation=ItemList&version=2.00&site=DMM.co.jp&timestamp=2014-01-01+00%3A00%3A00&" +
        "keyword=%A5%C7%A5%A3%A1%BC%A5%A8%A5%E0%A5%A8%A5%E0&offset=1&hits=1&sort=rank")
    }
  }

}
