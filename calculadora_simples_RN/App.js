// import { StatusBar } from 'expo-status-bar';
// import { StyleSheet, Text, View } from 'react-native';
import React, { useState } from 'react';
import { View, StyleSheet } from 'react-native';
import { Provider as PaperProvider, Text, Button, TextInput } from 'react-native-paper';

export default function App() {
  const [display, setDisplay] = useState('');

  const handlePress = (value) => {
    setDisplay((prev) => prev + value);
  };

  const handleClear = () => setDisplay('');

  const handleCalculate = () => {
    try {
      const result = eval(display);
      setDisplay(String(result));
    } catch {
      setDisplay('Erro');
    }
  };

  return (
    <PaperProvider>
      <View style={styles.container}>
        <Text style={styles.title}>Calculadora Simples</Text>

        <TextInput
          mode="outlined"
          label="Resultado"
          value={display}
          style={styles.display}
          editable={false}
        />

        <View style={styles.row}>
          {['7', '8', '9'].map((num) => (
            <CalcButton key={num} label={num} onPress={() => handlePress(num)} />
          ))}
        </View>

        <View style={styles.row}>
          {['4', '5', '6'].map((num) => (
            <CalcButton key={num} label={num} onPress={() => handlePress(num)} />
          ))}
        </View>

        <View style={styles.row}>
          {['1', '2', '3'].map((num) => (
            <CalcButton key={num} label={num} onPress={() => handlePress(num)} />
          ))}
        </View>

        <View style={styles.row}>
          <CalcButton label="0" onPress={() => handlePress('0')} />
          <CalcButton label="C" onPress={handleClear} />
          <CalcButton label="=" onPress={handleCalculate} />
        </View>

        <View style={styles.row}>
          {['+', '-', '*', '/'].map((op) => (
            <CalcButton key={op} label={op} onPress={() => handlePress(op)} />
          ))}
        </View>
      </View>
    </PaperProvider>
  );
}

const CalcButton = ({ label, onPress }) => (
  <Button mode="contained" style={styles.button} onPress={onPress}>
    {label}
  </Button>
);

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    justifyContent: 'center',
    backgroundColor: '#f1f1f1',
  },
  title: {
    fontSize: 26,
    textAlign: 'center',
    marginBottom: 20,
    fontWeight: 'bold',
  },
  display: {
    marginBottom: 20,
    fontSize: 24,
  },
  row: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 10,
  },
  button: {
    flex: 1,
    margin: 4,
    paddingVertical: 10,
  },
});