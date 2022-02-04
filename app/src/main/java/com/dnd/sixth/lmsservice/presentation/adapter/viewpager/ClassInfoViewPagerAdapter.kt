package com.dnd.sixth.lmsservice.presentation.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dnd.sixth.lmsservice.presentation.home.manage.student.info.ClassInfoFragment
import com.dnd.sixth.lmsservice.presentation.home.manage.student.info.UserInfoFragment

class ClassInfoViewPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    private val fragmentList = listOf<Fragment>(ClassInfoFragment(), UserInfoFragment())

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

}