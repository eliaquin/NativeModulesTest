import React, {useState} from 'react';
import {
  SafeAreaView,
  StatusBar,
  useColorScheme,
  Button,
  View,
  StyleSheet,
} from 'react-native';
import {
  startRecordingAsync,
  stopRecordingAsync,
} from './src/modules/NativeAudioRecorder.ts';
import {Colors} from 'react-native/Libraries/NewAppScreen';

function App(): React.JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';
  const [isRecording, setIsRecording] = useState(false);

  const color = isDarkMode ? Colors.darker : Colors.lighter;

  const styles = StyleSheet.create({
    container: {
      display: 'flex',
      flexDirection: 'column',
      flex: 1,
      justifyContent: 'center',
      alignItems: 'center',
    },
    backgroundStyle: {
      backgroundColor: color,
      flex: 1,
    },
  });

  const handleRecording = async () => {
    if (!isRecording) {
      await startRecordingAsync('lego');
      setIsRecording(true);
    } else {
      const file = await stopRecordingAsync();
      setIsRecording(false);
      console.log(file);
    }
  };

  return (
    <SafeAreaView style={styles.backgroundStyle}>
      <StatusBar
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
        backgroundColor={color}
      />
      <View style={styles.container}>
        <Button onPress={handleRecording} title="Toggle recording" />
      </View>
    </SafeAreaView>
  );
}

export default App;
