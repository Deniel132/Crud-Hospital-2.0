package dev.Daniel.Hospital_20.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.Daniel.Hospital_20.model.enums.Specialty;
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
public class Ward {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Specialty specialty;

	@ManyToOne
	@JoinColumn(name = "hospital_id")
	@JsonBackReference
	private Hospital hospital;

	@OneToMany(mappedBy = "ward", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Room> rooms;


	public Ward(Specialty specialty, Hospital hospital) {
		this.specialty = specialty;
		this.hospital = hospital;
	}


}
