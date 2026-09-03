import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./BookingConfirmation.css";

interface BookingResponse {
  bookingId: number;
  userName: string;
  seatNumber: number;
  source: string;
  destination: string;
  travelDate: string;
  departureTime: string;
  arrivalTime: string;
  amount: number;
  bookingTime: string;
}

interface LocationState {
  tripId: number;
  seatId: number;
  seatNumber: number;
}

function BookingConfirmation() {

  const location = useLocation();
  const navigate = useNavigate();

  const state = location.state as LocationState | null;

  const [loading, setLoading] = useState(false);
  const [booking, setBooking] = useState<BookingResponse | null>(null);
  const [error, setError] = useState("");

  /*
   * If booking information is missing
   */
  if (!state) {
    return (
      <div className="booking-page">

        <div className="booking-error">

          <h2>Booking information missing</h2>

          <p>
            Please select a seat again.
          </p>

          <button
            onClick={() => navigate("/user/dashboard")}
          >
            Back to Dashboard
          </button>

        </div>

      </div>
    );
  }

  /*
   * ================= BOOKING API =================
   */

  const handleBooking = async () => {

    try {

      setLoading(true);
      setError("");

      /*
       * Get JWT token from localStorage
       */
      const token = localStorage.getItem("token");

      if (!token) {
        setError("You are not logged in. Please login again.");
        navigate("/login");
        return;
      }

      /*
       * Booking request
       */
      const request = {
        userId: 1,
        tripId: state.tripId,
        seatId: state.seatId
      };

      console.log("Booking Request:", request);

      /*
       * Call Booking API
       */
      const response = await fetch(
        "http://localhost:8080/booking/book",
        {
          method: "POST",

          headers: {
            "Content-Type": "application/json",

            /*
             * IMPORTANT:
             * Send JWT token
             */
            "Authorization": `Bearer ${token}`
          },

          body: JSON.stringify(request)
        }
      );

      console.log(
        "Booking API status:",
        response.status
      );

      /*
       * Read response safely.
       *
       * Do NOT directly call response.json()
       * because 403/401 responses may have
       * an empty body.
       */
      const responseText = await response.text();

      console.log(
        "Booking API raw response:",
        responseText
      );

      /*
       * Handle unsuccessful response
       */
      if (!response.ok) {

        let errorMessage = "Booking failed";

        if (responseText) {

          try {

            const errorData = JSON.parse(responseText);

            errorMessage =
              errorData.message ||
              errorData.error ||
              `Booking failed. Status: ${response.status}`;

          } catch {

            errorMessage =
              responseText ||
              `Booking failed. Status: ${response.status}`;
          }

        } else {

          if (response.status === 401) {
            errorMessage =
              "Your login session has expired. Please login again.";
          }

          else if (response.status === 403) {
            errorMessage =
              "You are not authorized to make this booking.";
          }

          else {
            errorMessage =
              `Booking failed. Status: ${response.status}`;
          }
        }

        throw new Error(errorMessage);
      }

      /*
       * Successful response
       */
      if (!responseText) {
        throw new Error(
          "Booking was processed but the server returned no booking details."
        );
      }

      const data: BookingResponse =
        JSON.parse(responseText);

      console.log(
        "Booking API response:",
        data
      );

      /*
       * Store booking response
       */
      setBooking(data);

    } catch (err) {

      console.error(
        "Booking error:",
        err
      );

      if (err instanceof Error) {

        setError(err.message);

      } else {

        setError(
          "Unable to complete booking"
        );

      }

    } finally {

      setLoading(false);

    }
  };


  /*
   * ================= BOOKING SUCCESS =================
   */

  if (booking) {

    return (

      <div className="booking-page">

        <nav className="booking-navbar">

          <div className="booking-logo">
            🚌 ALKANANDA
          </div>

        </nav>


        <main className="booking-container">

          <div className="success-card">

            <div className="success-icon">
              ✓
            </div>

            <h1>
              Booking Confirmed!
            </h1>

            <p className="success-message">
              Your bus ticket has been booked successfully.
            </p>

            <div className="booking-id">
              Booking ID: #{booking.bookingId}
            </div>


            <div className="ticket-details">

              <div className="detail-row">
                <span>Passenger</span>

                <strong>
                  {booking.userName}
                </strong>
              </div>


              <div className="detail-row">
                <span>Route</span>

                <strong>
                  {booking.source} → {booking.destination}
                </strong>
              </div>


              <div className="detail-row">
                <span>Travel Date</span>

                <strong>
                  {booking.travelDate}
                </strong>
              </div>


              <div className="detail-row">
                <span>Departure</span>

                <strong>
                  {booking.departureTime?.slice(0, 5)}
                </strong>
              </div>


              <div className="detail-row">
                <span>Arrival</span>

                <strong>
                  {booking.arrivalTime?.slice(0, 5)}
                </strong>
              </div>


              <div className="detail-row">
                <span>Seat Number</span>

                <strong>
                  {booking.seatNumber}
                </strong>
              </div>


              <div className="detail-row total">
                <span>Total Amount</span>

                <strong>
                  ₹{booking.amount}
                </strong>
              </div>

            </div>


            <button
              className="dashboard-button"
              onClick={() =>
                navigate("/user/dashboard")
              }
            >
              Back to Dashboard
            </button>

          </div>

        </main>

      </div>

    );
  }


  /*
   * ================= CONFIRMATION PAGE =================
   */

  return (

    <div className="booking-page">

      <nav className="booking-navbar">

        <div
          className="booking-logo"
          onClick={() =>
            navigate("/user/dashboard")
          }
        >
          🚌 ALKANANDA
        </div>


        <button
          className="back-button"
          onClick={() => navigate(-1)}
        >
          ← Back
        </button>

      </nav>


      <main className="booking-container">

        <div className="confirmation-card">

          <p className="booking-label">
            BOOKING CONFIRMATION
          </p>


          <h1>
            Confirm Your Seat
          </h1>


          <p className="booking-subtitle">
            Please review your seat selection before
            confirming your booking.
          </p>


          <div className="confirmation-details">

            <div className="detail-row">

              <span>
                Trip ID
              </span>

              <strong>
                {state.tripId}
              </strong>

            </div>


            <div className="detail-row">

              <span>
                Seat Number
              </span>

              <strong>
                {state.seatNumber}
              </strong>

            </div>


            <div className="detail-row">

              <span>
                Seat ID
              </span>

              <strong>
                {state.seatId}
              </strong>

            </div>

          </div>


          {error && (

            <div className="booking-error-message">

              ⚠️ {error}

            </div>

          )}


          <button
            className="confirm-button"
            onClick={handleBooking}
            disabled={loading}
          >

            {loading
              ? "Confirming Booking..."
              : "Confirm Booking →"
            }

          </button>

        </div>

      </main>

    </div>

  );
}

export default BookingConfirmation;