package com.nativemodulestest

import com.facebook.react.bridge.*
import java.io.IOException

class AudioRecorderModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    private val audioRecorderService = AudioRecorderService(reactContext)

    override fun getName(): String {
        return "AudioRecorderModule"
    }

    @ReactMethod
    fun startRecording(suffix: String, promise: Promise) {
        try {
            val result = audioRecorderService.startRecording(suffix)
            promise.resolve(result)
        } catch (e: IOException) {
            promise.reject("AUDIO_RECORD_ERROR", "Failed to start recording: ${e.message}")
        }
    }

    @ReactMethod
    fun stopRecording(promise: Promise) {
        try {
            val filePath = audioRecorderService.stopRecording()
            promise.resolve(filePath)
        } catch (e: Exception) {
            promise.reject("AUDIO_RECORD_ERROR", "Failed to stop recording: ${e.message}")
        }
    }
}
