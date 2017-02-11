package org.aos.ddo.support

/**
  * Created by adarr on 2/10/2017.
  */
trait SearchPrefix {
  /**
    * Used when qualifying a search with a prefix.
    * Examples include finding "HalfElf" from qualified "Race:HalfElf"
    * @return A default or applied prefix
    */
  def searchPrefixSource: String

  /**
    * An optional delimiter such as a colon when overridden.
    * By default, this is set to Option.None
    * @return The delimiter, if it exists.
    */
  def delimiter : Option[String] = None
  def searchPrefix: String = s"""$searchPrefixSource${delimiter.getOrElse("")}"""
}
