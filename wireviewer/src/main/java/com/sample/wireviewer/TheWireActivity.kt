package com.sample.wireviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.codebase_sdk.CharSdk
import com.sample.codebase_sdk.util.AppType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TheWireActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CharSdk.launchApplication(applicationContext, AppType.THE_WIRE)
    }
}