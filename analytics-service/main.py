from fastapi import FastAPI
import mysql.connector
import pandas as pd

app = FastAPI(
    title="Alkananda Analytics Service",
    version="1.0"
)


def get_connection():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="Nikhil@123",
        database="alkananda"
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

    return df.to_dict(orient="records")