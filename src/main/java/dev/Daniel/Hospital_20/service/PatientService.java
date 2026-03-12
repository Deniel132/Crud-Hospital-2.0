package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.model.Patient;
import dev.Daniel.Hospital_20.repository.Admission_logRepository;
import dev.Daniel.Hospital_20.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

	private final PatientRepository patientRepository;
	private final Admission_logRepository logRepository;

	public PatientService(PatientRepository patientRepository, Admission_logRepository logRepository) {
		this.patientRepository = patientRepository;
		this.logRepository = logRepository;

	}


	public List<Patient> getAll() {
		return this.patientRepository.findAll();
	}

	public Patient getById(Long id) {
		Patient patient = this.patientRepository.findById(id).orElse(null);

		if (patient == null) {
			throw new EntityNotFoundException("Paciente Nao Encontrado");
		} else {
			return patient;
		}
	}


	public Patient save(Patient patient) {
		Patient patient1 = new Patient();
		patient1.setName(patient.getName());
		patient1.setCpf(patient.getCpf());
		patient1.setPhone(patient.getPhone());
		return this.patientRepository.save(patient1);
	}


	public Patient att_all(Long id, Patient patient) {
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

	public void setInternado(Long id) {
		Patient patient = getById(id);
		patient.setIs_hospitalized(true);
		this.patientRepository.save(patient);
	}

	public void setAlta(Long id) {
		Patient patient = getById(id);
		patient.setIs_hospitalized(false);
		this.patientRepository.save(patient);
	}


}
