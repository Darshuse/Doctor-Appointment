package com.me.stc.doctor.appointment.DoctorAppointment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.stc.doctor.appointment.DoctorAppointment.entities.Appointment;
import com.me.stc.doctor.appointment.DoctorAppointment.entities.Patient;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	List<Appointment> findAllByCancelingStatus(byte status);
	List<Appointment>  findAllByPatient(Patient patient);
}
