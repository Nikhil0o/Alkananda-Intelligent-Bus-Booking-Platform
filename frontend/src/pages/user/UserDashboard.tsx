import { useNavigate } from "react-router-dom";
import "./UserDashboard.css";
import logo from "../../assets/alkananda_logo.png";
import SearchBox from "../../components/SearchBox";

function UserDashboard() {

  const email = localStorage.getItem("email");

  const navigate = useNavigate();


  return (

    <div className="dashboard">


      {/* ================= NAVBAR ================= */}

      <nav className="navbar">

        <div className="logo">

          <img
            src={logo}
            alt="Alkananda Smart Bus Services"
          />

        </div>


        <div className="nav-links">

          <span className="active">
            Dashboard
          </span>


          <span
            onClick={() => navigate("/user/bookings")}
          >
            My Bookings
          </span>


          <span>

            <button
              onClick={() => navigate("/user/profile")}
            >
              Profile
            </button>
          </span>


          <div className="user-info">

            <div className="avatar">
              👤
            </div>

            <span>
              {email || "User"}
            </span>

          </div>

        </div>

      </nav>



      {/* ================= MAIN ================= */}

      <main className="dashboard-content">


        {/* ================= WELCOME ================= */}

        <section className="welcome-section">

          <p className="small-title">
            WELCOME BACK
          </p>

          <h1>
            Where do you want to go?
          </h1>

          <p className="subtitle">
            Find your perfect bus and enjoy a comfortable journey.
          </p>

        </section>



        {/* ================= SEARCH ================= */}

        <SearchBox />



        {/* ================= STATS ================= */}

        <section className="stats">


          <div className="stat-card">

            <span className="stat-icon">
              🎫
            </span>

            <div>

              <p>
                Total Trips
              </p>

              <h3>
                0
              </h3>

            </div>

          </div>



          <div className="stat-card">

            <span className="stat-icon">
              🚌
            </span>

            <div>

              <p>
                Upcoming Trips
              </p>

              <h3>
                0
              </h3>

            </div>

          </div>



          <div className="stat-card">

            <span className="stat-icon">
              ⭐
            </span>

            <div>

              <p>
                Travel Points
              </p>

              <h3>
                0
              </h3>

            </div>

          </div>


        </section>



        {/* ================= UPCOMING ================= */}

        <section className="upcoming-section">


          <div className="section-header">

            <h2>
              Upcoming Trip
            </h2>

            <span
              onClick={() => navigate("/user/bookings")}
            >
              View all →
            </span>

          </div>


          <div className="empty-trip">

            <div className="empty-icon">
              🚌
            </div>

            <h3>
              No upcoming trips
            </h3>

            <p>
              Your upcoming bookings will appear here.
            </p>

          </div>


        </section>


      </main>

    </div>

  );
}

export default UserDashboard;