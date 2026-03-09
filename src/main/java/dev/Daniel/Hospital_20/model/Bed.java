package dev.Daniel.Hospital_20.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Bed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long bed_number;
	private String status;


	@ManyToOne
	@JoinColumn(name = "room_id")
	@JsonBackReference
	private Room room;



}
