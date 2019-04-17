package org.aos.ddo.model.spells.component

import org.aos.ddo.model.spells.ComponentType

/**
  * A component required to enact a spell.
  */
sealed trait Component {
  val componentType : ComponentType
}

/**
  * [[http://ddowiki.com/page/Material_component Material Components]] are one or more physical substances or objects
  * that are annihilated by the spell energies in the casting process.
  * In DDO, all the spells of the same level and class which require a material component use the same item.
  * Those items are sold by reagent vendors around Stormreach.
  * They stack together up to 1000, so a typical spellcaster will keep hundreds of components at hand for each of the
  * spell levels he can cast.
  *
  * @note This is mostly included for completeness to allow the user to see this may require inventory space
  *       but has no other real impact on values except on whether or not they wish to use a Eschew Material effect.
  */
trait MaterialComponent extends Component {
  /**
    * The name of the component.
    *
    * Some components are only used for spell casting while others are also actual items such as a potion of
    * Bull's Strength for Tenser's Transformation.
    */
  val id : String
  override val componentType: ComponentType = ComponentType.MaterialComponent
}

/**
  * [[http://ddowiki.com/page/Somatic_component Somatic Component]] requires physical gesturing and thus may be affected
  * by [[http://ddowiki.com/page/Arcane_spell_failure arcane spell failure]] or other cases where motion is encumbered.
  */
trait SomaticComponent extends Component {
  override val componentType: ComponentType = ComponentType.SomaticComponent
}

/**
  * [[http://ddowiki.com/page/Verbal_component Verbal Components]] require the ability to speak.  Silence / deafness can
  * affect the ability to cast.
  */
trait VerbalComponent extends Component {
  override val componentType: ComponentType = ComponentType.VerbalComponent
}

