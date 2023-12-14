import cats.syntax.all._

trait Counter[F[_]] {
  def incr: F[Unit]
  def get: F[Int]
}

object Counter {
  def make(map: Map[String, Int]): Counter[Option] = new Counter[Option] {
    override def incr: Option[Unit] =
      map.map { case (_,v) => v + 1 }
         .toList
         .headOption
         .void

    override def get: Option[Int] =
      map
        .toList
        .headOption
        .map { case (_,v) => v}
  }
}


trait Items[F[_]] {
  def getAll: F[List[Item]]
  def add(item: Item): F[Unit]
}



case class Item()