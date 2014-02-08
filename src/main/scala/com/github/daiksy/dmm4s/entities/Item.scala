package com.github.daiksy.dmm4s.entities

case class Item(
    serviceName: String,
    floorName: String,
    categoryName: String,
    title: String,
    url: String,
    affiliateURL: String,
    urlForSmartPhone: String,
    affiliateUrlForSmartPhone: String,
    imageUrlForList: String,
    imageUrlSmall: String,
    imageUrlLarge: String,
    sampleImageUrls: List[String],
    itemInfo: ItemInfo) {
}

case class ItemInfo(
    keyword: List[String],
    series: List[String],
    maker: List[String],
    actor: List[String],
    actress: List[String],
    director: List[String],
    author: List[String],
    label: List[String]) {
}

object Item {
  def apply(node: xml.Node): Item = {
    Item(
      (node \ "service_name").text,
      (node \ "floor_name").text,
      (node \ "category_name").text,
      (node \ "title").text,
      (node \ "URL").text,
      (node \ "affiliateURL").text,
      (node \ "URLsp").text,
      (node \ "affiliateURLsp").text,
      (node \ "imageURL" \ "list").text,
      (node \ "imageURL" \ "small").text,
      (node \ "imageURL" \ "large").text,
      (node \ "sampleImageURL" \ "sample_s" \ "image").map(_.text).toList,
      ItemInfo((node \ "iteminfo").head)
    )
  }
}

object ItemInfo {
  def apply(node: xml.Node): ItemInfo = {
    ItemInfo(
      (node \ "keyword" \ "name").map(_.text).toList,
      (node \ "series" \ "name").map(_.text).toList,
      (node \ "maker" \ "name").map(_.text).toList,
      (node \ "actor" \ "name").map(_.text).toList,
      (node \ "actress" \ "name").map(_.text).toList,
      (node \ "director" \ "name").map(_.text).toList,
      (node \ "author" \ "name").map(_.text).toList,
      (node \ "label" \ "name").map(_.text).toList
    )
  }
}

