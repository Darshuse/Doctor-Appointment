package com.me.stc.doctor.appointment.DoctorAppointment.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exception = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(PatientNotFoundException.class)
	public final ResponseEntity<Object> handlePatientNotFoundException(PatientNotFoundException ex, WebRequest request)
			throws Exception {
		ExceptionResponse exception = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exception, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AppointmentNotFoundException.class)
	public final ResponseEntity<Object> handleAppointmentNotFoundException(AppointmentNotFoundException ex, WebRequest request)
			throws Exception {
		ExceptionResponse exception = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exception, HttpStatus.NOT_FOUND);
	}


}
