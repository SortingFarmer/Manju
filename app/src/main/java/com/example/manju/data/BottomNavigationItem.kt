package com.example.manju.data

data class BottomNavigationItem(
    val route: String,
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val badgeCount: Int? = 0,
)