package com.me.stc.doctor.appointment.DoctorAppointment.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.me.stc.doctor.appointment.DoctorAppointment.entities.Appointment;

public interface AppointmentService {

	public List<Appointment> findAll();

	public Optional<Appointment> update(Appointment appointment);

	public Appointment save(Appointment appointment);

	public Optional<Appointment> find(int id);

	public List<Appointment> findAllBySysDate();

	public List<Appointment> findAllByDate(Date date);

	public List<Appointment> findAllByPatient(String email);

}
