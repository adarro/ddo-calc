library(Dice)
context("CriticalProfile")

test_that("CriticalProfile display correctly translates", {
  #single digit shows the number in brackets
  cprof <- CriticalProfile(r=20:20,m=4)
  expect_equal("[20] x4",display(cprof))
  #default properties result in [20] x1
  cprof <- CriticalProfile()
  expect_equal("[20] x1",display(cprof))
  #range of numbers correctly displays [min-max] xMultiplier
  cprof <- CriticalProfile(r=18:20,m=3)
  expect_equal("[18-20] x3",display(cprof))
  #non-positive multiplier not allowed
  expect_error(CriticalProfile(r=17:20,m=0))
  #only allow single number for multiplier (i.e. 1...x not allowed)
  expect_error(CriticalProfile(r=17:20,m=1:3))


})


test_that("WeaponDamage displays correctly",
          {
            #Default Dice
            ddice <- Dice()
            #dice with no extra
            zdice <- Dice(quantity=2,sides=6,extra=0)
            #dice with positive extra
            pdice <- Dice(quantity=2,sides=6,extra=3)
            #dice with negative extra
            ndice <- Dice(quantity=2,sides=6,extra=-3)

            #modifier with no extra
            wdm <- WeaponDamageModifier(modifier = 2,dice=zdice);wdm;
            expect_equal("2[2d6]",display(wdm))

            #modifier with positive extra
            wdm <- WeaponDamageModifier(modifier = 2,dice=pdice);wdm;
            expect_equal("2[2d6] +3",display(wdm))

            #modifier with negative extra
            wdm <- WeaponDamageModifier(modifier = 2,dice=ndice);wdm;
            expect_equal("2[2d6] -3",display(wdm))

            #modifier with default dice
            wdm <- WeaponDamageModifier(modifier = 2,dice=ddice);wdm;
            expect_equal("2[1d1]",display(wdm))

            #no modifier
            wdm <- WeaponDamageModifier(modifier = 0,dice=ddice);wdm;
            expect_equal("[1d1]",display(wdm))


          })
