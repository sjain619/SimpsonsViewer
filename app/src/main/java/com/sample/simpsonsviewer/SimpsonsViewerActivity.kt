package com.sample.simpsonsviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.codebase_sdk.CharSdk
import com.sample.codebase_sdk.util.AppType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SimpsonsViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CharSdk.launchApplication(applicationContext, AppType.SIMPSONS)
    }
}