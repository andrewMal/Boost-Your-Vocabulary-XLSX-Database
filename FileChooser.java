package copyboostYourVoc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;



public class FileChooser {//file chooser, to select the database file(.xlsx) the first time of run
	
	static String pathOfSer ="PathForVocFile.ser"; //Serialization file which keep the directory of selected xlsx file
	static String path;//path of xlsx file
	
	public FileChooser(String pathOfSer) {
		if(new File(pathOfSer).isFile()){// if the serialization file exist 
			try {
	            FileInputStream fileIn = new FileInputStream("PathForVocFile.ser");
	        	ObjectInputStream in = new ObjectInputStream(fileIn);
	            path = (String) in.readObject();// take path of .xlsx from .ser file
	            in.close();
	            fileIn.close();
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }catch (ClassNotFoundException e3){
	        	e3.printStackTrace();
	        }
		}
		else{//if is the first time when you run the app you must choose it 
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			jfc.setDialogTitle("Select an excel file for database");
			jfc.setAcceptAllFileFilterUsed(false);//we choose what kind of file
			FileNameExtensionFilter filter = new FileNameExtensionFilter("XLSX file", "xlsx");//only xls files
			jfc.addChoosableFileFilter(filter);

			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {//if we choose right file
				path=jfc.getSelectedFile().getPath();
				 try {
			            FileOutputStream fileOut =  new FileOutputStream("PathForVocFile.ser");//write to .ser file the path
			            ObjectOutputStream out = new ObjectOutputStream(fileOut);
			            out.writeObject(path);
			            out.close();
			            fileOut.close();
			        } catch (IOException e2) {
			            e2.printStackTrace();
			        }
			}
			else{
				Object[] options ={"Import","Cancel"};
				int n=JOptionPane.showOptionDialog(jfc, "You have to import database, if you haven't xlsx file create new.", "Import Database", 
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				if(n==0){//try again
					new FileChooser(pathOfSer);
				}
				else{
					System.exit(0);//terminate the program bcs you must have database 
				}
			}

		}
	}
}