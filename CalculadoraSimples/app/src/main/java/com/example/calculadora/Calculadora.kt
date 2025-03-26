package com.example.calculadora

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Calculadora (firstNumber: Int, operator: String, secondNumber: Int, display: String, startedSecondNumber: Boolean) {
    var firstNumber by mutableIntStateOf(firstNumber)
    var operator by mutableStateOf(operator)
    var secondNumber by mutableIntStateOf(secondNumber)
    var display by mutableStateOf(display)
    var startedSecondNumber by mutableStateOf(startedSecondNumber)
}