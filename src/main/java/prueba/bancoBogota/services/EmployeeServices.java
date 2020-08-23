/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.bancoBogota.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.bancoBogota.exceptions.EmployeeException;
import prueba.bancoBogota.model.Employee;
import prueba.bancoBogota.persistence.EmployeePersistence;

/**
 *
 * @author Daniel Felipe Rodriguez Villalba
 */
@Service
public class EmployeeServices {
    
    @Autowired
    private EmployeePersistence employeePersistence = null;
    
            
    /**
     * Metodo que retorna un Employee dado su nombre
     * @return ArrayListEmpleado representado con el nombre dado 
     */
    public ArrayList<Employee> getEmployees (){
        return employeePersistence.getEmployees();
        
    }
    /**
     * Metodo que retorna un Employee dado su nombre
     * @param employeeID Numero de identificacion del trabajador a obtener
     * @return Empleado representado con el nombre dado 
     */
    public Employee getEmployee (int employeeID) throws EmployeeException {
        return employeePersistence.getEmployee(employeeID);
        
    }
    
    /**
     * Metodo que crea un empleado dadas sus caracteristicas
     * @param emp Empleado que va a ser creado
     * @param employeeBoss Jefe del empleado, puede ser nulo 
     * @return Empleado creado segun los datos suministrados
     * @throws EmployeeException 
     */
    public Employee createEmployee (Employee emp, int employeeBoss) throws EmployeeException {
        return employeePersistence.createEmployee(emp,employeeBoss);
    }
    
    /**
     * Metodo que elimina el empleado segun su nombre completo
     * @param fullname Nombre del empleado a eliminar
     * @throws prueba.bancoBogota.exceptions.EmployeeException
     */
    public void deleteEmployee (int id) throws EmployeeException {
        employeePersistence.deleteEmployee(id);
    }
    
    
    
}
    
