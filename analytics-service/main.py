from fastapi import FastAPI
import mysql.connector
import pandas as pd
import os
from dotenv import load_dotenv
app = FastAPI(
    title="Alkananda Analytics Service",
    version="1.0"
)

def get_connection():
    return mysql.connector.connect(
        host=os.getenv("DB_HOST"),
        user=os.getenv("DB_USER"),
        password=os.getenv("DB_PASSWORD"),
        database=os.getenv("DB_NAME")
    )


@app.get("/")
def home():
    return {
        "message": "Alkananda Analytics Service is running"
    }


@app.get("/analytics/bookings")
def get_bookings():

    connection = get_connection()

    query = """
        SELECT *
        FROM bookings
    """

    df = pd.read_sql(query, connection)

    connection.close()

    # Convert NaN / NaT to None
    df = df.astype(object).where(pd.notnull(df), None)

    return df.to_dict(orient="records")

@app.get("/analytics/bookings/kpi")
def booking_kpi():

    connection = get_connection()

    query = """
        SELECT *
        FROM bookings
    """

    df = pd.read_sql(query, connection)
    connection.close()

    df = df.astype(object).where(pd.notnull(df), None)

    # total_bookings = len(df)

    status_counts = df["status"].value_counts().to_dict()

    return {
        "total_bookings": len(df),
        "Status-count": status_counts
    }

@app.get("/analytics/bookings/summary")
def booking_summary():

    connection = get_connection()

    query = """
        SELECT
            COUNT(*) AS total_bookings,
            SUM(status = 'CONFIRMED') AS confirmed_bookings,
            SUM(status = 'CANCELLED') AS cancelled_bookings,
            SUM(CASE WHEN status = 'CONFIRMED' THEN amount ELSE 0 END) AS total_revenue,
            AVG(CASE WHEN status = 'CONFIRMED' THEN amount END) AS average_booking_amount
        FROM bookings
    """

    df = pd.read_sql(query, connection)

    connection.close()

    df = df.fillna(0)

    return df.to_dict(orient="records")[0]

@app.get("/analytics/revenue/by-trip")
def revenue_by_trip():

    connection = get_connection()

    query = """
        SELECT
            trip_id,
            COUNT(*) AS total_bookings,
            SUM(CASE
                WHEN status = 'CONFIRMED' THEN 1
                ELSE 0
            END) AS confirmed_bookings,
            SUM(CASE
                WHEN status = 'CONFIRMED' THEN amount
                ELSE 0
            END) AS revenue
        FROM bookings
        GROUP BY trip_id
        ORDER BY revenue DESC
    """

    df = pd.read_sql(query, connection)

    connection.close()

    df = df.fillna(0)

    return df.to_dict(orient="records")

@app.get("/analytics/bookings/by-route")
def bookings_by_route():

    connection = get_connection()

    query = """
        SELECT
            r.id AS route_id,
            r.source,
            r.destination,
            COUNT(b.id) AS total_bookings,
            SUM(
                CASE
                    WHEN b.status = 'CONFIRMED' THEN 1
                    ELSE 0
                END
            ) AS confirmed_bookings,
            SUM(
                CASE
                    WHEN b.status = 'CANCELLED' THEN 1
                    ELSE 0
                END
            ) AS cancelled_bookings,
            SUM(
                CASE
                    WHEN b.status = 'CONFIRMED' THEN b.amount
                    ELSE 0
                END
            ) AS revenue
        FROM bookings b
        JOIN trips t ON b.trip_id = t.id
        JOIN routes r ON t.route_id = r.id
        GROUP BY r.id, r.source, r.destination
        ORDER BY revenue DESC
    """

    df = pd.read_sql(query, connection)

    connection.close()

    df = df.fillna(0)

    return df.to_dict(orient="records")

@app.get("/analytics/bookings/daily")
def daily_booking_trend():

    connection = get_connection()

    query = """
        SELECT
            DATE(booking_time) AS booking_date,
            COUNT(*) AS total_bookings
        FROM bookings
        GROUP BY DATE(booking_time)
        ORDER BY booking_date
    """

    df = pd.read_sql(query, connection)

    connection.close()

    return df.to_dict(orient="records")

@app.get("/analytics/revenue/daily")
def daily_revenue_trend():

    connection = get_connection()

    query = """
        SELECT
            DATE(booking_time) AS revenue_date,
            SUM(
                CASE
                    WHEN status = 'CONFIRMED'
                    THEN amount
                    ELSE 0
                END
            ) AS total_revenue
        FROM bookings
        GROUP BY DATE(booking_time)
        ORDER BY revenue_date
    """

    df = pd.read_sql(query, connection)

    connection.close()

    df = df.fillna(0)

    return df.to_dict(orient="records")

@app.get("/analytics/utilization")
def seat_utilization():

    connection = get_connection()

    query = """
        SELECT
            t.id AS trip_id,
            b.bus_number,
            COUNT(s.id) AS total_seats,
            SUM(CASE WHEN s.status = 'BOOKED' THEN 1 ELSE 0 END) AS booked_seats,
            SUM(CASE WHEN s.status = 'AVAILABLE' THEN 1 ELSE 0 END) AS available_seats,
            ROUND(
                SUM(CASE WHEN s.status = 'BOOKED' THEN 1 ELSE 0 END)
                * 100.0 / COUNT(s.id),
                2
            ) AS utilization_percentage
        FROM trips t
        JOIN buses b ON t.bus_id = b.id
        JOIN seats s ON s.trip_id = t.id
        GROUP BY t.id, b.bus_number
        ORDER BY t.id
    """

    df = pd.read_sql(query, connection)

    connection.close()

    df = df.fillna(0)

    return df.to_dict(orient="records")