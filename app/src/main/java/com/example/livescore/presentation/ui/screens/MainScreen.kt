package com.example.livescore.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.livescore.presentation.viewmodel.ScoreViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    viewModel: ScoreViewModel = hiltViewModel(),
    onDetailsClick: () -> Unit = {} // This navigation callback will go to WebSocket screen
) {
    // Trigger data loading on first composition.
    LaunchedEffect(Unit) {
        viewModel.loadMatchData(key = "SA_vs_SL_2024-12-05_1732276435.300452")
    }
    // Extract the API responses via your ViewModel.
   val miniCard = viewModel.miniCard
    val venueInfo = viewModel.venueInfo
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF000000)
    ) {
        when {
            isLoading || miniCard==null || venueInfo==null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
            errorMessage != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error: $errorMessage", color = MaterialTheme.colorScheme.error)
                }
            }
            else -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize().background(Color(0xFF000000))
                       ,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ){


                    Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp,top=16.dp).background(Color(0xFF000000))){
                        ScoreScreen(miniCard)
                    }
                    VenueScreen(venueInfo)





                }
                }
            }
        }

}
