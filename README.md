# Employee CRUD API (Spring Boot & Oracle DB)

A RESTful web service built with Spring Boot, Spring Data JPA, and Oracle Database to perform CRUD (Create, Read, Update, Delete) operations on employee records.

---

## 🚀 Features
- **Create**: Add new employees with detailed validations (name, email, department, salary, join date).
- **Read**: Fetch all employees or find a specific employee by their unique ID.
- **Update**: Modify existing employee records.
- **Delete**: Remove employee records.
- **Auto-validation**: Server-side checks to ensure integrity (e.g., non-empty fields, positive salary, valid emails).
- **Oracle Sequence Generation**: Auto-incrementing primary keys managed via Oracle database sequence.

---

## 🛠️ Technology Stack
- **Backend Framework**: Spring Boot
- **Database Access**: Spring Data JPA & Hibernate
- **Database**: Oracle Database (10g+)
- **Build Tool**: Maven
- **Java Version**: 17+

---

## ⚙️ Configuration & Setup

### 1. Database Schema Setup
Run the following SQL commands in your Oracle Database to create the required sequence and table:

```sql
-- Create sequence for employee ID generation
CREATE SEQUENCE EMPLOYEE_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Create the employees table
CREATE TABLE EMPLOYEES (
    EMPLOYEE_ID NUMBER(19) NOT NULL,
    EMPLOYEE_NAME VARCHAR2(255) NOT NULL,
    EMAIL VARCHAR2(255) NOT NULL UNIQUE,
    DEPARTMENT VARCHAR2(255) NOT NULL,
    SALARY NUMBER(38, 2) NOT NULL,
    JOIN_DATE DATE NOT NULL,
    CONSTRAINT pk_employees PRIMARY KEY (EMPLOYEE_ID)
);
```

### 2. Configure application.properties
Update [src/main/resources/application.properties](src/main/resources/application.properties) with your Oracle Database connection details:

```properties
spring.datasource.url=jdbc:oracle:thin:@//<host>:<port>/<service_name_or_SID>
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# Server Configuration
server.port=8097
```

---

## 📡 API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **GET** | `/api/employees` | Retrieve a list of all employees |
| **GET** | `/api/employees/{id}` | Retrieve a specific employee by ID |
| **POST** | `/api/employees` | Create a new employee record |
| **PUT** | `/api/employees/{id}` | Update an existing employee record |
| **DELETE** | `/api/employees/{id}` | Delete an employee record |

### Sample JSON Request Body (POST / PUT)
```json
{
  "name": "Noyal",
  "email": "noyal@gmail.com",
  "department": "AI",
  "salary": 222200,
  "joinDate": "2026-07-14"
}
```

---

## 🏃 How to Run the Application Locally

1. Clone this repository:
   ```bash
   git clone https://github.com/noyalashwin0704-ai/employee-crud-oracle.git
   cd employee-crud-oracle
   ```
2. Build the project using Maven:
   ```bash
   mvn clean compile
   ```
3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the API at: `http://localhost:8097/api/employees`
