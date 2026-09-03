import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "./SeatSelection.css";

interface Seat {
  seatId: number;
  seatNumber: number;
  status: string;
}

function SeatSelection() {

  const { tripId } = useParams<{ tripId: string }>();

  const navigate = useNavigate();

  const [seats, setSeats] = useState<Seat[]>([]);
  const [selectedSeat, setSelectedSeat] = useState<number | null>(null);

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");


  useEffect(() => {

    const fetchSeats = async () => {

      try {

        setLoading(true);
        setError("");

        console.log("Fetching seats for trip:", tripId);

        const response = await fetch(
          `http://localhost:8080/seat/trip/${tripId}`
        );

        console.log("Seat API status:", response.status);

        if (!response.ok) {
          throw new Error(
            `Failed to fetch seats. Status: ${response.status}`
          );
        }

        const data: Seat[] = await response.json();

        console.log("Seats received:", data);

        setSeats(data);

      } catch (err) {

        console.error("Seat API error:", err);

        if (err instanceof Error) {
          setError(err.message);
        } else {
          setError("Unable to load seats");
        }

      } finally {

        setLoading(false);

      }

    };


    if (tripId) {
      fetchSeats();
    } else {
      setError("Trip ID is missing");
      setLoading(false);
    }

  }, [tripId]);


  const handleSeatClick = (seat: Seat) => {

    if (seat.status !== "AVAILABLE") {
      return;
    }

    setSelectedSeat(seat.seatId);

  };


  const getSelectedSeatNumber = () => {

    const seat = seats.find(
      (seat) => seat.seatId === selectedSeat
    );

    return seat ? seat.seatNumber : null;

  };


  const handleContinue = () => {

    if (selectedSeat === null) {
      alert("Please select a seat first.");
      return;
    }

    const seatNumber = getSelectedSeatNumber();

    console.log("Selected Seat ID:", selectedSeat);
    console.log("Selected Seat Number:", seatNumber);

    navigate("/user/booking-confirmation", {
      state: {
        tripId: Number(tripId),
        seatId: selectedSeat,
        seatNumber: seatNumber
      }
    });

  };


  return (

    <div className="seat-page">


      {/* ================= NAVBAR ================= */}

      <nav className="seat-navbar">

        <div
          className="seat-logo"
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



      {/* ================= CONTENT ================= */}

      <main className="seat-container">


        {/* ================= HEADING ================= */}

        <section className="seat-heading">

          <p>
            SELECT YOUR SEAT
          </p>

          <h1>
            Choose your seat
          </h1>

          <span>
            Trip ID: {tripId}
          </span>

        </section>



        {/* ================= LOADING ================= */}

        {loading && (

          <div className="seat-status">

            <div className="loader"></div>

            <h3>
              Loading seats...
            </h3>

          </div>

        )}



        {/* ================= ERROR ================= */}

        {!loading && error && (

          <div className="seat-status">

            <div className="status-icon">
              ⚠️
            </div>

            <h3>
              Unable to load seats
            </h3>

            <p>
              {error}
            </p>

          </div>

        )}



        {/* ================= SEATS ================= */}

        {!loading &&
          !error &&
          seats.length > 0 && (

            <section className="seat-layout">


              {/* LEGEND */}

              <div className="seat-legend">

                <div>
                  <span className="legend-box available"></span>
                  Available
                </div>

                <div>
                  <span className="legend-box selected"></span>
                  Selected
                </div>

                <div>
                  <span className="legend-box booked"></span>
                  Booked
                </div>

              </div>



              {/* DRIVER */}

              <div className="driver">

                🚌 Driver

              </div>



              {/* SEAT GRID */}

              <div className="seats-grid">

                {seats.map((seat) => {

                  const isAvailable =
                    seat.status === "AVAILABLE";

                  const isSelected =
                    selectedSeat === seat.seatId;


                  return (

                    <button
                      key={seat.seatId}

                      className={`
                        seat
                        ${isAvailable
                          ? "available"
                          : "booked"
                        }
                        ${isSelected
                          ? "selected"
                          : ""
                        }
                      `}

                      disabled={!isAvailable}

                      onClick={() =>
                        handleSeatClick(seat)
                      }
                    >

                      {seat.seatNumber}

                    </button>

                  );

                })}

              </div>



              {/* ================= SUMMARY ================= */}

              <div className="selection-summary">


                <div>

                  <span>
                    Selected Seat
                  </span>

                  <strong>

                    {getSelectedSeatNumber() ??
                      "None"}

                  </strong>

                </div>


                <button
                  className="continue-button"
                  disabled={selectedSeat === null}
                  onClick={handleContinue}
                >
                  Continue →
                </button>


              </div>

            </section>

          )}



        {/* ================= NO SEATS ================= */}

        {!loading &&
          !error &&
          seats.length === 0 && (

            <div className="seat-status">

              <div className="status-icon">
                🚌
              </div>

              <h3>
                No seats found
              </h3>

              <p>
                No seats are available for this trip.
              </p>

            </div>

          )}

      </main>

    </div>

  );

}

export default SeatSelection;