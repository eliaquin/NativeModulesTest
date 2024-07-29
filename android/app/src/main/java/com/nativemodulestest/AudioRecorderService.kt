package com.nativemodulestest

import android.content.Context
import android.media.MediaRecorder
import java.io.File
import java.io.IOException

class AudioRecorderService {
    private var mediaRecorder: MediaRecorder? = null
    private var audioFile: File? = null

    @Throws(IOException::class)
    fun startRecording(context: Context, suffix: String): String {
        if (mediaRecorder != null) {
            throw IOException("Recording already in progress")
        }

        mediaRecorder = MediaRecorder(context).apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.AMR_NB)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setAudioEncodingBitRate(12200)
            setAudioSamplingRate(8000)

            audioFile = File(context.getExternalFilesDir(null), "audio_record_${suffix}.amr")
            setOutputFile(audioFile?.absolutePath)

            prepare()
            start()
        }
        return "Recording started"
    }

    fun stopRecording(): String? {
        try {
            mediaRecorder?.apply {
                stop()
                release()
            }
            val filePath = audioFile?.absolutePath
            mediaRecorder = null
            audioFile = null
            return filePath
        } catch (e: Exception) {
            throw e
        }
    }
}
