# MarketMinds

**MarketMinds** is an Android application that provides categorized news along with real-time stock market data, making it a useful tool for staying informed on multiple fronts.

## Features

- Categorized news in sections like:
  - Entertainment
  - Sports
  - Science
  - Technology
  - Health
  - Business
- Real-time stock market data using [Alpha Vantage API](https://www.alphavantage.co/).
- Splash screen with login and sign-up pages.
- Simple and intuitive homepage for easy navigation between categories.
- Custom fonts for a modern look.
- Supports data backup and targets Android API level 31.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/marketminds.git
   Open the project in Android Studio.

2.Open the project in Android Studio.

3.Build and run the app on an emulator or a physical device.

##API Integration
This app uses the Alpha Vantage API to fetch real-time stock data. To use the API:

1.Get your free API key from Alpha Vantage.

2.Replace the placeholder API key in the code with your own key.
val response = service.getStockData(
    function = "GLOBAL_QUOTE",
    symbol = symbol,
    apiKey = "YOUR_API_KEY_HERE"
)


