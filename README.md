# 🚌 Alkananda – Intelligent Bus Ticket Booking Platform

Alkananda is a full-stack intelligent bus ticket booking platform designed to simplify the complete bus travel booking process.

The platform allows users to register, securely log in, search for trips, check seat availability, select seats, book tickets, view booking history, cancel bookings, and manage their profiles.

The system is built using a modern full-stack architecture with:

- React + TypeScript frontend
- Spring Boot backend
- Spring Security
- JWT authentication
- BCrypt password hashing
- MySQL database
- JPA / Hibernate
- FastAPI analytics service
- Python and Pandas
- Machine Learning capabilities
- AI assistance architecture

---

# 📌 Table of Contents

- [Project Overview](#-project-overview)
- [Key Features](#-key-features)
- [User Features](#-user-features)
- [Admin Features](#-admin-features)
- [Authentication & Security](#-authentication--security)
- [Trip Management](#-trip-management)
- [Seat Management](#-seat-management)
- [Booking System](#-booking-system)
- [My Bookings](#-my-bookings)
- [User Profile](#-user-profile)
- [Analytics](#-analytics)
- [Machine Learning](#-machine-learning)
- [AI Assistance](#-ai-assistance)
- [Frontend Architecture](#-frontend-architecture)
- [Backend Architecture](#-backend-architecture)
- [System Architecture](#-system-architecture)
- [Authentication Flow](#-authentication-flow)
- [Booking Flow](#-booking-flow)
- [Database Design](#-database-design)
- [Entity Relationships](#-entity-relationships)
- [API Endpoints](#-api-endpoints)
- [Technology Stack](#-technology-stack)
- [Project Structure](#-project-structure)
- [Frontend Pages](#-frontend-pages)
- [Security Implementation](#-security-implementation)
- [Analytics Architecture](#-analytics-architecture)
- [Installation & Setup](#-installation--setup)
- [Running the Project](#-running-the-project)
- [Current Development Status](#-current-development-status)
- [Future Enhancements](#-future-enhancements)
- [Scalability](#-scalability)
- [Project Objective](#-project-objective)
- [Learning Outcomes](#-learning-outcomes)
- [Conclusion](#-conclusion)

---

# 🚀 Project Overview

Alkananda is designed to provide a complete digital bus ticket booking experience.

The system follows a separation between reusable master data and actual travel trips.

### Reusable Master Data

The application maintains:

- Buses
- Routes

These entities can be reused for multiple trips.

### Trip Data

A trip is created by assigning:

- Existing Bus
- Existing Route
- Travel Date
- Departure Time
- Arrival Time
- Fare

This design avoids unnecessary duplication of bus and route information.

---

# ✨ Key Features

## 👤 User Management

- User registration
- User login
- BCrypt password hashing
- JWT authentication
- Role-based authorization
- Spring Security integration
- User profile
- Secure logout
- Protected APIs
- User-specific booking history

---

## 🚌 Bus Management

Admin can manage buses.

Bus information can include:

- Bus ID
- Bus number
- Bus details
- Associated trips

A bus acts as reusable master data.

The same bus can be assigned to multiple trips.

---

## 🛣️ Route Management

Routes are maintained as reusable master data.

A route contains:

- Route ID
- Source
- Destination
- Distance

Example:

```text
Route 1

Source      → Patna
Destination → Ranchi
Distance    → 300 KM
