
import eu.timepit.refined.api.{Refined, RefinedTypeOps}
import eu.timepit.refined.types.all.NonEmptyString
import eu.timepit.refined.numeric._
import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder
import io.estatico.newtype.macros._


object model {

  private type GreaterThan18 = Int Refined Greater[18]
  private object GreaterThan18 extends RefinedTypeOps[GreaterThan18, Int]

  @newtype case class Username(value: NonEmptyString)
  object Username {
    implicit val usernameDecoder: Decoder[Username] = Decoder[String].emap { s =>
      NonEmptyString.from(s).map(Username.apply)
    }
  }

  @newtype case class Age(value: GreaterThan18)
  object Age {
    implicit val ageDecoder: Decoder[Age] = Decoder[Int].emap { i =>
      GreaterThan18.from(i).map(Age.apply)
    }
  }

  final case class User(username: Username, age: Age)
  object User {
    implicit val userDecoder: Decoder[User] = deriveDecoder
  }

}