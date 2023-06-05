package pe.edu.ulima.dbaccess.navigations

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.ulima.dbaccess.ui.app.screens.HomeScreen
import pe.edu.ulima.dbaccess.ui.app.viewmodels.HomeViewModel

@Composable
fun AppNavigation(
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "/"
    ){
        // vista para mostrar el listado de pokemones
        composable(
            route = "/",
            arguments = listOf(
            )
        ){
            Log.d("AppNavigation", "++++++++++++++++++++++++++++++++++++")
            HomeScreen(
                viewModel = HomeViewModel(),
                navController
            )
        }
    }
}