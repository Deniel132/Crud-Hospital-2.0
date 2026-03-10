package dev.Daniel.Hospital_20.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.Daniel.Hospital_20.model.enums.Specialty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.jackson.JacksonComponent;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ward {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Specialty specialty;

	@ManyToOne
	@JoinColumn(name = "hospital_id")
	@JsonBackReference
	private Hospital hospital;

	@OneToMany
	@JsonManagedReference
	private List<Room> room;


	public Ward(Specialty specialty,Hospital hospital){
		this.specialty = specialty;
		this.hospital = hospital;
	}



}
