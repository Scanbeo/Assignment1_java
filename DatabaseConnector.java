package org.example.assignment1;

import javafx.scene.chart.PieChart;  // Importing for creating the pie chart

import java.sql.*;  // Importing JDBC-related classes

public class DatabaseConnector {

    // Setting up credentials for database connection
    private static final String URL = "jdbc:mysql://localhost:3306/gdp";
    private static final String USER = "root";
    private static final String PASS = "Sahil#12213456";

    // Method for establishing a connection with the database
    public Connection connect() {
        try {
            // Attempting a connection and returning it if successful
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            // Handling potential connection errors
            throw new RuntimeException("Trouble establishing connection with the database. ", e);
        }
    }

    // Method for fetching data from the database and populating the pie chart
    public void fetchData(PieChart pieChart) {
        String query = "SELECT CountryName, year2022 FROM gdpinformation";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            // Looping through each row in the query results
            while (resultSet.next()) {
                String countryName = resultSet.getString("CountryName");
                double gdp2022 = resultSet.getDouble("year2022");

                // Creating a data slice for the pie chart
                pieChart.getData().add(new PieChart.Data(countryName, gdp2022));
            }

        } catch (SQLException e) {
            // Catching and printing any SQL-related errors
            e.printStackTrace();
        }
    }
}
