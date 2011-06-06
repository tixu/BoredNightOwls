package be.xavier.bean

import sjson.json._
import scala.reflect.Manifest

/**
 * Small utility function for REST resources
 */
trait ResourcesUtil {

  /**
   * Serializes objects as JSON string
   * @val obj object to be serialized
   */
  def serializeAsJSON(obj: AnyRef): String = new String(Serializer.SJSON.out(obj))

  /**
   * Deserializes objects from JSON byte array
   * @val json JSON byte array
   * @type T type of the deserialized object
   */
  def deserializeJSON[T](json: Array[Byte])(implicit m: Manifest[T]): AnyRef =
    Serializer.SJSON.in[T](json)(m)

}

