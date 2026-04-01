# HSF302 – Practical Exam 2025 Fall Block 5 (Trial)

| Field        | Detail                            |
|--------------|-----------------------------------|
| Subject Code | HSF302                            |
| Subject Name | Working with Spring Framework     |
| Time         | 90 minutes (including submit time)|
| Exam Type    | EOS Practical Exam                |

> **Notes:**
> - a) Students are allowed to connect to the Internet for the **first 10 minutes** when setting up the project at the beginning of the examination.
> - b) After that, students can only use resources on their local computer.

---

## B) Exam Requirements

Develop the following pages using **Spring Boot 3.4.3**, **Spring JPA**, **Spring MVC 6**, and **Thymeleaf**, running on **JDK 21**.

**Project Code:** `RMS`

---

### Q1) List Function *(2.5 points)*

**Screen 1 – Shoes List**

```
+------------------+------------------+
|   Shoes Management                  |
+-------------------------------------+
|   Shoes List                        |
|                                     |
|   Shoes Name: [__________] [Search] |
|                          [Add New]  |
+----+--------+-------------+------+------+-----------+
| #  | Shoes  | Shoes Name  | Type | Price| Action    |
| No | No     |             |      | ($)  |           |
+----+--------+-------------+------+------+-----------+
| 01 | SH0101 | Adidas      | Sport| 200  | Delete|View|
+----+--------+-------------+------+------+-----------+
```

| #   | Functional Requirements                                                                                                                         |
|-----|-------------------------------------------------------------------------------------------------------------------------------------------------|
| 1.1 | When the user opens the page, **all shoes** in the database are displayed, ordered by **Shoes Name**.                                          |
| 1.2 | If the user enters a shoes name and clicks **"Search"**, the matching shoes are displayed using **wildcard search**, ordered by **Shoes Name**. |
| 1.3 | When the user clicks **"Add New"**, the software displays the **"Add New Shoes"** screen.                                                      |

---

### Q2) Delete Function *(1.5 points)*

**Screen 2 – Confirmation Dialog**

```
+------------------------------------------+
|             Confirmation                 |
|                                          |
|  Are you sure you want to delete         |
|  "Adidas Sport ...."?                    |
|                                          |
|         [ Yes ]       [ No ]            |
+------------------------------------------+
```

| #   | Functional Requirements                                                                                              |
|-----|----------------------------------------------------------------------------------------------------------------------|
| 2.1 | When the user clicks **"Delete"** on a row in the shoes list, the software shows a **confirmation dialog**.         |
| 2.2 | If the user selects **"Yes"**: delete the shoes from the database, show **"Deleted successfully"** message, and update the list. |
| 2.3 | If the user selects **"No"**: close the dialog.                                                                     |

---

### Q3) Add New Function *(4.0 points)*

**Screen 3 – Add New Shoes**

```
+-----------------------------------+
|   Shoes Management                |
|   Add New Shoes                   |
|                                   |
|   Shoes No:   [__________]        |
|   Shoes Name: [__________________]|
|   Price:      [_____] [$]         |
|   Type:       [Dropdown v]        |
|               (SP-Sport,          |
|                RN-Running,        |
|                TN-Tennis)         |
|                                   |
|        [Add New]   [Back]         |
+-----------------------------------+
```

**Screen Field Definitions**

| # | Field Name | Mandatory | Max Length | Description                                           | Type     |
|---|------------|-----------|------------|-------------------------------------------------------|----------|
| 1 | Shoes No   | Yes       | 10 chars   | Alphanumeric (a–Z and digits), no duplicate, no leading/trailing spaces | Text box |
| 2 | Price      | Yes       | 5 chars    | Greater than 0 and less than 100,000                  | Text box |
| 3 | Shoes Name | Yes       | 100 chars  | No leading/trailing spaces                            | Text box |
| 4 | Type       | Yes       | N/A        | Loaded from database                                  | Dropdown |
| 5 | Add New    | N/A       | –          | Submit button                                         | Button   |
| 6 | Back       | N/A       | –          | Go back to the list screen                            | Button   |

| #   | Functional Requirements                                                                                                                          |
|-----|--------------------------------------------------------------------------------------------------------------------------------------------------|
| 3.1 | When the user opens the page, **all fields are blank**.                                                                                         |
| 3.2 | When the user clicks **"Add New"**: a) validate input data; b) store data and show **"Created new shoes successfully"**; c) clear all input fields. |
| 3.3 | When the user clicks **"Back"**, the software shows the **list screen** with all shoes from the database.                                       |

---

### Q4) View Function *(1.5 points)*

**Screen 4 – View Shoes Detail**

```
+-------------------------------------+
|   Shoes Management                  |
|   View Shoes Info                   |
|                                     |
|   Shoes No:   [__________] (read-only)   |
|   Shoes Name: [__________________] (read-only) |
|   Price:      [_____] (read-only)   |
|   Type:       [_______] (read-only) |
|                                     |
|              [Back]                 |
+-------------------------------------+
```

| #   | Functional Requirements                                                                        |
|-----|-----------------------------------------------------------------------------------------------|
| 4.1 | When the user opens the page, **all fields display the selected shoe's information** (read-only). |
| 4.2 | When the user clicks **"Back"**, the software shows the **list screen**.                      |

---

### Q5) UI & Project Structure Requirements

#### Q5.1 – User Interface

1. Screen layout must follow the descriptions in the **Screen Layout section** above.
2. Field labels must match the descriptions in the **Screen Layout section**.
3. Graphical design must be **clear, high-contrast, and understandable**.

---

#### Q5.2 – Project Structure Requirements *(0.5 point)*

