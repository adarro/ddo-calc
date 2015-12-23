library(testthat)
library(Dice)

test_check("Dice")

library(covr)
package_coverage("testcovr")
## doublefun : .
##
## Package Coverage: 66.67%
## R/doublefun.r: 66.67%
zero_coverage(package_coverage("testcovr"))
