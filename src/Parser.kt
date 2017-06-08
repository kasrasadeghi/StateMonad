/**
 * Created by kasra on 6/7/17.
 */

/**
 * type parser = string -> (bool, string)
 */

/**
 * char -> string -> (bool, string)
 */
fun pchar(c: Char): (String) -> Pair<Boolean, String> {
  return fun(str: String): Pair<Boolean, String> {
    if (str.isEmpty()) return Pair(false, "")
    else if (str[0] == c) return Pair(true, str.substring(1))
    else return Pair(false, str)
  }
}

/**
 * string -> string -> (bool, string)
 */
fun pstr(match: String): (String) -> Pair<Boolean, String> {
  return fun(str: String): Pair<Boolean, String> {
    if (str.startsWith(match)) return Pair(true, str.substring(match.length))
    else return Pair(false, str)
  }
}

//TODO a generic parser constructor for a type T. needs a predicate: (String, T) -> Boolean, and a length: T -> Int
//TODO a future generic parser constructor would also need a Node constructor for the parser that makes a tree

/**
 * and combinator
 * (parser, parser) -> parser
 */
infix fun ((String) -> Pair<Boolean, String>).and(rightParser: (String) -> Pair<Boolean, String>): (String) -> Pair<Boolean, String> {
  // run the first parser on the input.
  // if the parser fails with empty,
  //   return with empty
  // if the parser fails with str
  //   return with fail, str
  // if the parser succeeds,
  //   return the result of running the second parser
  val leftParser = this
  return fun(str: String): Pair<Boolean, String> {
    val (success, newstr) = leftParser.invoke(str)
    return if (success) rightParser.invoke(newstr) else Pair(success, newstr)
  }
}

/**
 * or combinator
 * (parser, parser) -> parser
 */
infix fun ((String) -> Pair<Boolean, String>).or(rightParser: (String) -> Pair<Boolean, String>): (String) -> Pair<Boolean, String> {
  // run the first parser on the input.
  // if the parser fails with empty,
  //   return with empty
  // if the parser fails with str
  //   return with fail, str
  // if the parser succeeds,
  //   return the result of running the second parser
  val leftParser = this
  return fun(str: String): Pair<Boolean, String> {
    val (success, newstr) = leftParser.invoke(str)
    return if (success) Pair(success, newstr) else rightParser.invoke(str)
  }
}

fun test() {
  println("pchar('h')(\"hello\")                  => ${pchar('h')("hello")}")
  println("(pchar('h') and pchar('e'))(\"hello\") => ${(pchar('h') and pchar('e'))("hello")}")
}

/*

Grammar for the Language used in the Monadic State Evaluator:

where ` (backtick) surrounds a reg-exp
      ' surrounds a string/char literal


expr  ::= s-exp | int | op
s-exp ::= '(' op (expr)* ')'
int   ::= `[1-9][0-9]*`
op    ::= '=' | '+'

 */

fun main(args: Array<String>) {
  val pbinop = pchar('=') or pchar('+')
  TODO()
}