/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.bancoBogota.model;

import java.util.ArrayList;
import prueba.bancoBogota.exceptions.EmployeeException;

/**
 *
 * @author Daniel Felipe Rodriguez Villalba
 */
public class Employee {
    private String fullname ;
    private String function ;
    private Employee boss ;
    private int id;
    
    public Employee () {
    }
    
    public void setBoss (Employee boss) throws EmployeeException{
        if (boss !=  null){
            this.boss = boss;
        }
        else{
            throw new EmployeeException(" El jefe que quiere asignar este empleado es nulo. ") ;
        }
    }

    public String getFullname() {
        return fullname;
    }

    public String getFunction() {
        return function;
    }

    public int getId() {
        return id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Employee getBoss() {
        return boss;
    }
    
    
    
}
