# Swag Labs Automation Framework

An end-to-end automated testing framework built using **Java**, **Selenium WebDriver**, **TestNG**, and the **Page Object Model (POM)** design pattern. This framework automates core e-commerce workflows (Login, Product navigation, Cart management, and Checkout overview) for the [Swag Labs](https://www.saucedemo.com/) demo application.

---

## 🚀 Key Features

* **Page Object Model (POM) & PageFactory:** Modular structure separating test scripts from page locators and actions using `@FindBy` annotations.
* **Data-Driven Testing:** Utilizes TestNG `@DataProvider` to execute test scripts with multiple datasets.
* **Base Class Architecture:** Centralized setup and teardown configurations handling browser initialization and driver management.
* **Modular Package Structure:** Clean separation of concerns across base, page, and test packages.

---

## 📁 Project Directory Structure

```text
swaglab_project/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── basepkg/
│   │   │   │   ├── Base_class.java         # Driver setup, browser initialization & teardown
│   │   │   │   └── Reporterclass.java      # Reporting and logging utilities
│   │   │   │
│   │   │   └── pagepkg/
│   │   │       ├── Login_page.java         # Login page locators & action methods (@FindBy)
│   │   │       ├── Product_page.java       # Product catalog & inventory actions
│   │   │       ├── Cart_page.java          # Shopping cart validation actions
│   │   │       ├── Checkout_page.java      # Customer info & shipping details
│   │   │       └── Checkout_overview_page.java # Order review & confirmation
│   │   │
│   │   └── resources/                      # Configuration files, properties, or test data
│   │
│   └── test/
│       └── java/
│           └── testpkg/
│               ├── Login_test.java         # Data-driven test cases for login functionality
│               ├── Product_test.java       # Inventory and product selection tests
│               ├── Cart_test.java          # Cart functionality and item removal tests
│               ├── Checkout_test.java      # Checkout form validation tests
│               └── Checkout_overview_test.java # End-to-end order placement verification
│
└── pom.xml                                 # Maven project object model & dependencies




🛠️ Tech Stack & Prerequisites
Language: Java (JDK 11 or higher)

Automation Tool: Selenium WebDriver (v4.x)

Test Framework: TestNG

Build Tool: Apache Maven

IDE: Eclipse IDE / IntelliJ IDEA

Browser: Google Chrome
