import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./MyBooking.css";
interface Booking {
  bookingId: number;
  seatNumber: number;
  source: string;
  destination: string;
  travelDate: string;
  busNumber: string;
  departureTime: string;
  arrivalTime: string;
  amount: number;
  status: string;
  bookingTime: string;
}

function MyBookings() {
  const navigate = useNavigate();

  const [bookings, setBookings] = useState<Booking[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchBookings();
  }, []);

  const fetchBookings = async () => {
    try {
      setLoading(true);
      setError("");

      const token = localStorage.getItem("token");
      const userId = localStorage.getItem("userId");

      if (!token) {
        navigate("/login");
        return;
      }

      if (!userId || userId === "undefined") {
        setError(
          "User information is missing. Please login again."
        );
        return;
      }

      const response = await fetch(
        `http://localhost:8080/booking/user/${userId}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
          }
        }
      );

      if (response.status === 401) {
        localStorage.clear();
        navigate("/login");
        return;
      }

      if (!response.ok) {
        throw new Error(
          `Failed to load bookings. Status: ${response.status}`
        );
      }

      const data: Booking[] = await response.json();

      setBookings(data);
    } catch (err) {
      console.error("My bookings error:", err);

      if (err instanceof Error) {
        setError(err.message);
      } else {
        setError("Unable to load your bookings.");
      }
    } finally {
      setLoading(false);
    }
  };

  const handleCancel = async (bookingId: number) => {
    const confirmed = window.confirm(
      "Are you sure you want to cancel this booking?"
    );

    if (!confirmed) {
      return;
    }

    try {
      const token = localStorage.getItem("token");

      const response = await fetch(
        `http://localhost:8080/booking/cancel/${bookingId}`,
        {
          method: "DELETE",
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      if (!response.ok) {
        throw new Error(
          `Cancellation failed. Status: ${response.status}`
        );
      }

      await fetchBookings();

      alert("Booking cancelled successfully.");
    } catch (err) {
      console.error("Cancel booking error:", err);

      if (err instanceof Error) {
        alert(err.message);
      } else {
        alert("Unable to cancel booking.");
      }
    }
  };

  const formatTime = (time?: string) => {
    if (!time) return "--";

    const [hour, minute] = time.split(":");

    const date = new Date();
    date.setHours(
      Number(hour),
      Number(minute),
      0,
      0
    );

    return date.toLocaleTimeString([], {
      hour: "2-digit",
      minute: "2-digit"
    });
  };

  const formatDate = (date?: string) => {
    if (!date) return "--";

    const value = new Date(`${date}T00:00:00`);

    return value.toLocaleDateString("en-IN", {
      day: "2-digit",
      month: "short",
      year: "numeric"
    });
  };

  return (
    <div className="my-bookings-page">

      <nav className="my-bookings-navbar">

        <div
          className="my-bookings-logo"
          onClick={() =>
            navigate("/user/dashboard")
          }
        >
          🚌 ALKANANDA
        </div>

        <button
          className="back-dashboard-button"
          onClick={() =>
            navigate("/user/dashboard")
          }
        >
          ← Dashboard
        </button>

      </nav>

      <main className="my-bookings-container">

        <div className="my-bookings-header">

          <div>
            <p className="page-label">
              YOUR JOURNEY
            </p>

            <h1>My Bookings</h1>

            <p>
              View and manage all your bus bookings.
            </p>
          </div>

          <div className="booking-count">
            <span>{bookings.length}</span>
            <small>
              {bookings.length === 1
                ? "Booking"
                : "Bookings"}
            </small>
          </div>

        </div>

        {loading && (
          <div className="booking-state">
            <div className="loading-spinner"></div>
            <p>Loading your bookings...</p>
          </div>
        )}

        {!loading && error && (
          <div className="booking-state error-state">
            <div className="state-icon">
              ⚠️
            </div>

            <h2>
              Unable to load bookings
            </h2>

            <p>{error}</p>

            <button
              onClick={fetchBookings}
            >
              Try Again
            </button>
          </div>
        )}

        {!loading &&
          !error &&
          bookings.length === 0 && (
            <div className="booking-state empty-state">

              <div className="empty-icon">
                🎫
              </div>

              <h2>
                No bookings yet
              </h2>

              <p>
                You haven't booked any bus tickets yet.
              </p>

              <button
                onClick={() =>
                  navigate("/user/dashboard")
                }
              >
                Search Buses →
              </button>

            </div>
          )}

        {!loading &&
          !error &&
          bookings.length > 0 && (
            <div className="bookings-list">

              {bookings.map((booking) => {

                const isCancelled =
                  booking.status === "CANCELLED";

                return (
                  <div
                    className={`booking-card ${
                      isCancelled
                        ? "cancelled-card"
                        : ""
                    }`}
                    key={booking.bookingId}
                  >

                    <div className="ticket-top">

                      <div className="ticket-brand">
                        <span>🚌</span>
                        <strong>
                          ALKANANDA
                        </strong>
                      </div>

                      <div
                        className={`status-badge ${
                          isCancelled
                            ? "cancelled"
                            : "confirmed"
                        }`}
                      >
                        {booking.status}
                      </div>

                    </div>

                    <div className="ticket-main">

                      <div className="route-section">

                        <div className="location">
                          <strong>
                            {booking.source}
                          </strong>

                          <span>
                            Source
                          </span>
                        </div>

                        <div className="route-line">
                          <span>●</span>
                          <div></div>
                          <span>●</span>
                        </div>

                        <div className="location destination">
                          <strong>
                            {booking.destination}
                          </strong>

                          <span>
                            Destination
                          </span>
                        </div>

                      </div>

                      <div className="booking-info-grid">

                        <div className="info-item">
                          <span>
                            📅 Travel Date
                          </span>

                          <strong>
                            {formatDate(
                              booking.travelDate
                            )}
                          </strong>
                        </div>

                        <div className="info-item">
                          <span>
                            🕐 Departure
                          </span>

                          <strong>
                            {formatTime(
                              booking.departureTime
                            )}
                          </strong>
                        </div>

                        <div className="info-item">
                          <span>
                            🕐 Arrival
                          </span>

                          <strong>
                            {formatTime(
                              booking.arrivalTime
                            )}
                          </strong>
                        </div>

                        <div className="info-item">
                          <span>
                            🚌 Bus
                          </span>

                          <strong>
                            {booking.busNumber ||
                              "--"}
                          </strong>
                        </div>

                        <div className="info-item">
                          <span>
                            💺 Seat
                          </span>

                          <strong>
                            {booking.seatNumber}
                          </strong>
                        </div>

                        <div className="info-item">
                          <span>
                            🎫 Booking ID
                          </span>

                          <strong>
                            #{booking.bookingId}
                          </strong>
                        </div>

                      </div>

                    </div>

                    <div className="ticket-bottom">

                      <div className="amount-section">
                        <span>
                          Total Fare
                        </span>

                        <strong>
                          ₹{booking.amount}
                        </strong>
                      </div>

                      {!isCancelled && (
                        <button
                          className="cancel-button"
                          onClick={() =>
                            handleCancel(
                              booking.bookingId
                            )
                          }
                        >
                          Cancel Booking
                        </button>
                      )}

                    </div>

                  </div>
                );
              })}

            </div>
          )}

      </main>

    </div>
  );
}

export default MyBookings;