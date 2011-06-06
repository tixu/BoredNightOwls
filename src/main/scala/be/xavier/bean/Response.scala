package be.xavier.bean


import java.util.Date
import scala.reflect.BeanInfo
import sjson.json._

@BeanInfo
case class Data (val niss:String,val content:String){
  
}


@BeanInfo
case class MetaInformation (val quality:String,val qos :String){
 
}

@BeanInfo
case class Response ( @JSONTypeHint(classOf[MetaInformation])val metainformation : MetaInformation,@JSONTypeHint(classOf[Data])val data:Data ) {

  
  
}



 
