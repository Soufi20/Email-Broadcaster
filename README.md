# Email-Broadcaster

## 📌 Overview
This project is a **Java Swing application** that allows you to send bulk emails automatically.  
It reads recipient data from an **Excel file (.xlsx)** and sends personalized emails using **JavaMail API**.

## 🚀 Features
- GUI interface built with **Java Swing**.
- Select an Excel file containing:
  - First Name
  - Last Name
  - Email Address
  - Username
  - Password
- Sends emails to all recipients in the Excel file.
- Custom message field for adding additional text.
- Uses **Gmail SMTP server** with authentication.

## 📂 Excel File Format
The Excel file must have the following columns (starting from row 2):
| First Name | Last Name | Email | Username | Password |
|------------|-----------|-------|----------|----------|
