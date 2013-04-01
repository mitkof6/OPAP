package gui;

import java.awt.Toolkit;
import java.io.BufferedReader;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import util.Settings;

/**
 * This is general table frame.
 * 
 * @author STANEV DIMITAR
 */
public class ShowTableWindow extends JFrame {
	
	//Variable
	private static final long serialVersionUID = 1L;
	private JTable generalTable;
	private Object[][] generalData;
	private boolean flag = false;
	private int index = 1;
	private String tempStringR, tempStringL;
	private final char oneSurprise = '➊', twoSurprise = '➋', oneNormal = '➀', twoNormal = '➁', cross = 'ⓧ';
	/**
	 * Constructor
	 */
	public ShowTableWindow(){
		
		//Settings
		super("General Table");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("iconImage.gif")));
		
		//Create Table
		String[] columnNames = {"#",
				"DATE",
                "REAL",
                "LOGIC",
                "MIN",
                "MAX",
                "COEFF",
                "PERC %",
                "S1R",
                "SXR",
                "S2R",
                "STR",
                "S1L",
                "SXL",
                "S2L",
                "STL",
                "PER R",
                "PER L",
                "NR",
                "NL",
                "SP1",
                "SP2"};
		generalData = new Object[100][22];
		for(int i = 0;i<100;i++){
			generalData[i][0] = i+1;
			generalData[i][1] = new String("");
			generalData[i][2] = new String("");
			generalData[i][3] = new String("");
			generalData[i][4] = new String("");
			generalData[i][5] = new String("");
			generalData[i][6] = new String("");
			generalData[i][7] = new String("");
			generalData[i][8] = new String("");
			generalData[i][9] = new String("");
			generalData[i][10] = new String("");
			generalData[i][11] = new String("");
			generalData[i][12] = new String("");
			generalData[i][13] = new String("");
			generalData[i][14] = new String("");
			generalData[i][15] = new String("");
			generalData[i][16] = new String("");
			generalData[i][17] = new String("");
			generalData[i][18] = new String("");
			generalData[i][19] = new String("");
			generalData[i][20] = new String("");
			generalData[i][21] = new String("");
		}
		generalTable = new JTable(generalData, columnNames);
		
		//Make size
		TableColumn column = null;
		for (int i = 0; i < 22; i++) {
		    column = generalTable.getColumnModel().getColumn(i);
		    if (i==2) {
		        column.setPreferredWidth(130);
		    }else if(i==3){
		    	column.setPreferredWidth(90);
		    }else if(i>3&&i<7){
		    	column.setPreferredWidth(50);
		    }else if(i==1){
		    	column.setPreferredWidth(25);	
		    }else{
		    	column.setPreferredWidth(20);
		    }
		}

		//generalTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(generalTable);
		add(scrollPane);
		
		//Call Method
		loadData(JOptionPane.showInputDialog("Class?"));
		//Method markSurprises call
		markSurprises();
		
	}
	
	/**
	 * Loads data to the general table.
	 * @param categoria the name of the class to open
	 */
	private void loadData(String categoria){
		
		Settings specialChar = new Settings();
		
		//Read
		while(true){
			try {
				
				FileInputStream fstream = new FileInputStream("DataBase"+specialChar.getSpecialCharacter()+categoria+"-"+index+".txt");
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				
				String strLine;
				index++;
				tempStringR = "";
				tempStringL = "";
				while ((strLine = br.readLine()) != null){
					setData(strLine);
				}
				
				//Flag
				flag = false;
			} catch (FileNotFoundException e) {
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Sets the data to the general table.
	 * @param the line to split
	 */
	private void setData(String line){
		String[] arrayTemp = line.split(";\\s*");
		
		if(arrayTemp[0].equalsIgnoreCase("ChangeData")){
			flag = true;
			generalTable.getModel().setValueAt(tempStringR, index-2, 2);
			generalTable.getModel().setValueAt(tempStringL, index-2, 3);
		}else if(arrayTemp.length<2){
			return;
		}
		if(flag==false){

			if(arrayTemp[0].equalsIgnoreCase("DATE: ")){
				generalTable.getModel().setValueAt(arrayTemp[1], index-2, 1);
			}else{
				tempStringR += arrayTemp[1].toUpperCase()+" ";
				tempStringL += arrayTemp[5].toUpperCase()+" ";
			}
		}else{
			if(arrayTemp[0].equalsIgnoreCase("MIN: ")){
				generalTable.getModel().setValueAt(arrayTemp[1], index-2, 4);
			}else if(arrayTemp[0].equalsIgnoreCase("MAX: ")){
				generalTable.getModel().setValueAt(arrayTemp[1], index-2, 5);
			}else if(arrayTemp[0].equalsIgnoreCase("COEFF: ")){
				generalTable.getModel().setValueAt(arrayTemp[1], index-2, 6);
			}else if(arrayTemp[0].equalsIgnoreCase("PERCENTAGE  %: ")){
				generalTable.getModel().setValueAt(arrayTemp[1], index-2, 7);
			}else if(arrayTemp[0].equalsIgnoreCase("SYMMETRY 1: ")){
				generalTable.getModel().setValueAt(arrayTemp[1], index-2, 8);
				generalTable.getModel().setValueAt(arrayTemp[2], index-2, 12);
			}else if(arrayTemp[0].equalsIgnoreCase("SYMMETRY X: ")){
				generalTable.getModel().setValueAt(arrayTemp[1], index-2, 9);
				generalTable.getModel().setValueAt(arrayTemp[2], index-2, 13);
			}else if(arrayTemp[0].equalsIgnoreCase("SYMMETRY 2: ")){
				generalTable.getModel().setValueAt(arrayTemp[1], index-2, 10);
				generalTable.getModel().setValueAt(arrayTemp[2], index-2, 14);
			}else if(arrayTemp[0].equalsIgnoreCase("SYMMETRY TOTAL: ")){
				generalTable.getModel().setValueAt(arrayTemp[1], index-2, 11);
				generalTable.getModel().setValueAt(arrayTemp[2], index-2, 15);
			}else if(arrayTemp[0].equalsIgnoreCase("PERMUTATION: ")){
				generalTable.getModel().setValueAt(arrayTemp[1], index-2, 16);
				generalTable.getModel().setValueAt(arrayTemp[2], index-2, 17);
			}else if(arrayTemp[0].equalsIgnoreCase("NON NEXT: ")){
				generalTable.getModel().setValueAt(arrayTemp[1], index-2, 18);
				generalTable.getModel().setValueAt(arrayTemp[2], index-2, 19);
			}else if(arrayTemp[0].equalsIgnoreCase("SURPRISE 1: ")){
				generalTable.getModel().setValueAt(arrayTemp[1], index-2, 20);
			}else if(arrayTemp[0].equalsIgnoreCase("SURPRISE 2: ")){
				generalTable.getModel().setValueAt(arrayTemp[1], index-2, 21);
		}
	}
	}
	
	
	/**
	 * Method for marking the show table for surprises.
	 */
	private void markSurprises(){
		for(int i = 0;i<index;i++){
			String real = (String)generalTable.getModel().getValueAt(i, 2);
			String logic = (String)generalTable.getModel().getValueAt(i, 3);
			String newReal = "";
			for(int j = 0;j<real.length();j += 2){
				if(logic.charAt(j)=='2'){
					if(real.charAt(j)=='2'){
						newReal += twoSurprise+" ";
					}else if(real.charAt(j)=='1'){
						newReal += oneSurprise+" ";
					}	
				}else{
					if(real.charAt(j)=='1'){
						newReal += oneNormal+" ";
						
					}else if(real.charAt(j)=='2'){
						newReal += twoNormal+" ";
					}else{
						newReal += cross+" ";
					}
				}
			}
			generalTable.getModel().setValueAt(newReal, i, 2);
		}
	}
}

