package com.globa.pexelstestapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.globa.pexelstestapp.home.HomeScreen

@Composable
fun NavController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable(
            route = "home"
        ) {
            HomeScreen(onPhotoClick = {})
        }
        composable(
            route = "details?photoId={photoId}",
            arguments = listOf(navArgument("photoId") { type = NavType.IntType })
        ) {
            //PhotoDetailsScreen
        }
        composable(
            route = "bookmarks"
        ) {
            //BookmarksScreen
        }
    }
}