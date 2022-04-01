package com.me.stc.doctor.appointment.DoctorAppointment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.stc.doctor.appointment.DoctorAppointment.entities.Patient;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	public Optional<Patient> findByEmail(String email);

}
