package com.me.stc.doctor.appointment.DoctorAppointment.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.me.stc.doctor.appointment.DoctorAppointment.entities.Appointment;

@Repository
@Transactional
public class AppointmentDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Appointment>findAllBySysDate(){
		List<Appointment> today_appoinments=entityManager.createNamedQuery("Appointment.findAllThisDayAppointment").getResultList();
		return today_appoinments;
		
	}
	
	
	public List<Appointment>findAllByDate(Date date){
		Query q=entityManager.createNamedQuery("Appointment.findAllThisDayAppointment");
		q.setParameter(1, date);
		List<Appointment> appoinments_by_date=q.getResultList();
		return appoinments_by_date;
		
	}
	

}
