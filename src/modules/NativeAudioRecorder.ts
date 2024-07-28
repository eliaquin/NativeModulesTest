import {NativeModules} from 'react-native';

const {AudioRecorderModule} = NativeModules;

export const startRecordingAsync = (suffix: string) =>
  AudioRecorderModule.startRecording(suffix);

export const stopRecordingAsync = () => AudioRecorderModule.stopRecording();
