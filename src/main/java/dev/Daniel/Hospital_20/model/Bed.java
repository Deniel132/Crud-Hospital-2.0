package dev.Daniel.Hospital_20.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.Daniel.Hospital_20.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long bed_number;
	private Status status = Status.UNOCCUPIED;

	@ManyToOne
	@JoinColumn(name = "room_id")
	@JsonBackReference
	private Room room;



}
