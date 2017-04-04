package org.aos.ddo.model.feats

/**
  * Created by adarr on 3/26/2017.
  */
trait PactMagicPrefix extends PactPrefix { self: ClassFeat =>
  override def prefix: Option[String] = Some("Pact Magic")

}
