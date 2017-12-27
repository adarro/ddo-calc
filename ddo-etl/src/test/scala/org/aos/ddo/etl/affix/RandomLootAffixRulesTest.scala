package org.aos.ddo.etl.affix

import org.scalatest.{FunSpec, Matchers}

/**
  * Created by adarr on 5/9/2017.
  */
class RandomLootAffixRulesTest extends FunSpec with Matchers {
  val rules = RandomLootAffixRules
  describe("RandomLootAffixRulesTest") {
    describe("Prefixes") {
      describe("Unnamed and hidden") {
        it("should read hidden prefixes from source data") {
          rules.UnnamedPrefix shouldNot be(empty)
        }
        ignore("should have categories") {
          Utils.DiffEntries(rules.UnnamedPrefix) shouldBe empty
        }
      }

      describe("Elemental Absorption") {
        it("should read from source data") {
          rules.ElementalAbsorptionPrefix shouldNot be(empty)
        }
        it("should have categories") {
          Utils.DiffEntries(rules.ElementalAbsorptionPrefix) shouldBe empty
        }

        describe("Elemental Resistance") {
          it("should read from source data") {
            rules.ElementalResistancePrefix shouldNot be(empty)
          }
          it("should have categories") {
            Utils.DiffEntries(rules.ElementalResistancePrefix) shouldBe empty
          }
        }

        describe("Ability (Stat)") {
          it("should read from source data") {
            rules.BaseAbilityPrefix shouldNot be(empty)
          }
          it("should have categories") {
            Utils.DiffEntries(rules.BaseAbilityPrefix) shouldBe empty
          }
        }

        describe("Saves") {
          it("should read from source data") {
            rules.SavesPrefix shouldNot be(empty)
          }
          it("should have categories") {
            Utils.DiffEntries(rules.SavesPrefix) shouldBe empty
          }
        }

        describe("Defensive") {
          it("should read from source data") {
            rules.DefensivePrefix shouldNot be(empty)
          }
          it("should have categories") {
            Utils.DiffEntries(rules.DefensivePrefix) shouldBe empty
          }
        }

        describe("Offensive") {
          it("should read from source data") {
            rules.OffensivePrefix shouldNot be(empty)
          }
          it("should have categories") {
            /*  Deadly is listed in the source text as a valid prefix, but appears to only be valid as a suffix or extra so the below test fails */
            Utils.DiffEntries(rules.OffensivePrefix) shouldBe empty

          }
        }
        describe("Tactics") {
          it("should read from source data") {
            rules.TacticsPrefix shouldNot be(empty)
          }
          it("should have categories") {
            /*  Deadly is listed in the source text as a valid prefix, but appears to only be valid as a suffix or extra so the below test fails */
            Utils.DiffEntries(rules.TacticsPrefix) shouldBe empty
          }
        }

        describe("Threat") {
          it("should read from source data") {
            rules.ThreatPrefix shouldNot be(empty)
          }
          it("should have categories") {
            /*  Deadly is listed in the source text as a valid prefix, but appears to only be valid as a suffix or extra so the below test fails */
            Utils.DiffEntries(rules.ThreatPrefix) shouldBe empty
          }
        }
        describe("Spellpower") {
          it("should read from source data") {
            rules.SpellPowerPrefix shouldNot be(empty)
          }
          it("should have categories") {
            /*  Deadly is listed in the source text as a valid prefix, but appears to only be valid as a suffix or extra so the below test fails */
            Utils.DiffEntries(rules.SpellPowerPrefix) shouldBe empty
          }
        }
        describe("Spell Focus") {
          it("should read from source data") {
            rules.SpellFocusPrefix shouldNot be(empty)
          }
          it("should have categories") {
            /*  Deadly is listed in the source text as a valid prefix, but appears to only be valid as a suffix or extra so the below test fails */
            Utils.DiffEntries(rules.SpellFocusPrefix) shouldBe empty
          }
        }

        describe("Weapon Damage") {
          it("should read from source data") {
            rules.WeaponDamagePrefix shouldNot be(empty)
          }
          it("should have categories") {
            /*  Deadly is listed in the source text as a valid prefix, but appears to only be valid as a suffix or extra so the below test fails */
            Utils.DiffEntries(rules.WeaponDamagePrefix) shouldBe empty
          }
        }

        describe("Skills") {
          it("should read from source data") {
            rules.SkillsPrefix shouldNot be(empty)
          }
          it("should have categories") {
            /*  Deadly is listed in the source text as a valid prefix, but appears to only be valid as a suffix or extra so the below test fails */
            Utils.DiffEntries(rules.SkillsPrefix) shouldBe empty
          }
        }

      }
      /*





      ThreatPrefix,
      SpellPowerPrefix,
      SpellFocusPrefix,
      WeaponDamagePrefix,
      SkillsPrefix,
      UnnamedPrefix
     */

    }
  }
}
