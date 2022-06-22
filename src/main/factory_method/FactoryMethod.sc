/***
 * refactoring.guru link
 * @Link(https://refactoring.guru/design-patterns/factory-method)
 */
object TransportTypes extends Enumeration{
  type TransportTypes = Value
  val Truck = Value(1, "Truck")
  val Ship = Value(2, "Ship")
  //Think that this value will be added in the future
  val Plane = Value(3, "Plane")
}

abstract class Transport(transportType: TransportTypes.Value)


case class Ship(transportType: TransportTypes.Value = TransportTypes.Ship) extends Transport(transportType = transportType)
case class Truck(transportType: TransportTypes.Value = TransportTypes.Truck) extends Transport(transportType = transportType)

abstract class Logistic {
  def planDelivery: Transport = createTransport
  def createTransport: Transport
}


class RoadLogistic extends Logistic {
  override def createTransport:Transport = {
    val transport = Truck()
    println(s"I'm creating the transport by ${transport.transportType}.....\n")
    transport
  }
}

class SeaLogistic extends Logistic {
  override def createTransport: Transport = {
    val transport = Ship()
    println(s"I'm creating the transport by ${transport.transportType}.....\n")
    transport
  }
}

val roadLogisticFactory = new RoadLogistic()
val shipLogisticFactory = new SeaLogistic()
roadLogisticFactory.createTransport
shipLogisticFactory.createTransport

/***
 * Now if we want to add a new transport type it would be really simple ,
 * let's suppose that we want to add the transport by Plane
 */
case class Plane(transportType: TransportTypes.Value = TransportTypes.Plane) extends Transport(transportType = transportType)
class AirLogistic extends Logistic{
  override def createTransport: Transport = {
    val transport = Plane()
    println(s"I'm creating the transport by ${transport.transportType}.....\n")
    transport
  }
}
val airLogisticFactory = new AirLogistic()
airLogisticFactory.createTransport

/***
 * Refactoring.guru suggests to use this pattern when you don't know beforehand the exact number of types
 * and dependencies.
 * This design pattern is the father of many other patterns like Abstract Factory, Prototype and Builder
 */