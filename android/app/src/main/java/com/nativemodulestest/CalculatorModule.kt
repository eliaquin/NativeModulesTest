package com.nativemodulestest

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise

class CalculatorModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "CalculatorModule"
    }

    @ReactMethod
    fun addNumbers(a: Double, b: Double, promise: Promise) {
        try {
            val result = a + b
            promise.resolve(result)
        } catch (e: Exception) {
            promise.reject("CALCULATION_ERROR", e)
        }
    }
}