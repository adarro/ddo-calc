package org.aos.ddo.support.slots.item.randomloot

import org.aos.ddo.support.slots.item.RandomLootGen

/**
  * Created by adarr on 5/5/2017.
  */
case class RandomLoot(prefix: RandomPrefix,
                      suffix: RandomSuffix,
                      extra: Option[RandomExtra]) extends RandomLootGen
