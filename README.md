# Factory Management System (FMS)

[cite_start]This project is a comprehensive management software developed as a final project for the **CME 2210 Object-Oriented Analysis and Design** course at Dokuz Eylül University[cite: 6, 7, 8]. [cite_start]The system is designed to automate factory operations, including production tracking, resource distribution, and workforce management[cite: 50, 51].

## 🚀 How to Run (Step-by-Step)

Follow these steps to run the project on your local machine:

1. [cite_start]**Install Java:** Ensure you have Java Development Kit (JDK) installed (Version 8 or higher is recommended)[cite: 416, 442].
2. **Download the Project:** Download this repository as a ZIP file or clone it using Git.
3. [cite_start]**Prepare Data Files:** Ensure the `.csv` files (e.g., `employee_data.csv`, `stock_data.csv`) are in the same directory as the source code[cite: 184, 449].
4. **Compile the Project:** Open your terminal, navigate to the folder, and run:
   `javac MainMenuSystem.java`
5. **Run the Application:** Type the following command to start:
   [cite_start]`java MainMenuSystem` [cite: 424]

## 💻 System Features & Roles

[cite_start]The application uses **Role-Based Access Control** (RBAC)[cite: 85, 190]. The interface changes based on the user type:

* [cite_start]**Manager:** Can monitor production, evaluate employee performance, manage shifts, and handle stock[cite: 57, 58, 62].
* [cite_start]**Employee:** Can view payroll, check shift schedules, and submit leave requests[cite: 66, 67, 70].
* [cite_start]**Customer:** Can browse products, place orders, and track order status[cite: 72, 74, 77].

## 🛠 Technical Details

* [cite_start]**Language:** Java[cite: 416].
* [cite_start]**Architecture:** Three-layer structure (Presentation, Application, and Data Layers)[cite: 170].
* [cite_start]**Data Storage:** Lightweight file-based system using **CSV/TXT** files[cite: 184, 449].
* **Design Principles:** Built using core **OOP** principles:
    * [cite_start]**Encapsulation:** Used to secure private data via classes like `PersonalInfo`[cite: 191, 436].
    * [cite_start]**Inheritance:** User roles extend a base `User` class[cite: 431, 432].
    * [cite_start]**Polymorphism:** Handles different behaviors for different user roles at runtime[cite: 437, 445].

## 🧪 Testing Approach

The system was tested to ensure reliability:
* [cite_start]**Unit Testing:** Verified modules like `OrderService` and `AuthenticationService`[cite: 502].
* [cite_start]**Boundary Testing:** Checked edge cases like empty data files or insufficient stock to prevent crashes[cite: 506, 511].

---

[cite_start]**Project Team:** Atakan Kahraman, Emre Altundal, Serhat Aydın [cite: 10, 11, 12]  
[cite_start]**Academic Supervisor:** Prof. Dr. Semih Utku [cite: 14]  
[cite_start]**Institution:** Dokuz Eylül University [cite: 1, 4]
