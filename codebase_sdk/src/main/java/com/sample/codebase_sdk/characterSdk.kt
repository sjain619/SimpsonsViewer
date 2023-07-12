package com.sample.codebase_sdk

import android.content.Context
import android.content.Intent
import com.sample.codebase_sdk.util.AppType
import com.sample.codebase_sdk.view.CharacterActivity

interface CharacterSdk {
    fun launchApplication(context: Context, appType: AppType)
}

object CharSdk : CharacterSdk{

    override fun launchApplication(context: Context, appType: AppType) {
        Intent(context, CharacterActivity::class.java).apply{
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra("APP_TYPE", appType)
            context.startActivity(this)
        }
    }

}