package mobileDataHouse.dao;

import mobileDataHouse.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class EmployeeDAO {

	private static List<Employee> employeeList;
	{
		employeeList = new ArrayList();
		employeeList.add(new Employee(101, "Иван", "Иванов", "Иванович", "Бухалтерия"));
		employeeList.add(new Employee(201, "Сидор", "Сидоров", "Сидорович", "Юридический отдел"));
		employeeList.add(new Employee(301, "Петр", "Петров", "Петрович", "Маркетинговый отдел"));
		employeeList.add(new Employee(System.currentTimeMillis(), "Гассан", "Абдуррахман", "ибн Хоттаб", "Клиентский отдел"));
	}

	/**
	 * возвращает список сотрудниов
	 *
	 * @return список всех сотрудникоа
	 */
	public List<Employee> getEmployeelist() {
		return employeeList;
	}

	/**
	 * выдает Employee по идентификатору или null.
	 * 
	 * @param id идентификатор струдника
	 * @return выдает Employee по идентификатору
	 */
	public Employee get(Long id) {
		return getEmployeelist().stream().filter((x) -> x.getId().equals(id) ).findFirst().orElseGet(null);
	}

	/**
	 * создание сотрудника и возврат его обновленного, по сути присвоение id значения и запись в список
	 *
	 * @param employee сотрудника объекта Employee
	 * @return объект Employee с обновленным id
	 */
	public Employee create(Employee employee) {
		employee.setId(System.currentTimeMillis());
		getEmployeelist().add(employee);
		return employee;
	}

	/**
	 * Удаление сотрудника по его идентификатору, если не найдёт то возвратит null
	 *
	 * @param id сотрудник объекта Employee
	 * @return сотрудник объекта Employee
	 */
	public Employee delete(Long id) {

		Employee employeeDelete = getEmployeelist().stream().filter((x) -> x.getId().equals(id) ).findFirst().orElseGet(null);

		if (employeeDelete != null) {
			getEmployeelist().remove(employeeDelete);
			return employeeDelete;
		} else {
			return null;
		}
	}

	/**
	 * обновление данных сотрудника по идентификатору, если не найдёт, то возвращает null
	 *
	 * @param employeeUpdate объект Employee
	 * @return employee объект Employee
	 */
	public Employee update(Employee employeeUpdate) {

		if (employeeUpdate == null || employeeUpdate.getId() == null ){
			return null;
		}

		Employee employee = getEmployeelist().stream().filter((x) -> x.getId().equals(employeeUpdate.getId()) ).findFirst().orElseGet(null);
		employee.update(employeeUpdate);
		return employee;
	}

}