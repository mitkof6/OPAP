package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JTable;

/**
 * Saves the data to the DataBase directory.
 * 
 * @author STANEV DIMITAR
 * 
 */
public class Save {
	
	//Variables
	private int index;
	private Settings specialChar;
	private static final String dateFormat = "MM/dd/yy";
	
	/**
	 * Constructor
	 * @param name to save
	 * @param matches number of matches
	 * @param table the data JTable
	 * @param resultTable the result JTable
	 * 
	 */
	public Save(String name, int matches, JTable table, JTable resultTable, boolean flagLoad){

		Formatter output;
		specialChar = new Settings();
		try {
			if(flagLoad==false){
				index = findIndex(name);
				output = new Formatter("DataBase"+specialChar.getSpecialCharacter()+name+"-"+index+".txt");
			}else{
				output = new Formatter("DataBase"+specialChar.getSpecialCharacter()+name+".txt");
			}
			
			
			Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			output.format("DATE: ;%s\n", sdf.format(cal.getTime()));
			
			for(int i = 0;i<matches;i++){
				output.format("%s;%s;%s;%s;%s;%s\n", table.getModel().getValueAt(i, 0), table.getModel().getValueAt(i, 1), table.getModel().getValueAt(i, 2), table.getModel().getValueAt(i, 3), table.getModel().getValueAt(i, 4), table.getModel().getValueAt(i, 5));
			}
			output.format("ChangeData\n");
			for(int i = 0;i<12;i++){
				output.format("%s;%s;%s\n", resultTable.getModel().getValueAt(i, 0), resultTable.getModel().getValueAt(i, 1), resultTable.getModel().getValueAt(i, 2));
			}
			output.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
	}
	
	/**
	 * Private method to find the index to save.
	 * 
	 * @param name of the class
	 * @return index to save
	 * 
	 */
	private int findIndex(String name){
		index = 1;
		@SuppressWarnings("unused")
		Scanner tempIn;
		
		while(true){
			try {
				tempIn = new Scanner(new File("DataBase"+specialChar.getSpecialCharacter()+name+"-"+index+".txt"));
				index++;
			} catch (FileNotFoundException e) {
				return index;
			}
		}
		
	}
}
