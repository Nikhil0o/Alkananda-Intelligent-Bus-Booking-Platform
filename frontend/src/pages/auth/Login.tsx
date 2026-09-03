import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { loginUser } from "../../services/authService";
import "./Login.css";

function Login() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("USER");

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    setError("");

    if (!email.trim()) {
      setError("Please enter your email.");
      return;
    }

    if (!password.trim()) {
      setError("Please enter your password.");
      return;
    }

    try {
      setLoading(true);

      console.log("Login request:", {
        email,
        role,
      });

      const data = await loginUser(
        email,
        password,
        role
      );

      console.log("Login response:", data);

      // Check whether backend sent userId
      if (!data.userId) {
        console.error(
          "userId is missing from login response:",
          data
        );

        setError(
          "Login successful, but user information is missing."
        );

        return;
      }

      // Store login information
      localStorage.setItem(
        "token",
        data.token
      );

      localStorage.setItem(
        "userId",
        String(data.userId)
      );

      localStorage.setItem(
        "email",
        data.email
      );

      localStorage.setItem(
        "role",
        String(data.role)
      );

      // Verify localStorage
      console.log(
        "Stored token:",
        localStorage.getItem("token")
      );

      console.log(
        "Stored userId:",
        localStorage.getItem("userId")
      );

      console.log(
        "Stored email:",
        localStorage.getItem("email")
      );

      console.log(
        "Stored role:",
        localStorage.getItem("role")
      );

      const userRole =
        String(data.role).toUpperCase();

      if (userRole === "USER") {
        navigate("/user/dashboard");
      } else if (userRole === "ADMIN") {
        alert(
          "Admin login successful. Admin dashboard will be added later."
        );
      } else if (userRole === "ANALYTICS") {
        alert(
          "Analytics login successful. Analytics dashboard will be added later."
        );
      } else {
        alert("Login successful.");
      }

    } catch (err) {
      console.error(
        "Login error:",
        err
      );

      if (err instanceof Error) {
        setError(err.message);
      } else {
        setError(
          "Login failed. Please check your credentials."
        );
      }

    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-page">

      <div className="login-card">

        <div className="login-logo">

          <div className="login-logo-icon">
            🚌
          </div>

          <h1>ALKANANDA</h1>

          <p>
            Smart Bus Ticket Booking
          </p>

        </div>

        <form
          className="login-form"
          onSubmit={handleSubmit}
        >

          <div className="login-field">

            <label htmlFor="email">
              Email
            </label>

            <input
              id="email"
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e) =>
                setEmail(e.target.value)
              }
              required
            />

          </div>

          <div className="login-field">

            <label htmlFor="password">
              Password
            </label>

            <input
              id="password"
              type="password"
              placeholder="Enter your password"
              value={password}
              onChange={(e) =>
                setPassword(e.target.value)
              }
              required
            />

          </div>

          <div className="login-field">

            <label htmlFor="role">
              Role
            </label>

            <select
              id="role"
              value={role}
              onChange={(e) =>
                setRole(e.target.value)
              }
            >

              <option value="USER">
                User
              </option>

              <option value="ADMIN">
                Admin
              </option>

              <option value="ANALYTICS">
                Analytics
              </option>

            </select>

          </div>

          {error && (
            <div className="login-error">
              ⚠️ {error}
            </div>
          )}

          <button
            type="submit"
            className="login-button"
            disabled={loading}
          >
            {loading
              ? "Logging in..."
              : "Login →"}
          </button>

        </form>

        <div className="login-register">

          <span>
            Don't have an account?
          </span>{" "}

          <Link to="/register">
            Create Account
          </Link>

        </div>

      </div>

    </div>
  );
}

export default Login;