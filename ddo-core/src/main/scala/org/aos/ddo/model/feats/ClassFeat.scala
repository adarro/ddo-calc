package org.aos.ddo.model.feats

import enumeratum.Enum
import org.aos.ddo.model.classes.CharacterClass
import org.aos.ddo.model.classes.CharacterClass.Ranger
import org.aos.ddo.model.compendium.types.{MainType, MonsterType}
import org.aos.ddo.support.naming.FriendlyDisplay
import org.aos.ddo.support.requisite._

/**
  * Created by adarr on 2/14/2017.
  */
sealed trait ClassFeat
    extends Feat
    with FriendlyDisplay
    with SubFeatInformation with ClassRequisiteImpl
    with FeatMatcher { self: FeatType with Requisite with Inclusion =>
  val matchFeat: PartialFunction[Feat, ClassFeat] = {
    case x: ClassFeat => x
  }
  val matchFeatById: PartialFunction[String, ClassFeat] = {
    case x: String if ClassFeat.namesToValuesMap.contains(x) =>
      ClassFeat.withNameOption(x) match {
        case Some(y) => y
      }
  }
}

// scalastyle:off number.of.methods
object ClassFeat extends Enum[ClassFeat] with FeatSearchPrefix {
  case class FavoredEnemyType(mainTypes: Option[MonsterType])
      extends ClassFeat
      with FavoredEnemy
      with MainType
      with SubFeat

  protected def favoredEnemies = {
    for { m <- MonsterType.values } yield FavoredEnemyType(Some(m))
  }

  /**
    * @todo Need to add Grants for Gnomish / Deep Gnome Tier 4/ Deep Wood Stalker Tier 5
    *       Harper Tier 4, Epic Primal Avatar Tier 2
    */
  case object FavoredEnemy
      extends ClassFeat
      with FeatRequisiteImpl
      with GrantsToClass
      with ParentFeat
      with Passive {
    val rangerLevels = (5 to 20 by 5) :+ 1
    override def grantToClass: Seq[(CharacterClass, Int)] =
      rangerLevels.map((Ranger, _))

    override val subFeats = favoredEnemies
  }

  case object ArcaneLore extends ClassFeat with ArcaneLore

  case object ArtificerConstructMastery
      extends ClassFeat
      with ArtificerConstructMastery

  case object ArtificerKnowledgeScrolls
      extends ClassFeat
      with ArtificerKnowledgeScrolls {
    override protected def nameSource: String = "Scrolls"
  }

  case object InscribeArtificerScroll
      extends ClassFeat
      with InscribeArtificerScroll

  case object RapidReload extends ClassFeat with RapidReload

  case object ReanimateConstruct extends ClassFeat with ReanimateConstruct

  case object Trapfinding extends ClassFeat with Trapfinding

  case object UnleashIronDefender extends ClassFeat with UnleashIronDefender

  case object ArtificerCraftMastery
      extends ClassFeat
      with ArtificerCraftMastery

  case object RuneArmUse extends ClassFeat with RuneArmUse

  case object ArtificerKnowledgeWands
      extends ClassFeat
      with ArtificerKnowledgeWands {
    override protected def nameSource: String = "Wands"
  }

  case object Trapmaking extends ClassFeat with Trapmaking

  case object RetainEssence extends ClassFeat with RetainEssence

  case object ArtificerKnowledgeArmsAndArmor
      extends ClassFeat
      with ArtificerKnowledgeArmsAndArmor {
    override protected def nameSource: String = "Arms and Armor"
  }

  case object ArtificerKnowledgePotions
      extends ClassFeat
      with ArtificerKnowledgePotions {
    override protected def nameSource: String = "Potions"
  }

  case object ArtificerKnowledgeWondrousItems
      extends ClassFeat
      with ArtificerKnowledgeWondrousItems {
    override protected def nameSource: String = "Wondrous Items"
  }

  case object ArtificerSkillMastery
      extends ClassFeat
      with ArtificerSkillMastery

  case object SneakAttack extends ClassFeat with SneakAttack
  // Barbarian Feats
  case object DismissRage extends ClassFeat with DismissRage
  case object FastMovementBarbarian
      extends ClassFeat
      with FastMovementBarbarian
  case object GreaterRage extends ClassFeat with GreaterRage
  case object ImprovedUncannyDodge extends ClassFeat with ImprovedUncannyDodge
  case object IndomitableWill extends ClassFeat with IndomitableWill
  case object MightyRage extends ClassFeat with MightyRage
  case object Rage extends ClassFeat with Rage
  case object TirelessRage extends ClassFeat with TirelessRage
  case object TrapSense extends ClassFeat with TrapSense
  case object UncannyDodge extends ClassFeat with UncannyDodge
  case object WildernessLore extends ClassFeat with WildernessLore
  case object ReligiousLore extends ClassFeat with ReligiousLore

  // Druid feats
  case object CallWolfCompanion extends ClassFeat with CallWolfCompanion
  case object DruidSpontaneousCasting
      extends ClassFeat
      with DruidSpontaneousCasting {
    override protected def nameSource: String = "Spontaneous Casting"
  }
  case object DruidicOath extends ClassFeat with DruidicOath
  case object Goodberry extends ClassFeat with Goodberry
  case object WildEmpathy extends ClassFeat with WildEmpathy
  case object WildShapeBear extends ClassFeat with WildShapeBear {
    override protected def nameSource: String = "Bear"
  }
  case object WildShapeWolf extends ClassFeat with WildShapeWolf {
    override protected def nameSource: String = "Wolf"
  }
  case object WildShapeDireBear extends ClassFeat with WildShapeDireBear {
    override protected def nameSource: String = "Dire Bear"
  }
  case object WildShapeWinterWolf extends ClassFeat with WildShapeWinterWolf {
    override protected def nameSource: String = "Winter Wolf"
  }
  case object WildShapeFireElemental
      extends ClassFeat
      with WildShapeFireElemental {
    override protected def nameSource: String = "Fire Elemental"
  }

