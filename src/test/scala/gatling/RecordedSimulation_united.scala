
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation_united extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8080")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9,ru-KZ;q=0.8,ru;q=0.7")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")

	val headers_0 = Map(
		"Cache-Control" -> "max-age=0",
		"Upgrade-Insecure-Requests" -> "1")

    val uri1 = "http://localhost:8080/united"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/united")
			.headers(headers_0))

	setUp(scn.inject(constantUsersPerSec(100) during (5 minutes)))
			  .throttle(
						  jumpToRps(500),
						  holdFor(2 hours)
						).protocols(httpProtocol)
}