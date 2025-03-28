package com.example.cacatesouro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cacatesouro.ui.theme.CacaTesouroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CacaTesouroTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "Home") {
                    composable("Home") {
                        HomeComposable {
                            navController.navigate("Pergunta")
                        }
                    }

                    composable("Pergunta") {
                        PerguntaComposable(
                            onBack = { navController.navigate("Home") },
                            onNext = { navController.navigate("PrimeiraDica") }
                        )
                    }
                    composable("PrimeiraDica") {
                        PrimeiraDicaComposable(
                            onBack = { navController.navigate("Pergunta") },
                            onNext = { navController.navigate("SegundaDica") }
                        )
                    }
                    composable("SegundaDica") {
                        SegundaDicaComposable(
                            onBack = { navController.navigate("PrimeiraDica") },
                            onNext = { navController.navigate("TerceiraDica") }
                        )
                    }
                    composable("TerceiraDica") {
                        TerceiraDicaComposable(
                            onBack = { navController.navigate("SegundaDica") },
                            onNext = { navController.navigate("Tesouro") }
                        )
                    }
                    composable("Tesouro") {
                        TesouroComposable {
                            navController.navigate("Home")
                        }
                    }
                }
            }
        }
    }
}
