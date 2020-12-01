package com.rgoe.camera

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.params.OutputConfiguration
import android.hardware.camera2.params.SessionConfiguration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private val REQUEST_CAMERA = 10
    private val executorCamera = Executors.newSingleThreadExecutor()

    private val CAMERA_FRONT = 1
    private val CAMERA_BACK = 0
    private var cameraID = CAMERA_FRONT

    private lateinit var surfacePreview: SurfaceView
    private lateinit var cameraDevice: CameraDevice

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CAMERA) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                configCamera()
            } else {
                Toast.makeText(this, "Es necesario aceptar el permiso de la camara para este ejercicio", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val switchButton = findViewById<FloatingActionButton>(R.id.switchCamera)
        surfacePreview = findViewById<SurfaceView>(R.id.surfaceView)

        switchButton.setOnClickListener{
            switchCamera()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            configCamera()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
        }
    }

    @SuppressLint("MissingPermission")
    fun configCamera() {

        surfacePreview.holder.addCallback(object: SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {

                val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager

                cameraManager.openCamera(cameraManager.cameraIdList.first(),
                    callback, null)
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) { }

            override fun surfaceDestroyed(holder: SurfaceHolder) { }

        })
    }

    private var callback = object: CameraDevice.StateCallback() {

        override fun onOpened(camera: CameraDevice) {
            cameraDevice = camera
            val builderCapture  = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
            builderCapture.addTarget(surfacePreview.holder.surface)

            val captureRequest = builderCapture.build()

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                camera.createCaptureSession(
                    listOf(surfacePreview.holder.surface),
                    object: CameraCaptureSession.StateCallback() {
                        override fun onConfigured(session: CameraCaptureSession) {
                            session.setRepeatingRequest(captureRequest, null, null)
                        }

                        override fun onConfigureFailed(session: CameraCaptureSession) { }

                    },
                    null
                )
            } else {
                val sessionConfiguration = SessionConfiguration(
                    SessionConfiguration.SESSION_REGULAR,
                    listOf(
                        OutputConfiguration(surfacePreview.holder.surface)
                    ),
                    executorCamera,
                    object: CameraCaptureSession.StateCallback() {
                        override fun onConfigured(session: CameraCaptureSession) {
                            session.setRepeatingRequest(captureRequest, null, null)
                        }

                        override fun onConfigureFailed(session: CameraCaptureSession) { }

                    }
                )

                camera.createCaptureSession(sessionConfiguration)
            }
        }

        override fun onDisconnected(camera: CameraDevice) { }

        override fun onError(camera: CameraDevice, error: Int) { }

    }

    @SuppressLint("MissingPermission")
    fun switchCamera(){
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        cameraDevice.close()
        if(cameraID == CAMERA_FRONT){
            cameraID=CAMERA_BACK

            cameraManager.openCamera(cameraID.toString(), callback, null)
        }else if (cameraID == CAMERA_BACK){
            cameraID=CAMERA_FRONT
            cameraManager.openCamera(cameraID.toString(), callback, null)
        }
    }
}