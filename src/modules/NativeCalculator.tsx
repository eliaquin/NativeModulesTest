import {NativeModules} from 'react-native';

const {CalculatorModule} = NativeModules;

export const addNumbers = async (a: number, b: number) => {
  return await CalculatorModule.addNumbers(a, b);
};
