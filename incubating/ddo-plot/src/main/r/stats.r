#stats

#' Encapsulates an ability score
#' in DDO Terms, this will be one of the base stats such as STR (Strength)
#' @slot score value of the ability. Generally this will be a positive integer
abilityscore <- setClass(
  "abilityscore",
  slots = c(score = "numeric"),
  prototype = list( score =0),
  # Make a function that can test to see if the data is consistent.
  # This is not called if you have an initialize function defined!
  validity=function(object)
  {
    if(object@score < 0) {
      return("Scores may not always be positive, but they should never be negative")
    }
    return(TRUE)
  }
)

setGeneric(name="modifier",
  def=function(theObject)
  {
    standardGeneric("modifier")
  }
)

#' Extracts the effective modifier from an ability score
#'
#' Scores above 11 yield a bonus, below 10 incur a penalty
#' @param theObject target containing the ability score
#' @return modifier
#' @seealso modifierFromScore
setMethod(f="modifier",
          signature = "abilityscore",
          def=function(theObject) {
            modifierFromScore(theObject@score)
            })

#' Extracts the effective modifier from an ability score
#'
#' Scores above 11 yield a bonus, below 10 incur a penalty
#' @param score An ability score
#' @return (\code{score} - 10) / 2
#' @examples
#' modifierFromScore(25) -> 12
#' modifierFromScore(11) -> 0
#' modifierFromScore(8) -> -1
modifierFromScore <- function (score) {
  (score - 10) %/% 2
}
