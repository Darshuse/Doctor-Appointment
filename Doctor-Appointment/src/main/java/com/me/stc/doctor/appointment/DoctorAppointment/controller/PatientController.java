package com.me.stc.doctor.appointment.DoctorAppointment.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.me.stc.doctor.appointment.DoctorAppointment.entities.Patient;
import com.me.stc.doctor.appointment.DoctorAppointment.exception.PatientNotFoundException;
import com.me.stc.doctor.appointment.DoctorAppointment.service.PatientServiceImp;


@RestController
public class PatientController {
	@Autowired
	PatientServiceImp service;

	@GetMapping(path = "/patients")
	public List<Patient> findAll() {
		List<Patient> patient_list = service.findAll();
		if (patient_list == null || patient_list.isEmpty())
			throw new PatientNotFoundException("no patients found ");
		return patient_list;
	}

	@GetMapping(path = "/patients/email/{email}")
	public Patient find(@PathVariable String email) {
		Patient patient = service.findByEmail(email).orElse(null);

		if (patient == null)
			throw new PatientNotFoundException("no patients found ");
		return patient;
	}

	@GetMapping(path = "/patients/id/{id}")
	public Patient find(@PathVariable int id) {
		Patient patient = service.find(id).orElse(null);

		if (patient == null)
			throw new PatientNotFoundException("no patients found ");
		return patient;
	}

	@PostMapping(path = "/patients")
	public ResponseEntity<Patient> save(@RequestBody @Valid Patient patient) {

		Patient savedPatient = service.save(patient);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/email/{email}")
				.buildAndExpand(savedPatient.getEmail()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/patients")
	public Optional<Patient> update(@RequestBody Patient patient) {
		Optional<Patient> updatedPatient = service.update(patient);
		if (updatedPatient == null)
			throw new PatientNotFoundException("no patients found ");
		return updatedPatient;
	}
	

}
