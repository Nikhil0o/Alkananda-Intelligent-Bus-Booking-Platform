import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./SearchBox.css";

function SearchBox() {

  const navigate = useNavigate();

  const [from, setFrom] = useState("");
  const [to, setTo] = useState("");
  const [travelDate, setTravelDate] = useState("");


  const handleSearch = () => {

    if (!from.trim() || !to.trim() || !travelDate) {
      alert("Please enter From, To and Travel Date");
      return;
    }

    const url =
      `/user/search-results?from=${encodeURIComponent(from.trim())}` +
      `&to=${encodeURIComponent(to.trim())}` +
      `&date=${travelDate}`;

    console.log("Navigating to:", url);

    navigate(url);
  };


  return (

    <section className="search-card">

      <div className="search-header">

        <h2>
          Search Your Journey
        </h2>

        <span className="route-icon">
          ⇄
        </span>

      </div>


      <div className="search-fields">

        {/* FROM */}

        <div className="field">

          <label>
            FROM
          </label>

          <input
            type="text"
            placeholder="Departure city"
            value={from}
            onChange={(e) => setFrom(e.target.value)}
          />

        </div>


        {/* TO */}

        <div className="field">

          <label>
            TO
          </label>

          <input
            type="text"
            placeholder="Destination city"
            value={to}
            onChange={(e) => setTo(e.target.value)}
          />

        </div>


        {/* DATE */}

        <div className="field">

          <label>
            TRAVEL DATE
          </label>

          <input
            type="date"
            value={travelDate}
            onChange={(e) => setTravelDate(e.target.value)}
          />

        </div>


        {/* BUTTON */}

        <button
          className="search-button"
          onClick={handleSearch}
        >
          Search Journey
        </button>

      </div>

    </section>

  );
}

export default SearchBox;