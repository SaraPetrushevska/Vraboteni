package mk.com.example.vraboteni.web.Controller;

import lombok.AllArgsConstructor;
import mk.com.example.vraboteni.model.Employee;
import mk.com.example.vraboteni.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class EmployeeController<model> {

    private final EmployeeService employeeService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/showNewEmployeeForm")
    public String newEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "newEmployeeForm";
    }
    @PostMapping ("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping ("/showFormUpdate/{id}")
    public String showFormUpdate(@PathVariable (value = "id") long id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "updateEmployee";
    }

    @GetMapping ("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id, Model model){
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
}
