package org.com.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class DiagnosticCentre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int diagnosticCentreId;
	private String centreName;
	private String centreAddress;
	
	@JsonManagedReference
	@OneToMany(targetEntity=Test.class, mappedBy="diagnosticCentre")
	private Set<Test> setOfTests;
	
//	@OneToMany(mappedBy = "diagnosticCentre")
//	private List<Appointment> appointmentList;
	
	
	public Set<Test> getSetOfTests() {
		return setOfTests;
	}
	public void setSetOfTests(Set<Test> setOfTests) {
		this.setOfTests = setOfTests;
	}
	public int getDiagnosticCentreId() {
		return diagnosticCentreId;
	}
	public void setDiagnosticCentreId(int diagnosticCentreId) {
		this.diagnosticCentreId = diagnosticCentreId;
	}
	public String getCentreName() {
		return centreName;
	}
	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}
	public String getCentreAddress() {
		return centreAddress;
	}
	public void setCentreAddress(String centreAddress) {
		this.centreAddress = centreAddress;
	}
//	public List<Test> getListOfTests() {
//		return listOfTests;
//	}
//	public void setListOfTests(List<Test> listOfTests) {
//		this.listOfTests = listOfTests;
//	}
	
//	public List<Appointment> getAppointmentList() {
//		return appointmentList;
//	}
//	public void setAppointmentList(List<Appointment> appointmentList) {
//		this.appointmentList = appointmentList;
//	}
//	@Override
//	public String toString() {
//		return "DiagnosticCentre [diagnosticCentreId=" + diagnosticCentreId + ", centreName=" + centreName
//				+ ", centreAddress=" + centreAddress + ", listOfTests=" + listOfTests + ", appointmentList="
//				+ appointmentList + "]";
//	}
	
	
}
