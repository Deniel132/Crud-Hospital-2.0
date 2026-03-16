package dev.Daniel.Hospital_20.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String roomCode;
	private boolean isFilled;


	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Bed> beds;


	@ManyToOne
	@JoinColumn(name = "ward_id")
	@JsonBackReference
	private Ward ward;


	public Room(String roomCode, Ward ward) {
		this.roomCode = roomCode;
		this.ward = ward;
	}


}
