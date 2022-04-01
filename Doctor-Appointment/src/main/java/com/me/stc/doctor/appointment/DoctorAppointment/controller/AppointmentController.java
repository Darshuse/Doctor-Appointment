package com.me.stc.doctor.appointment.DoctorAppointment.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.me.stc.doctor.appointment.DoctorAppointment.entities.Appointment;
import com.me.stc.doctor.appointment.DoctorAppointment.exception.AppointmentNotFoundException;
import com.me.stc.doctor.appointment.DoctorAppointment.service.AppointmentServiceImp;

@RestController
public class AppointmentController {
	@Autowired
	AppointmentServiceImp service;

	@PostMapping(path = "/appointments")
	public ResponseEntity<Appointment> save(@RequestBody @Valid Appointment appointment) {
		Appointment saved_appointment = service.save(appointment);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id/{id}")
				.buildAndExpand(saved_appointment.getId()).toUri();
		return ResponseEntity.created(location).build();

	}
	

	@GetMapping("/appointments/id/{id}")
	public Appointment find(@PathVariable int id) {
		Appointment appointment = service.find(id).orElse(null);
		if (appointment == null)
			throw new AppointmentNotFoundException("no appointment found ");
		return appointment;
	}

	@GetMapping("/appointments")
	public List<Appointment> findAll() {
		List<Appointment> appointment_list = service.findAll();
		if (appointment_list == null || appointment_list.isEmpty())
			throw new AppointmentNotFoundException("no appointment found ");
		return appointment_list;
	}

	@PutMapping("/appointments/cancel")
	public void cancel(@RequestBody Appointment appointment) {
		service.cancel(appointment);
	}

	@PutMapping("/appointments")
	public Optional<Appointment> update(@RequestBody Appointment appointment) {
		Optional<Appointment> updated_appointment = service.update(appointment);
		if (updated_appointment.get() == null)
			throw new AppointmentNotFoundException("no appointment found ");
		return updated_appointment;
	}
	@GetMapping("/appointments/today")
	public List<Appointment> findBySysDate(){
		List<Appointment> today_appointment_list = service.findAllBySysDate();
		if (today_appointment_list == null || today_appointment_list.isEmpty())
			throw new AppointmentNotFoundException("no appointment found ");
		return today_appointment_list;
		
	}
	//should test
	@GetMapping("/appointments/")
	public List<Appointment> findBySysDate(@RequestParam String date){
		Date appointment_Date=new Date(date);
		List<Appointment> appointment_list_by_date = service.findAllByDate(appointment_Date);
		if (appointment_list_by_date == null || appointment_list_by_date.isEmpty())
			throw new AppointmentNotFoundException("no appointment found ");
		return appointment_list_by_date;
		
	}

	@GetMapping("/appointments/patient/history")
	public List<Appointment> findBypatient(@RequestParam String email){
		List<Appointment> patient_history = service.findAllByPatient(email);
		if (patient_history == null || patient_history.isEmpty())
			throw new AppointmentNotFoundException("no appointment found ");
		return patient_history;
		
	}
	
}
