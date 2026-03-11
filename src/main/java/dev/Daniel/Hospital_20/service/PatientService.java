package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.model.Patient;
import dev.Daniel.Hospital_20.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

	private final PatientRepository patientRepository;


	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}



	public List<Patient> getAll(){return this.patientRepository.findAll();}

	public Patient getById(Long id){
		Patient patient = this.patientRepository.findById(id).orElse(null);

		if (patient == null){
			throw new EntityNotFoundException("Paciente Nao Encontrado");
		}else {
			return patient;
		}
	}


	public Patient save(Patient patient){
		Patient patient1 = new Patient();
		patient1.setName(patient.getName());
		patient1.setCpf(patient.getCpf());
		patient1.setPhone(patient.getPhone());


		return this.patientRepository.save(patient1);
	}


	public Patient att_all(Long id,Patient patient){
		Patient patientNew = getById(id);

		patientNew.setCpf(patient.getCpf());
		patientNew.setName(patient.getName());
		patientNew.setPhone(patient.getPhone());

		return save(patientNew);
	}

	public void deleteById(Long id){

		Patient patient = getById(id);

		this.patientRepository.deleteById(id);
	}

	public Patient setInternado(Long id){
		Patient patient = getById(id);

		patient.setIs_hospitalized(true);

		return this.patientRepository.save(patient);
	}

	public Patient setAlta(Long id){
		Patient patient = getById(id);

		patient.setIs_hospitalized(false);

		return this.patientRepository.save(patient);
	}



}
