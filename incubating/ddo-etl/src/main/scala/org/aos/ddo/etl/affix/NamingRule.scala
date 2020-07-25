package io.truthencode.ddo.etl.affix

trait NamingRule {
  val category: Category
  val affixes: List[String]
}
