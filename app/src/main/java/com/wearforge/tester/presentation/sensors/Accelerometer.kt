package com.wearforge.tester.presentation.sensors

import com.mutualmobile.composesensors.AccelerometerSensorState

class Accelerometer {

    fun GetData(AccelerometerState: AccelerometerSensorState): Triple<Float, Float, Float> {

        val x: Float = AccelerometerState.xForce
        val y: Float = AccelerometerState.yForce
        val z: Float = AccelerometerState.zForce

        return Triple(x,y,z)
    }

    fun IsAvaliable(AccelerometerState: AccelerometerSensorState): Boolean {
        return AccelerometerState.isAvailable
    }
}