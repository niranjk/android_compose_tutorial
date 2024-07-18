package com.niranjan.khatri.androidcomposetutorial

val demo =
    NavItem(
        route = "demoGraph",
        icon = R.drawable.ic_demo,
        title = "Demo",
    )

val home =
    NavItem(
        route = "homeGraph",
        icon = R.drawable.ic_home,
        title = "Home",
    )

data class NavItem(
    val route: String,
    val icon: Int,
    val title: String,
)

class NavGraphConfiguration(
    val destinations: List<NavItem> = emptyList(),
)

val NavGraphConfiguration.startDestination
    get() = destinations.first()
