package pe.edu.ulima.dbaccess.ui.app.screens

import android.app.Activity
import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pe.edu.ulima.dbaccess.ui.app.viewmodels.HomeViewModel

@Composable
@Preview
fun HomeScreenPreview(){
    HomeScreen(
        HomeViewModel(),
        rememberNavController()
    )
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavHostController
) {
    // get activity
    val context = LocalContext.current
    val activity = context as Activity
    // load data
    Log.d("HOME_SCREEN", "+++++++++++++++++++++++++++++++++++++++")
    viewModel.setPokemons(activity)
    Text("homeee")
}