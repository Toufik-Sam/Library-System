# Library-System
# 📚 Java Console Library Management App

## 📝 Description

This Java-based console application simulates a simple library management system where users can **sign up**, **Sign in**, and interact with their personal virtual library. An **admin user** has access to additional functionalities to manage users and books in the system.

The app tracks each user's reading progress, manages book collections, and provides a basic CRUD (Create, Read, Update, Delete) interface for both regular users and the admin.

---

## 🔑 User Authentication

- **Sign Up** – Create a new user account with a username, email, and password. A personal library is automatically created.
- **Sign In** – Access the system using existing credentials.
- **Admin Access** – Special account:
  - **Username**: `admin`
  - **Email**: `admin@library.com`
  - **Password**: `000`

---

## 👤 Regular User Features (After Login)

Once logged in as a regular user, the following features are available:

1. 📖 **Read a Book**
   - Resume reading from the last saved page.
   - The system automatically saves your progress.
   
2. 📚 **Get List of Book Collection**
   - View all the books currently in your personal library.

3. ➕ **Add New Book**
   - Add a new book to your collection.

4. 🗑️ **Delete a Book**
   - Remove a book from your personal library.

5. 🔍 **Find a Book**
   - Search for a book by title in The Stock.

6. 🛠️ **Edit Account Info**
   - Update your username, email, or password.

7. 🚪 **Logout**
   - Exit the session safely.

---

## 🛡️ Admin Features

The special admin user can perform the following management tasks:

1. 👥 **Get Users List**
   - View all registered users.

2. ➕ **Add New User**
   - Create a new user account.

3. 🔍 **Find a User**
   - Search for a user by username or email.

4. 🗑️ **Delete a User**
   - Remove a user account and their library.

5. 📚 **Get Books List in Stock**
   - View all books available in the system-wide stock (not tied to personal libraries).

6. ➕ **Add New Book to Stock**
   - Add a new book to the global stock.

7. 🔍 **Find a Book in Stock**
   - Search for a book in the stock.

8. 🗑️ **Delete a Book from Stock**
   - Remove a book from the global stock.

9. 🚪 **Logout**
   - Safely exit the admin session.

---
![image alt](https://github.com/Toufik-Sam/Library-System/blob/Master/C1.PNG)
![image alt](https://github.com/Toufik-Sam/Library-System/blob/Master/C2.PNG)
![image alt](https://github.com/Toufik-Sam/Library-System/blob/Master/C4.PNG)



