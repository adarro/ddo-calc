package org.aos.ddo.model.feats
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.{Cleric, FavoredSoul, Paladin}
import org.aos.ddo.support.requisite.{ClassRequisite, RequiresAnyOfClass}

/**
  * Created by adarr on 4/18/2017.
  */
trait DivineClassBase extends ClassRequisite with RequiresAnyOfClass {
  override def anyOfClass: Seq[(CharacterClass, Int)] =
    allowedClasses.map((_, minLevel))
  protected val allowedClasses = List(Cleric, FavoredSoul, Paladin)
  protected val minLevel: Int = 1
}
