/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.bancoBogota.RESTControllers;

import java.text.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import prueba.bancoBogota.exceptions.EmployeeException;
import prueba.bancoBogota.model.Employee;
import prueba.bancoBogota.services.EmployeeServices;

/**
 *
 * @author Daniel Felipe Rodriguez Villalba
 */
@RestController

@RequestMapping(value = "/employee")
public class EmployeeAPIController {

    @Autowired
    private EmployeeServices employeeServices = null;

    /**
     * Metodo GET que retorna todos los empleados
     *
     * @return Empleado encontrado con el nombre dado y estado de la peticion
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployee() {
        
        return new ResponseEntity<>(employeeServices.getEmployees(), HttpStatus.OK);
    }

    /**
     * Metodo GET que retorna un empleado dado su nombre
     *
     * @param employeeID Id del empleado que se va a retornar
     * @return Empleado encontrado con el nombre dado y estado de la peticion
     * @throws EmployeeException
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/{employeeID}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployee(@PathVariable int employeeID) throws EmployeeException {

        return new ResponseEntity<>(employeeServices.getEmployee(employeeID), HttpStatus.OK);
    }

    /**
     * Metodo POST que agrega un empleado segun los atributos dados
     *
     * @param body Atributos del empleado a agregar
     * @return Empleado creado a partir de los datos dados y estado de la
     * peticion
     * @throws EmployeeException
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/new", method = RequestMethod.POST)
    public ResponseEntity<?> postEmployee(@RequestBody String body) throws EmployeeException {
        try{
            
            Employee emp = new Employee();
            JSONObject obj = new JSONObject(body);
            int id = Integer.parseInt(obj.getString("id"));
            emp.setId(id);
            emp.setFullname(obj.getString("fullname"));
            emp.setFunction(obj.getString("function"));
            int employeeBoss = 0;
            try {
                employeeBoss = Integer.parseInt(obj.getString("boss"));
            }catch (NumberFormatException e){ 
                employeeBoss = 0;
            }
            return new ResponseEntity<>(employeeServices.createEmployee(emp, employeeBoss), HttpStatus.OK);
        }catch (NumberFormatException e){
            throw new EmployeeException("El identificador debe ser un numero" );
        }
        
    }

    /**
     * Metodo DELETE que elimina un empleado dado su nombre completo
     *
     * @param employeeId Id del empleado a eliminar
     * @return Estado de la peticion
     * @throws prueba.bancoBogota.exceptions.EmployeeException
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/{employeeId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployee(@PathVariable int employeeId) throws EmployeeException {
        employeeServices.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
