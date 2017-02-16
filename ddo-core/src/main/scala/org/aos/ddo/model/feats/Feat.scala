package org.aos.ddo.model.feats

import enumeratum.{Enum, EnumEntry}
import org.aos.ddo.support.requisite.{Inclusion, Requisite}
import org.aos.ddo.support.StringUtils.Extensions
import org.aos.ddo.support.naming.DisplayName

/**
  * Created by adarr on 2/14/2017.
  */
trait Feat extends EnumEntry with DisplayName {
  self: FeatType
    with Requisite
    with Inclusion

    with FeatMatcher =>
 // val entryName: String

  override protected def nameSource: String =
    entryName.splitByCase.toPascalCase
}

object Feat extends Enum[Feat] with FeatSearchPrefix {
  override def values: Seq[Feat] =
    GeneralFeat.values ++ ClassFeat.values ++ RacialFeat.values
}
