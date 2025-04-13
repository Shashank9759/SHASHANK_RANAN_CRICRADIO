package com.example.livescore.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.livescore.presentation.viewmodel.WebSocketViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle

@Composable
fun WebSocketScreen(
    viewModel: WebSocketViewModel = hiltViewModel(),

    onBack: () -> Unit = {}
) {
    var inputMessage by remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        viewModel.connectWebSocket()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = inputMessage,
            onValueChange = { inputMessage = it },
            label = { Text("Message to Send", color = Color.White) },
            textStyle = TextStyle(color = Color.White),
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color.White,
//                unfocusedBorderColor = Color.Gray,
//                cursorColor = Color.White
//            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            viewModel.sendMessage(inputMessage)
            inputMessage = ""
        }) {
            Text("Send")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Received: ${viewModel.receivedMessage}", color = Color.White)

        viewModel.connectionError?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Error: $it", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}