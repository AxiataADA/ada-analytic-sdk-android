package com.example.adaanalyticdemo

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.ada.analytic.ADAAnalytic
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private var analytic = ADAAnalytic.sharedInstance
    private var BTAdapter = BluetoothAdapter.getDefaultAdapter()

    private val REQUEST_BLUETOOTH = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE),
                1234)
            return
        } else {

            analytic.setup(Constants.APP_NAME ,Constants.URL ,Constants.APP_KEY ,Constants.APP_SECRET ,applicationContext)
        }

        logo.setOnClickListener {
            gotoADA()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1234) {
            analytic.setup(
                Constants.APP_NAME,
                Constants.URL,
                Constants.APP_KEY,
                Constants.APP_SECRET,
                applicationContext
            )
        }
    }


    fun requestBluetooth(view: View) {
        if (BTAdapter != null) {
            if (!BTAdapter.isEnabled()) {
                val enableBT = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBT, REQUEST_BLUETOOTH)
            } else {
                Toast.makeText(this, "Bluetooth already enabled.", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Your phone does not support Bluetooth", Toast.LENGTH_LONG).show()
        }

    }

    fun gotoADA() {
        val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://ada-asia.com"))
        startActivity(i)
    }

    fun login(view: View) {
        analytic.logEvent("Login", mapOf("username" to "username"))
    }

    fun addFriend(view: View) {
        analytic.logEvent("Add Friend", mapOf("friend_id" to "123456"))
    }

    fun purchase(view: View) {
        analytic.logEvent("Purchase", mapOf("user" to "username", "purchase_id" to "223323"))
    }
}