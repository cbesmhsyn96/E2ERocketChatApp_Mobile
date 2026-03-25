<h1 align="center"> E2ERocketChatApp_MobileTest_Appium </h1>
<p align="center"> An Enterprise-Grade Mobile Test Automation Framework for Seamless Rocket.Chat End-to-End Validation </p>

<p align="center">
  <img alt="Build" src="https://img.shields.io/badge/Build-Passing-brightgreen?style=for-the-badge">
  <img alt="Issues" src="https://img.shields.io/badge/Issues-0%20Open-blue?style=for-the-badge">
  <img alt="Contributions" src="https://img.shields.io/badge/Contributions-Welcome-orange?style=for-the-badge">
  <img alt="License" src="https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge">
</p>
<!-- 
  **Note:** These are static placeholder badges. Replace them with your project's actual badges.
  You can generate your own at https://shields.io
-->

## 📋 Table of Contents

- [Overview](#-overview)
- [Key Features](#-key-features)
- [Tech Stack & Architecture](#-tech-stack--architecture)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [Usage](#-usage)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🌟 Overview

**E2ERocketChatApp_MobileTest_Appium** is a comprehensive, behavior-driven development (BDD) automation suite designed specifically for the Rocket.Chat mobile application. By leveraging the power of **Appium** for cross-platform mobile interactions and **ThoughtWorks Gauge** for human-readable test specifications, this project ensures that critical user journeys—from workspace selection to full authentication—are verified with surgical precision.

### The Hook
This framework provides a scalable, maintainable, and highly readable automation foundation that allows quality assurance teams to execute complex mobile end-to-end scenarios with minimal overhead and maximum reliability.

### The Problem
> Mobile applications, especially communication platforms like Rocket.Chat, undergo frequent updates and complex state changes. Manual regression testing of authentication flows, workspace configurations, and multi-screen navigation is not only time-consuming but also prone to human error. Without a structured automation framework, teams struggle to maintain consistent quality across different devices and environments, leading to delayed release cycles and potential regressions in core user functionality.

### The Solution
E2ERocketChatApp_MobileTest_Appium addresses these challenges by implementing a modular architecture where test logic is decoupled from element identification. By using **Gauge Specifications**, stakeholders can understand test intent without diving into code. The inclusion of **Java Faker** for dynamic data generation and **Apache POI** for data-driven testing via Excel ensures that the suite is both robust and versatile, capable of handling thousands of unique test cases without manual data entry.

### Architecture Overview
The project follows a **Behavior-Driven Development (BDD)** approach using the **Gauge framework**. High-level `specs` define the business logic, while `concepts` encapsulate reusable step sequences. The underlying implementation is built on **Java 21**, utilizing **Appium 10.0.0** for mobile driver management and **Selenium 4.39.0** for advanced interaction helpers. Data management is centralized through a combination of JSON-based locators and externalized test data.

---

## ✨ Key Features

This framework is built with the end-user and the developer in mind, focusing on maintainability and clarity.

- 📝 **Human-Readable Specifications:** Uses ThoughtWorks Gauge to write tests in plain language, making it easy for non-technical stakeholders to review and validate test coverage.
- 🧩 **Modular Concept Design:** Common workflows like "Auth Navigation" or "Sign Up" are bundled into reusable concepts, reducing code duplication and making the suite easier to maintain.
- 🎲 **Dynamic Data Generation:** Integrated with **Java Faker**, allowing the system to generate realistic, random user data (names, emails, etc.) on the fly for robust sign-up testing.
- 📊 **Data-Driven Capabilities:** Leverages **Apache POI** to read test users and configurations from Excel spreadsheets (`users.xlsx`), enabling bulk testing across multiple account types.
- 🔍 **Clean Locator Management:** Element identifiers are externalized in organized JSON files (`HomeScreen.json`, `LoginScreen.json`), ensuring that UI changes only require updates in one central location.
- 📜 **Advanced Logging & Traceability:** Implements **Log4j2** for detailed execution logs, providing a clear audit trail and simplifying the debugging process when tests fail.
- 🛠️ **Robust Helper Utilities:** Includes specialized Selenium and Appium helpers to handle complex mobile gestures, explicit waits, and element synchronization.

---

## 🛠️ Tech Stack & Architecture

The project utilizes a modern, high-performance stack to ensure stability and compatibility with the latest mobile OS versions.

| Technology | Purpose | Why it was Chosen |
| :--- | :--- | :--- |
| **Java 21** | Primary Programming Language | Offers the latest LTS features, improved performance, and robust ecosystem support for mobile automation. |
| **Appium 10.0.0** | Mobile Automation Library | The industry standard for cross-platform mobile testing, providing deep integration with Android and iOS. |
| **ThoughtWorks Gauge** | BDD Framework | Enables markdown-based test specifications and powerful "concept" reuse for clean, readable suites. |
| **Maven** | Build & Dependency Management | Standardizes the project lifecycle and handles the complex dependency tree required for Appium and Selenium. |
| **Selenium 4.39.0** | Web/Mobile Interaction | Provides the core WebDriver protocols and interaction helpers necessary for element manipulation. |
| **Log4j2** | Logging Framework | Provides high-performance, asynchronous logging to track test execution and error states. |
| **Jackson Databind** | JSON Processing | Used to parse external locator files, keeping the Java code clean and decoupled from the UI layer. |
| **Apache POI** | Excel Integration | Facilitates data-driven testing by allowing the suite to ingest data directly from `.xlsx` files. |
| **Java Faker** | Mock Data Generation | Streamlines the creation of unique, valid test data for registration and profile flows. |

---

## 📁 Project Structure

The repository is organized following best practices for Gauge-based Java projects, separating logic, data, and configuration.

```
cbesmhsyn96-E2ERocketChatApp_Mobile-9dba261/
├── 📁 concepts/                    # Reusable Gauge business steps (BDD)
│   ├── 📄 AuthNavigation.cpt       # Navigation logic between auth screens
│   ├── 📄 AuthenticationSelectionScreen.cpt
│   ├── 📄 HomeScreen.cpt           # Reusable home screen interactions
│   ├── 📄 LoginScreen.cpt          # Standardized login sequences
│   ├── 📄 SignUpScreen.cpt         # Registration workflow abstractions
│   └── 📄 WorkspaceScreen.cpt      # Workspace setup sequences
├── 📁 env/                         # Environment-specific configurations
│   └── 📁 default/
│       ├── 📄 default.properties    # General framework properties
│       └── 📄 java.properties       # Java-specific execution settings
├── 📁 specs/                       # High-level test specifications (Gherkin-style)
│   ├── 📄 AuthNavigation.spec      # Specs for auth routing logic
│   ├── 📄 AuthenticationSelectionScreen.spec
│   ├── 📄 FullJourneyE2E.spec      # Comprehensive End-to-End user flow
│   ├── 📄 HomeScreen.spec          # Home screen validation tests
│   ├── 📄 LoginScreen.spec         # Login functionality tests
│   ├── 📄 SignUpScreen.spec        # User registration tests
│   └── 📄 WorkspaceScreen.spec     # Workspace connection tests
├── 📁 src/                         # Implementation source code
│   ├── 📁 main/java/               # Core framework logic
│   │   ├── 📁 helpers/             # Low-level automation utilities
│   │   │   ├── 📄 Locator.java     # Logic for reading JSON locators
│   │   │   ├── 📄 OtherHelper.java
│   │   │   └── 📄 SeleniumHelper.java # Wait/Interaction wrappers
│   │   ├── 📁 model/               # Data objects
│   │   │   ├── 📄 User.java        # User entity representation
│   │   │   └── 📄 FakerDataModel.java # Dynamic data mapping
│   │   ├── 📁 testdata/            # Logic for data generation
│   │   │   └── 📄 FakerDataFactory.java # Factory for random test data
│   │   └── 📁 utils/               # General utilities
│   │       ├── 📄 ExcelUtils.java  # POI-based Excel reader
│   │       └── 📄 UserManager.java # User session/state management
│   └── 📁 test/                    # Test-specific resources
│       ├── 📁 java/org/example/    # Step implementations
│       │   ├── 📄 BaseTest.java    # Driver setup and teardown
│       │   └── 📄 StepImplementation.java # Mapping specs to Java code
│       └── 📁 resources/           # Static assets for tests
│           ├── 📁 locators/        # JSON element identifiers
│           │   ├── 📄 HomeScreen.json
│           │   └── 📄 LoginScreen.json
│           ├── 📁 testdata/        # Static test data files
│           │   └── 📄 users.xlsx   # Spreadsheet for data-driven tests
│           ├── 📁 testlogs/        # Historical execution logs
│           └── 📄 log4j2.xml       # Logger configuration
├── 📄 Jenkinsfile                  # CI/CD pipeline definition
├── 📄 manifest.json                # Gauge project manifest
├── 📄 pom.xml                      # Maven project configuration
├── 📄 README.md                    # Project documentation
└── 📄 LICENSE                       # MIT License file
```

---

## 🚀 Getting Started

To get this framework running on your local machine, follow these steps.

### Prerequisites

- **Java Development Kit (JDK) 21**: Ensure your `JAVA_HOME` is set correctly.
- **Apache Maven**: For dependency management.
- **Gauge**: Install the Gauge framework.
  ```bash
  # On macOS using Homebrew
  brew install gauge
  # On Windows using Chocolatey
  choco install gauge
  ```
- **Appium Server**: Install Appium via NPM.
  ```bash
  npm install -g appium
  ```
- **Android Studio / Xcode**: For mobile emulators/simulators and platform tools (adb).

### Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-repo/E2ERocketChatApp_MobileTest_Appium.git
    cd E2ERocketChatApp_MobileTest_Appium
    ```

2.  **Install Dependencies:**
    Use Maven to download all required Java libraries (Appium, Selenium, Gauge-Java, etc.).
    ```bash
    mvn clean install -DskipTests
    ```

3.  **Configure Environment:**
    Update the properties in `env/default/default.properties` to match your local Appium server URL and the path to your Rocket.Chat `.apk` or `.app` file.

---

## 🔧 Usage

The framework allows you to run specific specifications or the entire suite.

### Running All Tests
To execute the entire test suite via Maven:
```bash
mvn test
```

### Running Specific Specifications
You can target specific files using the Gauge CLI:
```bash
gauge run specs/LoginScreen.spec
```

### Running the End-to-End Journey
To run the full end-to-end user flow:
```bash
gauge run specs/FullJourneyE2E.spec
```

### Viewing Reports
Gauge automatically generates detailed HTML reports after execution.
- Reports are typically found in the `reports/` directory.
- Open `index.html` in your browser to see a step-by-step breakdown of the execution, including screenshots on failure.

### Data Management
- **Static Data:** Modify `src/test/resources/testdata/users.xlsx` to add or update test accounts.
- **Element Locators:** If the app UI changes, update the corresponding JSON file in `src/test/resources/locators/`.

---

## 🤝 Contributing

We welcome contributions to improve the test coverage and framework capabilities of E2ERocketChatApp_MobileTest_Appium!

### How to Contribute

1. **Fork the repository** - Click the 'Fork' button at the top right of this page.
2. **Create a feature branch** 
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Make your changes** - Add new specs, update helpers, or improve documentation.
4. **Test thoroughly** - Ensure all tests pass with your changes.
   ```bash
   mvn test
   ```
5. **Commit your changes** - Write clear, descriptive commit messages.
   ```bash
   git commit -m 'Add: New validation steps for Workspace Screen'
   ```
6. **Push to your branch**
   ```bash
   git push origin feature/amazing-feature
   ```
7. **Open a Pull Request** - Submit your changes for review.

### Development Guidelines

- ✅ **Maintain BDD Principles:** Ensure new specifications are easy to read and understand.
- 📝 **Update Locators:** Do not hardcode element identifiers in Java; use the JSON locator files.
- 🧪 **Write Clean Concepts:** If a sequence of steps is used more than once, move it into a `.cpt` file.
- 📚 **Document Helpers:** Add Javadoc comments to any new utility methods in the `helpers` package.
- 🔄 **Atomic Commits:** Keep your commits focused on a single logical change.

---

## 📝 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for complete details.

### What this means:

- ✅ **Commercial use:** You can use this project commercially.
- ✅ **Modification:** You can modify the code for your own needs.
- ✅ **Distribution:** You can distribute this software.
- ✅ **Private use:** You can use this project privately.
- ⚠️ **Liability:** The software is provided "as is", without warranty of any kind.
- ⚠️ **Trademark:** This license does not grant trademark rights.

---

<p align="center">Made by @cbesmhsyn96</p>
<p align="center">
  <a href="#">⬆️ Back to Top</a>
</p>