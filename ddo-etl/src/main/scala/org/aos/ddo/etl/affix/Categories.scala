package org.aos.ddo.etl.affix

/**
  * Created by adarr on 5/8/2017.
  */
object Categories {

  case class Cat(name: String, subCategory: Option[String] = None) extends Category

}
