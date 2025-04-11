# 💼 Job Portal System

The **Job Portal System** is a console-based application developed using **Java** and **MySQL**, designed to bridge the gap between job seekers and recruiters. It provides a seamless interface for users to register, log in, and interact with job postings.

---

## 📌 Features

### 👤 User Authentication
- Sign up and sign in for both **Recruiters** and **Job Seekers**
- Email and password validation using a custom `Validator` class
- Role-based dashboards

### 🧑‍💼 Recruiter Dashboard
- Post new job openings
- View all posted jobs
- View applicants for each job
- Accept or reject applications

### 🙋‍♂️ Job Seeker Dashboard
- Search and view available job listings
- Apply for jobs
- View your own applied applications

### 🗃️ Database Integration (MySQL)
- Stores user details, job postings, and job applications
- CRUD operations implemented using JDBC
- Normalized database structure with foreign keys

---

## 🧱 Technologies Used
- **Java** (OOP principles + JDBC)
- **MySQL** (for backend database)
- **Eclipse IDE** for development
- **MVC Architecture** for clean separation of concerns

---

## 💡 OOP Concepts Used

- **Encapsulation**: All data members in the `User`, `Job`, and `Application` classes are private and accessed via getters and setters.
- **Abstraction**: The complexity of database connections and operations is hidden within the controller and model layers.
- **Inheritance**: (If applicable) Can be extended to include inheritance in future updates like Admin user from base User.
- **Polymorphism**: Role-based dashboard behavior using method overriding (e.g., `displayDashboard()` for different user roles).

---

## 📁 Project Structure

```
job-portal-system/
├── model/
│   ├── User.java
│   ├── Job.java
│   ├── Application.java
│   └── Database.java
├── controller/
│   ├── UserController.java
│   ├── JobController.java
│   └── ApplicationController.java
├── view/
│   ├── AuthenticationView.java
│   ├── RecruiterDashboard.java
│   └── JobSeekerDashboard.java
├── util/
│   └── Validator.java
└── Main.java (Index.java)
```

---

## 🚀 How to Run

1. Clone the repository
   ```bash
  https://github.com/Dhanyaravi0903/JobPortalSystem-final.git
   ```
2. Import into Eclipse or any Java IDE
3. Ensure MySQL is running and update credentials in `Database.java`
4. Run `Index.java` to start the application

---

## 🤝 Contributing

Pull requests are welcome! Feel free to fork the repo and submit improvements.

---

## 📜 License

This project is open-source and free to use under the [MIT License](LICENSE).
