import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { useEffect, useState } from 'react';
import { FlatList, Image, ScrollView, StyleSheet, Text, TouchableOpacity, View } from 'react-native';

// Função para buscar filmes da API pública
const getMovies = async () => {
  try {
    const response = await fetch('https://jsonplaceholder.typicode.com/posts');
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Erro ao buscar filmes:', error);
    return [];
  }
};

export default function App() {
  const [movies, setMovies] = useState([]);

  useEffect(() => {
    getMovies().then(setMovies);
  }, []);

  return (
    <View style={styles.container}>
      <FlatList
        data={movies}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <TouchableOpacity style={styles.card}>
            <Text style={styles.title}>{item.title}</Text>
            <Text>{item.body}</Text>  {/* Aqui você pode colocar mais detalhes */}
          </TouchableOpacity>
        )}
      />
    </View>
  );
}

// Tela inicial (lista de filmes)
const HomeScreen = ({ navigation }) => {
  const [movies, setMovies] = useState([]);

  useEffect(() => {
    getMovies().then(setMovies);
  }, []);

  return (
    <View style={styles.container}>
      <FlatList
        data={movies}
        keyExtractor={(item, index) => index.toString()}
        renderItem={({ item }) => (
          <TouchableOpacity
            style={styles.card}
            onPress={() => navigation.navigate('Details', { movie: item })}
          >
            <Text style={styles.title}>{item.title}</Text>
          </TouchableOpacity>
        )}
      />
    </View>
  );
};

// Tela de detalhes
const DetailsScreen = ({ route }) => {
  const { movie } = route.params;

  return (
    <ScrollView contentContainerStyle={styles.detailsContainer}>
      {movie.posterUrl && (
        <Image source={{ uri: movie.posterUrl }} style={styles.image} />
      )}
      <Text style={styles.detailsTitle}>{movie.title}</Text>
      <Text style={styles.overview}>{movie.plot || 'Sem descrição disponível.'}</Text>
      <Text style={styles.info}>Ano: {movie.year}</Text>
      <Text style={styles.info}>Diretor: {movie.director}</Text>
      <Text style={styles.info}>Atores: {movie.actors}</Text>
    </ScrollView>
  );
};

// Navegação
const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={HomeScreen} options={{ title: 'Filmes de Ação' }} />
        <Stack.Screen name="Details" component={DetailsScreen} options={{ title: 'Detalhes do Filme' }} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

// Estilos
const styles = StyleSheet.create({
  container: { flex: 1, padding: 16 },
  card: {
    padding: 16,
    marginBottom: 12,
    backgroundColor: '#eee',
    borderRadius: 8,
  },
  title: { fontSize: 16, fontWeight: 'bold' },
  detailsContainer: { padding: 16, alignItems: 'center' },
  image: {
    width: 300,
    height: 450,
    borderRadius: 10,
    marginBottom: 20,
    backgroundColor: '#ccc',
  },
  detailsTitle: { fontSize: 22, fontWeight: 'bold', marginBottom: 10, textAlign: 'center' },
  overview: { fontSize: 16, textAlign: 'justify', marginBottom: 10 },
  info: { fontSize: 14, marginTop: 4 }
});
