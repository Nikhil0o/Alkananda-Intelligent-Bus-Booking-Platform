# 🚌 Alkananda – Intelligent Bus Ticket Booking Platform

Alkananda is a full-stack intelligent bus ticket booking platform designed to simplify bus discovery, trip management, seat selection, and online ticket booking.

The platform combines a modern frontend, Spring Boot backend, MySQL database, Machine Learning, analytics services, and AI assistance to provide a secure, scalable, and intelligent travel booking experience.

---

## 🚀 Key Features

### 👤 User Management
- User registration and login
- Secure password hashing using BCrypt
- JWT-based authentication
- Role-based authorization
- Spring Security integration

### 🚌 Bus & Route Management
- Admin can manage buses
- Admin can create and manage reusable routes
- Route contains source, destination, and distance
- Buses and routes act as reusable master data

### 🗓️ Trip Management
- Admin creates trips by assigning:
  - Existing bus
  - Existing route
  - Travel date
  - Departure time
  - Arrival time
  - Fare
- Prevents duplication of route information across trips

### 💺 Seat Management
- Seat creation and management
- Seat availability checking
- Booked/available seat status
- Seat selection during booking

### 🎫 Booking System
- Secure ticket booking
- Booking linked with user, trip, and seat
- Booking status management
- Booking history

### 📊 Analytics
- Booking analytics
- Revenue analytics
- Route performance analysis
- Seat occupancy analysis
- Booking trends

### 🤖 Machine Learning
- Machine Learning integration for intelligent analytics and prediction
- Data-driven insights for improving the travel booking experience

### 🧠 AI Assistance
- AI-powered travel assistance
- Helps users interact with the booking platform more naturally
- Designed to provide intelligent assistance during the travel planning process

---

# 🏗️ System Architecture

```text
                    ┌──────────────────────┐
                    │    React Frontend    │
                    └──────────┬───────────┘
                               │
                               │ REST API
                               ▼
                    ┌──────────────────────┐
                    │   Spring Boot API    │
                    │                      │
                    │ Controllers          │
                    │ Services             │
                    │ Repositories         │
                    │ Spring Security      │
                    │ JWT Authentication   │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │    MySQL Database    │
                    └──────────────────────┘
                              
                               │
                 ┌─────────────┴─────────────┐
                 ▼                           ▼
      ┌────────────────────┐       ┌──────────────────┐
      │ Analytics / ML     │       │ AI Assistance    │
      │ Service            │       │                  │
      └────────────────────┘       └──────────────────┘
