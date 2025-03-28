package com.example.cacatesouro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class SegundaDicaComposable(onBack: () -> Unit, onNext: () -> Unit) {

    @Composable
    fun SegundaDica(navigateFirstHint: Unit, navigateThirdHint: Unit){
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Segunda Dica")
            Button(onClick = {}) {
                Text("Voltar")
            }
            Button(onClick = {}) {
                Text("Próxima Dica")
            }
        }
    }
}

