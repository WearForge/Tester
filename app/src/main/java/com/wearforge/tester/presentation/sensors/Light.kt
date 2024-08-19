package com.wearforge.tester.presentation.sensors

import com.mutualmobile.composesensors.LightSensorState

class Light {

    fun GetData(LightState: LightSensorState): Float {
        val illuminance: Float = LightState.illuminance

        return illuminance
    }

    fun IsAvaliable(LightState: LightSensorState): Boolean {
        return LightState.isAvailable
    }
}