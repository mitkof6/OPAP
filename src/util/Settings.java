package util;

/**
 * Class for overall settings.
 * 
 * @author STANEV DIMITAR
 */
public class Settings {
	
	//Variables
	private  String spCharLinux = "//", spCharWindows = "\\", sc;
	
	/**
	 * Constructor
	 */
	public Settings(){
		//OS
    	if(System.getProperty("os.name").contains("Windows")){
    		sc = spCharWindows;
    	}else{
    		sc = spCharLinux;
    	}
	}
	
	/**
	 * Method for getting the OS special character.
	 * 
	 * @return the special character
	 */
	public String getSpecialCharacter(){
		return sc;
	}
}
