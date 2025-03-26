package com.example.calculadora

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class CalculadoraViewComposable {

    @Composable
    fun Calculadora(calculadora: Calculadora) {

        Column (
            modifier = Modifier.fillMaxSize()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.Center

        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                Text(text = calculadora.display, style = TextStyle(fontSize = 60.sp), modifier = Modifier.padding(end = 8.dp))
            }
            Row (
                modifier = Modifier.fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Button(onClick = { concatNumber(calculadora, "1") },
                    modifier = Modifier.weight(1f))
                {
                    Text("1")
                }
                Button(onClick = { concatNumber(calculadora, "2") },
                    modifier = Modifier.weight(1f))
                {
                    Text("2")
                }
                Button(onClick = { concatNumber(calculadora, "3") },
                    modifier = Modifier.weight(1f))
                {
                    Text("3")
                }
                Button(onClick = {getOperator(calculadora, "+")},
                    modifier = Modifier.weight(1f))
                {
                    Text("+")
                }
            }
            Row {
                Button(onClick = {concatNumber(calculadora, "4")},
                    modifier = Modifier.weight(1f))
                {
                    Text("4")
                }
                Button(onClick = {concatNumber(calculadora, "5")},
                    modifier = Modifier.weight(1f))
                {
                    Text("5")
                }
                Button(onClick = {concatNumber(calculadora, "6")},
                    modifier = Modifier.weight(1f))
                {
                    Text("6")
                }
                Button(onClick = {getOperator(calculadora, "-")},
                    modifier = Modifier.weight(1f))
                {
                    Text("-")
                }
            }
            Row {
                Button(onClick = {concatNumber(calculadora, "7")},
                    modifier = Modifier.weight(1f))
                {
                    Text("7")
                }
                Button(onClick = {concatNumber(calculadora, "8")},
                    modifier = Modifier.weight(1f))
                {
                    Text("8")
                }
                Button(onClick = {concatNumber(calculadora, "9")},
                    modifier = Modifier.weight(1f))
                {
                    Text("9")
                }
                Button(onClick = {getOperator(calculadora, "*")},
                    modifier = Modifier.weight(1f))
                {
                    Text("*")
                }
            }
            Row {
                Button(onClick = {clear(calculadora)}, modifier = Modifier.weight(1f)) {
                    Text("C")
                }
                Button(onClick = {concatNumber(calculadora, "0")},
                    modifier = Modifier.weight(1f))
                {
                    Text("0")
                }
                Button(onClick = {calculadora.display = calculate(calculadora)},
                    modifier = Modifier.weight(1f))
                {
                    Text("=")
                }
                Button(onClick = {getOperator(calculadora, "/")},
                    modifier = Modifier.weight(1f))
                {
                    Text("/")
                }
            }
        }
    }
}

fun concatNumber(calculadora: Calculadora, number: String) {
    if (calculadora.startedSecondNumber) {
        calculadora.display = number
        calculadora.startedSecondNumber = false
    } else {
        if (calculadora.display == "0") {
            calculadora.display = number
        } else {
            calculadora.display += number
        }
    }
}

fun clear(calculadora: Calculadora) {
    calculadora.operator = ""
    calculadora.firstNumber = 0
    calculadora.secondNumber = 0
    calculadora.display = "0"
    calculadora.startedSecondNumber = false
}

fun getOperator(calculadora: Calculadora, operator: String) {
    calculadora.firstNumber = calculadora.display.toInt()
    calculadora.operator = operator
    calculadora.startedSecondNumber = true
}

fun calculate(calculadora: Calculadora): String {
    calculadora.secondNumber = calculadora.display.toInt()

    if (calculadora.operator == "+") {
        return (calculadora.firstNumber + calculadora.secondNumber).toString()
    } else if (calculadora.operator == "-") {
        return (calculadora.firstNumber - calculadora.secondNumber).toString()
    } else if (calculadora.operator == "*") {
        return (calculadora.firstNumber * calculadora.secondNumber).toString()
    } else if (calculadora.operator == "/") {
        if (calculadora.secondNumber == 0) {
            return "0"
        }
        return (calculadora.firstNumber / calculadora.secondNumber).toString()
    } else {
        return "0"
    }
}