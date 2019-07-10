package com.example.demo.controller;

import java.io.PrintWriter;
import java.util.List;

import com.opencsv.CSVWriter;

import entity.Responds;

public class WriteDataToCSV {
	public static void writeDataToCsvUsingStringArray(PrintWriter writer,List<Responds> responds) {
		String[] CSV_HEADER = { "id", "firstname", "lastname","email","why do you want to join nateg","previous experience","comments","warning email","end of period email","date of form" };
		try (
			CSVWriter csvWriter = new CSVWriter(writer,
		                CSVWriter.DEFAULT_SEPARATOR,
		                CSVWriter.NO_QUOTE_CHARACTER,
		                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
		                CSVWriter.DEFAULT_LINE_END);
		){
			csvWriter.writeNext(CSV_HEADER);
 
			for (Responds respond : responds) {
				String[] data = {
						respond.getId(),
						respond.getName(),
						respond.getFamilyName(),
						respond.getEmail(),
						respond.getWhyDoyouWwannajoinNateg(),
						respond.getVolenteeringeExperience(),
						respond.getComment(),
						respond.getState(),
						respond.getFinalstate(),
						respond.getDate()
				};
				
				csvWriter.writeNext(data);
			}
			
			System.out.println("Write CSV using CSVWriter successfully!");
		}catch (Exception e) {
			System.out.println("Writing CSV error!");
			e.printStackTrace();
		}
	}
	
	
}
