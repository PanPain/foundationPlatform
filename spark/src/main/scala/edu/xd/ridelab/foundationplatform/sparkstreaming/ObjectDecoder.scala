package edu.xd.ridelab.foundationplatform.sparkstreaming

import edu.xd.ridelab.receiver.util.SerializeUtils
import kafka.serializer.Decoder
import kafka.utils.VerifiableProperties

/**
  * 反序列化kafka value
  * @author cwz
  * @date 2018/5/6
  * @since 0.0.0
  */
class ObjectDecoder(props: VerifiableProperties = null) extends Decoder[AnyRef]{
  override def fromBytes(bytes: Array[Byte]): AnyRef = SerializeUtils.deserialize(bytes)
}
