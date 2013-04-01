package gui;


import java.awt.BorderLayout;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import util.Calculate;
import util.InputFile;
import util.Save;

/**
 * Main window frame.
 * 
 * @author STANEV DIMITAR
 */
public class Window extends JFrame{
	
	
	
	//Variables
	private static final long serialVersionUID = 1L;
	private JButton exit, news, load, calculate, save, showTable, clear, credits;
	private JScrollPane scrollPane, resultScrollPane;
	private JTable table, resultTable;
	private Object[][] data, resultData;
	private int matches;
	private ButtonHandler handler;
	private boolean flag = false, flagNew = false, flagNewOverflow = false, flagLoad = false;
	private int tempIndex = 0;
	private JLabel status;
	
	/**
	 * Constructor
	 */
	public Window(){
		
		//Setting
		super("Opap Statistics");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("iconImage.gif")));
		
		//Buttons
		JToolBar menuBar = new JToolBar();
		news = new JButton("New");
		calculate = new JButton("Calculate");
		calculate.setEnabled(false);
		save = new JButton("Save");
		save.setEnabled(false);	
		load = new JButton("Load");
		load.setEnabled(false);
		clear = new JButton("Clear");
		clear.setEnabled(false);
		showTable = new JButton("ShowTable");
		credits = new JButton("Credits");
		exit  = new JButton("Exit");
		menuBar.add(news);
		menuBar.add(calculate);
		menuBar.add(save);
		menuBar.add(load);
		menuBar.add(clear);
		menuBar.add(showTable);
		menuBar.add(credits);
		menuBar.add(exit);
		add(menuBar, BorderLayout.NORTH);
			
		//Handlers
		handler = new ButtonHandler();
		news.addActionListener(handler);
		calculate.addActionListener(handler);
		save.addActionListener(handler);
		load.addActionListener(handler);
		clear.addActionListener(handler);
		showTable.addActionListener(handler);
		credits.addActionListener(handler);
		exit.addActionListener(handler);


		
	}
	/**
	 * Handlers of the buttons.
	 */
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource()==news){
				
				//Reusable
				if(flagNew==true){
					Window application = new Window();
					application.pack();
					application.setVisible(true);
					setVisible(false);
					return;
				}
				flagNew = true;
				
				//Table
				String[] columnNames = {"MATCH",
		                "REAL",
		                "1",
		                "X",
		                "2",
		                "LOGIC"};
				
				String lines = JOptionPane.showInputDialog("How many matches?");
				matches = Integer.parseInt(lines);
				data = new Object[matches][6];
				for(int i = 0;i<matches;i++){
					data[i][0] = (i+1);
					data[i][1] = new String("");
					data[i][2] = new String("");
					data[i][3] = new String("");
					data[i][4] = new String("");
					data[i][5] = new String("");
					
				}
				table = new JTable(data, columnNames);
				scrollPane = new JScrollPane(table);
				add(scrollPane, BorderLayout.WEST);
				
				//Status Label
				status = new JLabel("Status: New");
				add(status, BorderLayout.AFTER_LAST_LINE);
				
				//Result
				String[] resaultColumnNames = {"FUNC",
		                "REAL",
		                "LOGIC"};
				resultData = new Object[12][3];
				resultData[0][0] = "MIN: ";
				resultData[1][0] = "MAX: ";
				resultData[2][0] = "COEFF: ";
				resultData[3][0] = "PERCENTAGE  %: ";
				resultData[4][0] = "SYMMETRY 1: ";
				resultData[5][0] = "SYMMETRY X: ";
				resultData[6][0] = "SYMMETRY 2: ";
				resultData[7][0] = "SYMMETRY TOTAL: ";
				resultData[8][0] = "PERMUTATION: ";
				resultData[9][0] = "NON NEXT: ";
				resultData[10][0] = "SURPRISE 1: ";
				resultData[11][0] = "SURPRISE 2: ";
				
				for(int i = 0;i<12;i++){
					resultData[i][1] = new String("");
					resultData[i][2] = new String("");
				}
				resultTable = new JTable(resultData, resaultColumnNames);
				resultScrollPane =  new JScrollPane(resultTable);
				add(resultScrollPane, BorderLayout.CENTER);

				setSize(830, 297);
				
				//Enable
				calculate.setEnabled(true);
				save.setEnabled(false);	
				load.setEnabled(true);
				clear.setEnabled(true);
				
				//Flag Load Init
				flagLoad = false;
				
			}else if(event.getSource()==exit){
				System.exit(0);
			}else if(event.getSource()==calculate){
				Calculate calc = new Calculate(table, matches);
				for(int i = 0;i<matches;i++){
					String[] logic = calc.getLogic();
					table.getModel().setValueAt(logic[i], i, 5);
				}
				resultTable.getModel().setValueAt(calc.getMin(), 0, 1);
				resultTable.getModel().setValueAt(calc.getMin(), 0, 2);
				resultTable.getModel().setValueAt(calc.getMax(), 1, 1);
				resultTable.getModel().setValueAt(calc.getMax(), 1, 2);
				resultTable.getModel().setValueAt(calc.getRealCoeff(), 2, 1);
				resultTable.getModel().setValueAt(calc.getRealCoeff(), 2, 2);
				resultTable.getModel().setValueAt(calc.getPercentage(), 3, 1);
				resultTable.getModel().setValueAt(calc.getPercentage(), 3, 2);
				resultTable.getModel().setValueAt(calc.getSymmetry1R(), 4, 1);
				resultTable.getModel().setValueAt(calc.getSymmetry1L(), 4, 2);
				resultTable.getModel().setValueAt(calc.getSymmetryXR(), 5, 1);
				resultTable.getModel().setValueAt(calc.getSymmetryXL(), 5, 2);
				resultTable.getModel().setValueAt(calc.getSymmetry2R(), 6, 1);
				resultTable.getModel().setValueAt(calc.getSymmetry2L(), 6, 2);
				resultTable.getModel().setValueAt(calc.getSymmetryTotalR(), 7, 1);
				resultTable.getModel().setValueAt(calc.getSymmetryTotalL(), 7, 2);
				resultTable.getModel().setValueAt(calc.getPerReal(), 8, 1);
				resultTable.getModel().setValueAt(calc.getPerLogic(), 8, 2);
				resultTable.getModel().setValueAt(calc.getNonReal(), 9, 1);
				resultTable.getModel().setValueAt(calc.getNonLogic(), 9, 2);
				resultTable.getModel().setValueAt(calc.getSurprise1(), 10, 1);
				resultTable.getModel().setValueAt(calc.getSurprise1(), 10, 2);
				resultTable.getModel().setValueAt(calc.getSurprise2(), 11, 1);
				resultTable.getModel().setValueAt(calc.getSurprise2(), 11, 2);
				
				//Status
				status.setText("Status: Calculate");
				
				//Enable
				save.setEnabled(true);
			}else if(event.getSource()==save){
				String name = JOptionPane.showInputDialog("Class?");
				@SuppressWarnings("unused")
				Save save = new Save(name, matches, table, resultTable, flagLoad);
				
				//Status
				status.setText("Status: Saved");
				
				//Flag Load Init
				flagLoad = false;
			}else if(event.getSource()==load){
				
				//Clear
				for(int i = 0;i<matches;i++){
					for(int j = 1;j<6;j++){
						table.getModel().setValueAt("", i, j);
					}
				}
				
				//Flag
				flagNewOverflow = false;
				
				InputFile inputFile = new InputFile();//file chooser class
                Scanner inputStream;
				try {

					inputStream = new Scanner(new File(inputFile.getFile()));
					while(inputStream.hasNext()&&flagNewOverflow==false){
	                	loadData(inputStream.nextLine());
	                }
					flag = false;
					tempIndex = 0;
					
					//Flag Load Init
					flagLoad = true;
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}//make an input stream  
				
				//Status
				status.setText("Status: Loaded File -> "+inputFile.getFile());
			}else if(event.getSource()==showTable){
				ShowTableWindow generalTable = new ShowTableWindow();
				generalTable.setVisible(true);
				generalTable.setSize(1370, 300);
				
				//Status
				status.setText("Status: Show Table");
			}else if(event.getSource()==clear){
				for(int i = 0;i<matches;i++){
					for(int j = 1;j<6;j++){
						table.getModel().setValueAt("", i, j);
					}
				}
				
				for(int i = 0;i<12;i++){
					for(int j = 1;j<3;j++){
						resultTable.getModel().setValueAt("", i, j);
					}
				}
				
				//Flag Load Init
				flagLoad = false;
				
				//Status
				status.setText("Status: Clear");
				
				//Enable
				save.setEnabled(false);
			}else if(event.getSource()==credits){
				Credits message = new Credits();
				message.setVisible(true);
				message.pack();
				
				//Status
				status.setText("Status: Credits");
			}
		}
	}
	
	/**
	 * Load method.
	 * @param line to load
	 */
	private void loadData(String line){
		
		//Split
		String[] arrayTemp = line.split(";\\s*");
		
		if(arrayTemp[0].equalsIgnoreCase("ChangeData")){
			flag = true;
		}else if(arrayTemp.length<2){
			return;
		}
		if(flag==false){
			if(arrayTemp[0].equalsIgnoreCase("DATE: ")){
				//Do nothing
			}else{
				if(tempIndex>=matches){
					JOptionPane.showMessageDialog(null, "Make new!", "Error New", JOptionPane.ERROR_MESSAGE);
					flagNewOverflow = true;
					return;
				}
				for(int i = 1;i<6;i++){
					table.getModel().setValueAt(arrayTemp[i], tempIndex, i);
				}
				tempIndex++;
			}
		}else{
			if(arrayTemp[0].equalsIgnoreCase("MIN: ")){
				resultTable.getModel().setValueAt(arrayTemp[1], 0, 1);
				resultTable.getModel().setValueAt(arrayTemp[2], 0, 2);
			}else if(arrayTemp[0].equalsIgnoreCase("MAX: ")){
				resultTable.getModel().setValueAt(arrayTemp[1], 1, 1);
				resultTable.getModel().setValueAt(arrayTemp[2], 1, 2);
			}else if(arrayTemp[0].equalsIgnoreCase("COEFF: ")){
				resultTable.getModel().setValueAt(arrayTemp[1], 2, 1);
				resultTable.getModel().setValueAt(arrayTemp[2], 2, 2);
			}else if(arrayTemp[0].equalsIgnoreCase("PERCENTAGE  %: ")){
				resultTable.getModel().setValueAt(arrayTemp[1], 3, 1);
				resultTable.getModel().setValueAt(arrayTemp[2], 3, 2);
			}else if(arrayTemp[0].equalsIgnoreCase("SYMMETRY 1: ")){
				resultTable.getModel().setValueAt(arrayTemp[1], 4, 1);
				resultTable.getModel().setValueAt(arrayTemp[2], 4, 2);
			}else if(arrayTemp[0].equalsIgnoreCase("SYMMETRY X: ")){
				resultTable.getModel().setValueAt(arrayTemp[1], 5, 1);
				resultTable.getModel().setValueAt(arrayTemp[2], 5, 2);
			}else if(arrayTemp[0].equalsIgnoreCase("SYMMETRY 2: ")){
				resultTable.getModel().setValueAt(arrayTemp[1], 6, 1);
				resultTable.getModel().setValueAt(arrayTemp[2], 6, 2);
			}else if(arrayTemp[0].equalsIgnoreCase("SYMMETRY TOTAL: ")){
				resultTable.getModel().setValueAt(arrayTemp[1], 7, 1);
				resultTable.getModel().setValueAt(arrayTemp[2], 7, 2);
			}else if(arrayTemp[0].equalsIgnoreCase("PERMUTATION: ")){
				resultTable.getModel().setValueAt(arrayTemp[1], 8, 1);
				resultTable.getModel().setValueAt(arrayTemp[2], 8, 2);
			}else if(arrayTemp[0].equalsIgnoreCase("NON NEXT: ")){
				resultTable.getModel().setValueAt(arrayTemp[1], 9, 1);
				resultTable.getModel().setValueAt(arrayTemp[2], 9, 2);
			}else if(arrayTemp[0].equalsIgnoreCase("SURPRISE 1: ")){
				resultTable.getModel().setValueAt(arrayTemp[1], 10, 1);
				resultTable.getModel().setValueAt(arrayTemp[2], 10, 2);
			}else if(arrayTemp[0].equalsIgnoreCase("SURPRISE 2: ")){
				resultTable.getModel().setValueAt(arrayTemp[1], 11, 1);
				resultTable.getModel().setValueAt(arrayTemp[2], 11, 2);
			}
		}
	}
}
