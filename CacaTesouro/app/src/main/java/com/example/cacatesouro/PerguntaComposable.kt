package com.example.cacatesouro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class PerguntaComposable(onBack: () -> Unit, onNext: () -> Unit) {

    @Composable
    fun Pergunta(navigateHome: Unit, navigateFirstHint: Unit){
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Qual o nome da charada?")
            Button(onClick = {}) {
                Text("Voltar")
            }
            Button(onClick = {}) {
                Text("Primeira Dica")
            }
        }
    }
}

