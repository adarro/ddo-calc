package org.aos.ddo.model.misc

import java.time.Duration

trait CoolDown {
def coolDown : Option[Duration]
}
