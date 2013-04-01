package util;

import javax.swing.JTable;
/**
 * The calculation class.
 * 
 * @author STANEV DIMITAR
 */
public class Calculate {

	//Variables
	private JTable table;
	private double maxCoeff = 1, minCoeff = 1 ,realCoeff = 1;
	private double[][] coeff;
	private String[] real;
	private String[] logic;
	private int matches, nonReal = 0, nonLogic = 0, symmetry1R = 0, symmetry2R = 0, symmetryXR = 0, symmetryTotalR, symmetry1L = 0, symmetry2L = 0, symmetryXL = 0, symmetryTotalL, surprise1 = 0, surprise2 = 0;
	private String perReal = "", perLogic = "";
	private double percentage;
	
	/**
	 * Constructor
	 */
	public Calculate(JTable table, int matches){
		
		this.table = table;
		this.matches = matches;
		logic = new String[matches];
		coeff = new double[matches][3];
		real = new String[matches];
		
		//Call methods
		convert();
		calc();
		nonAgain();
		symmetry();
		surprise();
	}
	
	/**
	 * Main calculation method.
	 */
	private void calc(){
		
		int perCounter1R = 0, perCounter2R = 0, perCounterXR = 0, perCounter1L = 0, perCounter2L = 0, perCounterXL = 0;
		double min, max;
		for(int i = 0;i<matches;i++){
			max = coeff[i][0];
			min = coeff[i][0];
			if(real[i].equalsIgnoreCase("1")){
				realCoeff *= coeff[i][0];
				perCounter1R++;
			}else if(real[i].equalsIgnoreCase("x")){
				realCoeff *= coeff[i][1];
				perCounterXR++;
			}else if(real[i].equalsIgnoreCase("2")){
				realCoeff *= coeff[i][2];
				perCounter2R++;
			}
			
			if(real[i].equalsIgnoreCase("1")){
				if(coeff[i][0]<coeff[i][2]){
					logic[i] = "1";
					perCounter1L++;
				}else{
					logic[i] = "2";
					perCounter2L++;
				}
			}else if(real[i].equalsIgnoreCase("2")){
				if(coeff[i][2]<coeff[i][0]){
					logic[i] = "1";
					perCounter1L++;
				}else{
					logic[i] = "2";
					perCounter2L++;
				}
			}else if(real[i].equalsIgnoreCase("x")){
				logic[i] = "x";
				perCounterXL++;
			}
			
			for(int j = 0;j<3;j++){
				if(max<coeff[i][j]){
					max = coeff[i][j];
				}
				if(min>coeff[i][j]){
					min = coeff[i][j];
				}
			}
			
			minCoeff *=min;
			maxCoeff *=max;
			
		}

		perReal = String.format("%d-%d-%d", perCounter1R, perCounterXR, perCounter2R);
		perLogic = String.format("%d-%d-%d", perCounter1L, perCounterXL, perCounter2L);
		
		if(realCoeff==maxCoeff){
			percentage = 100d;
		}else{
			percentage = ((realCoeff-minCoeff)/((maxCoeff-minCoeff)/100));
		}
	}
	
	
	/**
	 * Non next method.
	 */
	private void nonAgain(){
		for(int i = 0;i<matches;i++){
			if((i+1)==matches){
				break;
			}else{
				if(!real[i].equalsIgnoreCase(real[i+1])){
					nonReal++;
				}
				if(!logic[i].equalsIgnoreCase(logic[i+1])){
					nonLogic++;
				}
			}	
		}
	}
	
	/**
	 * Symmetry method.
	 */
	private void symmetry(){
		int middle = matches/2;
		for(int i = 0;i<middle;i++){
			if(real[i].equalsIgnoreCase(real[matches-i-1])){
				symmetryTotalR++;
				if(real[i].equalsIgnoreCase("1")){
					symmetry1R++;
				}else if(real[i].equalsIgnoreCase("2")){
					symmetry2R++;
				}else if(real[i].equalsIgnoreCase("x")){
					symmetryXR++;
				}
			}
			
			if(logic[i].equalsIgnoreCase(logic[matches-i-1])){
				symmetryTotalL++;
				if(logic[i].equalsIgnoreCase("1")){
					symmetry1L++;
				}else if(logic[i].equalsIgnoreCase("2")){
					symmetry2L++;
				}else if(logic[i].equalsIgnoreCase("x")){
					symmetryXL++;
				}
			}
		}
	}
	
