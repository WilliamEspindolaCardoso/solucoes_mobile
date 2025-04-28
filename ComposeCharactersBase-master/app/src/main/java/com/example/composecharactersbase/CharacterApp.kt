package com.example.composecharactersbase

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// =================== Retrofit ===================
data class Character(
    val character: String,
    val quote: String,
    val image: String
)

interface SimpsonsApiService {
    @GET("quotes?count=10")
    suspend fun getCharacters(): List<Character>
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://thesimpsonsquoteapi.glitch.me/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(SimpsonsApiService::class.java)

// =================== Compose ===================
@Preview
@Composable
fun CharacterApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "character_list") {
        composable("character_list") { CharacterListScreen(navController) }
        composable("favorites") { FavoritesScreen(navController) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(navController: NavHostController) {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("favorites", Context.MODE_PRIVATE) }
    var characters by remember { mutableStateOf<List<Character>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var hasError by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    // Função para carregar mais personagens
    fun loadMoreCharacters() {
        coroutineScope.launch {
            try {
                isLoading = true
                val newCharacters = apiService.getCharacters()
                characters = characters + newCharacters // Junta os novos com os antigos
                isLoading = false
            } catch (e: Exception) {
                e.printStackTrace()
                hasError = true
                isLoading = false
            }
        }
    }

    // Carrega a primeira vez
    LaunchedEffect(Unit) {
        loadMoreCharacters()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Personagens") },
                actions = {
                    IconButton(onClick = { navController.navigate("favorites") }) {
                        Icon(Icons.Default.Star, contentDescription = "Favoritos")
                    }
                }
            )
        }
    ) { padding ->
        if (hasError) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Erro ao carregar personagens")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(characters) { index, character ->

                    CharacterCard(character, prefs)

                    // Quando chegar nos últimos 3 itens, carrega mais
                    if (index >= characters.size - 3 && !isLoading) {
                        loadMoreCharacters()
                    }
                }

                // Mostra um loading no final da lista enquanto carrega mais
                if (isLoading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavHostController) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
    val favorites = prefs.getStringSet("favorites", emptySet()) ?: emptySet()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favoritos") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.StarBorder, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(favorites.toList()) { name ->
                Text(text = name, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
fun CharacterCard(character: Character, prefs: SharedPreferences) {
    val favorites = prefs.getStringSet("favorites", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
    var isFavorite by remember { mutableStateOf(favorites.contains(character.character)) }
    val editor = prefs.edit()
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = character.image),
                contentDescription = "Character Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = character.character, style = MaterialTheme.typography.titleMedium)
                Text(text = character.quote)
            }

            IconButton(
                onClick = {
                    coroutineScope.launch {
                        isFavorite = !isFavorite
                        if (isFavorite) favorites.add(character.character) else favorites.remove(character.character)
                        editor.putStringSet("favorites", favorites).apply()
                    }
                }
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Star else Icons.Default.StarBorder,
                    contentDescription = "Favoritar",
                    tint = if (isFavorite) Color.Yellow else Color.Gray
                )
            }
        }
    }
}
