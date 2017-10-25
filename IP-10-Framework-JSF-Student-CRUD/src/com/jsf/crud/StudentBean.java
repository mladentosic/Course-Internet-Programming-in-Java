package com.jsf.crud;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.jsf.crud.db.operations.DatabaseOperation;

@ManagedBean @RequestScoped
public class StudentBean {

	private int id;  
	private String name;  
	private String email;  
	private String password;  
	private String gender;  
	private String address;
	private int godina_studija;
	private boolean budjet;
	private int stanje_racuna;

	public ArrayList<StudentBean>studentsListFromDB;

	public int getId() {
		return id;	
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}  
	
	
	@PostConstruct
	public void init() {
		studentsListFromDB = DatabaseOperation.getStudentsListFromDB();
	}

	
	public ArrayList<StudentBean> studentsList() {
		return studentsListFromDB;
	}
	

	public String saveStudentDetails(StudentBean newStudentObj) {
		return DatabaseOperation.saveStudentDetailsInDB(newStudentObj);
	}
	

	public String editStudentRecord(int studentId) {
		return DatabaseOperation.editStudentRecordInDB(studentId);
	}
	

	public String updateStudentDetails(StudentBean updateStudentObj) {
		return DatabaseOperation.updateStudentDetailsInDB(updateStudentObj);
	}
	

	public String deleteStudentRecord(int studentId) {
		return DatabaseOperation.deleteStudentRecordInDB(studentId);
	}

	public boolean getBudjet() {
		return budjet;
	}

	public void setBudjet(boolean budjet) {
		this.budjet = budjet;
	}

	public int getStanje_racuna() {
		return stanje_racuna;
	}

	public void setStanje_racuna(int stanje_racuna) {
		this.stanje_racuna = stanje_racuna;
	}

	public int getGodina_studija() {
		return godina_studija;
	}

	public void setGodina_studija(int godina_studija) {
		this.godina_studija = godina_studija;
	}

}