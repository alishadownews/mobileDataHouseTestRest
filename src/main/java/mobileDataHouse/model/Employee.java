package mobileDataHouse.model;

import lombok.*;



@NoArgsConstructor
@ToString(exclude="id")
@EqualsAndHashCode(exclude="id")
public class Employee {

	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private String firstName;
	@Getter
	@Setter
	private String lastName;
	@Getter
	@Setter
	private String patronymic;
	@Getter
	@Setter
	private String departament;

	public Employee(long id, String firstName, String lastName, String patronymic, String departament) {
		this.id = id;
		update(firstName, lastName, patronymic, departament);
	}

	public void update(String firstName, String lastName, String patronymic, String departament) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.patronymic = patronymic;
		this.departament = departament;
	}

	public void update(Employee employee){
		this.firstName = employee.firstName;
		this.lastName = employee.lastName;
		this.patronymic = employee.patronymic;
		this.departament = employee.departament;
	}

}