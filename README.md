# FleetForYou

A JavaFX desktop application for managing a vehicle rental business. The app provides a login flow and dashboards to manage clients, vehicles, stands, rentals, and related extras, backed by a SQL database accessed through a DAO layer.

## Features
- Login and session handling
- CRUD for clients, stands, vehicles, and users
- Rental creation and management (including vehicle status and return dates)
- Extras/forms workflow via dedicated screens
- JavaFX UI with FXML views and custom styling

## Tech Stack
- Java 21
- JavaFX 21 (FXML + Controls)
- Maven
- Microsoft SQL Server (primary) or MySQL (optional)
- Dotenv for local DB configuration

## Project Structure
- `src/main/java/com/fleetforyou/fleetforyou/Controllers/` JavaFX controllers (UI logic)
- `src/main/java/com/fleetforyou/fleetforyou/Application/Services/` Application services (business logic)
- `src/main/java/com/fleetforyou/fleetforyou/Infrastructure/DAO/` DAO layer for database access
- `src/main/java/com/fleetforyou/fleetforyou/Domain/` Domain models, DTOs, enums, and utilities
- `src/main/resources/com/fleetforyou/fleetforyou/FXML/` UI screens
- `src/main/resources/com/fleetforyou/fleetforyou/stylesheet.css` Global styles
- `DataBase/` SQL scripts and diagram

## Database
Primary connection uses Microsoft SQL Server (via `DBConnection`). A MySQL connector is included as an optional alternative.

SQL Server scripts:
- `DataBase/RentCar.sql` schema
- `DataBase/InserirDados.sql` seed data

MySQL scripts:
- `DataBase/RentCar-MySQL.sql` schema
- `DataBase/InserirDados-MySQL.sql` seed data

### Environment Variables
Create a `.env` file in the project root:

```env
DB_URL=jdbc:sqlserver://localhost:1433;databaseName=RENTCAR;encrypt=true;trustServerCertificate=true
DB_USER=sa
DB_PASSWORD=your_password
```

## Run Locally
Prerequisites:
- JDK 21
- Maven (or use the Maven Wrapper)
- SQL Server running with the schema loaded

Start the app:

```bash
./mvnw clean javafx:run
```

On Windows:

```bat
mvnw.cmd clean javafx:run
```

## Notes
- The app starts at a login screen and loads the initial dashboard after authentication.
- Database connectivity is required for most screens.

## License
Not specified.
