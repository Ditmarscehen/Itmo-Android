package com.example.fragments


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            setupBottomNavigation()
        }
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigation()
    }
    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        val navGraphIds = listOf(
            R.navigation.home_navigation,
            R.navigation.dashboard_navigation,
            R.navigation.notifications_navigation
        )
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.my_nav_host_fragment,
            intent = intent
        )


        currentNavController = controller
    }
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            var a:Int= 1
            val currentTag = bottom_nav_view.selectedItemId
            Toast.makeText(applicationContext,"$currentTag ${R.id.dashboard_navigation}",Toast.LENGTH_SHORT).show()
            val currentRoot = supportFragmentManager.findFragmentById(a)
            if (currentRoot != null) {
                Toast.makeText(applicationContext,"sasai",Toast.LENGTH_SHORT).show()
                currentRoot.childFragmentManager.popBackStackImmediate()
                if (currentRoot.childFragmentManager.backStackEntryCount == 0) {
                    currentRoot.fragmentManager?.popBackStackImmediate(
                        currentTag,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )
                    val lastTag = currentTag-1
                    if (lastTag != 0 ) {
                        bottom_nav_view.selectedItemId = lastTag
                    } else {
                        finish()
                    }
                }
            }else{
                Toast.makeText(applicationContext,"x_x",Toast.LENGTH_SHORT).show()
            }
        }
    }



}

