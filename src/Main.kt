import java.util.*

/**
 * Created by kasra on 6/6/17.
 */

fun main(args: Array<String>) {
  State() bind op("(= a 1)") bind op("(= a 2)")
}

class State {
  var state: HashMap<String, Int> = HashMap()

  infix fun bind(apply: (State) -> State): State {
    return apply(this)
  }

  fun evaluate(expression: String): State {
    // discussion: the evaluation of an expression based on a state
    // first parse the expression
    // then replace the pieces of the expression that are replace-able with things that were previously in it
    // then recursively descend down the tree and find the value
    // then modify the state so that it has that new value

    val tree = Node("expr")
    tree += parse(expression)
    TODO()
  }
}

fun op(description: String): (State) -> State {
  return { it.evaluate(description) }
}

/**
 * Parses everything that has a matching parenthesis until either the end of the string or a closing parenthesis that
 * does not have a match
 */
fun parse(expression: String): List<Node> {
  val list: ArrayList<Node> = ArrayList()
  expression.forEachIndexed { i, c ->
    if (c == ')')
      return list

    if (c == '(')
      list += parse(expression.substring(i + 1))


  }

  throw IllegalArgumentException()
}

data class Node(var type: String, var value: String = "") {
  val children: ArrayList<Node> = ArrayList()
  operator fun plusAssign(parse: List<Node>) {
    children.addAll(parse)
  }
}
