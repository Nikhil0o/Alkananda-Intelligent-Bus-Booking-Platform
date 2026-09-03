# 🚌 Alkananda – Intelligent Bus Ticket Booking Platform

Alkananda is a full-stack intelligent bus ticket booking platform designed to simplify bus discovery, trip management, seat selection, and online ticket booking.

The platform combines a modern React frontend, Spring Boot backend, MySQL database, analytics, Machine Learning, and AI assistance to provide a secure, scalable, and intelligent travel booking experience.

---

## 🚀 Key Features

### 👤 User Management

- User registration
- User login
- Secure password hashing using BCrypt
- JWT-based authentication
- Role-based authorization
- Spring Security integration
- User profile management
- Secure logout

---

### 🚌 Bus & Route Management

- Admin can manage buses
- Admin can create and manage reusable routes
- Route contains:
  - Source
  - Destination
  - Distance
- Buses and routes act as reusable master data
- Prevents unnecessary duplication of route information

---

### 🗓️ Trip Management

Admin can create trips by assigning:

- Existing bus
- Existing route
- Travel date
- Departure time
- Arrival time
- Fare

Trip information is connected with reusable Bus and Route master data.

---

### 🔍 Trip Search

Users can search for available trips using:

- Source
- Destination
- Travel date

The frontend displays matching trips and allows users to continue to seat selection.

---

### 💺 Seat Management

- Seat creation and management
- Seat availability checking
- Available/Booked seat status
- Interactive seat selection
- Trip-specific seat availability
- Prevents booking of already booked seats

Users can view the seat layout before booking their ticket.

---

### 🎫 Ticket Booking

Users can:

1. Search for a trip
2. Select a trip
3. View available seats
4. Select a seat
5. Confirm the booking
6. Receive booking confirmation
7. View the booked ticket in My Bookings

Booking is securely associated with:

- User
- Trip
- Seat
- Bus
- Route

---

### 📋 My Bookings

Users can view their complete booking history.

Each booking provides information such as:

- Booking ID
- Bus number
- Seat number
- Source
- Destination
- Travel date
- Departure time
- Arrival time
- Booking amount
- Booking time
- Booking status

Users can also cancel eligible bookings.

When a booking is cancelled:

- Booking status becomes `CANCELLED`
- The booking record is preserved
- The seat becomes available again

---

### 👤 User Profile

The frontend provides a dedicated user profile page.

Users can view:

- Profile name
- Email address
- User ID
- Account role
- Account information

The profile page also provides quick navigation to:

- Dashboard
- My Bookings
- Logout

---

## 📊 Analytics

The platform includes a separate analytics service for analyzing booking data.

Analytics capabilities include:

- Booking analytics
- Revenue analytics
- Route performance analysis
- Seat occupancy analysis
- Booking trends
- Popular route analysis

The analytics service is separated from the core booking application to support scalability and independent data processing.

### Analytics Technology

- Python
- FastAPI
- Pandas
- MySQL

---

## 🤖 Machine Learning

Machine Learning is integrated into the platform for intelligent analytics and prediction.

Potential applications include:

- Demand prediction
- Travel pattern analysis
- Booking prediction
- Route performance prediction
- Data-driven travel insights

The ML layer can be extended independently as the amount of booking and travel data increases.

---

## 🧠 AI Assistance

Alkananda is designed to support AI-powered travel assistance.

The AI assistant can help users:

- Understand available travel options
- Interact naturally with the platform
- Get travel-related assistance
- Improve the overall booking experience

The AI layer is designed as an independent service so that it can be enhanced without changing the core booking system.

---

# 💻 Frontend

The frontend is built using:

- React
- TypeScript
- Vite
- React Router
- CSS

### Frontend User Flow

```text
                         ┌──────────────┐
                         │    Login     │
                         └──────┬───────┘
                                │
                                ▼
                    ┌──────────────────────┐
                    │   User Dashboard     │
                    └──────────┬───────────┘
                               │
             ┌─────────────────┼─────────────────┐
             │                 │                 │
             ▼                 ▼                 ▼
       Search Trip        My Bookings         Profile
             │                 │                 │
             ▼                 ▼                 ▼
      Search Results     Booking History      User Info
             │                 │                 │
             ▼                 ▼                 ▼
      Seat Selection     Cancel Booking       Logout
             │
             ▼
    Booking Confirmation
             │
             ▼
       Booking Success
---

# 🔐 Authentication Flow

Alkananda uses Spring Security, BCrypt, and JWT for secure authentication and authorization.

```text
                         User
                          │
                          │ Email + Password
                          ▼
                  React Frontend
                          │
                          │ POST /auth/login
                          ▼
                   AuthController
                          │
                          ▼
                     AuthService
                          │
                          ▼
                  UserRepository
                          │
                          ▼
                   MySQL Database
                          │
                          ▼
                 BCrypt Password Check
                          │
                          ▼
                  JWT Token Generation
                          │
                          ▼
                  React Frontend
                          │
                          │ Store JWT
                          ▼
              Protected API Request
                          │
                          │ Bearer Token
                          ▼
             JwtAuthenticationFilter
                          │
                          ▼
                    Validate JWT
                          │
                          ▼
                 Extract User Email
                          │
                          ▼
                  Load User + Role
                          │
                          ▼
                  SecurityContext
                          │
                          ▼
                Protected Controller
                          │
                          ▼
                     Service
                          │
                          ▼
                     Database
