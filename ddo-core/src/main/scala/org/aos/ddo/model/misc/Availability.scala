package org.aos.ddo.model.misc

import scala.collection.immutable.HashSet

/**
  * Created by adarr on 1/28/2017.
  */
sealed trait Availability {
  def availabilityLevels: Set[AvailabilityLevel] = new HashSet[AvailabilityLevel]()

  def isFreeToPlay: Boolean = availabilityLevels.contains(AvailabilityLevel.FreeToPlay) && availabilityLevels.contains(AvailabilityLevel.Favor)

  def isPremiumFeature: Boolean = availabilityLevels.contains(AvailabilityLevel.Premium)

}

/**
  * Feature is available to anyone
  */
trait FreeToPlayFeature extends Availability {
  abstract override def availabilityLevels: Set[AvailabilityLevel] = super.availabilityLevels + AvailabilityLevel.FreeToPlay
}

trait FavorFeature extends Availability {
  abstract override def availabilityLevels: Set[AvailabilityLevel] = super.availabilityLevels + AvailabilityLevel.Favor
}
/**
  * Feature is available once any purchase has been made
  */
trait PremiumFeature extends Availability {
  abstract override def availabilityLevels: Set[AvailabilityLevel] = super.availabilityLevels + AvailabilityLevel.Premium
}

/**
  * Feature is available only via direct purchase
  */
trait IconicFeature extends Availability {
  abstract override def availabilityLevels: Set[AvailabilityLevel] = super.availabilityLevels + AvailabilityLevel.Iconic
}

/**
  * Feature is available with active VIP subscription
  */
trait VIP extends Availability {
  abstract override def availabilityLevels: Set[AvailabilityLevel] = super.availabilityLevels + AvailabilityLevel.VIP
}
