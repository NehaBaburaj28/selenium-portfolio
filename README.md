# Selenium Java Test Automation Framework

![Java](https://img.shields.io/badge/Java-17-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.43.0-green)
![TestNG](https://img.shields.io/badge/TestNG-7.10.2-red)
![Maven](https://img.shields.io/badge/Maven-3.x-blue)

A scalable, data-driven test automation framework built with 
Selenium WebDriver, Java, TestNG, and Maven following the 
Page Object Model (POM) design pattern.

---

## 🛠️ Tech Stack

| Tool | Purpose |
|---|---|
| Java 17 | Programming language |
| Selenium WebDriver 4.43.0 | Browser automation |
| TestNG 7.10.2 | Test execution & reporting |
| Maven | Build & dependency management |
| WebDriverManager 6.3.4 | Automatic browser driver management |
| Page Object Model | Framework design pattern |

---

## 📁 Project Structure

```
selenium-portfolio/
├── src/
│   ├── main/java/com/neha/
│   │   └── pages/              # Page Object classes
│   │       ├── LoginPage.java
│   │       ├── ProductsPage.java
│   │       └── CheckoutPage.java
│   └── test/java/com/neha/
│       ├── base/               # BaseTest & Listeners
│       │   ├── BaseTest.java
│       │   └── ScreenshotListener.java
│       ├── tests/              # Test classes
│       │   ├── LoginTest.java
│       │   ├── ProductsTest.java
│       │   └── CheckoutTest.java
│       ├── utils/              # Utilities
│       │   └── ExcelUtils.java
│       └── resources/
│           └── testdata/
│               └── TestData.xlsx   # Test data (editable by testers)
├── screenshots/                # Auto-captured on test failure
├── testng.xml                  # TestNG suite configuration
└── pom.xml                     # Maven dependencies

```

## ✅ Test Scenarios Covered

### Login Tests
- Valid login redirects to products page
- Invalid credentials show error message
- Locked out user is blocked from login

### Products Tests
- Products page loads with correct title
- Adding item to cart updates cart badge
- Specific product is visible on the page

### Checkout Tests
- Complete end-to-end checkout flow
- Order confirmation message is displayed

---

## 🔑 Key Framework Features

- **Page Object Model (POM)** — separates page interactions from test logic for maintainability
- **WebDriverManager** — automatically downloads and configures the correct ChromeDriver version
- **Screenshot on Failure** — automatically captures and saves a screenshot when any test fails
- **BaseTest class** — centralised browser setup and teardown shared across all tests
- **TestNG Listeners** — hooks into test lifecycle for screenshot capture and reporting

---

## ▶️ How to Run

### Prerequisites
- Java JDK 17+
- Maven 3.x
- Google Chrome (latest)

### Clone the repository
```bash
git clone https://github.com/NehaBaburaj28/selenium-portfolio.git
cd selenium-portfolio
```

### Run all tests
```bash
mvn test
```

### Run a specific test class
```bash
mvn test -Dtest=LoginTest
```

### Run in headless mode
Uncomment this line in `BaseTest.java`:
```java
options.addArguments("--headless");
```


---

## 📸 Screenshots

Failed test screenshots are automatically saved to the `/screenshots` folder with the format:
testMethodName_yyyy-MM-dd_HH-mm-ss.png

---

## 🌐 Application Under Test

[SauceDemo](https://www.saucedemo.com) — a demo e-commerce application purpose-built for automation practice.

---

## 👤 Author

**Neha Baburaj**  
QA Automation Engineer  
[LinkedIn](https://www.linkedin.com/in/nehababuraj) | 
[GitHub](https://github.com/NehaBaburaj28)
