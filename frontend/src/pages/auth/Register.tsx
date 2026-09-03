import { useState } from "react";
import { registerUser } from "../../services/authService";

function Register() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("USER");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const data = await registerUser(name, email, password, role);

      console.log("Registration successful:", data);
      alert("Registration successful!");

      setName("");
      setEmail("");
      setPassword("");
    } catch (error) {
      console.error(error);
      alert("Registration failed!");
    }
  };

  return (
    <div>
      <h1>Create Alkananda Account</h1>

      <form onSubmit={handleSubmit}>
        <div>
          <label>Name</label>
          <input
            type="text"
            placeholder="Enter your name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>

        <div>
          <label>Email</label>
          <input
            type="email"
            placeholder="Enter your email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>

        <div>
          <label>Password</label>
          <input
            type="password"
            placeholder="Create a password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>

        <div>
          <label>Role</label>

          <select
            value={role}
            onChange={(e) => setRole(e.target.value)}
          >
            <option value="USER">User</option>
            <option value="ADMIN">Admin</option>
            <option value="ANALYTICS">Analytics</option>
          </select>
        </div>

        <button type="submit">
          Register
        </button>
      </form>
    </div>
  );
}

export default Register;