# Native Modules Test: Audio Recording in React Native

This project demonstrates the implementation of native modules in React Native, specifically focusing on audio recording functionality with background support on Android.

## Project Overview

This React Native application showcases the integration of native Android modules to enable audio recording capabilities. Key features include:

- Custom native module for audio recording
- Background audio recording using Android's foreground services
- Support for Android API level 34 and above

## Technical Implementation

The project includes the following key components:

1. `AudioRecorderModule`: A React Native bridge module that exposes audio recording functions to JavaScript.
2. `AudioRecorderService`: A Kotlin service that handles the actual audio recording functionality.
3. `AudioRecorderForegroundService`: An Android foreground service that allows audio recording to continue in the background.

## Getting Started

### Prerequisites

Ensure you have completed the [React Native - Environment Setup](https://reactnative.dev/docs/environment-setup) before proceeding.

### Installation

1. Clone the repository
2. Install dependencies:
   ```bash
   npm install
   # or
   yarn install
   
### Running the Application

1. Start the Metro server:

   ```bash
   npm start
   # or
   yarn start

2. Run the app on Android:

   ```bash
   npm run android
   # or
   yarn android

### Usage
The app provides a simple interface to start and stop audio recording. The recording will continue even when the app is in the background, thanks to the implemented foreground service.

### Permissions
No code has been added to handle permissions on this test app. Make sure to manually grant the necessary permissions for audio recording and background services.
