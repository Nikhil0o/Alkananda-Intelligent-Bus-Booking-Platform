import { useEffect, useState } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import "./SearchResults.css";


interface Trip {

  tripId: number;

  busNumber: string;

  busType: string | null;

  source: string;

  destination: string;

  travelDate: string;

  departureTime: string;

  arrivalTime: string;

  fare: number;

  totalSeats: number;

  availableSeats: number;

}


interface TripResponse {

  content: Trip[];

  totalElements: number;

  totalPages: number;

  number: number;

  size: number;

}


function SearchResults() {


  const [searchParams] = useSearchParams();

  const navigate = useNavigate();


  const source = searchParams.get("from") || "";

  const destination = searchParams.get("to") || "";

  const date = searchParams.get("date") || "";


  const [trips, setTrips] = useState<Trip[]>([]);

  const [loading, setLoading] = useState(true);

  const [error, setError] = useState("");


  useEffect(() => {

    const fetchTrips = async () => {

      try {

        setLoading(true);

        setError("");


        console.log("SOURCE:", source);

        console.log("DESTINATION:", destination);

        console.log("DATE:", date);


        const url =
          `http://localhost:8080/trip/search` +
          `?source=${encodeURIComponent(source)}` +
          `&destination=${encodeURIComponent(destination)}` +
          `&date=${encodeURIComponent(date)}` +
          `&page=0` +
          `&size=10`;


        console.log("API URL:", url);


        const response = await fetch(url);


        console.log("HTTP STATUS:", response.status);


        if (!response.ok) {

          throw new Error(
            `Server returned ${response.status}`
          );

        }


        const data: TripResponse = await response.json();


        console.log("API RESPONSE:", data);

        console.log("TRIPS:", data.content);


        setTrips(data.content || []);

      }

      catch (err) {

        console.error("SEARCH ERROR:", err);

        setError(
          err instanceof Error
            ? err.message
            : "Unable to load trips"
        );

      }

      finally {

        setLoading(false);

      }

    };


    if (source && destination && date) {

      fetchTrips();

    }

    else {

      setLoading(false);

      setError("Search information is missing.");

    }


  }, [source, destination, date]);


  return (

    <div className="search-results-page">


      {/* ================= NAVBAR ================= */}

      <nav className="results-navbar">


        <div
          className="results-logo"
          onClick={() => navigate("/user/dashboard")}
        >

          🚌 ALKANANDA

        </div>


        <button
          className="back-button"
          onClick={() => navigate("/user/dashboard")}
        >
          ← Back to Dashboard
        </button>


      </nav>



      {/* ================= CONTENT ================= */}

      <main className="results-container">


        {/* SEARCH SUMMARY */}

        <div className="results-heading">

          <p className="results-label">
            BUS SEARCH
          </p>

          <h1>
            {source} → {destination}
          </h1>

          <p>
            Travel date: <strong>{date}</strong>
          </p>

        </div>



        {/* LOADING */}

        {loading && (

          <div className="status-box">

            <div className="loader"></div>

            <h3>
              Searching buses...
            </h3>

            <p>
              Please wait while we find available trips.
            </p>

          </div>

        )}



        {/* ERROR */}

        {!loading && error && (

          <div className="status-box error-box">

            <div className="status-icon">
              ⚠️
            </div>

            <h3>
              Unable to load trips
            </h3>

            <p>
              {error}
            </p>

            <button
              onClick={() => navigate("/user/dashboard")}
            >
              Search Again
            </button>

          </div>

        )}



        {/* NO TRIPS */}

        {!loading &&
          !error &&
          trips.length === 0 && (

            <div className="status-box">

              <div className="status-icon">
                🚌
              </div>

              <h3>
                No buses found
              </h3>

              <p>
                No trips are available for this route and date.
              </p>

              <button
                onClick={() =>
                  navigate("/user/dashboard")
                }
              >
                Search Again
              </button>

            </div>

          )}



        {/* TRIPS */}

        {!loading &&
          !error &&
          trips.length > 0 && (

            <>

              <div className="results-count">

                {trips.length} bus
                {trips.length !== 1 ? "es" : ""} found

              </div>


              <div className="trip-list">


                {trips.map((trip) => (

                  <div
                    className="trip-card"
                    key={trip.tripId}
                  >


                    {/* BUS INFO */}

                    <div className="bus-info">

                      <div className="bus-icon">
                        🚌
                      </div>

                      <div>

                        <h2>
                          {trip.busNumber}
                        </h2>

                        <p>
                          {trip.busType || "Standard Bus"}
                        </p>

                      </div>

                    </div>



                    {/* ROUTE */}

                    <div className="trip-route">


                      <div className="time-block">

                        <strong>
                          {trip.departureTime?.slice(0, 5)}
                        </strong>

                        <span>
                          {trip.source}
                        </span>

                      </div>


                      <div className="route-line">

                        <span>
                          ●
                        </span>

                        <div></div>

                        <span>
                          ●
                        </span>

                      </div>


                      <div className="time-block">

                        <strong>
                          {trip.arrivalTime?.slice(0, 5)}
                        </strong>

                        <span>
                          {trip.destination}
                        </span>

                      </div>


                    </div>



                    {/* FARE */}

                    <div className="fare-info">

                      <span>
                        Starting from
                      </span>

                      <strong>
                        ₹{trip.fare}
                      </strong>


                      <span className="seats">

                        {trip.availableSeats > 0
                          ? `${trip.availableSeats} seats available`
                          : "Seats unavailable"}

                      </span>

                    </div>



                    {/* BUTTON */}

                    <button
                      className="select-button"
                      disabled={trip.availableSeats <= 0}
                      onClick={() =>
                        navigate(
                          `/user/seat-selection/${trip.tripId}`
                        )
                      }
                    >

                      {trip.availableSeats > 0
                        ? "Select Seats →"
                        : "Sold Out"}

                    </button>


                  </div>

                ))}


              </div>

            </>

          )}


      </main>

    </div>

  );

}


export default SearchResults;