  case object WildShapeWaterElemental
      extends ClassFeat
      with WildShapeWaterElemental {
    override protected def nameSource: String = "Water Elemental"
  }

  case object ImprovedWildEmpathy extends ClassFeat with ImprovedWildEmpathy

  case object VenomImmunity extends ClassFeat with VenomImmunity

  case object TimelessBody extends ClassFeat with TimelessBody
  // Favored Soul Class feats
  case object EnergyResistanceAcid extends ClassFeat with EnergyResistanceAcid
  case object EnergyResistanceCold extends ClassFeat with EnergyResistanceCold
  case object EnergyResistanceElectricity
      extends ClassFeat
      with EnergyResistanceElectricity
  case object EnergyResistanceFire extends ClassFeat with EnergyResistanceFire
  case object EnergyResistanceSonic
      extends ClassFeat
      with EnergyResistanceSonic

  // Monk Class Feats
  case object ArmorClassBonus extends ClassFeat with ArmorClassBonus
  case object FlurryOfBlows extends ClassFeat with FlurryOfBlows
  case object UnarmedStrike extends ClassFeat with UnarmedStrike
  case object FinishingMoves extends ClassFeat with FinishingMoves

  case object Evasion extends ClassFeat with Evasion
  case object Meditation extends ClassFeat with Meditation
  case object FastMovementMonk extends ClassFeat with FastMovementMonk
  case object StillMind extends ClassFeat with StillMind
  case object PathOfHarmoniousBalance
      extends ClassFeat
      with PathOfHarmoniousBalance
  case object PathOfInevitableDominion
      extends ClassFeat
      with PathOfInevitableDominion
  case object KiStrikeMagic extends ClassFeat with KiStrikeMagic
  case object KiStrikeLawful extends ClassFeat with KiStrikeLawful
  case object KiStrikeAdamantine extends ClassFeat with KiStrikeAdamantine

  case object SlowFall extends ClassFeat with SlowFall
  case object PurityOfBody extends ClassFeat with PurityOfBody

  case object AdeptOfForms extends ClassFeat with AdeptOfForms
  case object MasterOfForms extends ClassFeat with MasterOfForms
  case object GrandmasterOfForms extends ClassFeat with GrandmasterOfForms

  case object DiamondBody extends ClassFeat with DiamondBody
  case object DiamondSoul extends ClassFeat with DiamondSoul
  case object WholenessOfBody extends ClassFeat with WholenessOfBody
  case object ImprovedEvasion extends ClassFeat with ImprovedEvasion

  case object AbundantStep extends ClassFeat with AbundantStep
  case object QuiveringPalm extends ClassFeat with QuiveringPalm
  case object EmptyBody extends ClassFeat with EmptyBody
  case object PerfectSelf extends ClassFeat with PerfectSelf
  case object PerfectSlowFall extends ClassFeat with PerfectSlowFall
  case object ShiningStar extends ClassFeat with ShiningStar

  //Paladin Class Feats
  case object AuraOfGood extends ClassFeat with AuraOfGood
  case object AuraOfCourage extends ClassFeat with AuraOfCourage
  case object SmiteEvil extends ClassFeat with SmiteEvil
  case object DivineGrace extends ClassFeat with DivineGrace
  case object DivineHealth extends ClassFeat with DivineHealth
  case object LayOnHands extends ClassFeat with LayOnHands
  case object FearImmunity extends ClassFeat with FearImmunity

  // Ranger Class Feats (The rese are currently already implemented under general feats
  case object HideInPlainSight extends ClassFeat with HideInPlainSight

  // Rogue Class Feats
  case object CripplingStrike extends ClassFeat with CripplingStrike
  case object DefensiveRoll extends ClassFeat with DefensiveRoll
  case object Opportunist extends ClassFeat with Opportunist
  case object SkillMastery extends ClassFeat with SkillMastery
  case object SlipperyMind extends ClassFeat with SlipperyMind

  //Warlock Class Feats
  case object EldritchBlastFocused extends ClassFeat with EldritchBlastFocused
  case object EldritchBlastDamage extends ClassFeat with EldritchBlastDamage
  case object PactFey extends ClassFeat with PactFey
  case object PactFiend extends ClassFeat with PactFiend
  case object PactGreatOldOne extends ClassFeat with PactGreatOldOne
  case object PactDamage extends ClassFeat with PactDamage
  case object DeceiveItem extends ClassFeat with DeceiveItem

  case object BeguilingDefenses extends ClassFeat with BeguilingDefenses
  case object FiendishResilience extends ClassFeat with FiendishResilience
  case object ThoughtShield extends ClassFeat with ThoughtShield

  case object PactMagicBlindness extends ClassFeat with PactMagicBlindness
  case object PactMagicKnock extends ClassFeat with PactMagicKnock
  case object PactMagicRage extends ClassFeat with PactMagicRage

  case object DarkDelirium extends ClassFeat with DarkDelirium
  case object DarkOnesLuck extends ClassFeat with DarkOnesLuck
  case object EntropicWard extends ClassFeat with EntropicWard
  case object ParagonsAegis extends ClassFeat with ParagonsAegis

  case object MistyEscape extends ClassFeat with MistyEscape
  case object HurlThroughHell extends ClassFeat with HurlThroughHell
  case object CreateThrall extends ClassFeat with CreateThrall
  override lazy val values = findValues
}
