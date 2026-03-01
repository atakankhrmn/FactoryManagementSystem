# Factory Management System (FMS)

This project is an enterprise-level management software developed as a final project for the **CME 2210 Object-Oriented Analysis and Design** course at Dokuz Eylül University. It aims to optimize factory operations through a role-based modular system and automated workflows.

## 🚀 How to Run (Step-by-Step)

Follow these steps to run the project on your local machine:

1. **Install Java:** Ensure you have Java Development Kit (JDK) installed (Version 8 or higher is recommended).
2. **Download the Project:** Download this repository as a ZIP file or clone it using Git.
3. **Prepare Data Files:** Ensure the `.csv` files (e.g., `employee_data.csv`, `stock_data.csv`) are in the same directory as the source code.
4. **Compile the Project:** Open your terminal, navigate to the folder, and run:
   `javac MainMenuSystem.java`
5. **Run the Application:** Type the following command to start the console interface:
   `java MainMenuSystem`

## 💻 System Architecture & Roles

The system is built on a **Three-Layer Architecture** (Presentation, Application, and Data) to ensure a clear separation of concerns. It implements **Role-Based Access Control (RBAC)** to provide unique functionalities tailored to different user types.

### 👤 User Roles
* **Manager:** High-level access for overseeing factory operations, including production monitoring, performance reviews, and stock management.
* **Employee:** Focused on personal workflow, including payroll monitoring, shift schedules, and submitting leave requests.
* **Customer:** External interface for commercial interaction, allowing users to place orders and track status.

## 🛠 Technical Implementation Details

### Object-Oriented Design (OOAD)
The software is a direct implementation of rigorous design phases, including Use Case, Class, and Sequence diagrams.
* **Encapsulation:** Sensitive user and operational data are protected within classes like `PersonalInfo` and `User`.
* **Inheritance:** A clean class hierarchy where `Manager`, `Employee`, and `Customer` inherit from an abstract `User` base class.
* **Polymorphism:** Runtime dispatch handles role-specific behaviors within the shared menu system.



### Data Management & Persistence
Instead of a traditional SQL database, this system utilizes a custom **Flat-File Database** logic:
* **DataService:** A centralized utility class responsible for all input/output operations.
* **CSV Processing:** High-performance data handling using Java's `BufferedReader` and `FileWriter`.
* **Persistence:** All operational changes are immediately synchronized with local `.csv` files.



## 🔮 Future Improvements
* Transitioning from CSV files to a relational database like **SQLite** or **MySQL**.
* Development of a **Graphical User Interface (GUI)** using Java Swing or JavaFX.
* Implementation of advanced encryption and secure logging mechanisms.

---

**Project Team:** Atakan Kahraman, Emre Altundal, Serhat Aydın  
**Academic Supervisor:** Prof. Dr. Semih Utku  
**Institution:** Dokuz Eylül University, Faculty of Engineering
