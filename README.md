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

### 📊 Analytics

The platform includes an analytics service for analyzing booking data.

Planned/implemented analytics include:

- Booking analytics
- Revenue analytics
- Route performance analysis
- Seat occupancy analysis
- Booking trends
- Popular routes

Analytics services are separated from the core booking application to support scalability.

---

### 🤖 Machine Learning

Machine Learning is integrated into the platform for intelligent analytics and prediction.

Potential applications include:

- Demand prediction
- Travel pattern analysis
- Booking prediction
- Route performance prediction
- Data-driven travel insights

---

### 🧠 AI Assistance

Alkananda is designed to include AI-powered travel assistance.

The AI assistant can help users:

- Understand available travel options
- Interact naturally with the platform
- Get travel-related assistance
- Improve the overall booking experience

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
Login
  │
  ▼
User Dashboard
  │
  ├── Search Trip
  │      │
  │      ▼
  │   Search Results
  │      │
  │      ▼
  │   Seat Selection
  │      │
  │      ▼
  │   Booking Confirmation
  │      │
  │      ▼
  │   Booking Success
  │
  ├── My Bookings
  │      │
  │      └── Cancel Booking
  │
  └── Profile
         │
         └── Logout
### Authentication Flow
User Login
     │
     ▼
Spring Security
     │
     ▼
AuthenticationManager
     │
     ▼
User Verification
     │
     ▼
BCrypt Password Validation
     │
     ▼
JWT Token Generation
     │
     ▼
Frontend Stores Token
     │
     ▼
JWT Authentication Filter
     │
     ▼
Protected REST APIs
### System Architecture
                         ┌──────────────────────────┐
                         │      React Frontend      │
                         │                          │
                         │  Login                   │
                         │  Registration             │
                         │  Dashboard               │
                         │  Trip Search             │
                         │  Search Results          │
                         │  Seat Selection          │
                         │  Booking                 │
                         │  My Bookings             │
                         │  User Profile            │
                         └────────────┬─────────────┘
                                      │
                                      │ REST API
                                      ▼
                         ┌──────────────────────────┐
                         │     Spring Boot API      │
                         │                          │
                         │ Controllers              │
                         │ Services                 │
                         │ Repositories             │
                         │ Spring Security          │
                         │ JWT Authentication       │
                         └────────────┬─────────────┘
                                      │
                                      │ JPA / Hibernate
                                      ▼
                         ┌──────────────────────────┐
                         │      MySQL Database      │
                         │                          │
                         │ Users                    │
                         │ Buses                    │
                         │ Routes                   │
                         │ Trips                    │
                         │ Seats                    │
                         │ Bookings                 │
                         └────────────┬─────────────┘
                                      │
                         ┌────────────┴────────────┐
                         │                         │
                         ▼                         ▼
                ┌──────────────────┐      ┌──────────────────┐
                │ Analytics / ML   │      │ AI Assistance    │
                │ Service          │      │ Service          │
                │                  │      │                  │
                │ FastAPI          │      │ Intelligent      │
                │ Python           │      │ Travel Assistant │
                │ Pandas           │      │                  │
                └──────────────────┘      └──────────────────┘
### Backend Architecture
                Controller
                    │
                    ▼
                 Service
                    │
                    ▼
                Repository
                    │
                    ▼
              MySQL Database
###Main Entities
User
  │
  ├────────────── Booking
  │                  │
  │                  ├── Trip
  │                  │    │
  │                  │    └── Route
  │                  │
  │                  ├── Seat
  │                  │
  │                  └── Bus
  │
  └── Role
