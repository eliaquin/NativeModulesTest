package com.nativemodulestest

import android.media.MediaRecorder
import android.os.Environment
import com.facebook.react.bridge.*
import java.io.File
import java.io.IOException

class AudioRecorderModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    private var mediaRecorder: MediaRecorder? = null
    private var audioFile: File? = null

    override fun getName(): String {
        return "AudioRecorderModule"
    }

    @ReactMethod
    fun startRecording(suffix: String, promise: Promise) {
        try {
            mediaRecorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.AMR_NB) // Optimized for voice
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB) // Optimized for voice
                setAudioEncodingBitRate(12200) // Standard bitrate for AMR-NB
                setAudioSamplingRate(8000) // Standard sample rate for AMR-NB

                audioFile = File(reactApplicationContext.getExternalFilesDir(null), "audio_record_${suffix}.amr")
                setOutputFile(audioFile?.absolutePath)

                prepare()
                start()
            }
            promise.resolve("Recording started")
        } catch (e: IOException) {
            promise.reject("AUDIO_RECORD_ERROR", "Failed to start recording: ${e.message}")
        }
    }

    @ReactMethod
    fun stopRecording(promise: Promise) {
        try {
            mediaRecorder?.apply {
                stop()
                release()
            }
            mediaRecorder = null
            promise.resolve(audioFile?.absolutePath)
        } catch (e: Exception) {
            promise.reject("AUDIO_RECORD_ERROR", "Failed to stop recording: ${e.message}")
        }
    }
}