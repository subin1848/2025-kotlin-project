package com.example.studytimerproject

import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        // tab-layout과 view-pager 연결
        configureBottomNavigation()

        // 유저 정보 표시
        val currentUser = FirebaseAuth.getInstance().currentUser
        val email = currentUser?.email

        // 우선 email -> 이후 nickname으로 변경
        Toast.makeText(this, "환영합니다. $email 님!", Toast.LENGTH_SHORT).show()
    }

    private fun configureBottomNavigation() {
        val viewPager = findViewById<androidx.viewpager.widget.ViewPager>(R.id.vp_ac_main_frag_pager)
        val tabLayout = findViewById<com.google.android.material.tabs.TabLayout>(R.id.tl_ac_main_bottom_menu)

        // viewpager와 adapter 연결
        viewPager.adapter = MainFragmentStatePagerAdapter(supportFragmentManager, 3)
        // tablayout과 viewpager 연결
        tabLayout.setupWithViewPager(viewPager)

        // bottom navigation custom
        val bottomNaviLayout: View =
            this.layoutInflater.inflate(R.layout.bottom_tab, null, false)

        tabLayout.getTabAt(0)?.customView =
            bottomNaviLayout.findViewById(R.id.btn_bottom_navi_chart_tab) as RelativeLayout
        tabLayout.getTabAt(1)?.customView =
            bottomNaviLayout.findViewById(R.id.btn_bottom_navi_home_tab) as RelativeLayout
        tabLayout.getTabAt(2)?.customView =
            bottomNaviLayout.findViewById(R.id.btn_bottom_navi_account_tab) as RelativeLayout
    }
}