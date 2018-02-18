package mobileDataHouse.controller;

import mobileDataHouse.dao.EmployeeDAO;
import mobileDataHouse.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeRestController {
    final String NO_EMPLOYEE_WITH_THIS_ID = "Нет сотрудника с таким идентификатором ";

	
	@Autowired
	private EmployeeDAO employeeDAO;




    /**
     * возвращает список сотрудниов
     *
     * @return список всех сотрудникоа
     */
	@CrossOrigin()
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeDAO.getEmployeelist();
	}

    /**
     * выдает Employee по идентификатору или null.
     *
     * @param id идентификатор струдника
     * @return выдает Employee по идентификатору
     */
	@CrossOrigin()
	@GetMapping("/employees/{id}")
	public ResponseEntity getEmployee(@PathVariable("id") Long id) {

		Employee employee = employeeDAO.get(id);
		if (employee == null) {
			return new ResponseEntity(NO_EMPLOYEE_WITH_THIS_ID + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(employee, HttpStatus.OK);
	}

    /**
     * создание сотрудника и возврат его обновленного, по сути присвоение id значения и запись в список
     *
     * @param employee сотрудника объекта Employee
     * @return объект Employee с обновленным id
     */
	@CrossOrigin()
    @PostMapping(value = "/employees")
	public ResponseEntity createCustomer(@RequestBody Employee employee) {

		employeeDAO.create(employee);

		return new ResponseEntity(employee, HttpStatus.OK);
	}

    /**
     * Удаление сотрудника по его идентификатору, если не найдёт то возвратит null
     *
     * @param id сотрудник объекта Employee
     * @return сотрудник объекта Employee
     */
	@CrossOrigin()
	@DeleteMapping("/employees/{id}")
	public ResponseEntity deleteCustomer(@PathVariable Long id) {

		if (null == employeeDAO.delete(id)) {
			return new ResponseEntity(NO_EMPLOYEE_WITH_THIS_ID + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}


    /**
     * обновление данных сотрудника по идентификатору, если не найдёт, то возвращает null
     *
     * @param employee объект Employee
     * @return employee объект Employee
     */
	@CrossOrigin()
    @PutMapping("/employees")
	public ResponseEntity updateCustomer(@RequestBody Employee employee) {

		Employee employeeUpdate = employeeDAO.update(employee);

		if (null == employeeUpdate) {
			return new ResponseEntity(NO_EMPLOYEE_WITH_THIS_ID + employee.getId().toString(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(employeeUpdate, HttpStatus.OK);
	}

}