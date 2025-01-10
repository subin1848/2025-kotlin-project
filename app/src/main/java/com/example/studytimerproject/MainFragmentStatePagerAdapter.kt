package com.example.studytimerproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MainFragmentStatePagerAdapter(fm: FragmentManager, val fragmentCount: Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ChartFragment()
            1 -> HomeFragment()
            2 -> AccountFragment()
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }

    override fun getCount(): Int = fragmentCount
}