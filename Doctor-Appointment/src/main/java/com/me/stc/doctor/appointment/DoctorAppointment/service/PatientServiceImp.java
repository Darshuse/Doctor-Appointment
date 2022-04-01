package com.me.stc.doctor.appointment.DoctorAppointment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.stc.doctor.appointment.DoctorAppointment.dao.PatientRepository;
import com.me.stc.doctor.appointment.DoctorAppointment.entities.Patient;

@Service
public class PatientServiceImp implements PatientService {
	@Autowired
	PatientRepository repo;

	@Override
	public List<Patient> findAll() {
		// TODO Auto-generated method stub
		List<Patient> patient_list = repo.findAll();
		return patient_list;
	}

	@Override
	public Optional<Patient> findByEmail(String email) {
		// TODO Auto-generated method stub
		Optional<Patient> patient = repo.findByEmail(email);
		return patient;
	}

//comment remeber to implement it 
	@Override
	public Optional<Patient> update(Patient patient) {
		// TODO Auto-generated method stub
		Optional<Patient> updated_patient = repo.findById(patient.getId());
		if (updated_patient.orElse(null) != null) {
			updated_patient.get().setId(patient.getId());
			updated_patient.get().setEmail(patient.getEmail());
			updated_patient.get().setName(patient.getName());
			repo.save(updated_patient.get());
		}
		return updated_patient;
	}

	@Override
	public Patient save(Patient patient) {
		// TODO Auto-generated method stub
		Patient saved_patient = repo.save(patient);
		return saved_patient;
	}

	@Override
	public Optional<Patient> find(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

}
