# NegotiAI

> "Negotiate Smarter. Win Better Deals."

NegotiAI is a premium, AI-powered negotiation assistant built for Android using Kotlin and Jetpack Compose. It analyzes job offers, contracts, vendor quotes, and salary hikes to uncover hidden clauses, assess market competitiveness, and generate strategic counter-offers to help you secure the best possible deal.

## Features

- **Modern Premium Interface**: Sleek dark mode design inspired by top-tier SaaS platforms, built purely with Jetpack Compose.
- **AI Document Analysis**: Submit details of your offer and let the Gemini-powered AI analyze the terms for risks and opportunities.
- **Strategic Insights**: Receive a calculated breakdown of your offer's strengths, weaknesses, and a recommended strategy.
- **Local History Tracking**: All past negotiations are securely saved locally on your device using Room database for easy review.
- **Dynamic Theming**: Fluid and highly polished layout utilizing Material 3 components, animations, and typography.

## Tech Stack

- **UI**: Jetpack Compose (Material Design 3)
- **Language**: Kotlin
- **Architecture**: MVVM, Clean Architecture patterns
- **Local Database**: Room
- **Networking**: Retrofit, OkHttp, kotlinx.serialization
- **AI Engine**: Google Gemini API

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone <repository_url>
   cd NegotiAI
   ```

2. **Configure API Keys**
   This application requires a Google Gemini API key to function.
   - Go to [Google AI Studio](https://aistudio.google.com/app/apikey) and get a free Gemini API Key.
   - In the root of the project, create a file named `.env` (or copy `.env.example`).
   - Add your API key to the file:
     ```
     GEMINI_API_KEY="your_api_key_here"
     ```

3. **Build & Run**
   Open the project in Android Studio and sync Gradle files. Ensure you have the Android SDK installed and configured.
   Build and run on your preferred emulator or physical device.
