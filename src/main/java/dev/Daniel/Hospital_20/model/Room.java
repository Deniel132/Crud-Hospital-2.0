package dev.Daniel.Hospital_20.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Room {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	private String room_code;
	private String status = "vasio";


	@OneToMany
	@JsonManagedReference
	private List<Bed> bed;


	@ManyToOne
	@JoinColumn(name = "ward_id")
	@JsonBackReference
	private Ward ward;




}
