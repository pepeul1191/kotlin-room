package pe.edu.ulima.dbaccess.ui.app.screens

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    Log.d("HOME_SCREEN", "+++++++++++++++++++++++++++++++++++++++")
    Text("homeee")
}