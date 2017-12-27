package org.aos.ddo.etl

import configs.Result
import org.aos.ddo.etl.affix.RandomLootAffixCategories.MyConfig
import configs.syntax._
/**
  * Created by adarr on 5/8/2017.
  */
package object affix {
   lazy val data: Result[MyConfig] = EtlConfig.config.get[MyConfig]("data.random-loot.affix-category")
   def local: String = data.value.local
}
