package calculator

import calculator.domain.Calculator
import calculator.domain.Number
import calculator.domain.Operator
import calculator.userinterface.Console
import calculator.userinterface.UserInterface

fun main() {
    val ui: UserInterface = Console()
    val app = CalculatorApplication(ui)
    app.run()
}

class CalculatorApplication(private val userInterface: UserInterface) {

    private val mathExpressionDelimiter = " "

    fun run() {
        userInterface.showInput()
        val input = userInterface.inputMathExpression()
        val operands = input.split(mathExpressionDelimiter).filterIndexed { idx, _ -> idx % 2 == 0 }.map { Number(it) }.toList()
        val operators = input.split(mathExpressionDelimiter).filterIndexed { idx, _ -> idx % 2 == 1 }.map { Operator.of(it) }.toList()

        val calculator = Calculator(operands, operators)
        val result = calculator.calculate()

        userInterface.showResult(result.value)
    }
}
