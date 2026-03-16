package dev.Daniel.Hospital_20.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true)
	private String cpf;

	private String phone;

	private Boolean isHospitalized = Boolean.FALSE;

	public Patient(String name, String cpf, String phone) {
		this.name = name;
		this.cpf = cpf;
		this.phone = phone;
	}
}
