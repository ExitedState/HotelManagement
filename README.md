<h1>Hotel Management System</h1>
    <p>
        A simple yet effective hotel management system built using Java and the Spring framework. The system helps manage reservations, service usages, guests, rooms, and staff members in a hotel.
    </p>
<h2>Features</h2>
    <ul>
        <li>Manage reservations, including creating, updating, retrieving, and deleting reservations</li>
        <li>Track service usage by guests and staff members</li>
        <li>Manage guest information</li>
        <li>Manage room information</li>
        <li>Manage staff information</li>
    </ul>
<h2>Technologies Used</h2>
    <ul>
        <li>Java</li>
        <li>Spring Framework</li>
        <li>Jakarta Persistence API (JPA)</li>
        <li>Lombok</li>
        <li>FasterXML Jackson</li>
        <li>html/css/javascript</li>
        <li>DATABASE : postgreSQL</li>
    </ul>
<h2>Getting Started</h2>
    <p>
        To get started with the project, follow these steps:
    </p>
    <ol>
        <li>Clone the repository</li>
        <li>Install the required dependencies recommended for using IntelliJ.</li>
        <li>Create a new file called <code>application-dev.properties</code> in the <code>src/main/resources</code> directory</li>
        <li>In the <code>application-dev.properties</code> file, add the following lines:
            <pre>DB_USERNAME=your_database_username //by default, it is postgres
DB_PASSWORD=your_database_password //using the password from PostgreSQL installation</pre>
        </li>
        <li>Make sure to create a database with the following name in application.properties. By default, you should create a database named "hotelmanagementdb"</li>
        <li>Configure the database connection settings in the <code>application.properties</code> file</li>
        <li>Build and run the project</li>
    </ol>
