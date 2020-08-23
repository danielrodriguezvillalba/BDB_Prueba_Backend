package prueba.bancoBogota.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import prueba.bancoBogota.exceptions.EmployeeException;
import prueba.bancoBogota.model.Employee;

@SpringBootTest
class DemoApplicationTests {

    @Test
    public void shouldAddFullNameToEmployee() {
        Employee emp = new Employee();
        String name = "Daniel Rodriguez";
        emp.setFullname(name);
        assertEquals(name,emp.getFullname());
    }
    
    @Test
    public void shouldAddFunctionToEmployee() {
        Employee emp = new Employee();
        String function = "Desarrollador Full Stack";
        emp.setFunction(function);
        assertEquals(function,emp.getFunction());
    }
    
    @Test
    public void shouldAddIdToEmployee() {
        Employee emp = new Employee();
        int id = 15464854;
        emp.setId(id);
        assertEquals(id,emp.getId());
    }
    
    @Test
    public void shouldAddBossToEmployee() throws EmployeeException {
        Employee emp = new Employee();
        Employee boss = new Employee();
        String nameB = "Santiago Suarez";
        boss.setFullname(nameB);
        String name = "Daniel Rodriguez";
        emp.setFullname(name);
        String function = "Desarrollador Full Stack";
        emp.setFunction(function);
        String functionB = "Analista Senior ";
        boss.setFunction(functionB);
        int id = 15464854;
        emp.setId(id);
        int idB = 15234354;
        boss.setId(idB);
        emp.setBoss(boss);
        assertEquals(boss, emp.getBoss());
    }
    
    @Test
    public void shouldAddFunctionAndFullNameToEmployee() {
        Employee emp = new Employee();
        String function = "Desarrollador Full Stack";
        emp.setFunction(function);
        assertEquals(function,emp.getFunction());
        String name = "Daniel Rodriguez";
        emp.setFullname(name);
        assertEquals(name,emp.getFullname());
    }
}
