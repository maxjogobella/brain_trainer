package com.example.brain_kid.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.brain_kid.R
import com.example.brain_kid.presentation.fragment.FragmentLevel
import com.example.brain_kid.presentation.fragment.OnBoarding1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val valueSharedPref = getPreferences(SHARED_PREF_KEY_ONBOARDING, ONBOARDING_VISIBLE)

        if (valueSharedPref == ONBOARDING_VISIBLE) {
            launchOnBoarding()
        }

        if (valueSharedPref == ONBOARDING_INVISIBLE) {
            launchLevelFragment()
        }
    }

    private fun launchOnBoarding() {
        FragmentUtils.launchFragmentWithAnimation(
            supportFragmentManager,
            R.id.main_container,
            OnBoarding1.newInstance()
        )

    }

    private fun launchLevelFragment() {
        FragmentUtils.launchFragmentWithAnimation(
            supportFragmentManager,
            R.id.main_container,
            FragmentLevel.newInstance()
        )
    }


    private fun getPreferences(
        key : String,
        defaultValue : Int
    ) : Int {
        return getSharedPreferences(
            SHARED_PREF_NAME, MODE_PRIVATE
        ).getInt(key, defaultValue)
    }

    companion object {

        const val ONBOARDING_VISIBLE = 1
        const val ONBOARDING_INVISIBLE = 2

        const val SHARED_PREF_KEY_ONBOARDING = "pref_onboarding"
        const val SHARED_PREF_NAME = "shared_pref"
    }

}