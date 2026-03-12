# AI Smart Email Assistant 📧🤖

1)Download the email-writer-ext folder from GitHub.

2)Open Chrome and go to chrome://extensions/.

3)Enable Developer Mode (top right).

4)Click Load unpacked and select the folder.

THE EXTENSION WILL BE UP RUNNING.

An AI-powered, full-stack application and Chrome Extension that integrates directly into Gmail to generate context-aware, professional email replies. 

This project consists of a Spring Boot REST API, a React web dashboard, and a custom Chrome Extension that injects an "AI Reply" button directly into the Gmail compose window.

---

## 🌟 Features

- **Gmail Integration:** Seamlessly injects an "AI Reply" button into the Gmail user interface.
- **Context-Aware Replies:** Automatically reads the incoming email and generates a relevant, professional reply using Google's Gemini AI.
- **Customizable Tones:** Choose between Professional, Casual, or Friendly tones for your generated responses.
- **Web Dashboard:** Includes a responsive React web interface to test and use the AI email generator outside of Gmail.
- **Secure Backend:** A Spring Boot backend utilizing Spring WebFlux to securely communicate with the external AI API.

---

## 🛠️ Tech Stack

- **Backend:** Java 17, Spring Boot, Spring WebFlux, REST API
- **Frontend:** React.js, Vite, Material UI (MUI), Axios
- **Extension:** JavaScript (Chrome Extension API, DOM MutationObserver)
- **AI Model:** Google Gemini 1.5 Flash API

---

## 📁 Project Structure

This is a monorepo containing three distinct parts of the application:

```text
ai-email-assistant/
├── email-writer-sb/        # Spring Boot Backend API
├── email-writer-react/     # React Frontend Dashboard
└── email-writer-ext/       # Chrome Extension Source Code
🚀 Getting Started
Follow these instructions to run the project locally on your machine.

Prerequisites
Java 17+ and Maven

Node.js & npm

Google Gemini API Key

1. Backend Setup (Spring Boot)
Navigate to the backend directory:

Bash
cd email-writer-sb
Create an application.properties file inside src/main/resources/.

Add your Gemini API credentials (refer to the application.example.properties file for the exact format):

Properties
gemini.api.url=[https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=](https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=)
gemini.api.key=YOUR_ACTUAL_API_KEY
Run the Spring Boot application:

Bash
mvn spring-boot:run
The API will start running on http://localhost:8080

2. Frontend Setup (React)
Open a new terminal and navigate to the frontend directory:

Bash
cd email-writer-react
Install the dependencies:

Bash
npm install
Start the Vite development server:

Bash
npm run dev
The web interface will be available at http://localhost:5173

3. Chrome Extension Setup
Open Google Chrome and navigate to chrome://extensions/.

Turn on Developer mode (toggle switch in the top right corner).

Click on Load unpacked.

Select the email-writer-ext folder from this repository.

Ensure the backend is running on port 8080, then open Gmail to test the extension!

💡 How to Use
Using the Web Interface:

Open the React web app.

Paste an incoming email into the text box.

Select a desired tone from the dropdown.

Click "Generate Reply" and copy the AI-generated response.

Using the Chrome Extension:

Open Gmail and click "Reply" on an email.

Look for the new AI Reply button injected next to the "Send" button in the compose toolbar.

Click it to automatically read the email thread and generate a response directly into your compose box.

⚠️ Important Note About API Keys
Do NOT upload your application.properties or .env files containing your Gemini API keys to GitHub. This repository uses .gitignore to keep those files secure. If you fork this project, be sure to keep your keys hidden!


