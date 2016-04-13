package org.aos.ddo.web

sealed trait Tag
object HtmlTag {
  val ListItem: String = "li"
  val UnorderedList: String = "ul"
  val TableHeader: String = "th"
  val TableRow: String = "tr"
  val TableData: String = "td"
}
