package com.sample.codebase_sdk.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.sample.codebase_sdk.R
import com.sample.codebase_sdk.util.AppType
import com.sample.codebase_sdk.viewModel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {
    private val viewModel by lazy{
        ViewModelProvider(this)[CharacterViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        viewModel.appType = intent.getSerializableExtra("APP_TYPE") as AppType

        val host = supportFragmentManager.findFragmentById(R.id.frag_container) as NavHostFragment

        setupActionBarWithNavController(host.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.frag_container).navigateUp()
    }

}
