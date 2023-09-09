package com.o7services.musicplayer

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    var permission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        if(it){
            //songs
        }else{
            //alert
            //go to setting
            //exit finish
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        if(ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED)
        {

        }else{
            permission.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }

    }
}