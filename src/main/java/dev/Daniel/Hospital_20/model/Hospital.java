package dev.Daniel.Hospital_20.model;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.jackson.JacksonComponent;

import java.util.List;

@Entity
@Data
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String phone;
	private String cnpj;


	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Ward> wards;
}
