package Json_StreamingAPI_Patient.jom;

import java.util.ArrayList;
import java.util.List;

import Json_StreamingAPI_Patient.entity.*;
import Json_StreamingAPI_Patient.jom.*;

public class Main {
	public static void main(String[] args) {
		List<Patient> patientList = JsonHandler.getListOfPatients("data/Patient.json");
		System.out.println(patientList);
		
	}
}
