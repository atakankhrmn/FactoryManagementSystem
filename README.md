Factory Management System (FMS)
This is a comprehensive management software developed as a final project for the CME 2210 Object-Oriented Analysis and Design course at Dokuz Eylül University. The system is designed to automate factory operations, including production tracking, resource distribution, and workforce management.
+1

🚀 How to Run (Step-by-Step)
Even if you have no prior experience with Java, you can run this project by following these steps:

Install Java: Ensure you have Java Development Kit (JDK) installed on your computer. (Version 8 or higher is recommended).

Download the Project: Download this repository as a ZIP file and extract it, or clone it using:
git clone https://github.com/atakankhrmn/FactoryManagementSystem.git


Check Data Files: Ensure the .csv files (like employee_data.csv, stock_data.csv) are in the correct directory as the program reads and writes to these files.
+2

Compile the Project: Open your terminal/command prompt, navigate to the project folder, and type:
javac MainMenuSystem.java

Run the Application: Type the following command to start the console interface:
java MainMenuSystem

💻 System Features & Roles
The application uses Role-Based Access Control, meaning the menu changes based on who logs in:
+1


Manager: Can monitor production, evaluate employee performance, manage shifts, and handle stock or financial data.
+1


Employee: Can view their own payroll, check shift schedules, and submit leave/vacation requests.
+2


Customer: Can browse products, place orders, track order status, and leave feedback.
+2

🛠 Technical Details

Language: Java.


Architecture: Layered Architecture (Presentation, Application, and Data Layers).
+1


Storage: File-based system using CSV/TXT files instead of a traditional SQL database.
+1

Design Principles: Built using core OOP principles:


Encapsulation: Private data is secured and accessed through specific methods.
+2


Inheritance: Specialized user roles (Manager, Employee, Customer) extend a base User class.
+1


Polymorphism: Used to handle different behaviors for different user roles at runtime.
+1

🧪 Testing Approach
The system has been rigorously tested to ensure reliability:


Unit Tests: Verified individual modules like AuthenticationService and OrderService.


Boundary Tests: Checked for edge cases like empty data files or out-of-stock scenarios to prevent system crashes.
+1


Project Team: Atakan Kahraman, Emre Altundal, Serhat Aydın 


Academic Supervisor: Prof. Dr. Semih Utku 


Institution: Dokuz Eylül University, Faculty of Engineering
