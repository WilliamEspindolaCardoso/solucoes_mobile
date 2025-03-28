package com.example.calculadora

import androidx.benchmark.perfetto.Row
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class CalculadoraViewComposable {

    @Composable
    fun Calculadora(calculadora: Calculadora) {

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.SpaceAround
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = calculadora.display,
                    style = TextStyle(fontSize = 50.sp, color = Color.Black),
                    modifier = Modifier.padding(end =16.dp))
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Button(onClick = { concatNumber(calculadora, "7") },
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape
                )
                {
                    Text("7", fontSize = 24.sp)
                }
                Button(onClick = { concatNumber(calculadora, "8") },
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape)
                {
                    Text("8", fontSize = 24.sp)
                }
                Button(onClick = { concatNumber(calculadora, "9") },
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape)
                {
                    Text("9", fontSize = 24.sp)
                }
                Button(onClick = {getOperator(calculadora, "+")},
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    ))
                {
                    Text("+", fontSize = 24.sp)
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {concatNumber(calculadora, "4")},
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape)
                {
                    Text("4", fontSize = 24.sp)
                }
                Button(onClick = {concatNumber(calculadora, "5")},
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape)
                {
                    Text("5", fontSize = 24.sp)
                }
                Button(onClick = {concatNumber(calculadora, "6")},
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape)
                {
                    Text("6", fontSize = 24.sp)
                }
                Button(onClick = {getOperator(calculadora, "-")},
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    ))
                {
                    Text("-", fontSize = 24.sp)
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {concatNumber(calculadora, "1")},
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape)
                {
                    Text("1", fontSize = 24.sp)
                }
                Button(onClick = {concatNumber(calculadora, "2")},
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape)
                {
                    Text("2", fontSize = 24.sp)
                }
                Button(onClick = {concatNumber(calculadora, "3")},
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape)
                {
                    Text("3", fontSize = 24.sp)
                }
                Button(onClick = {getOperator(calculadora, "*")},
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    ))
                {
                    Text("*", fontSize = 24.sp)
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {clear(calculadora)},
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )) {
                    Text("C", fontSize = 24.sp)
                }
                Button(onClick = {concatNumber(calculadora, "0")},
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape)
                {
                    Text("0", fontSize = 24.sp)
                }
                Button(onClick = {calculadora.display = calculate(calculadora)},
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    ))
                {
                    Text("=", fontSize = 24.sp)
                }
                Button(onClick = {getOperator(calculadora, "/")},
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    ))
                {
                    Text("/", fontSize = 24.sp)
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