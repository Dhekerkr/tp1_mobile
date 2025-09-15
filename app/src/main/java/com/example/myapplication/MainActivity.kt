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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { EventsScreen() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Evènements") },
                actions = {
                    IconButton(onClick ={}) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favori")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {

                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
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

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(modifier = Modifier.weight(1f), onClick ={}) {
                    Text("Inscription")
                }
                OutlinedButton(modifier = Modifier.weight(1f), onClick ={}) {
                    Text("Pas intéressé")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventsScreenPreview() = EventsScreen()