	/**
	 * Method for surprise calculations.
	 */
	private void surprise(){
		for(int i = 0;i<matches;i++){
			if(logic[i].equalsIgnoreCase("2")){
				if(real[i].equalsIgnoreCase("2")){
					surprise1++;
				}else if(real[i].equalsIgnoreCase("1")){
					surprise2++;
				}
			}
			
		}
	}
	
	/**
	 * Convert method for coefficients.
	 */
	private void convert(){
		for(int i = 0;i<matches;i++){
			real[i] = (String) table.getModel().getValueAt(i, 1);
			for(int j = 2;j<=4;j++){
				coeff[i][j-2] = Double.parseDouble((String) table.getModel().getValueAt(i, j));
			}
		}
	}
	
	//Get Methods
	/**
	 * Get coefficients.
	 * @return coeff
	 */
	public double[][] getCoeff(){
		return coeff;
	}
	
	/**
	 * Get real.
	 * @return real
	 */
	public String[] getReal(){
		return real;
	}
	
	/**
	 * Get logic.
	 * @return logic
	 */
	public String[] getLogic(){
		return logic;
	}
	
	/**
	 * Get max coefficient.
	 * @return maxCoeff
	 */
	public String getMax(){
		return String.format("%.2f", maxCoeff);
	}
	
	/**
	 * Get min coefficient.
	 * @return minCoeff
	 */
	public String getMin(){
		return String.format("%.2f", minCoeff);
	}
	
	/**
	 * Get real coefficients.
	 * @return realCoeff
	 */
	public String getRealCoeff(){
		return String.format("%.2f", realCoeff);
	}
	
	/**
	 * Get percentage.
	 * @return percentage
	 */
	public String getPercentage(){
		return String.format("%.2f", percentage);
	}
	
	/**
	 * Get real permutation.
	 * @return perReal
	 */
	public String getPerReal(){
		return perReal;
	}
	
	/**
	 * Get logic permutation.
	 * @return perLogic
	 */
	public String getPerLogic(){
		return perLogic;
	}
	
	/**
	 * Get real non next.
	 * @return nonReal
	 */
	public int getNonReal(){
		return nonReal;
	}
	
	/**
	 * Get logic non next.
	 * @return nonLogic
	 */
	public int getNonLogic(){
		return nonLogic;
	}
	
	/**
	 * Get real symmetry 1.
	 * @return symmetry1R
	 */
	public int getSymmetry1R(){
		return symmetry1R;
	}
	
	/**
	 * Get real symmetry x.
	 * @return symmetryXR
	 */
	public int getSymmetryXR(){
		return symmetryXR;
	}
	
	/**
	 * Get real symmetry 2.
	 * @return symmetry2R
	 */
	public int getSymmetry2R(){
		return symmetry2R;
	}
	
	/**
	 * Get real symmetry total.
	 * @return symmetryTotalR
	 */
	public int getSymmetryTotalR(){
		return symmetryTotalR;
	}
	
	/**
	 * Get logic symmetry 1.
	 * @return symmetry2L
	 */
	public int getSymmetry1L(){
		return symmetry1L;
	}
	
	/**
	 * Get logic symmetry x.
	 * @return symmetryXL
	 */
	public int getSymmetryXL(){
		return symmetryXL;
	}
	
	/**
	 * Get logic symmetry 2.
	 * @return symmetry2L
	 */
	public int getSymmetry2L(){
		return symmetry2L;
	}
	
	/**
	 * Get logic symmetry total.
	 * @return symmetryTotalL
	 */
	public int getSymmetryTotalL(){
		return symmetryTotalL;
	}
	
	/**
	 * Get surprise in 1.
	 * @return surprise1
	 */
	public int getSurprise1(){
		return surprise1;
	}
	
	/**
	 * Get surprise in 2.
	 * @return surprise2
	 */
	public int getSurprise2(){
		return surprise2;
	}
}

