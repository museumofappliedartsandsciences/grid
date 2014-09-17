import play.api.mvc._
import com.gu.mediaservice.syntax._
import lib.Config

// TODO: share with the copy in media-api...

object CorsFilter extends Filter {
  import scala.concurrent._
  import ExecutionContext.Implicits.global

  def apply(f: (RequestHeader) => Future[Result])(request: RequestHeader): Future[Result] = {

    val corsAllowOrigin = Config.corsAllowedOrigin

    f(request).map { _.withHeaders(
      "Access-Control-Allow-Credentials" -> "true",
      "Access-Control-Allow-Origin" -> corsAllowOrigin
    ) }
  }

}