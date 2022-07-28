import twitter4j.{Twitter, TwitterException,TwitterFactory}
import twitter4j.auth.{AccessToken, RequestToken}

import java.io.{
  BufferedReader,File, IOException,
  InputStreamReader,OutputStream
}
import java.net.{URI,URISyntaxException}

object Main extends App {
  val twitter = new TwitterFactory().getInstance()
  val requestToken = twitter.getOAuthRequestToken()
  println("Got request token.")
  println("Request token: " + requestToken.getToken())
  println("Request token secret: " + requestToken.getTokenSecret())
  var accessToken:AccessToken = null

  val br = new BufferedReader(new InputStreamReader(System.in))
  while (null == accessToken) {
    println("Open the following URL and grant access to your account:")
    println(requestToken.getAuthorizationURL())
    print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:")
    val pin = br.readLine()
    try {
      if (pin.length() > 0) {
        accessToken = twitter.getOAuthAccessToken(requestToken, pin)
      } else {
        accessToken = twitter.getOAuthAccessToken(requestToken)
      }
    } catch {
      case te:TwitterException =>
      if (401 == te.getStatusCode()) {
        println("Unable to get the access token.")
      } else {
        te.printStackTrace()
      }
    }
  }
  println("Got access token.")
  println("Access token: " + accessToken.getToken())
  println("Access token secret: " + accessToken.getTokenSecret())
}
