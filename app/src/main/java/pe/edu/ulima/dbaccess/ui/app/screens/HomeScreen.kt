package pe.edu.ulima.dbaccess.ui.app.screens

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import pe.edu.ulima.dbaccess.models.beans.Pokemon
import pe.edu.ulima.dbaccess.navigations.uis.TopBar
import pe.edu.ulima.dbaccess.ui.app.viewmodels.HomeViewModel
import pe.edu.ulima.dbaccess.R

@Composable
@Preview
fun HomeScreenPreview(){
    HomeScreen(
        HomeViewModel(),
        rememberNavController()
    )
}

@OptIn(ExperimentalFoundationApi::class)
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
    Column(
    ) {
        TopBar(
            showBottomSheetDialog = {

            },
            navController,
            1
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(5) // Specify the number of columns
        ) {
            items(viewModel.pokemons!!.size) { i ->
                val pokemon: Pokemon = viewModel.pokemons!![i]
                Image(
                    painter = rememberImagePainter(data = pokemon.imageUrl),
                    contentDescription = pokemon.name,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 10.dp)
                        .clickable {
                            Log.d("POKEMON_SCREEN", pokemon.id.toString())
                            navController.navigate("/pokemon/edit?pokemon_id=${pokemon.id.toString()}")
                        },
                )
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = {
                navController.navigate("/pokemon/new")
            },
            content = {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null
                ) },
            modifier = Modifier.padding(20.dp)
        )
    }
}