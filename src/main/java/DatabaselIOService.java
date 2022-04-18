/**
 * import java.sql.Connection;
 * import java.sql.DriverManager;
 * import java.sql.ResultSet;
 * import java.sql.SQLException;
 * import java.sql.Statement;
 * import java.time.LocalDate;
 * import java.util.ArrayList;
 * import java.util.List;
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Create class DatabaseIOService
 *
 * @author Sirin
 *
 */
public class DatabaselIOService {

    public List<EmployeePayrollData> readData() throws DBException {
        String query = "select * from employee_payroll;";

        /**
         * Create Arrylist EmployeePayrollData and object
         */
        List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();
        try (Connection connection = this.establishConnection()) {
            System.out.println("Connection is successfull!!! " + connection);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate startDate = resultSet.getDate("start").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(id, name, salary));
            }
            /**
             * *throw the SQLException
             */

        } catch (SQLException e) {
            throw new DBException("Cannot establish connection", DBException.ExceptionType.CONNECTION_FAIL);
        }
        return employeePayrollList;
    }

    public int updateEmployeeData(String name, double salary) throws DBException {
        return this.updateEmployeeDataUsingStatement(name, salary);
    }

    /**
     * Create the method and passing the parameters
     *
     * @param name
     * @param salary
     * @return
     * @throws DBException
     */

    private int updateEmployeeDataUsingStatement(String name, double salary) throws DBException {
        String sql = String.format("update employee_payroll set salary = %.2f where name = '%s'", salary, name);
        try (Connection connection = this.establishConnection()) {
            System.out.println("Connection is successfull!!! " + connection);
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DBException("Cannot establish connection", DBException.ExceptionType.CONNECTION_FAIL);
        }
    }

    /**
     * JDBC Connection
     *
     * @return
     * @throws SQLException
     */
    private Connection establishConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
        String userName = "root";
        String password = "123456";
        System.out.println("Establishing connection to database : " + jdbcURL);
        return DriverManager.getConnection(jdbcURL, userName, password);
    }
}
