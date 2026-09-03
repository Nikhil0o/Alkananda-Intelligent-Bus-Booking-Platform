import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/auth/Login";
import Register from "./pages/auth/Register";
import UserDashboard from "./pages/user/UserDashboard";
import SearchResults from "./pages/user/SearchResults";
import SeatSelection from "./pages/user/SeatSelection";
import MyBookings from "./pages/user/MyBookings";
import UserProfile from "./pages/user/UserProfile";
// import UserProfile from "./pages/user/UserProfile";

function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route
          path="/login"
          element={<Login />}
        />

        <Route
          path="/register"
          element={<Register />}
        />

        <Route
          path="/user/dashboard"
          element={<UserDashboard />}
        />

        <Route
          path="/user/search-results"
          element={<SearchResults />}
        />

        <Route
          path="/user/seat-selection/:tripId"
          element={<SeatSelection />}
        />

        <Route
          path="/user/bookings"
          element={<MyBookings />}
        />

        <Route
          path="/user/profile"
          element={<UserProfile />}
        />

      </Routes>
    </BrowserRouter>
  );
}

export default App;