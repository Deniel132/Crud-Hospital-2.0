package dev.Daniel.Hospital_20.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JacksonComponent;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ward {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String specialty;

	@ManyToOne
	@JoinColumn(name = "hospital_id")
	@JsonBackReference
	private Hospital hospital;

	@OneToMany
	@JsonManagedReference
	private List<Room> room;


	public Ward(String specialty,Hospital hospital){
		this.specialty = specialty;
		this.hospital = hospital;
	}



}
