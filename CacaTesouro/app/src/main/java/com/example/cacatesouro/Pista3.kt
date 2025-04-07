package com.example.cacatesouro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
    fun Pista3(onBack: () -> Unit, onNext: () -> Unit) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text("Pista 3",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(18.dp))

            Button(onClick = { onBack() }) {
                Text("Voltar a Segunda Pista",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold)
            }
            Button(onClick = { onNext() }) {
                Text("Respoder",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
    }

