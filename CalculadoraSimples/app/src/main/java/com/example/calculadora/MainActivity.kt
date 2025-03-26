package com.example.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraTheme {
                val calculadoraView = CalculadoraViewComposable()
                val calculadora = Calculadora(0, "", 0,"0", false)
                calculadoraView.Calculadora(calculadora)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculadoraTheme {
        val calculadoraView = CalculadoraViewComposable()
        val calculadora = Calculadora(0, "", 0,"0", false)
        calculadoraView.Calculadora(calculadora)
    }
}