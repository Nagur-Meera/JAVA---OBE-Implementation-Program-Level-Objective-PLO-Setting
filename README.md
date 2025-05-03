# 🎓 OBE Implementation: Program Level Objective (PLO) Setting

## 📘 Description

This is a Java Swing application developed as part of **CS307 - Mobile Application Development using Java** at **SRM University AP**. It supports **Outcome Based Education (OBE)** by enabling CRUD operations for **Program Level Objectives (PLOs)**.

The application provides:
- GUI-based data entry and management
- Local SQLite database integration
- Clean modular code structure

---

## 👨‍💻 Authors

- Nikhil Sireesh Thalakola [AP22110010009]  
- Mansoor Muzahid Shaik [AP22110010019]  
- Nagur Meeravali Shaik [AP22110010061]  
- Ramgopal Mukhamatam [AP22110010063]

---

## 🏫 Department of Computer Science and Engineering  
**SRM University AP, Amaravati**

---

## 🛠️ Technologies

- Java (AWT/Swing)
- SQLite with JDBC
- NetBeans IDE
- MVC-like modular Java classes

---

## 📁 Project Structure (NetBeans)

![Project Structure](https://github.com/user-attachments/assets/3e7f3dc5-e06d-472b-b873-572a488687b0)



---

## 🧱 Database Table

Table: `program_lo`

| Column Name      | Type    | Description                 |
|------------------|---------|-----------------------------|
| `ID`             | INTEGER | Primary key, auto increment |
| `prog_lo_code`   | TEXT    | PLO Code (not null)         |
| `prog_lo_no`     | TEXT    | PLO Number                  |
| `prog_lo_name`   | TEXT    | Name/Title of PLO           |
| `prog_lo_details`| TEXT    | Description/Details         |

---

## 🚀 How to Run (in NetBeans)

1. **Open NetBeans** and go to **File > Open Project**
2. Select the `teamplo_program_level_objective_setting` project folder.
3. Right-click the project > **Run**.
4. Use the GUI to create/delete PLOs.
5. The database is automatically created in the project directory.

---

## 🖼️ Screenshots
### Architecture Diagram
![Architecture Diagram](https://github.com/user-attachments/assets/cf96dd27-fa2b-4d79-b061-877b93502fa5)

### Main Menu
> ![Main Menu](https://github.com/user-attachments/assets/059c093a-dfcb-4673-a8be-beb66f847e21)

### Create PLO
> ![Create PLO](https://github.com/user-attachments/assets/3a30f1ed-39cc-40a6-bbc2-96fbcb6c4bbf)

### Update PLO
> ![Update PLO](https://github.com/user-attachments/assets/eaa5673a-7475-46fd-bd5b-1878d68d2674)

### Delete PLO
> ![Delete PLO](https://github.com/user-attachments/assets/a3ff70bd-0ffd-49cb-8cda-eb9f3b11c76c)


> 📁 **screenshots in PPT.**

---

## ✅ Features

- [x] SQLite-based persistence
- [x] Modular Java Swing interface
- [x] Create and delete functionality
- [x] Error handling and feedback dialogs

---

## 📌 Future Scope

- Add PLO update and view forms
- Export data to CSV/PDF
- User authentication layer
- Integration with other modules (courses, COs, etc.)

---

## 📄 License

This project is for academic use only under SRM University AP.  
Feel free to adapt it with credit.

---
