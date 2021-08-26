package com.example.lanterna

import android.content.pm.PackageManager.FEATURE_CAMERA_ANY
import android.content.pm.PackageManager.FEATURE_CAMERA_FLASH
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.camera2.CameraManager
import android.os.Build
import android.util.Log
import android.widget.Switch
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity(), SensorEventListener {
    lateinit var sensorManager: SensorManager
    var lumi: Sensor? = null
    var ambient: Float = 0.0f

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var flashcontrol: Switch
        var cameraManager: CameraManager
        var piscar: Switch

        piscar = findViewById(R.id.flash)
        flashcontrol = findViewById(R.id.turnflash)
        cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager

        flashcontrol.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                cameraManager.setTorchMode("0",true)
                flashcontrol.setText("Flash ON")
            }else{
                cameraManager.setTorchMode("0",false)
                flashcontrol.setText("Flash OFF")
            }
        }
        setupSensor()
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun luminosidade(lumi: Float){
        var cameraManager: CameraManager
        cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        if(lumi < 1) {
            cameraManager.setTorchMode("0", true)

        }
    }

    fun setupSensor(){
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        lumi = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onSensorChanged(p0: SensorEvent) {
        Log.v("Test","Bora: ${p0.values[0]}")
        ambient = p0.values[0]
        luminosidade(ambient)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, lumi, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}


