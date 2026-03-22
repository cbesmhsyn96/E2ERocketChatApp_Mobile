<h1 align="center"> E2ERocketChatApp_Mobile </h1>
<p align="center"> Seamless, Scalable, and Industrial-Grade End-to-End Automation for Mobile Communication Ecosystems </p>

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

---

### 📑 Table of Contents
- [Overview](#-overview)
- [Key Features](#-key-features)
- [Tech Stack & Architecture](#-tech-stack--architecture)
- [Project Structure](#-project-structure)
- [Demo & Screenshots](#-demo--screenshots)
- [Environment Variables](#-environment-variables)
- [Getting Started](#-getting-started)
- [Usage](#-usage)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🌟 Overview

**E2ERocketChatApp_Mobile** is a world-class, behavior-driven automation framework engineered to validate the integrity, usability, and performance of the RocketChat mobile application. By leveraging the power of **Gauge**, **Appium**, and **Java**, this project provides a comprehensive suite for executing complex user journeys—from authentication and workspace configuration to real-time messaging—ensuring a flawless mobile experience across diverse device environments.

### ⚠️ The Problem
> In the fast-paced world of mobile communication, manual regression testing is a bottleneck. Validating intricate flows like multi-factor authentication, workspace switching, and real-time socket connections across multiple OS versions is error-prone and time-consuming. Development teams often struggle with inconsistent test data, brittle locators, and lack of visibility into failure logs, leading to delayed releases and compromised user trust.

### ✅ The Solution
**E2ERocketChatApp_Mobile** eliminates these challenges by providing a decoupled, highly maintainable automation architecture. It utilizes a **Concept-based BDD approach** to make tests readable for non-technical stakeholders while implementing robust **JSON-based locator management** and **automated data generation** via JavaFaker. This ensures that every release is backed by a rigorous, repeatable, and data-driven verification process.

### 🏗️ Architecture Overview
The framework is built on a modular **Model-View-Helper** pattern using:
- **Gauge** for scenario specification and business logic abstraction.
- **Appium 10.0.0** as the primary driver for mobile interaction.
- **Selenium 4.39.0** for low-level element handling and synchronization.
- **Excel/JSON Integration** for decoupled configuration and test data.

---

## ✨ Key Features

### 🚀 Behavior-Driven Development (BDD) with Gauge
Write tests in plain language that everyone can understand. The project uses `.spec` files and `.cpt` (concept) files to bridge the gap between technical implementation and business requirements. This allows for high-level concepts like "Complete User Sign-Up" to be reused across multiple scenarios.

### 📱 Full Journey End-to-End Validation
The framework isn't just for unit checks; it's built for "Full Journeys." It covers the entire lifecycle of a RocketChat user:
- **Authentication Selection:** Navigating the initial gateway.
- **Workspace Configuration:** Connecting to custom enterprise servers.
- **Login/Sign-Up:** Handling secure entry and account creation.
- **Home Navigation:** Ensuring post-auth stability.

### 📊 Advanced Data Management
- **JavaFaker Integration:** Automatically generate unique names, emails, and passwords for every test run, preventing data collisions.
- **Excel-Driven Testing:** Utilize `users.xlsx` via Apache POI to manage large-scale user credentials and environmental data without hardcoding values.

### 🔍 Decoupled Locator Strategy
Stop fighting brittle tests. By externalizing locators into dedicated **JSON files** (e.g., `HomeScreen.json`, `LoginScreen.json`), the framework ensures that UI changes only require a single update in a configuration file, not a complete rewrite of the test code.

### 📝 Industrial Logging & Reporting
Powered by **Log4j2**, the framework maintains a meticulous history of test execution. From `current-log.log` to rotating `oldLog` archives, debugging failed scenarios is streamlined through granular event tracking and exception capturing.

---

## 🛠️ Tech Stack & Architecture

### Verified Technology Stack

| Technology | Purpose | Why it was Chosen |
|:--- |:--- |:--- |
| **Java 11** | Core Language | Provides a robust, typed environment for enterprise-level test development. |
| **Appium 10.0.0** | Mobile Automation | The industry standard for cross-platform (Android/iOS) mobile testing. |
| **Gauge-Java** | BDD Framework | Exceptional readability and advanced "Concept" reuse capabilities. |
| **Selenium 4.39.0** | Web/Mobile Driver | Offers superior synchronization (Waits) and element interaction APIs. |
| **Apache POI** | Data Handling | Enables seamless reading of Excel-based test data for high-volume testing. |
| **JavaFaker** | Data Generation | Ensures fresh, randomized data for every execution to maintain test isolation. |
| **Log4j2** | Logging | Provides high-performance, asynchronous logging for deep debugging. |
| **Jackson** | JSON Parsing | Manages the mapping of external locator files into the Java execution engine. |

---

## 📁 Project Structure

```
cbesmhsyn96-E2ERocketChatApp_Mobile/
├── 📁 concepts/                # Gauge reusable business logic (Concepts)
│   ├── 📄 SignUpScreen.cpt     # Steps for user registration
│   ├── 📄 WorkspaceScreen.cpt  # Steps for server configuration
│   └── 📄 AuthNavigation.cpt   # High-level auth flow logic
├── 📁 specs/                   # Executable BDD specifications
│   ├── 📄 FullJourneyE2E.spec  # Complete app walkthrough
│   ├── 📄 LoginScreen.spec     # Deep-dive login scenarios
│   └── 📄 HomeScreen.spec      # Post-auth verification
├── 📁 src/main/java/           # Core Framework Logic
│   ├── 📁 helpers/             # Selenium/Appium wrapper utilities
│   │   ├── 📄 Locator.java     # JSON locator mapping engine
│   │   └── 📄 SeleniumHelper.java # Common UI interaction methods
│   ├── 📁 model/               # Data objects (POJOs)
│   │   ├── 📄 User.java        # User entity definition
│   │   └── 📄 FakerDataModel.java # Dynamic data structures
│   └── 📁 utils/               # Technical utility classes
│       ├── 📄 ExcelUtils.java  # Apache POI implementation
│       └── 📄 UserManager.java # Logic for session/user handling
├── 📁 src/test/resources/      # Static resources and config
│   ├── 📁 locators/            # JSON element definitions
│   │   ├── 📄 LoginScreen.json # Key-value pairs for login UI
│   │   └── 📄 HomeScreen.json  # Locators for navigation
│   ├── 📁 testdata/            # External data sources
│   │   └── 📄 users.xlsx       # Managed user credential lists
│   └── 📄 log4j2.xml           # Logging configuration
├── 📁 env/                     # Gauge environment properties
│   └── 📁 default/             
│       └── 📄 java.properties  # JVM and framework settings
├── 📄 pom.xml                  # Maven dependency and plugin management
└── 📄 manifest.json            # Gauge project metadata
```

---

## 📸 Demo & Screenshots

### 🖼️ Test Execution View

  <img src="https://placehold.co/800x450/2d2d4d/ffffff?text=Appium+Mobile+Execution" alt="Appium Mobile Execution" width="100%">
  <em><p align="center">Real-time execution of the FullJourneyE2E specification on a mobile emulator.</p></em>

  <img src="https://placehold.co/800x450/2d2d4d/ffffff?text=Gauge+HTML+Reports" alt="Gauge HTML Reports" width="100%">
  <em><p align="center">Comprehensive Gauge report showing passed scenarios and step-by-step breakdowns.</p></em>

---

## 🚀 Getting Started

### Prerequisites
To run this automation suite, ensure your development environment meets the following requirements:
*   **Java Development Kit (JDK) 11**: Required for Maven and framework compatibility.
*   **Apache Maven**: For dependency management and test execution.
*   **Appium Server**: (v2.0+ recommended) to bridge tests to devices.
*   **Gauge**: The core BDD runner must be installed on your system.
*   **Mobile Device/Emulator**: Android or iOS environment configured with RocketChat.

### Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-repo/E2ERocketChatApp_Mobile.git
   cd E2ERocketChatApp_Mobile
   ```

2. **Install Dependencies**
   Use Maven to download all required JARs (Appium, Selenium, Gauge-Java, etc.):
   ```bash
   mvn clean install -DskipTests
   ```

3. **Verify Gauge Installation**
   Ensure the Gauge Java plugin is initialized:
   ```bash
   gauge install java
   ```

---

## 🔧 Usage

### Running All Specifications
To execute the entire test suite against the default environment:
```bash
mvn test
```
*Alternatively, using the Gauge CLI:*
```bash
gauge run specs
```

### Running a Specific Scenario
If you wish to focus only on the Login functionality:
```bash
gauge run specs/LoginScreen.spec
```

### Data-Driven Execution
The framework automatically pulls from `src/test/resources/testdata/users.xlsx`. To modify test users:
1. Open the `.xlsx` file.
2. Add or modify user rows.
3. Save and re-run the tests; the `ExcelUtils` will automatically inject the new data.

### Viewing Logs
Logs are generated in real-time during execution. Check the following path for detailed traces:
```text
src/test/resources/testlogs/current-log.log
```

---

## 🤝 Contributing

We welcome contributions to improve the RocketChat Mobile Automation suite! Whether it's adding new specs, optimizing helpers, or updating locators, your help is appreciated.

### How to Contribute

1. **Fork the repository** - Click the 'Fork' button at the top right of this page
2. **Create a feature branch** 
   ```bash
   git checkout -b feature/new-locator-strategy
   ```
3. **Make your changes** - Ensure you adhere to the `SeleniumHelper` patterns
4. **Test thoroughly** - Run the full spec suite to ensure no regressions
   ```bash
   mvn test
   ```
5. **Commit your changes** - Use descriptive messages
   ```bash
   git commit -m 'Add: Implementation for Multi-Factor Auth Concept'
   ```
6. **Push to your branch**
   ```bash
   git push origin feature/new-locator-strategy
   ```
7. **Open a Pull Request** - Submit your changes for review

### Development Guidelines
- ✅ Follow the **Page Object Model (POM)** and **Concept** patterns.
- 📝 Externalize all UI locators into the `locators/` JSON directory.
- 🧪 Use `FakerDataFactory` for creating temporary test entities.
- 🔄 Ensure `BaseTest.java` is updated if driver initialization logic changes.

---

## 📝 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for complete details.

### What this means:
- ✅ **Commercial use:** You can use this suite for enterprise testing.
- ✅ **Modification:** You can adapt the framework for other mobile apps.
- ✅ **Distribution:** You can share the testing logic with your team.
- ⚠️ **Liability:** The software is provided "as is", without warranty.

---

<p align="center">Built for Excellence by the E2ERocketChatApp_Mobile Team</p>
<p align="center">
  <a href="#">⬆️ Back to Top</a>
</p>