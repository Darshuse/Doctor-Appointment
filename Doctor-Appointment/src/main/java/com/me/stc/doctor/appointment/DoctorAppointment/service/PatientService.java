package com.me.stc.doctor.appointment.DoctorAppointment.service;

import java.util.List;
import java.util.Optional;

import com.me.stc.doctor.appointment.DoctorAppointment.entities.Patient;

public interface PatientService {
	public List<Patient> findAll();

	public Optional<Patient> findByEmail(String email);

	public Optional<Patient> update(Patient patient);

	public Patient save(Patient patient);

	public Optional<Patient> find(int id);

}
