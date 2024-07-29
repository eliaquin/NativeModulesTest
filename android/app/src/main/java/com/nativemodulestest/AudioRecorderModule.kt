package com.nativemodulestest

import android.content.Intent
import com.facebook.react.bridge.*

class AudioRecorderModule(private val reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String = "AudioRecorderModule"

    @ReactMethod
    fun startRecording(suffix: String, promise: Promise) {
        try {
            val intent = Intent(reactContext, AudioRecorderForegroundService::class.java).apply {
                action = "START_RECORDING"
                putExtra("suffix", suffix)
            }
            reactContext.startForegroundService(intent)
            promise.resolve("Recording started")
        } catch (e: Exception) {
            promise.reject("AUDIO_RECORD_ERROR", "Failed to start recording: ${e.message}")
        }
    }

    @ReactMethod
    fun stopRecording(promise: Promise) {
        try {
            val intent = Intent(reactContext, AudioRecorderForegroundService::class.java).apply {
                action = "STOP_RECORDING"
            }
            reactContext.startForegroundService(intent)
            promise.resolve("Recording stopped")
        } catch (e: Exception) {
            promise.reject("AUDIO_RECORD_ERROR", "Failed to stop recording: ${e.message}")
        }
    }
}
