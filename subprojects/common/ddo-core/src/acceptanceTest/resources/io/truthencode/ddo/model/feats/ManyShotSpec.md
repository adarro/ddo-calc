# [Feat: Manyshot](- "before")

[Manyshot](- "setFeatId(#TEXT)")
[wiki](https://ddowiki.com/page/Manyshot "view Manyshot on wiki")

## [Usage](- "Usage and description")

Usage: [Active](- "?=isActive()")

Expend one charge to fire three arrows in quick succession. You have +1 Critical Threat Range and Critical Multiplier
with these shots. Each of these shots can Doubleshot. 2 second cooldown. This feat
gives [3](- "?=javaSafeChargeInfo().getMaxCharges()") charges, you
regain [1](- "?=#qry=javaSafeChargeInfo().quantity.orElse(99)") charge
every [12](- "?=javaSafeChargeInfo().safeInterval()") seconds you don't use Manyshot. Passive: You gain Doubleshot equal
to 1.5x your Base Attack Bonus with Longbows and Shortbows.

## [Prerequisites](-)

Requirements needed to select this feat

| [ ][prereq] [Prerequisites][result] |
| ----------------------------------- |
| Base Attack Bonus: 6                |
| Dexterity: 17                       |
| Point Blank Shot                    |
| Rapid Shot                          |

### [Bonus Selections](-)

Can be selected in addition to the normal level progression

| [ ][bonus] [Bonus Feats][result] |
| -------------------------------- |
| Fighter: 1                       |
| Fighter: 2                       |
| Fighter: 4                       |
| Fighter: 6                       |
| Fighter: 8                       |
| Fighter: 10                      |
| Fighter: 12                      |
| Fighter: 14                      |
| Fighter: 16                      |
| Fighter: 18                      |
| Fighter: 20                      |

### [Granted To](-)

Includes any classes or other exceptions that provide this automatically

| [ ][granted] [Granted][result] |
| ------------------------------ |
| Ranger: 6                      |

[prereq]: - "c:verify-rows=#feat:verifyPrerequisites()"
[bonus]: - "c:verify-rows=#feat:verifyBonusSelections()"
[granted]: - "c:verify-rows=#feat:verifyGrantSelections()"
[result]: - "?=#feat"
