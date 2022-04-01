package com.me.stc.doctor.appointment.DoctorAppointment.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the appointment database table.
 * 
 */
@Entity
@NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a")
@NamedNativeQuery(name="Appointment.findAllThisDayAppointment",query = "select * from appointment where appointment_DateTime <=   adddate(curdate(),INTERVAL 1 DAY)  \r\n" + 
		"and  appointment_DateTime  >= NOW() - INTERVAL 1 DAY and canceling_status=0" + 
		" ")
@NamedNativeQuery(name="Appointment.findAllbyDate",query = "select * from appointment where appointment_DateTime <=   adddate(?,INTERVAL 1 DAY)  \r\n" + 
		"and  appointment_DateTime  >= NOW() - INTERVAL 1 DAY and canceling_status=0" + 
		" ")
public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date appointment_DateTime;
    
    
    @NotNull
	@Column(name = "canceling_status")
	private byte cancelingStatus;

    
	@Column(name = "cancle_reason")
	private String cancleReason;

	// bi-directional many-to-one association to Patient
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "patient_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Patient patient;

	public Appointment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAppointment_DateTime() {
		return this.appointment_DateTime;
	}

	public void setAppointment_DateTime(Date appointment_DateTime) {
		this.appointment_DateTime = appointment_DateTime;
	}

	public byte getCancelingStatus() {
		return this.cancelingStatus;
	}

	public void setCancelingStatus(byte cancelingStatus) {
		this.cancelingStatus = cancelingStatus;
	}

	public String getCancleReason() {
		return this.cancleReason;
	}

	public void setCancleReason(String cancleReason) {
		this.cancleReason = cancleReason;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}