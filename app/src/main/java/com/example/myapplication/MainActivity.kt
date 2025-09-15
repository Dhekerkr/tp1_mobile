package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement // <= needed for Row spacing
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppNavigation() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            // One TopBar for all screens; title changes with route
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            val title = when (currentRoute) {
                "inscription" -> "Inscription"
                "desole" -> "Désolé"
                else -> "Accueil"
            }
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = { /* no-op */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { /* no-op */ }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favori")
                    }
                }
            )
        }
    ) { inner ->
        NavHost(
            navController = navController,
            startDestination = "accueil",
            modifier = Modifier.padding(inner)
        ) {
            composable("accueil") {
                EventsScreen(
                    onInscription = { navController.navigate("inscription") },
                    onDesole = { navController.navigate("desole") }
                )
            }
            composable("inscription") {
                InscriptionScreen(onBack = { navController.popBackStack() })
            }
            composable("desole") {
                DesoleScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}

@Composable
fun EventsScreen(onInscription: () -> Unit, onDesole: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Bloc du milieu
        Column(
            modifier = Modifier
                .weight(1f) // ✅ prend tout l’espace restant
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // ✅ centre verticalement
        ) {
            Image(
                painter = painterResource(id = R.drawable.event),
                contentDescription = "Affiche de l'évènement",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(16.dp))

            Text("Où : Ecole ingénieur ISIS", fontWeight = FontWeight.SemiBold)
            Text("Quand : 24 octobre")
        }

        // Bloc du bas (boutons)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(modifier = Modifier.weight(1f), onClick = onInscription) {
                Text("Inscription")
            }
            OutlinedButton(modifier = Modifier.weight(1f), onClick = onDesole) {
                Text("Pas intéressé")
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun EventsScreenPreview() {
    EventsScreen(onInscription = {}, onDesole = {})
}
