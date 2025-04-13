package com.example.livescore.domain.usecase



import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch

class WebSocketUseCase @Inject constructor(
    private val httpClient: HttpClient
) {

    private var session: WebSocketSession? = null
    private var collectJob: Job? = null

    suspend fun connect(
        onReceive: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {
            session = httpClient.webSocketSession {
                url {
                    protocol = URLProtocol.WSS
                    host = "ws.postman-echo.com"
                    port = 443
                    encodedPath = "/raw"
                }
            }

            collectJob = CoroutineScope(Dispatchers.IO).launch {
                try {
                    session?.incoming
                        ?.consumeAsFlow()
                        ?.filterIsInstance<Frame.Text>()
                        ?.collect { frame ->
                            onReceive(frame.readText())
                        }
                } catch (e: Exception) {
                    onError(Exception("Connection error: ${e.message}"))
                }
            }

            // Send initial test message
            sendMessage("test-connection")

        } catch (e: Exception) {
            onError(Exception("Connection failed: ${e.message}"))
        }
    }

    suspend fun sendMessage(message: String) {
        try {
            session?.outgoing?.send(Frame.Text(message))
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun disconnect() {
        try {
            session?.close()
            collectJob?.cancel()
            session = null
        } catch (e: Exception) {

        }
    }
}
