package io.truthencode.ddo.etl.affix

/**
  * Created by adarr on 5/8/2017.
  */
trait Category {
  val name: String
  val subCategory: Option[String]
  def isTopLevel : Boolean = subCategory.isEmpty
  def effectiveName: String = subCategory.getOrElse(name)
}
