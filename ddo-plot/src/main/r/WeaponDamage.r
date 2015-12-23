

CriticalProfile <- setClass(
  "CriticalProfile",
  slots = c(r = "integer", m = "numeric"),
  prototype = list(r=20:20,m=1),
  validity = function(object) {
#     if(length(object@r) == 0) {
#       return("Ranges generally include at least one number. Expected 0:Some positive number")
#     }
    if(length( object@m) > 1) {
      return("Expected single number.")
    }
    # print("Checking multiplier > 0")
    if( object@m < 1) {
      return("Multiplier must be positive. (Can be 1)")
    }
  }
)

WeaponDamageModifier <- setClass(
  "WeaponDamageModifier",
  slots = (c(modifier="numeric",critProfile = "CriticalProfile",dice="Dice")),
  prototype = list(modifier=0.0,critProfile=CriticalProfile(),dice=Dice())
)

setMethod(f="display",
          signature = "WeaponDamageModifier",
          def=function(theObject) {
            dice <- paste("[",
                          convertToDiceString(theObject@dice@quantity,theObject@dice@sides,0),
                          "]",sep="")
            extra <-
              if (theObject@dice@extra < 0) {
              paste(dice,theObject@dice@extra,sep = " ")
              } else if (theObject@dice@extra == 0) {paste(dice,sep = "")}
            else { paste(dice,paste("+",theObject@dice@extra,sep=""),sep = " ")}
            crit <- display(theObject@critProfile)
            if (theObject@modifier == 0) {
              paste(extra,sep = "")
              }
            else {
              paste(theObject@modifier,extra,sep = "")
            }

          })
#' display
#' should be called where a toString() method is desired. In the specific case
#' of dice, will display D&D dice, such as 3d6+2 for three six sided dice plus
#'  3 after the roll
#' @example wdm <- CriticalProfile(r=19:20,m=3)
#' display(wdm)
#' result "[19-20] x3"
setMethod(f="display",
          signature = "CriticalProfile",
          def=function(theObject) {
            if (min(theObject@r) == max(theObject@r)) {
              paste("[",max(theObject@r),"] x",theObject@m,sep="")
            }
            else {
              paste("[",min(theObject@r),"-",max(theObject@r),"] x",theObject@m,sep="")
            }

          })



