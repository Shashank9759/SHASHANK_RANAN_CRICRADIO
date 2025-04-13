# 📱 CricRadio App – Android Assignment

This project is an Android application developed as part of an assignment for the Android Intern position. The application replicates specific functionalities of the existing CricRadio app, focusing on UI design, API integration, and WebSocket communication.

---

## 📌 Assignment Overview

**Objective:**

Recreate a specific screen from the CricRadio app using **Jetpack Compose**, integrating data from two API endpoints, and implementing real-time communication via **WebSockets**.

**Key Requirements:**

- 🎨 Design a responsive UI based on the provided Figma design  
- 🔗 Integrate two API endpoints to fetch match and venue information  
- 🌐 Implement a WebSocket connection to demonstrate real-time data handling  
- 🧱 Follow best practices in Android development, including MVVM architecture and dependency injection using **Dagger Hilt**

---

## 🛠️ Tech Stack

- **Language:** Kotlin  
- **UI Framework:** Jetpack Compose  
- **Architecture:** MVVM (Model-View-ViewModel)  
- **Networking:** Ktor Client  
- **Dependency Injection:** Dagger Hilt  
- **WebSocket:** Ktor WebSocket Client  
- **Serialization:** Kotlinx Serialization  
- **Minimum SDK:** API 21  
- **Target SDK:** API 34  

---

## 🔗 API Integration

**Base URL:**  
`http://3.6.243.12:5001`

**Headers:**  
`Authorization: Basic Y3JpY2tldFJhZGlvOmNyaWNrZXRAJCUjUmFkaW8xMjM=`

### Endpoints

- **Mini Scorecard:**  
  `GET /api/v2/match/mini-match-card`  
  **Parameter:** `key=SA_vs_SL_2024-12-05_1732276435.300452`

- **Venue Info:**  
  `GET /api/v2/match/venue-info`  
  **Parameter:** `key=SA_vs_SL_2024-12-05_1732276435.300452`

### Implementation Details

- API calls are made using the **Ktor client** with appropriate headers and parameters  
- Responses are parsed using **Kotlinx Serialization**  
- Data is exposed to the UI through **ViewModels**, adhering to **MVVM architecture**

---

## 🔄 WebSocket Integration

**WebSocket Server:**  
`wss://ws.postman-echo.com/raw`

### Functionality

- Establishes a WebSocket connection using **Ktor WebSocket client**  
- Provides a UI with a text field to input messages and a button to send them  
- Displays the echoed response from the server in real-time  
- Implements error handling and retry logic for failed connections  

> ⚠️ Ensure that your network allows connections to the specified WebSocket server

---

## 📸 Screenshots

### 🏏 Main Scorecard Screen  
![Main Scorecard](https://github.com/user-attachments/assets/1d53a799-9623-4f82-8878-d680067afa07)

### 🏟️ Venue Information  
![Venue Info](https://github.com/user-attachments/assets/a926a44b-3d99-48a2-aa26-27aba325c3ff)

### 🔄 WebSocket Communication  
![WebSocket](https://github.com/user-attachments/assets/eed445aa-5392-4a6c-a3e9-f5f2d52b5a48)

### 📲 Responsive UI  
![Responsive UI](https://github.com/user-attachments/assets/7efac69d-a57c-4c26-8dec-ac0d1ae1e49b)

---

## ✅ Features Implemented

- ✅ Responsive UI matching the provided Figma design  
- ✅ Integration of two API endpoints to fetch and display match and venue information  
- ✅ Real-time communication using WebSockets with message echo functionality  
- ✅ Clean architecture following the MVVM pattern  
- ✅ Dependency injection using Dagger Hilt  
- ✅ Error handling and retry mechanisms for network operations
