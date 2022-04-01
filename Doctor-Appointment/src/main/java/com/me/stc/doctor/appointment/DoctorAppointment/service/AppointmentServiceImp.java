package com.me.stc.doctor.appointment.DoctorAppointment.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.stc.doctor.appointment.DoctorAppointment.dao.AppointmentDao;
import com.me.stc.doctor.appointment.DoctorAppointment.dao.AppointmentRepository;
import com.me.stc.doctor.appointment.DoctorAppointment.entities.Appointment;
import com.me.stc.doctor.appointment.DoctorAppointment.entities.Patient;
import com.me.stc.doctor.appointment.DoctorAppointment.exception.AppointmentNotFoundException;
import com.me.stc.doctor.appointment.DoctorAppointment.exception.PatientNotFoundException;

@Service
public class AppointmentServiceImp implements AppointmentService {

	@Autowired
	AppointmentRepository repo;

	@Autowired
	AppointmentDao dao;

	@Autowired
	PatientServiceImp patientService;

	@Override
	public List<Appointment> findAll() {
		// TODO Auto-generated method stub

		List<Appointment> appointment_list = repo.findAllByCancelingStatus((byte) 0);

		return appointment_list;
	}

	@Override
	public Optional<Appointment> update(Appointment appointment) {
		// TODO Auto-generated method stub
		Optional<Appointment> updated_appointment = repo.findById(appointment.getId());

		if (updated_appointment.get() != null) {
			updated_appointment.get().setId(appointment.getId());
			updated_appointment.get().setAppointment_DateTime(appointment.getAppointment_DateTime());
			updated_appointment.get().setCancelingStatus(appointment.getCancelingStatus());
			updated_appointment.get().setCancleReason(appointment.getCancleReason());
			updated_appointment.get().setPatient(appointment.getPatient());
			repo.save(updated_appointment.get());
		}
		return updated_appointment;
	}

	public void cancel(Appointment appointment) {
		appointment.setCancelingStatus((byte) 1);
		Optional<Appointment> canceled_appointment = update(appointment);
		if (canceled_appointment.get() == null)
			throw new AppointmentNotFoundException("no appointment found ");
	}

	@Override
	public Appointment save(Appointment appointment) {
		// TODO Auto-generated method stub
		Patient patient = null;
		patient = patientService.findByEmail(appointment.getPatient().getEmail()).orElse(null);
		if (patient == null) {
			patient = patientService.save(appointment.getPatient());
		}
		appointment.setPatient(patient);
		Appointment savedappointment = repo.save(appointment);
		return savedappointment;
	}

	@Override
	public Optional<Appointment> find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> findAllBySysDate() {
		// TODO Auto-generated method stub
		List<Appointment> toDay_list = dao.findAllBySysDate();
		return toDay_list;
	}

	@Override
	public List<Appointment> findAllByDate(Date date) {
		// TODO Auto-generated method stub
		List<Appointment> appointments_by_date = dao.findAllByDate(date);
		return appointments_by_date;
	}
	@Override
	public List<Appointment> findAllByPatient(String email){
		
		Patient patient = patientService.findByEmail(email).orElse(null);
		if(patient==null)
			throw new PatientNotFoundException("no patients found ");
		
		List<Appointment> patient_history=repo.findAllByPatient(patient);
		return patient_history;

	}
}
