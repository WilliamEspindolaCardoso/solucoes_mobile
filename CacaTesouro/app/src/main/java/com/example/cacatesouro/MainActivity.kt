package com.example.cacatesouro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cacatesouro.ui.theme.CacaTesouroTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CacaTesouroTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "Home") {
                    composable("Home") {
                        Home {
                            navController.navigate("Pergunta")
                        }
                    }

                    composable("Pergunta") {
                        Pergunta(
                            onBack = { navController.navigate("Home") },
                            onNext = { navController.navigate("Pista1") }
                        )
                    }

                    composable("Pista1") {
                        Pista1(
                            onBack = { navController.navigate("Pergunta") },
                            onNext = { navController.navigate("Pista2") }
                        )
                    }

                    composable("Pista2") {
                        Pista2(
                            onBack = { navController.navigate("Pista1") },
                            onNext = { navController.navigate("Pista3") }
                        )
                    }

                    composable("Pista3") {
                        Pista3(
                            onBack = { navController.navigate("Pista2") },
                            onNext = { navController.navigate("Tesouro") }
                        )
                    }

                    composable("Tesouro") {
                        Tesouro {
                            navController.navigate("Home")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    CacaTesouroTheme {
        Home(onStart = {})
    }
}
