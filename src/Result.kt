/**
 * Created by kasra on 6/7/17.
 */

sealed class Result {
  data class Success<out T, out V>(val t: T, val v: V) : Result()
  data class Failure(val msg: String) : Result()
}