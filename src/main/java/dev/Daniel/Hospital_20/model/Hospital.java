package dev.Daniel.Hospital_20.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String phone;

	@Column(unique = true)
	private String cnpj;

	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Ward> wards;

	public Hospital(String name, String phone, String cnpj) {
		this.name = name;
		this.phone = phone;
		this.cnpj = cnpj;
	}
}
