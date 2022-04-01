package com.me.stc.doctor.appointment.DoctorAppointment.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

/**
 * The persistent class for the patient database table.
 * 
 */
@Entity
@NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Email
	@NotNull
	private String email;
	
	@NotNull
	@Size(min = 3, message = "lastName  should be at least 3 ")
	private String name;

	// bi-directional many-to-one association to Appointment
//	@OneToMany(mappedBy = "patient")
//	private List<Appointment> appointments;

	public Patient() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


}