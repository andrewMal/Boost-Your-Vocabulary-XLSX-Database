package copyboostYourVoc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1526692153713113920L;
	private JPanel contentPane;
	
	
	public MainMenu(){
		setResizable(false);
			
		setTitle("Boost Your Vocubulary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 512);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmImportMenuItem = new JMenuItem("Import excel file");
		mntmImportMenuItem.addActionListener(Model.importFileListener);// import other xlsx file if you want
		mnFile.add(mntmImportMenuItem);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(HelperUtil.mainMenuCloseListener);//action Listener (helper class)
		mnFile.add(mntmExit);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmYourVocabulary = new JMenuItem("Your Vocabulary");
		mntmYourVocabulary.addActionListener(HelperUtil.wordsOpenListener);//action Listener (helper class)
		
		mnMenu.add(mntmYourVocabulary);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Insert Word");
		mntmNewMenuItem_1.addActionListener(HelperUtil.insertOpenListener);
		
		mnMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Modify");
		mntmNewMenuItem_2.addActionListener(HelperUtil.updateOpenListener);
		mnMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmTraining = new JMenuItem("Training");
		mntmTraining.addActionListener(HelperUtil.trainOpenListener);
		
		mnMenu.add(mntmTraining);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setForeground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVocab = new JLabel("Boost your Vocabulary ");
		lblVocab.setForeground(new Color(240, 240, 240));
		lblVocab.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblVocab.setHorizontalAlignment(SwingConstants.CENTER);
		lblVocab.setBounds(144, 13, 315, 45);
		contentPane.add(lblVocab);
		
		JButton btnInsertNewWord = new JButton("Insert new word");
		btnInsertNewWord.addActionListener(HelperUtil.insertOpenListener);
		
		btnInsertNewWord.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnInsertNewWord.setForeground(SystemColor.activeCaption);
		btnInsertNewWord.setBounds(172, 152, 259, 45);
		contentPane.add(btnInsertNewWord);
		
		JButton btnMainMenuUpDel = new JButton("Update/Delete");
		btnMainMenuUpDel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMainMenuUpDel.setForeground(SystemColor.activeCaption);
		btnMainMenuUpDel.addActionListener(HelperUtil.updateOpenListener);
		
		btnMainMenuUpDel.setBounds(172, 210, 259, 45);
		contentPane.add(btnMainMenuUpDel);
		
		JButton btnMainMenuTraining = new JButton("Training");
		btnMainMenuTraining.addActionListener(HelperUtil.trainOpenListener);
				
		btnMainMenuTraining.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMainMenuTraining.setForeground(SystemColor.activeCaption);
		btnMainMenuTraining.setBounds(172, 268, 259, 45);
		contentPane.add(btnMainMenuTraining);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(HelperUtil.mainMenuCloseListener);
				
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExit.setForeground(SystemColor.activeCaption);
		btnExit.setBounds(172, 326, 259, 45);
		contentPane.add(btnExit);
		
		JButton btnAbout = new JButton("");
		btnAbout.addActionListener(HelperUtil.aboutOpenListener);
		
		btnAbout.setBackground(SystemColor.activeCaption);
		btnAbout.setToolTipText("About");
		btnAbout.setIcon(new ImageIcon(MainMenu.class.getResource("/copyboostYourVoc/About.png")));
		btnAbout.setBounds(524, 381, 68, 45);
		contentPane.add(btnAbout);
		
		JButton btnNewButton = new JButton("Your Vocabulary");
		btnNewButton.addActionListener(HelperUtil.wordsOpenListener);
					
		btnNewButton.setForeground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(172, 94, 259, 45);
		contentPane.add(btnNewButton);
	}
}
