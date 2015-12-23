# Some useful keyboard shortcuts for package authoring:
#
#   Build and Reload Package:  'Ctrl + Shift + B'
#   Check Package:             'Ctrl + Shift + E'
#   Test Package:              'Ctrl + Shift + T'

#' Dice - a die
#' Represents a die
#'
#' @param quantity Number of die
#' @param sides the number of sides, standard die are six sided die
#' @param extra allows for the addition of static number, i.e.
#'    2d6 +2, would add 2 after each die roll.
Dice <- setClass(
  "Dice",
  slots = c(quantity = "numeric", sides = "numeric", extra = "numeric"),
  prototype = list( quantity = 1, sides = 1,extra = 0),
#   # Make a function that can test to see if the data is consistent.
#   # This is not called if you have an initialize function defined!
   validity=function(object)
  {
    if(object@sides <= 0) {
      return("Zero orless sided dice are not allowed on this plane of existence.")
    }
     #TODO: quantity should be a whole number, you can't have 2.5 dice.
    if (object@quantity < 0) {
      return("Quantity of dice must exceed a negative number")
    }
    return(TRUE)
  }
)

# create a method to return compressed format
setGeneric(name="display",
           def=function(theObject)
           {
             standardGeneric("display")
           }
)

#' display
#' should be called where a toString() method is desired. In the specific case
#' of dice, will display D&D dice, such as 3d6+2 for three six sided dice plus
#'  3 after the roll
#'  @seealso \code{\link{convertToDiceString}}
setMethod(f="display",
          signature = "Dice",
            def=function(theObject) {
              convertToDiceString(theObject@quantity,theObject@sides,theObject@extra)
            })

#' fdisplay
#' Friendly Display
#' @return a string displaying the range in human terms, i.e. 2d6 shows 2-12
setGeneric(name="fdisplay",
           def=function(theObject)
           {
             standardGeneric("fdisplay")
           }
)

setMethod(f="fdisplay",
          signature = "Dice",
          def=function(theObject) {
            qopt <- 1:theObject@quantity
            sopt <- 1:theObject@sides
            x <- theObject@extra
            mn <- min(qopt) * min(sopt) + x
            mx <- max(qopt) * max(sopt) + x
            d<- paste(mn,"to",mx)

            print(d)
          })

#' singleToAvg
#' Utility function to get average from a single die number. assumes 8 = 1..8 etc
singleToAvg <- function(x) {
  if (x == 0)
    return (0)
  y <- 1:abs(x)
   sum(y) / length(y) * {if (x > 0) {1} else {-1}}
}

#' convertToDiceString
#' should be called where a toString() method is desired. In the specific case
#' of dice, will display D&D dice, such as 3d6+2 for three six sided dice plus
#'  3 after the roll
convertToDiceString <- function (qty = 1,sides = 1,extra = 0) {
  if (qty < 0) {
    throw("Quantity must be zero or more")
  }
  if (sides <= 0) {
    throw("Highlander, there must be AT LEAST One side ")
  }

  d <- if (qty > 0) {paste(qty,"d",sides,sep="")} else {""}
  x <- extra
  if (x != 0) {
    if (x > 0)
      d <- paste(d,"+",x,sep = "")
    else
      d<- paste(d,x,sep="")
  }
   d
}
# create a method to return average
setGeneric(name="avg",
           def=function(theObject)
           {
             standardGeneric("avg")
           }
)

setMethod(f="avg",
          signature = "Dice",
          def=function(theObject) {
            theObject@quantity * singleToAvg(theObject@sides) + theObject@extra
          }
)

roll <- function(theObject = NULL,count = 1) {
  if (!missing(theObject))
  switch(attr(theObject,"class")[1],
         Dice = sample(theObject@dice@sides,count,replace = T),
         WeaponDamageModifier = {
           if (theObject@modifier > 0) {
             x <- sample(theObject@dice@sides,count,replace = T)
             dim(x) <- c(1,length(x))
             apply(x,2,function(y) y + theObject@dice@extra)
           }
         })
}

