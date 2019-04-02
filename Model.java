package copyboostYourVoc;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class Model {
	
	static int turns;//count every answer
	static int correct;//count correct answers
	static int noOfRows;//number of rows for every sheet
	static int extraRows=0;
	static int randomIndex;
	static int randomSheet;
	static Sheet sh;//sheet of excel
	static Workbook wb;
	static FileInputStream fis;
	static FileOutputStream fos;
	static Row row;
	static Cell cellF;//cell for foreign word
	static Cell cellN;//cell for native word
	static final String[] sheets={"noun","adj","adv","verb"};//sheets name of excel file 
	static boolean modified=false;//flag variable

	
	//*****************Insert words WinodowAdapter*****************
	static class InsWindowAdapter extends WindowAdapter{
		
		@Override
		public void windowActivated(WindowEvent e) {
			InsertWords.textFieldNative.setText(""); //set the textfields empty
			InsertWords.textFieldForeign.setText("");
			try {
				fis = new FileInputStream(FileChooser.path); //connect with input xlsx
				wb = WorkbookFactory.create(fis); //create workbook

			} catch (EncryptedDocumentException | IOException | InvalidFormatException e1) {
				//e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Access to input file failed","Error",JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
	}
	//*****************Search Window adapter*****************
	static class SearchWindowAdapter extends WindowAdapter{
		
		@Override
		public void windowDeactivated(WindowEvent e){//clean text fields
			Search.textFieldForeignS.setText("");
			Search.textFieldNativeS.setText("");
		}
	}
	//*****************Update Window adapter*****************
	static class UpdateWinAdapter extends WindowAdapter{
		
		@Override
		public void windowActivated(WindowEvent e) {
			UpdateWords.position=0;
			UpdateWords.targetWords = new ArrayList<ArrayList<String>>();//Array list for search results(list of words)
			
			try{
				fis = new FileInputStream(FileChooser.path); //access
				wb = WorkbookFactory.create(fis);
				for(int j=0;j<sheets.length;j++){//iterate all sheets
					sh=wb.getSheet(sheets[j]);
					noOfRows=sh.getLastRowNum();
					for(int i=1; i<=noOfRows; i++){//iterate all rows  
						if (Search.byForeign){  //search by foreign
							if(sh.getRow(i).getCell(0).getStringCellValue().contains(Search.foreignWord)){ //if the contains the chars which typed add in list
								UpdateWords.targetWord=new ArrayList<String>();
								UpdateWords.targetWord.add(sh.getRow(i).getCell(0).getStringCellValue());
								UpdateWords.targetWord.add(sh.getRow(i).getCell(2).getStringCellValue());
								UpdateWords.targetWord.add(sheets[j]+" "+i); // take the row
								UpdateWords.targetWords.add(UpdateWords.targetWord);
							}

						}
						else{ //search by native
							if(sh.getRow(i).getCell(2).getStringCellValue().contains(Search.nativeWord)){
								UpdateWords.targetWord=new ArrayList<String>();
								UpdateWords.targetWord.add(sh.getRow(i).getCell(0).getStringCellValue());
								UpdateWords.targetWord.add(sh.getRow(i).getCell(2).getStringCellValue());
								UpdateWords.targetWord.add(sheets[j]+" "+i);
								UpdateWords.targetWords.add(UpdateWords.targetWord);
							}
						}
					}
				}
				
				UpdateWords.textFieldID.setText(UpdateWords.targetWords.get(0).get(2).toString()); //view of the search result
				UpdateWords.textFieldUpForeign.setText(UpdateWords.targetWords.get(0).get(0).toString());
				UpdateWords.textFieldUpNative.setText(UpdateWords.targetWords.get(0).get(1).toString());
				//System.out.println(UpdateWords.targetWords.size());
			}catch(InvalidFormatException | IOException e2){
				//e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "Access to input file failed","Error",JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}catch(IndexOutOfBoundsException e5){//if you delete a result
				JOptionPane.showMessageDialog(null, "Done","Done",JOptionPane.INFORMATION_MESSAGE);
				if(VocabularyApp.searchframe.isEnabled()==false){
					VocabularyApp.searchframe.setEnabled(true);
				}
				VocabularyApp.wordsframe.setEnabled(true);//open wordsframe if you opened it from there
				VocabularyApp.updateframe.setVisible(false);
			}
			
		}

	}
	//*****************TypeTrain WindowAdapter*****************
	static class TypeTrainWinAdapter extends WindowAdapter{
		@Override
		public void windowActivated(WindowEvent e) {
			try{
				fis=new FileInputStream(FileChooser.path);//access
				wb=WorkbookFactory.create(fis);
				randomSheet=ThreadLocalRandom.current().nextInt(0,4);//random take number from 0 to 3 
				
				sh=wb.getSheet(sheets[randomSheet]);//random sheet from sheets[]
				noOfRows=sh.getLastRowNum();
				randomIndex = ThreadLocalRandom.current().nextInt(1, noOfRows + 1);//random num with range max->numberOfRows min->one
				
				TypeTrain.textField1.setText(sh.getRow(randomIndex).getCell(0).toString()); //the demanded word
				TypeTrain.answer=sh.getRow(randomIndex).getCell(2).toString(); // the correct answer
		
			}catch( IOException | InvalidFormatException e8){
				//e8.printStackTrace();
				JOptionPane.showMessageDialog(null, "Access to input file failed","Error",JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
		@Override
		public void windowDeactivated(WindowEvent e) {
			TypeTrain.textField1.setText("");
			TypeTrain.textField2.setText("");//clean
			correct=0;                        // reset the counters
			turns=0;
			TypeTrain.progressBar.setValue(0);
		}
	}
	//*****************MultipleTrain Window Adapter*****************
	static class MultipleTrainWinAdapter extends WindowAdapter{
		public void windowActivated(WindowEvent e){
			
			try {
				fis=new FileInputStream(FileChooser.path);
				wb=WorkbookFactory.create(fis);
				
				for(int i=0;i<sheets.length;i++){
					sh=wb.getSheet(sheets[i]);
					if(sh.getLastRowNum()<3){//check if there are three words to every sheet for good functionality
						JOptionPane.showMessageDialog(null, "You must have at least three words for every category!"
								,"Add more words",JOptionPane.INFORMATION_MESSAGE);
						VocabularyApp.multipleframe.dispose();//terminate frame
						VocabularyApp.mainframe.setEnabled(true);
					}
				}
				
				randomSheet=ThreadLocalRandom.current().nextInt(0,4);//random take number from 0 to 3 
				
				sh=wb.getSheet(sheets[randomSheet]);//random sheet from sheets[]
				
				noOfRows=sh.getLastRowNum();
				
			} catch (IOException | InvalidFormatException e1) {
				JOptionPane.showMessageDialog(null, "Access to input file failed","Error",JOptionPane.ERROR_MESSAGE);
				//e1.printStackTrace();
				System.exit(0);
			}
		}
		
		public void windowDeactivated(WindowEvent e) {
			correct=0;                        // reset the counters
			turns=0;
			MultipleTrain.progressBar.setValue(0);//reset progress bar
		}
	}
	//*****************Words Window Adapter*****************
	static class WordsWinAdapter extends WindowAdapter{
		@Override
		public void windowActivated(WindowEvent e){
			try{
				if(Words.table.getRowCount()>0 && !modified){//if we have rows and the table hasn't changes do nothing
				}
				else if(modified){//if we have any change read again to refresh the view
					Words.tableModel.setRowCount(0);//set row count zero to override the table and avoid duplicate rows
					fis = new FileInputStream(FileChooser.path); //access
					wb = WorkbookFactory.create(fis);
					for(int j=0;j<sheets.length;j++){
						sh=Model.wb.getSheet(sheets[j]);
						noOfRows=Model.sh.getLastRowNum();
						for(int i=1; i<=noOfRows; i++){
							String texts[] ={sheets[j]+" "+i,sh.getRow(i).getCell(0).getStringCellValue(),sh.getRow(i).getCell(2).getStringCellValue()};
							Words.tableModel.addRow(texts);
						}
					}
					modified=false;
				}
				else{//first time open WordsFrame
					fis = new FileInputStream(FileChooser.path); //access
					wb = WorkbookFactory.create(fis);
					for(int j=0;j<sheets.length;j++){
						sh=Model.wb.getSheet(sheets[j]);
						noOfRows=Model.sh.getLastRowNum();
						for(int i=1; i<=noOfRows; i++){ 
							String texts[] ={sheets[j]+" "+i,sh.getRow(i).getCell(0).getStringCellValue(),sh.getRow(i).getCell(2).getStringCellValue()};
							Words.tableModel.addRow(texts);
						}
					}
				}
			}catch(InvalidFormatException | IOException e2){
				//e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "Access to input file failed","Error",JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
			
		

	}


	//*****************InsertWords Listeners*****************
	static ActionListener newInsertListener=(e)->{ // insert
		try{
			String foreignW = InsertWords.textFieldForeign.getText();//write foreign
			String nativeW = InsertWords.textFieldNative.getText();//write native
			int selectedIndex=InsertWords.choiceType.getSelectedIndex();//select category
			switch(selectedIndex){
			case 0:
				sh=wb.getSheet("noun");break;
			case 1:
				sh=wb.getSheet("adj");break;
			case 2:
				sh=wb.getSheet("adv");break;
			case 3:
				sh=wb.getSheet("verb");break;
			}
			noOfRows=sh.getLastRowNum(); // take the number of rows
			noOfRows++; //add row to insert new fields
			
			if (!foreignW.equals("") && !nativeW.equals("")){ // check if the fields aren't empty  
				                                              
				row = sh.createRow(noOfRows);// create row under the last row
				
				cellF=row.getCell(0);
				
				cellN=row.getCell(2);
				
				if (cellF==null || cellN==null){ //check if the cells is null
					cellF=row.createCell(0);
					cellN=row.createCell(2);
				}
				
				cellF.setCellValue(foreignW); //set the value of textfileds in cells
				cellN.setCellValue(nativeW);
				
				//fileIn.close();
				fos = new FileOutputStream(FileChooser.path);//access
				
				wb.write(fos);
				fos.flush();
			
				fos.close();
				JOptionPane.showMessageDialog(null, "The word insterted succesfully.","INSERT",JOptionPane.PLAIN_MESSAGE);
				extraRows++;
				System.out.println(noOfRows);
				System.out.println(extraRows);
				modified=true;//flag for change
			}
			else {
				JOptionPane.showMessageDialog(null,"You have to type in both of the fields","Type something",JOptionPane.INFORMATION_MESSAGE);
			}
		}catch(IOException e1){
			JOptionPane.showMessageDialog(null, "Access to input file failed","Error",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	};
	//*****************Search Listeners*****************
	static ActionListener searchFListener =(e)->{ //search foreign word
		Search.foreignWord = Search.textFieldForeignS.getText();
		VocabularyApp.searchframe.setEnabled(false);
		VocabularyApp.updateframe.setVisible(true);
		Search.byForeign=true;
	};
	
	static ActionListener searchNListener = (e)->{ //search native word
		Search.nativeWord = Search.textFieldNativeS.getText();
		VocabularyApp.searchframe.setEnabled(false);
		VocabularyApp.updateframe.setVisible(true);
		Search.byForeign=false;
	};
	//*****************UpdateWords Listeners*****************
	static ActionListener firstRecordListener = (e)->{ //first record(from arraylist) of the search 
		if(UpdateWords.targetWords.isEmpty()){
			JOptionPane.showMessageDialog(null, "No words for your search","Empty result",JOptionPane.WARNING_MESSAGE);
		}
		else{
			UpdateWords.textFieldID.setText(UpdateWords.targetWords.get(0).get(2).toString());
			UpdateWords.textFieldUpForeign.setText(UpdateWords.targetWords.get(0).get(0).toString());
			UpdateWords.textFieldUpNative.setText(UpdateWords.targetWords.get(0).get(1).toString());
			UpdateWords.position=0;
		}
	};
	
	static ActionListener nextRecordListener = (e)->{ //next record of the search
		try{
			if(UpdateWords.position<UpdateWords.targetWords.size()-1) UpdateWords.position++;
			 //when you press the next button increase the position  
			if (UpdateWords.targetWords.size()-1>=UpdateWords.position ){//next result
				UpdateWords.textFieldID.setText(UpdateWords.targetWords.get(UpdateWords.position).get(2).toString());
				UpdateWords.textFieldUpForeign.setText(UpdateWords.targetWords.get(UpdateWords.position).get(0).toString());
				UpdateWords.textFieldUpNative.setText(UpdateWords.targetWords.get(UpdateWords.position).get(1).toString());
			}
			else{//last result
				UpdateWords.textFieldID.setText(UpdateWords.targetWords.get(UpdateWords.targetWords.size()-1).get(2).toString());
				UpdateWords.textFieldUpForeign.setText(UpdateWords.targetWords.get(UpdateWords.targetWords.size()-1).get(0).toString());
				UpdateWords.textFieldUpNative.setText(UpdateWords.targetWords.get(UpdateWords.targetWords.size()-1).get(1).toString());
			}
		}catch(IndexOutOfBoundsException e8){ //if you press next when the position is in the last place 
			JOptionPane.showMessageDialog(null, "No other results","Results end",JOptionPane.INFORMATION_MESSAGE);
		}
		
	};
	
	static ActionListener previousRecordListener = (e)->{ // previous record
		try{//when you press the previous button decrease the position
			if(UpdateWords.position>=1)UpdateWords.position--;
			if (UpdateWords.position>=0){//previous
				UpdateWords.textFieldID.setText(UpdateWords.targetWords.get(UpdateWords.position).get(2).toString());
				UpdateWords.textFieldUpForeign.setText(UpdateWords.targetWords.get(UpdateWords.position).get(0).toString());
				UpdateWords.textFieldUpNative.setText(UpdateWords.targetWords.get(UpdateWords.position).get(1).toString());
			}
			else{//first result
				UpdateWords.textFieldID.setText(UpdateWords.targetWords.get(0).get(2).toString());
				UpdateWords.textFieldUpForeign.setText(UpdateWords.targetWords.get(0).get(0).toString());
				UpdateWords.textFieldUpNative.setText(UpdateWords.targetWords.get(0).get(1).toString());
			}
		}catch(IndexOutOfBoundsException e4){
			JOptionPane.showMessageDialog(null, "No other results","Results end",JOptionPane.INFORMATION_MESSAGE);
			
		}
	};
	
	static ActionListener lastRecordListener= (e)->{ // last record
		if(UpdateWords.targetWords.isEmpty()){
			JOptionPane.showMessageDialog(null, "No words from your search","Empty result",JOptionPane.WARNING_MESSAGE);
		}
		else{
			UpdateWords.textFieldID.setText(UpdateWords.targetWords.get(UpdateWords.targetWords.size()-1).get(2).toString());
			UpdateWords.textFieldUpForeign.setText(UpdateWords.targetWords.get(UpdateWords.targetWords.size()-1).get(0).toString());
			UpdateWords.textFieldUpNative.setText(UpdateWords.targetWords.get(UpdateWords.targetWords.size()-1).get(1).toString());
			UpdateWords.position=UpdateWords.targetWords.size()-1;
		}
	};

	
	static ActionListener doUpdateListener = (e)->{ //update after search 
		try{
			String sheetNum=UpdateWords.textFieldID.getText();
			String[] splitSheetNum=sheetNum.split(" ");//separate id name with id code
			
			sh=wb.getSheet(splitSheetNum[0]);//take the sheet name without id of word
			row=sh.getRow(Integer.parseInt(splitSheetNum[1]));
			cellF=row.createCell(0);//initialize the cells
			cellN=row.getCell(2);// >>
			
			cellF.setCellValue(UpdateWords.textFieldUpForeign.getText());
			cellN.setCellValue(UpdateWords.textFieldUpNative.getText());
			
			fos= new FileOutputStream(FileChooser.path);
			wb.write(fos);
			fos.flush();
			fos.close();
			JOptionPane.showMessageDialog(null,"Update done","Update",JOptionPane.PLAIN_MESSAGE);
			modified=true;
			
		}catch(IOException e2){
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Access to input file failed","Error",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	};
	
	static ActionListener doDeleteListener = (e)->{ // delete after search 
		try{
			String sheetNum=UpdateWords.textFieldID.getText();//id format is SheetName(String)+ number of row
			String[] splitSheetNum=sheetNum.split(" "); // split of sheet name and id
			sh=wb.getSheet(splitSheetNum[0]); //take sheet name
			row=sh.getRow(Integer.parseInt(splitSheetNum[1])); //take num of row
			int rowIndex=Integer.parseInt(splitSheetNum[1]); // turn it in integer
			
			noOfRows=sh.getLastRowNum();
			int dialogButton;
			dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure ?","Warning",JOptionPane.YES_NO_OPTION);
			if (dialogButton ==JOptionPane.YES_OPTION){
				if(rowIndex>=1 && rowIndex<noOfRows){
					sh.shiftRows(rowIndex+1,noOfRows,-1);//shift the rows and the target row goes to the end
				}
				if(rowIndex==noOfRows){
						Row removingRow=sh.getRow(rowIndex);
			        if(removingRow!=null){
			        	sh.removeRow(removingRow);//remove the row
			        }
			    }
				fos= new FileOutputStream(FileChooser.path);
				wb.write(fos);
				fos.flush();
				fos.close();
				modified=true;
				int row=Words.table.getSelectedRow();
				int modelRow=Words.table.convertRowIndexToModel(row);
				Words.tableModel.removeRow(modelRow);//remove row from jtable
				
			}
			
		}catch( IOException e3){
			
			JOptionPane.showMessageDialog(null, "Access to input file failed","Error",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}catch(ArrayIndexOutOfBoundsException e4){
			e4.printStackTrace();
		}
	};
	//*****************TypeTrain Listeners*****************
	static ActionListener typeTrainCheckListener = (e)->{ 
		if(TypeTrain.textField2.getText().equals(TypeTrain.answer)){ //check if the answer is correct
			TypeTrain.textField2.setForeground(new Color(0, 255, 0)); //set Green if is correct
			TypeTrain.textField2.setEditable(false);
			turns++;
			correct++;
			TypeTrain.progressBar.setValue((correct*100)/turns);
		}
		else{
			TypeTrain.textField2.setForeground(new Color(255, 0, 0)); //set red
			TypeTrain.textField2.setEditable(false);
			TypeTrain.textField2.setText(TypeTrain.textField2.getText()+"   correct: "+TypeTrain.answer); //view of correct answer
			turns++;
			TypeTrain.progressBar.setValue((correct*100)/turns);
		}
		
	};
	
	static ActionListener typeTrainNextListener = (e)->{ //next word 
		if(TypeTrain.textField2.isEditable()==true){
			turns++;
		}
		TypeTrain.textField2.setEditable(true); //editable again
		TypeTrain.textField2.setForeground(new Color(0, 0, 0)); //black text color
		TypeTrain.textField2.setText("");
		
		randomSheet=ThreadLocalRandom.current().nextInt(0,4);//random take number from 0 to 3 
		
		sh=wb.getSheet(sheets[randomSheet]);//random sheet from sheets[]
		noOfRows=sh.getLastRowNum();//get the count of rows for specific sheet
		
		randomIndex = ThreadLocalRandom.current().nextInt(1, noOfRows + 1);//get random row
		
		TypeTrain.textField1.setText(sh.getRow(randomIndex).getCell(0).toString());//fill the question from random row
		TypeTrain.answer=sh.getRow(randomIndex).getCell(2).toString();//take the answer
		
		if(Model.turns%2==0){ //alternate the demanded word
			TypeTrain.textField1.setText(sh.getRow(randomIndex).getCell(2).toString());
			TypeTrain.answer=sh.getRow(randomIndex).getCell(0).toString();
		}
		else{
			TypeTrain.textField1.setText(sh.getRow(randomIndex).getCell(0).toString());
			TypeTrain.answer=sh.getRow(randomIndex).getCell(2).toString();
		}
	};
	
	static class typeTrainKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {}
		
		//hot keys for type train
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER){				
				TypeTrain.btnCheck.doClick();//enter->btnCheck
			}
			else if (e.getKeyCode()==KeyEvent.VK_CONTROL){				
				TypeTrain.buttonNext.doClick();
			}
			else if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
				TypeTrain.mntmClearScore.doClick();
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {}
		
	}
	
	//*****************MultipleTrain Listeners*****************
	static ActionListener multipleTrainNext = (e)->{// take a demanded word randomly
			
		randomSheet=ThreadLocalRandom.current().nextInt(0,4);//random take number from 0 to 3 
		
		sh=wb.getSheet(sheets[randomSheet]);//random sheet from sheets[]
		
		noOfRows=sh.getLastRowNum();
		
		if(turns%2==0){ // query is for Native 
			randomIndex = ThreadLocalRandom.current().nextInt(1, noOfRows + 1);
			MultipleTrain.textField.setText(sh.getRow(randomIndex).getCell(0).toString());
			MultipleTrain.correctAnswer = sh.getRow(randomIndex).getCell(2).toString();
			MultipleTrain.answer1= sh.getRow(randomIndex).getCell(2).toString();
		
			randomIndex = ThreadLocalRandom.current().nextInt(1, noOfRows + 1); // take a wrong answer
			MultipleTrain.answer2 = sh.getRow(randomIndex).getCell(2).toString();
		
			randomIndex = ThreadLocalRandom.current().nextInt(1, noOfRows + 1); // take a wrong answer
			MultipleTrain.answer3 = sh.getRow(randomIndex).getCell(2).toString();
			
			while(true){//to take three different choices
				if(!(MultipleTrain.answer1.equals(MultipleTrain.answer2))&&!(MultipleTrain.answer2.equals(MultipleTrain.answer3))
						&&!(MultipleTrain.answer1.equals(MultipleTrain.answer3))){
					break;
				}
				else{
					randomIndex = ThreadLocalRandom.current().nextInt(1, noOfRows + 1); // take a wrong answer
					MultipleTrain.answer2 = sh.getRow(randomIndex).getCell(2).toString();
					
					randomIndex = ThreadLocalRandom.current().nextInt(1, noOfRows + 1); // take a wrong answer
					MultipleTrain.answer3 = sh.getRow(randomIndex).getCell(2).toString();
				}
			}
			
		}
		else{ //query is for foreign
			randomIndex = ThreadLocalRandom.current().nextInt(1, noOfRows + 1);
			MultipleTrain.textField.setText(sh.getRow(randomIndex).getCell(2).toString());
			MultipleTrain.correctAnswer = sh.getRow(randomIndex).getCell(0).toString();
			MultipleTrain.answer1= sh.getRow(randomIndex).getCell(0).toString();
		    
			randomIndex = ThreadLocalRandom.current().nextInt(1, noOfRows + 1);// take a wrong answer
			MultipleTrain.answer2 = sh.getRow(randomIndex).getCell(0).toString();
			
			randomIndex = ThreadLocalRandom.current().nextInt(1, noOfRows + 1);// take an other wrong answer
			MultipleTrain.answer3 = sh.getRow(randomIndex).getCell(0).toString();
			
			while(true){//to take three different choices
				if(!(MultipleTrain.answer1.equals(MultipleTrain.answer2))&&!(MultipleTrain.answer2.equals(MultipleTrain.answer3))
						&&!(MultipleTrain.answer1.equals(MultipleTrain.answer3))){
					break;
				}
				else{
					randomIndex = ThreadLocalRandom.current().nextInt(1, noOfRows + 1); // take a wrong answer
					MultipleTrain.answer2 = sh.getRow(randomIndex).getCell(0).toString();
					
					randomIndex = ThreadLocalRandom.current().nextInt(1, noOfRows + 1); // take a wrong answer
					MultipleTrain.answer3 = sh.getRow(randomIndex).getCell(0).toString();
				}
			}
			
		}
		List <String> answers = new ArrayList<>(); // add the answers in ArrayList
		answers.add(MultipleTrain.answer1);
		answers.add(MultipleTrain.answer2);
		answers.add(MultipleTrain.answer3);
		Collections.sort(answers);                 // sort the answers by name
		MultipleTrain.textFieldChoice1.setText(answers.get(0));
		MultipleTrain.textFieldChoice2.setText(answers.get(1));
		MultipleTrain.textFieldChoice3.setText(answers.get(2));
		MultipleTrain.textFieldChoice1.setForeground(new Color(0, 0, 0));
		MultipleTrain.textFieldChoice2.setForeground(new Color(0, 0, 0));
		MultipleTrain.textFieldChoice3.setForeground(new Color(0, 0, 0));
		

	};
	//first choice
	static class multipleChoiceOneMouseListener implements MouseListener{
		public void mouseClicked(MouseEvent e) { //when you clicked with your mouse in textfield turn red or green depends from your answer
			turns++;
			if(MultipleTrain.correctAnswer.equals(MultipleTrain.textFieldChoice1.getText())){//correct->green
				MultipleTrain.textFieldChoice1.setForeground(new Color(0, 255, 0));
				correct++;
			}
			else{//wrong->red
				MultipleTrain.textFieldChoice1.setForeground(new Color(255, 0, 0));
			}
			MultipleTrain.progressBar.setValue((correct*100)/turns);
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
	//second 
	static class multipleChoiceTwoMouseListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			turns++;
			if(MultipleTrain.correctAnswer.equals(MultipleTrain.textFieldChoice2.getText())){
				MultipleTrain.textFieldChoice2.setForeground(new Color(0, 255, 0));
				correct++;
			}
			else{
				MultipleTrain.textFieldChoice2.setForeground(new Color(255, 0, 0));
			}

			MultipleTrain.progressBar.setValue((correct*100)/turns);
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
	//third
	static class multipleChoiceThreeMouseListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			turns++;
			if(MultipleTrain.correctAnswer.equals(MultipleTrain.textFieldChoice3.getText())){
				MultipleTrain.textFieldChoice3.setForeground(new Color(0, 255, 0));
				correct++;
			}
			else{
				MultipleTrain.textFieldChoice3.setForeground(new Color(255, 0, 0));
			}

			MultipleTrain.progressBar.setValue((correct*100)/turns);
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
	
	//General Train Listeners*****************
	static ActionListener resetScore = (e)->{
		turns=0;
		correct=0;
		
	};
	static ActionListener importFileListener=(e)->{
		String temp="temp";
		VocabularyApp.filechooserframe=new FileChooser(temp);
		modified=true;
	};
	
	//***************** Words Listeners*****************
	static ActionListener SortByNativeListener=(e)->{
		Words.table.setAutoCreateRowSorter(true);
		Words.table.getRowSorter().toggleSortOrder(2);//sort by column 2
		for(int i=0; i<Words.table.getRowCount();i++){//correlate view with model
			Words.table.getRowSorter().convertRowIndexToModel(i);
		}
		
	};
	
	static ActionListener SortByForeignListener=(e)->{
		Words.table.setAutoCreateRowSorter(true);
		Words.table.getRowSorter().toggleSortOrder(1);
		for(int i=0; i<Words.table.getRowCount();i++){
			Words.table.getRowSorter().convertRowIndexToModel(i);
		}
		
	};
	
	static ActionListener SortByIDListener=(e)->{
		Words.table.setAutoCreateRowSorter(true);
		Words.table.getRowSorter().toggleSortOrder(0);
		for(int i=0; i<Words.table.getRowCount();i++){
			Words.table.getRowSorter().convertRowIndexToModel(i);
		}
		
	};
	
	static ActionListener HideForeignListener=(e)->{//hide column 1
		if(Words.countHideF%2==0){
			Words.table.getColumnModel().getColumn(1).setWidth(0);
			Words.table.getColumnModel().getColumn(1).setMinWidth(0);
			Words.table.getColumnModel().getColumn(1).setMaxWidth(0); 
		}
		else{//if press the hide second time is visible again
			Words.table.getColumnModel().getColumn(1).setMinWidth(Words.table.getWidth()-101/2);
			Words.table.getColumnModel().getColumn(1).setMaxWidth(Integer.MAX_VALUE);
			Words.table.getColumnModel().getColumn(1).setWidth(Words.table.getWidth()-101/2);
		}
		Words.countHideF++;
	};
	
	static ActionListener HideNativeListener =(e)->{
		if(Words.countHideN%2==0){
			Words.table.getColumnModel().getColumn(2).setWidth(0);
			Words.table.getColumnModel().getColumn(2).setMinWidth(0);
			Words.table.getColumnModel().getColumn(2).setMaxWidth(0); 
		}
		else{
			Words.table.getColumnModel().getColumn(2).setMinWidth(Words.table.getWidth()-101/2);
			Words.table.getColumnModel().getColumn(2).setMaxWidth(Integer.MAX_VALUE);
			Words.table.getColumnModel().getColumn(2).setWidth(Words.table.getWidth()-101/2);

		}
		Words.countHideN++;
	};
	
	
	
	
	static class wordsDoubleClickMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {//choose specific word by double click to modify it
			if(e.getClickCount()==2){
				String target = Words.table.getValueAt(Words.table.getSelectedRow(), 1).toString();
				Search.foreignWord=target;
				VocabularyApp.wordsframe.setEnabled(false);
				VocabularyApp.updateframe.setVisible(true);
				Search.byForeign=true;
			}
		}

		@Override
		public void mousePressed(MouseEvent e){}

		@Override
		public void mouseReleased(MouseEvent e){}

		@Override
		public void mouseEntered(MouseEvent e){}

		@Override
		public void mouseExited(MouseEvent e){}
		
	}
	
	//************About Listeners***************
	static class AboutMouseListenerlinkedin implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {//access to specific site with click 
			try{
				Desktop.getDesktop().browse(new URI("www.linkedin.com/in/andreas-malliotakis-047289158"));
			}catch(IOException | URISyntaxException e10){
				e10.printStackTrace();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
	static class AbboutMouseListenerGitHub implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			try{
				Desktop.getDesktop().browse(new URI("https://github.com/andrewMal"));
			}catch(IOException | URISyntaxException e10){
				e10.printStackTrace();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
	}
	

}

