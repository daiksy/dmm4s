package com.github.daiksy.dmm4s.entities

import scala.xml.XML

case class Item(
    serviceName: String,
    floorName: String,
    categoryName: String,
    contendId: String,
    productId: String,
    title: String,
    url: String,
    affiliateURL: String,
    urlForSmartPhone: String,
    affiliateUrlForSmartPhone: String,
    imageUrlForList: String,
    imageUrlSmall: String,
    imageUrlLarge: String,
    sampleImageUrls: List[String],
    itemInfo: List[ItemInfo]) {
}

case class ItemInfo(
    keyword: List[String],
    series: List[String],
    maker: List[String],
    actor: List[String],
    director: List[String],
    author: List[String],
    label: List[String]) {
}

object Item {

  def apply(node: xml.Node): Item = {
    Item(
      (node \ "result" \ "items" \ "item" \ "service_name").text,
      (node \ "result" \ "items" \ "item" \ "floor_name").text,
      (node \ "result" \ "items" \ "item" \ "category_name").text,
      (node \ "result" \ "items" \ "item" \ "content_id").text,
      (node \ "result" \ "items" \ "item" \ "product_id").text,
      (node \ "result" \ "items" \ "item" \ "title").text,
      (node \ "result" \ "items" \ "item" \ "url").text,
      (node \ "result" \ "items" \ "item" \ "affiliateURL").text,
      (node \ "result" \ "items" \ "item" \ "URLsp").text,
      (node \ "result" \ "items" \ "item" \ "affiliateURLsp").text,
      (node \ "result" \ "items" \ "item" \ "imageURL" \ "list").text,
      (node \ "result" \ "items" \ "item" \ "imageURL" \ "small").text,
      (node \ "result" \ "items" \ "item" \ "imageURL" \ "large").text,
      Nil, //TODO
      Nil //TODO
    )
  }
}

// TODO
//object ItemInfo {
//  def fromXml(node: xml.Node): ItemInfo {
//
//  }
//}

