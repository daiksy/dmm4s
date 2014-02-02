package com.github.daiksy.dmm4s.api

/**
 * DMM.R18全体を検索対象とするcase class
 *
 * @param apiId APIを利用するためにDMMから割り当てられたIDを設定します.
 * @param affiliateId DMMから割り当てられたアフィリエイトIDを設定します.
 */
case class DmmR18All(apiId: String, affiliateId: String) extends Dmm {

  protected val _apiId: String = apiId
  protected val _affiliateId: String = affiliateId
  protected val site: Site.Type = Site.DmmR18
  protected val service: String = ""

}
