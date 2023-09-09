package com.o7services.musicplayer

import android.content.pm.PackageManager
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    var permission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        if(it){
            //songs
            getSongs()
        }else{
            //alert
            //go to setting
            //exit finish
        }

    }

    var musicList = ArrayList<MusicContent>()
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
            getSongs()
        }else{
            permission.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }

    }

    fun getSongs(){
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC
        val cursor: Cursor? = contentResolver?.query(uri, null,
            selection, null, null)
        if(cursor != null) {
            while (cursor?.moveToFirst() == true) {
                musicList.add(
                    MusicContent(title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)),
                    duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)),
                    artistName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                storageLocation = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)))
                )
            }
        }

    }
}