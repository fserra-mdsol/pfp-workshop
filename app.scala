import cats.effect._
import com.comcast.ip4s._
import io.circe.Json
import model._
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.ember.server._
import org.http4s.circe._
import io.circe.literal._
import org.http4s.circe.CirceEntityDecoder._

object app extends IOApp {
  def hello(name: Username): Json =
    json"""{"hello": ${name.value.value}}"""


  val helloWorldService = HttpRoutes.of[IO] {
    case req @ POST -> Root / "user" => (for {
      user <- req.as[User]
      _ <- IO.println(s"Received user ${user}")
      resp <- Ok(hello(user.username))
    } yield resp).handleErrorWith(e => Ok(s"Got error: ${e.getMessage}"))

    case GET -> Root / "hello" / name =>
      IO.println(s"Received GET request for name $name") *>
      Ok(s"Hello, $name.")
  }.orNotFound

  def run(args: List[String]): IO[ExitCode] =
    EmberServerBuilder
      .default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(helloWorldService)
      .build
      .use(_ => IO.never)
      .as(ExitCode.Success)
}