| # | Requirement |
|---|-------------|
| 1 | Project must be in **IntelliJ** format. Project name convention: `<ClassName>_<StudentID>_<ProjectCode>_PE` |
| 2 | Project must be **Spring Boot** type, package type **"war"**, running on **JDK 21** |
| 3 | Use **Spring Boot 3.4.3**, **Spring JPA**, and **Spring MVC 6** + Thymeleaf. **Note:** Do not use other libraries e.g. Lombok |
| 4 | Use **MSSQL Server Driver** enclosed with the Spring Boot Project in IntelliJ for MSSQL Server 2016 or later |
| 5 | Application must follow the **MVC & Layered structural pattern** with the following Java package structure: |

**Package Structure**

| Package | Description |
|---|---|
| `com.<studentId>.mvc.entity` | Entity classes |
| `com.<studentId>.mvc.repository` | Repository Interface classes |
| `com.<studentId>.mvc.service` | Service Interface classes |
| `com.<studentId>.mvc.service.impl` | Service implementation classes |
| `com.<studentId>.mvc.controller` | Controller classes |
| `com.<studentId>.mvc.dto` | Data Transfer Objects |
| `com.<studentId>.mvc` | Configuration classes |

> **Note:** Replace `<studentId>` with your student ID in **lowercase**.

| # | Requirement (continued) |
|---|-------------------------|
| 6 | Class names and class attributes must be named according to the application requirements. |
| 7 | Zip the project folder and submit the `.zip` file to EOS. **Note:** Follow the submit guideline in the enclosed document. |
| 8 | **Table name** and **field name** in the database must follow the defined tables in the section below. If not followed, the result will not be graded. |
| 9 | Shoes Type list must be loaded from the database. |

---

#### Q5.3 – Database

> Use the provided SQL scripts to create the tables and insert shoes type data.

**Table: `shoes`**

| # | Field Name  | Type      | Size | Entity Type | Mandatory | Description           |
|---|-------------|-----------|------|-------------|-----------|-----------------------|
| 1 | `shoes_id`  | int       | N/A  | int         | Yes       | Auto generated (Identity) |
| 2 | `shoes_no`  | NVarchar  | 10   | String      | Yes       | Unique                |
| 3 | `shoes_name`| NVarchar  | 100  | String      | Yes       |                       |
| 4 | `price`     | float     | N/A  | double      | Yes       |                       |
| 5 | `type`      | NVarchar  | 10   | String      | Yes       | Stores type code      |

**Table: `shoes_type`**

| # | Field Name  | Type     | Size | Entity Type | Mandatory | Description                  |
|---|-------------|----------|------|-------------|-----------|------------------------------|
| 1 | `type_code` | NVarchar | 10   | String      | Yes       | Type code, Primary Key       |
| 2 | `type_name` | NVarchar | 50   | String      | Yes       |                              |

**SQL Script**

```sql
USE [HSF302_2025_PE]
GO

CREATE TABLE [dbo].[shoes] (
    [shoes_id]   INT IDENTITY(1,1) NOT NULL,
    [shoes_no]   NVARCHAR(10)  UNIQUE NOT NULL,
    [shoes_name] NVARCHAR(100) NOT NULL,
    [price]      FLOAT         NOT NULL,
    [type]       NVARCHAR(10)  NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[shoes_type] (
    [type_code] NVARCHAR(10) UNIQUE NOT NULL,
    [type_name] NVARCHAR(50) NOT NULL
) ON [PRIMARY]
GO

INSERT INTO [dbo].[shoes_type] ([type_code], [type_name]) VALUES ('TN', 'Tennis');
INSERT INTO [dbo].[shoes_type] ([type_code], [type_name]) VALUES ('SP', 'Sport');
INSERT INTO [dbo].[shoes_type] ([type_code], [type_name]) VALUES ('RN', 'Running');
GO
```

---

#### Q5.4 – Project Configuration Requirements

Configuration in `application.properties` must follow the conventions below:

| # | Property                             | Value                      |
|---|--------------------------------------|----------------------------|
| 1 | Database name                        | `HSF302_2025_PE`           |
| 2 | Server name & Port                   | `localhost:1433`           |
| 3 | `spring.datasource.password`         | `sa`                       |
| 4 | `spring.datasource.username`         | `sa`                       |
| 5 | `server.servlet.context-path`        | `/`                        |
| 6 | `server.port`                        | `8080`                     |
| 7 | `spring.jpa.hibernate.ddl-auto`      | `none`                     |
| 8 | Path for the application page        | `/`                        |
| 9 | Database authentication mode         | Username & Password        |
| 10| URL of application page              | `http://localhost:8080/`   |

---

## C) Grading Policies

> ⚠️ The following items are **mandatory**. Violating any one of them results in a **grade of zero**.

| # | Item |
|---|------|
| 1 | Compliance with **Q5.2** Project structure requirements |
| 2 | Compliance with **Q5.3** Database table names |
| 3 | Compliance with **Q5.4** Project configuration requirements |

---

## D) PE Submit Guideline

### D1) Before Zipping the Project Folder

Run `mvn clean` or manually delete compiled sources:

1. **Step 1:** Go to the folder containing the project.
2. **Step 2:** Open the project folder and verify the folder structure.
3. **Step 3:** Open the `target` folder.
4. **Step 4:** Delete the `classes` folder and any other compiled output folders.

> **⚠️ Note:** Check carefully to avoid mistakes. Copy the project to a backup folder before deleting anything.

### D2) Zip and Submit

5. **Step 5:** Go back to the folder from Step 1.
6. **Step 6:** Right-click the **project folder** → **"Send to"** → **"Compressed (zipped) folder"**.
7. **Step 7:** Submit the `.zip` file to **EOS** following the naming convention and submit guidelines in the enclosed document.