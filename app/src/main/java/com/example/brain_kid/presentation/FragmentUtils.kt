package com.example.brain_kid.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.brain_kid.R

object FragmentUtils {

    fun launchFragmentWithAnimation(
        fragmentManager : FragmentManager,
        containerId : Int,
        fragment : Fragment,
        slideIn: Int = R.anim.slide_in,
        fadeOut: Int = R.anim.fade_out,
        fadeIn: Int = R.anim.fade_in,
        slideOut: Int = R.anim.slide_out
    ) {

        fragmentManager.beginTransaction()
            .setCustomAnimations(slideIn, fadeOut, fadeIn, slideOut)
            .replace(containerId, fragment)
            .addToBackStack(null)
            .commit()

    }
}