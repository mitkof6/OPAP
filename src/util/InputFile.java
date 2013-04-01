package util;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

/**
 *<p>
 * The class for the Input File Dialog.
 * 
 * @author STANEV DIMITAR
 */
public class InputFile {
    
    
    /**
     * String global variable file.
     */
    private String file;
       
    /**
     * Constructor
     * 
     */
    public InputFile() {
        getFileOrDirectory();  
    }
    
    /**
     * Get the file.
     * 
     * @return the file object 
     */
    public String getFile(){
        return this.file;
    }
    
    /**
     * Private method for opening the file window and passing the results to the <code>file</code>.
     * 
     */
    private void getFileOrDirectory(){
    	
        JFileChooser fileToOpen = new JFileChooser();
        Settings specialChar = new Settings();
        
        try {
			File dataBaseDirectory = new File(new File("."+specialChar.getSpecialCharacter()+"DataBase").getCanonicalPath());
			fileToOpen.setCurrentDirectory(dataBaseDirectory);
			
			fileToOpen.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        @SuppressWarnings("unused")
			int resault = fileToOpen.showOpenDialog(null);
	        file = fileToOpen.getSelectedFile().getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
}