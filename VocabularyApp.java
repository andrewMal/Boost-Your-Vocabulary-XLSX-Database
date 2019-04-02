package copyboostYourVoc;

import java.awt.EventQueue;

public class VocabularyApp {
	
	static MainMenu mainframe;
	static UpdateWords updateframe;
	static TypeTrain typetrainframe;
	static InsertWords insertframe;
	static About aboutframe;
	static Search searchframe;
	static MultipleTrain multipleframe;
	static FileChooser filechooserframe;
	static Words wordsframe;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					filechooserframe=new FileChooser(FileChooser.pathOfSer);
					
					
					mainframe = new MainMenu();
					mainframe.setLocationRelativeTo(null);
					mainframe.setVisible(true);
					
					wordsframe = new Words();
					wordsframe.setLocationRelativeTo(null);
					wordsframe.setVisible(false);
					
					updateframe = new UpdateWords();
					updateframe.setLocationRelativeTo(null);
					updateframe.setVisible(false);
					
					typetrainframe = new TypeTrain();
					typetrainframe.setLocationRelativeTo(null);
					typetrainframe.setVisible(false);
					
					insertframe = new InsertWords();
					insertframe.setLocationRelativeTo(null);
					insertframe.setVisible(false);
					
					aboutframe = new About();
					aboutframe.setLocationRelativeTo(null);
					aboutframe.setVisible(false);
					
					searchframe = new Search();
					searchframe.setLocationRelativeTo(null);
					searchframe.setVisible(false);
					
					multipleframe = new MultipleTrain();
					multipleframe.setLocationRelativeTo(null);
					multipleframe.setVisible(false);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
