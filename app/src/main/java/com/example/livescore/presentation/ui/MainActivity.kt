package com.example.livescore.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.livescore.Utils.Screen
import com.example.livescore.presentation.ui.screens.MainScreen
import com.example.livescore.presentation.ui.screens.WebSocketScreen
import com.example.livescore.ui.theme.LiveScoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiveScoreTheme {
                MainApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000)),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "CricRadio App",
                        color = Color.White
                    )
                },
                actions = {
                    ElevatedButton(
                        onClick = { navController.navigate(Screen.WebSocket) },
                        shape = MaterialTheme.shapes.small,
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = Color(0xFF424242),
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 12.dp,
                            focusedElevation = 8.dp,
                            hoveredElevation = 8.dp
                        ),
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        Text("WebSocket")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Score,
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.Black)
        ) {
            composable(Screen.Score) {
                MainScreen(onDetailsClick = { })
            }
            composable(Screen.WebSocket) {
                WebSocketScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}
