/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.bancoBogota.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import prueba.bancoBogota.exceptions.EmployeeException;
import prueba.bancoBogota.model.Employee;

/**
 *
 * @author Daniel Felipe Rodriguez Villalba
 */
@Service
public class EmployeePersistence {

    static String urlDatabase = "jdbc:postgresql://ec2-54-91-178-234.compute-1.amazonaws.com:5432/d48l4hapres89d";
    static String usuarioDb = "illllkjgqboglz";
    static String passwordDb = "e7b91071078e04112440884a342f25f416a1d5d12d0284c52b59db0586fe7fce";
    static Connection c = null;

    /**
     * Metodo que realiza la coneccion a la base de datos
     */
    public void connection() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(urlDatabase, usuarioDb, passwordDb);
            //System.out.println("La conexion se realizo sin problemas!");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Metodo que retorna un Employee dado su nombre
     *
     * @param employeeID Numero de identificacion del empleado a traer
     * @return Empleado representado con el nombre dado
     */
    public Employee getBoss(int employeeID) {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            String consulta = "select * from employees where id='" + employeeID + "';";
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            Employee employee = new Employee();
            int boss = 0;
            while (rs.next()) {
                employee.setId(rs.getInt("id"));
                employee.setFullname(rs.getString("fullname"));
                employee.setFunction(rs.getString("function"));
                boss = rs.getInt("boss");
            }
            if (boss != 0) {
                Employee emp = new Employee();
                emp.setId(boss);
                employee.setBoss(emp);
            }

            rs.close();
            stmt.close();
            return employee;

        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Metodo que retorna un Employee dado su nombre
     *
     * @param employeeID Numero de identificacion del empleado a traer
     * @return Empleado representado con el nombre dado
     * @throws EmployeeException
     */
    public Employee getEmployee(int employeeID) throws EmployeeException {
        Statement stmt = null;
        if (c == null) {
            try {
                c = DriverManager.getConnection(urlDatabase, usuarioDb, passwordDb);
            } catch (Exception e) {
            }
        }
        try {
            Class.forName("org.postgresql.Driver");
            String consulta = "select * from employees where id='" + employeeID + "';";
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            Employee employee = new Employee();
            int boss = 0;
            while (rs.next()) {
                employee.setId(rs.getInt("id"));
                employee.setFullname(rs.getString("fullname"));
                employee.setFunction(rs.getString("function"));
                boss = rs.getInt("boss");
            }
            if (boss != 0) {
                Employee bossO = getBoss(boss);
                if (bossO == null) {
                    throw new EmployeeException("El jefe no existe en los empleados.");
                } else {
                    employee.setBoss(bossO);
                }
            }
            rs.close();
            stmt.close();
            if (employee.getFullname() == null) {
                throw new EmployeeException("El usuario que esta buscando no existe ");
            }
            return employee;

        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Metodo que crea un empleado dadas sus caracteristicas
     *
     * @param emp Empleado que va a ser creado
     * @param employeeBoss Jefe del empleado, puede ser nulo
     * @return Empleado creado segun los datos suministrados
     * @throws EmployeeException
     */
    public Employee createEmployee(Employee emp, int employeeBoss) throws EmployeeException {
        Statement stmt = null;
        if (c == null) {
            try {
                c = DriverManager.getConnection(urlDatabase, usuarioDb, passwordDb);
            } catch (Exception e) {
            }
        }
        try {

            Employee empP = getEmployee(employeeBoss);
            if (empP == null) {
                Class.forName("org.postgresql.Driver");
                c.setAutoCommit(false);
                stmt = c.createStatement();
                String sql;
                if (employeeBoss != 0) {
                    Employee bossO = getBoss(employeeBoss);
                    if (bossO == null) {
                        throw new EmployeeException("El jefe no existe en los empleados.");
                    } else {
                        emp.setBoss(bossO);
                        sql = "INSERT INTO employees (id, fullname,function,boss) "
                                + "VALUES ('" + emp.getId() + "','" + emp.getFullname() + "','" + emp.getFunction() + "','" + emp.getBoss().getId() + "');";
                    }
                } else {
                    sql = "INSERT INTO employees (id,fullname,function) " + "VALUES ('" + emp.getId() + "','" + emp.getFullname() + "','" + emp.getFunction() + "');";
                }

                stmt.executeUpdate(sql);
                stmt.close();
                c.commit();
            }
            else{
                throw new EmployeeException ("Ya existe un usuario con este numero de ID");
            }
            return emp;
        } catch (SQLException e) {
            throw new EmployeeException("Ya existe un empleado con esta informacion ");
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Metodo que elimina el empleado segun su nombre completo
     *
     * @param id Id del empleado a eliminar
     */
    public void deleteEmployee(int id) throws EmployeeException {
        Statement stmt = null;
        if (c == null) {
            try {
                c = DriverManager.getConnection(urlDatabase, usuarioDb, passwordDb);
            } catch (Exception e) {
            }
        }
        try {
            Class.forName("org.postgresql.Driver");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            //SQL de ejemplo mientras se define lo que se va a ingresar a la base de datos
            String sql = "DELETE FROM employees "
                    + "WHERE id = '" + id + "';";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
        } catch (SQLException e) {
            throw new EmployeeException("No existe el empleado que quiere eliminar ");
        } catch (Exception e) {
        }
    }

    /**
     * Metodo que retorna todos los empleados
     *
     * @return Lista con todos los empleados
     */
    public ArrayList<Employee> getEmployees() {
        Statement stmt = null;
        ArrayList<Employee> employees = new ArrayList<>();
        if (c == null) {
            try {
                c = DriverManager.getConnection(urlDatabase, usuarioDb, passwordDb);
            } catch (Exception e) {
            }
        }
        try {
            Class.forName("org.postgresql.Driver");
            String consulta = "select * from employees ;";
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            int boss = 0;
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFullname(rs.getString("fullname"));
                employee.setFunction(rs.getString("function"));
                boss = rs.getInt("boss");
                if (boss != 0) {
                    Employee bossO = getBoss(boss);
                    if (bossO == null) {
                        throw new EmployeeException("El jefe no existe en los empleados.");
                    } else {
                        employee.setBoss(bossO);
                    }
                }
                employees.add(employee);
            }
            rs.close();
            stmt.close();
            return employees;

        } catch (Exception e) {
        }
        return null;
    }

}
