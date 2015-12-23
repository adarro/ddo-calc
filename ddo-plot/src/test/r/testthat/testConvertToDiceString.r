library(Dice)
context("convertToDiceString")

test_that("dice display correctly translates", {
  expect_equal(convertToDiceString(3,"6",3), "3d6+3")
  expect_equal(convertToDiceString(),"1d1" )
  expect_equal(convertToDiceString(1),"1d1" )
  expect_equal(convertToDiceString(1,2),"1d2" )
  expect_equal(convertToDiceString(2,6),"2d6" )
  expect_equal(convertToDiceString(0,2,3),"+3" )
  expect_equal(convertToDiceString(0,2,-4),"-4" )
  #error conditions
  #can't have negative quantity of dice
  expect_error(convertToDiceString(-3))
  #can't have less than 1 sided dice (we're allowing a 1 sided dice)
  expect_error(convertToDiceString(3,0))

})

test_that("ensure average function rounds correctly", {
  expect_equal(singleToAvg(3),2)
  expect_equal(singleToAvg(25),13)
  expect_equal(singleToAvg(6),3.5)
  expect_equal(singleToAvg(0),0)
  expect_equal(singleToAvg(-12),-6.5)
})

test_that("Modifier is positive over 12, 0 at 10-11 and otherwise negative", {
  vals <- -2:14
  for (i in vals) {
    m <- modifierFromScore(i)
  #  print(paste("val",i," mod:",m))
    if (i < 10) {
  #    print(paste("val",i," mod:",m,"(<10)",m," < 0",m < 0))
      expect_true(m < 0)
    }
    else if (i %in% 10:11) {
   #   print(paste("val",i," mod:",m,"(10:11)",m, "==0",m==0))
      expect_true(m == 0)
    }
    else if (i > 11) {
    #  print(paste("val",i," mod:",m,"(12+)",m,">0",m >0))
      expect_true(m > 0)
    }

  }
})


