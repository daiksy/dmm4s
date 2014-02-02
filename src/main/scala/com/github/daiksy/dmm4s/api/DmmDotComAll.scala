package com.github.daiksy.dmm4s.api

/**
 * DMM.com全体を検索対象とするcase class
 *
 * @param apiId APIを利用するためにDMMから割り当てられたIDを設定します.
 * @param affiliateId DMMから割り当てられたアフィリエイトIDを設定します.
 */
case class DmmDotComAll(apiId: String, affiliateId: String) extends Dmm {

  protected val _apiId: String = apiId
  protected val _affiliateId: String = affiliateId
  protected val site: Site.Type = Site.DmmDotCom
  protected val service: String = ""

}
