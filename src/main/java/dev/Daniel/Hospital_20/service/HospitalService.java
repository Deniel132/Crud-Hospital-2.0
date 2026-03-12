package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.HospitalDTO;
import dev.Daniel.Hospital_20.model.Hospital;
import dev.Daniel.Hospital_20.repository.HospitalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

	private final HospitalRepository hospitalRepository;
	private final WardService wardService;


	public HospitalService(HospitalRepository hospitalRepository, WardService wardService) {
		this.hospitalRepository = hospitalRepository;
		this.wardService = wardService;
	}


	public Hospital save(HospitalDTO hospitalDTO) {
		Hospital hospital = new Hospital();

		if (getAll().stream().anyMatch(h -> h.getCnpj().equals(hospitalDTO.getCnpj()))) {
			throw new RuntimeException("CNPJ Invalido");
		}

		hospital.setNome(hospitalDTO.getNome());
		hospital.setCnpj(hospitalDTO.getCnpj());
		hospital.setPhone(hospitalDTO.getPhone());

		this.hospitalRepository.save(hospital);

		if (hospitalDTO.getWardDtoList() != null) {
			hospitalDTO.getWardDtoList().forEach(w -> w.setId_hospital(hospital.getId()));
			hospital.setWards(wardService.criarWard(hospitalDTO.getWardDtoList()));
		}
		return this.hospitalRepository.save(hospital);

	}

	public List<Hospital> getAll() {
		return this.hospitalRepository.findAll();
	}

	public Hospital getById(Long id) {
		Hospital hospital = this.hospitalRepository.findById(id).orElse(null);

		if (hospital == null) {
			throw new EntityNotFoundException("Hospital Nao Encontrado");
		} else {
			return hospital;
		}
	}


	public void deleteById(Long id) {
		Hospital hospital = getById(id);

		if (wardService.getAll().stream().anyMatch(w -> w.getHospital().getId().equals(hospital.getId()))) {
			throw new RuntimeException("Hospital nao pode ser deletado");
		} else {
			this.hospitalRepository.deleteById(id);
		}
	}

}
