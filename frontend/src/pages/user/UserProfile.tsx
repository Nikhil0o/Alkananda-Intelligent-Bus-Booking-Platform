import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./UserProfile.css";

interface UserProfileData {
  id: number;
  name: string;
  email: string;
  role: string;
}

function UserProfile() {
  const navigate = useNavigate();

  const [user, setUser] =
    useState<UserProfileData | null>(null);

  const [loading, setLoading] =
    useState(true);

  const [error, setError] =
    useState("");

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        setLoading(true);
        setError("");

        const token =
          localStorage.getItem("token");

        const userId =
          localStorage.getItem("userId");

        if (!token || !userId) {
          setError(
            "Your login session is not available. Please login again."
          );
          return;
        }

        const response = await fetch(
          `http://localhost:8080/user/${userId}`,
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        const text = await response.text();

        let data: any = {};

        if (text) {
          try {
            data = JSON.parse(text);
          } catch {
            data = {
              message: text,
            };
          }
        }

        if (response.status === 401) {
          localStorage.removeItem("token");
          localStorage.removeItem("userId");
          localStorage.removeItem("email");
          localStorage.removeItem("role");

          navigate("/login");
          return;
        }

        if (!response.ok) {
          throw new Error(
            data.message ||
              `Failed to load profile. Status: ${response.status}`
          );
        }

        setUser(data);
      } catch (err) {
        console.error("Profile error:", err);

        if (err instanceof Error) {
          setError(err.message);
        } else {
          setError("Unable to load profile.");
        }
      } finally {
        setLoading(false);
      }
    };

    fetchProfile();
  }, [navigate]);

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    localStorage.removeItem("email");
    localStorage.removeItem("role");

    navigate("/login");
  };

  if (loading) {
    return (
      <div className="profile-page">
        <div className="profile-loading">
          <div className="loading-spinner"></div>
          <p>Loading your profile...</p>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="profile-page">
        <div className="profile-error-card">
          <div className="error-icon">⚠️</div>

          <h2>Unable to Load Profile</h2>

          <p>{error}</p>

          <button
            onClick={() =>
              navigate("/user/dashboard")
            }
          >
            ← Back to Dashboard
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="profile-page">

      {/* Navbar */}
      <nav className="profile-navbar">

        <div
          className="profile-brand"
          onClick={() =>
            navigate("/user/dashboard")
          }
        >
          <div className="brand-icon">
            <img
              src="/src/assets/alkananda1_logo.jpeg"
              alt="Alkananda Logo"
            />
          </div>

          <div className="brand-text">
            <span>ALKANANDA</span>
            <small>SMART BUS SERVICES</small>
          </div>
        </div>

        <button
          className="back-button"
          onClick={() => navigate(-1)}
        >
          ← Back
        </button>

      </nav>

      {/* Main */}
      <main className="profile-main">

        {/* Page heading */}
        <div className="profile-heading">

          <div>
            <span className="heading-tag">
              ACCOUNT
            </span>

            <h1>My Profile</h1>

            <p>
              Manage and view your Alkananda
              account information
            </p>
          </div>

        </div>

        {/* Profile Card */}
        <section className="profile-card">

          {/* Cover */}
          <div className="profile-cover">

            <div className="cover-pattern"></div>

            <div className="avatar-wrapper">
              <div className="profile-avatar">
                {user?.name
                  ?.charAt(0)
                  .toUpperCase()}
              </div>

              <div className="online-dot"></div>
            </div>

          </div>

          {/* User identity */}
          <div className="profile-identity">

            <div>
              <h2>{user?.name}</h2>

              <p>
                {user?.email}
              </p>
            </div>

            <div className="role-badge">
              <span>●</span>
              {user?.role}
            </div>

          </div>

          {/* Details */}
          <div className="profile-content">

            <h3>
              Account Information
            </h3>

            <div className="profile-grid">

              <div className="info-box">

                <div className="info-icon">
                  👤
                </div>

                <div className="info-text">
                  <span>
                    Full Name
                  </span>

                  <strong>
                    {user?.name}
                  </strong>
                </div>

              </div>

              <div className="info-box">

                <div className="info-icon">
                  ✉️
                </div>

                <div className="info-text">
                  <span>
                    Email Address
                  </span>

                  <strong>
                    {user?.email}
                  </strong>
                </div>

              </div>

              <div className="info-box">

                <div className="info-icon">
                  🆔
                </div>

                <div className="info-text">
                  <span>
                    User ID
                  </span>

                  <strong>
                    #{user?.id}
                  </strong>
                </div>

              </div>

              <div className="info-box">

                <div className="info-icon">
                  🛡️
                </div>

                <div className="info-text">
                  <span>
                    Account Type
                  </span>

                  <strong>
                    {user?.role}
                  </strong>
                </div>

              </div>

            </div>

            {/* Quick actions */}
            <h3 className="quick-title">
              Quick Actions
            </h3>

            <div className="profile-actions">

              <button
                className="action-card booking-action"
                onClick={() =>
                  navigate("/user/bookings")
                }
              >
                <div className="action-icon">
                  🎫
                </div>

                <div>
                  <strong>
                    My Bookings
                  </strong>

                  <span>
                    View your bus tickets
                  </span>
                </div>

                <b>→</b>
              </button>

              <button
                className="action-card dashboard-action"
                onClick={() =>
                  navigate("/user/dashboard")
                }
              >
                <div className="action-icon">
                  🏠
                </div>

                <div>
                  <strong>
                    Dashboard
                  </strong>

                  <span>
                    Search and book buses
                  </span>
                </div>

                <b>→</b>
              </button>

            </div>

            {/* Logout */}
            <button
              className="logout-button"
              onClick={handleLogout}
            >
              <span>↪</span>
              Logout from Account
            </button>

          </div>

        </section>

        <p className="profile-footer">
          © 2026 Alkananda Smart Bus Services
        </p>

      </main>

    </div>
  );
}

export default UserProfile;