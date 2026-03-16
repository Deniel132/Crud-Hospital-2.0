package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.HospitalDTO;
import dev.Daniel.Hospital_20.model.Hospital;
import dev.Daniel.Hospital_20.repository.HospitalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HospitalService {

	private final HospitalRepository hospitalRepository;
	private final WardService wardService;


	public HospitalService(HospitalRepository hospitalRepository, WardService wardService) {
		this.hospitalRepository = hospitalRepository;
		this.wardService = wardService;
	}


	@Transactional
	public Hospital create(HospitalDTO hospitalDTO) {

		this.checkCnpj(hospitalDTO.getCnpj());
		Hospital hospital = new Hospital(hospitalDTO.getName(), hospitalDTO.getPhone(), hospitalDTO.getCnpj());
		this.hospitalRepository.save(hospital);

		if (hospitalDTO.getWardDtoList() != null && !hospitalDTO.getWardDtoList().isEmpty()) {
			hospital.setWards(wardService.generate(hospital, hospitalDTO.getWardDtoList()));
		}
		return this.hospitalRepository.save(hospital);
	}

	private void checkCnpj(String cnpj) {
		if (this.hospitalRepository.existsByCnpj(cnpj)) {
			throw new RuntimeException("CNPJ Invalido");
		}
	}

	public List<Hospital> getAll() {
		return this.hospitalRepository.findAll();
	}

	public Hospital getById(Long id) {
		return this.hospitalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hospital Nao Encontrado"));
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
