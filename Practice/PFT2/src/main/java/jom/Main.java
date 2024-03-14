package jom;

import java.util.List;

import entity.Patient;

public class Main {
	public static void main(String[] args) {
		List<Patient> PatientList = JsonHandle.getListOfPatients("data/Patient.json");
		System.out.println(PatientList);
	}
}
