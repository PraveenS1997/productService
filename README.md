Issues in creating Flyway Versioned Migrations using JPA Buddy Explorer


SET the default Schema name to the corresponding Database

1. Open JPA Explorer
2. Click + Button, Click Create Flyway Versioned Migration
3. In the Target section, Click the + button to add Data source
4. Add a new My SQL data source
5. Fill the userName, Password, Database name
6. Test the connection
7. Go to Schemas section, Un tick the Default Schema & Select the correct database name
8. Click Apply & Ok

Now the Versioned Migration will give only the latest changes instead of all the migration changes from previous migrations as well.
