package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.model.Patient;
import dev.Daniel.Hospital_20.repository.AdmissionLogRepository;
import dev.Daniel.Hospital_20.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

	private final PatientRepository patientRepository;
	private final AdmissionLogRepository logRepository;

	public PatientService(PatientRepository patientRepository, AdmissionLogRepository logRepository) {
		this.patientRepository = patientRepository;
		this.logRepository = logRepository;

	}


	public List<Patient> getAll() {
		return this.patientRepository.findAll();
	}

	public Patient getById(Long id) {
		return this.patientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente Nao Encontrado"));
	}

	public Patient save(Patient patient) {
		this.checkCpf(patient.getCpf());
		Patient patient1 = new Patient(patient.getName(), patient.getCpf(), patient.getPhone());
		return this.patientRepository.save(patient1);
	}

	private void checkCpf(String cpf) {
		if (this.patientRepository.existsByCpf(cpf)) {
			throw new RuntimeException("Cpf Invalido");
		}
	}

	public Patient attAll(Long id, Patient patient) {
		Patient patientNew = getById(id);
		patientNew.setCpf(patient.getCpf());
		patientNew.setName(patient.getName());
		patientNew.setPhone(patient.getPhone());
		return save(patientNew);
	}

	public void deleteById(Long id) {
		if (this.logRepository.findAll().stream().anyMatch(l -> l.getPatient().getId().equals(id))) {
			throw new RuntimeException("Paciente Nao podeSer Excluido");
		} else {
			this.patientRepository.deleteById(id);
		}
	}

	public void admitted(Long id) {
		Patient patient = getById(id);
		patient.setIsHospitalized(true);
		this.patientRepository.save(patient);
	}

	public void setAlta(Long id) {
		Patient patient = getById(id);
		patient.setIsHospitalized(false);
		this.patientRepository.save(patient);
	}


}
