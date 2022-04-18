/**
 * UC1 :- Ability to create a payroll service database and have java program connect to database.
 *
 */

/**
 * import all sql packages.
 */
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * Create class DBDemo
 *
 * @author Sirin
 *
 */
public class DBDemo {
    public static void main(String[] args) {
        /**
         * Added : JDBC url Address
         */
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
        String userName = "root";// userName
        /**
         * password
         */
        String password = "123456";
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath");
        }
        listDrivers();
        try {
            System.out.println("Establishing connection to database : " + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection is successfull!!! " + connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * create method listDrivers
     */

    private static void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            String driverClassName = driverList.nextElement().getClass().getName();
            System.out.println("Driver Class Name : " + driverClassName);
        }
    }
}