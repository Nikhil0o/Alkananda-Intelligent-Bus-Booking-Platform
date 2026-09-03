export async function registerUser(
  name: string,
  email: string,
  password: string,
  role: string
) {
  const response = await fetch(
    "http://localhost:8080/user/add",
    {
      method: "POST",

      headers: {
        "Content-Type": "application/json"
      },

      body: JSON.stringify({
        name,
        email,
        password,
        role
      })
    }
  );

  const data = await response.json();

  if (!response.ok) {
    throw new Error(
      data.message || "Registration failed"
    );
  }

  return data;
}


export async function loginUser(
  email: string,
  password: string,
  role: string
) {
  const response = await fetch(
    "http://localhost:8080/auth/login",
    {
      method: "POST",

      headers: {
        "Content-Type": "application/json"
      },

      body: JSON.stringify({
        email,
        password,
        role
      })
    }
  );

  const data = await response.json();

  if (!response.ok) {
    throw new Error(
      data.message || "Login failed"
    );
  }

  return data;
}