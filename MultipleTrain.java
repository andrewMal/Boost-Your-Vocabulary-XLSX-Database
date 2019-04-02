package copyboostYourVoc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class MultipleTrain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JPanel contentPane;
	static JTextField textField;
	static String correctAnswer;
	static String answer1;
	static String answer2;
	static String answer3;
	List <String> answers;
	static JTextField textFieldChoice1;
	static JTextField textFieldChoice2;
	static JTextField textFieldChoice3;
	static JProgressBar progressBar;

	
	public MultipleTrain() {
		setResizable(false);
		setTitle("Multiple Training");
		addWindowListener(new Model.MultipleTrainWinAdapter());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 451);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(HelperUtil.multipleCloseListener);
		mnFile.add(mntmNewMenuItem);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenuItem mntmPickAWord = new JMenuItem("Pick a word");
		mntmPickAWord.addActionListener(Model.multipleTrainNext);
		mnTools.add(mntmPickAWord);
		
		JMenuItem mntmResetScore = new JMenuItem("Reset Score");
		mntmResetScore.addActionListener(Model.resetScore);
		
		mnTools.add(mntmResetScore);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setBounds(274, 97, 293, 50);
		textField.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setColumns(10);
		
		JLabel lblMultiplechoiceTrainning = new JLabel("MultipleChoice Trainning");
		lblMultiplechoiceTrainning.setBounds(310, 13, 222, 34);
		lblMultiplechoiceTrainning.setForeground(SystemColor.menu);
		lblMultiplechoiceTrainning.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JButton buttonNext = new JButton("Pick a word");
		buttonNext.setBounds(355, 281, 131, 34);
		buttonNext.setForeground(new Color(153, 180, 209));
		buttonNext.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonNext.addActionListener(Model.multipleTrainNext);
		buttonNext.setIcon(null);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(708, 322, 89, 43);
		button_1.addActionListener(HelperUtil.multipleCloseListener);
			
		button_1.setIcon(new ImageIcon(MultipleTrain.class.getResource("/copyboostYourVoc/Exit.png")));
		

		
		textFieldChoice1 = new JTextField();
		
		textFieldChoice1.setBounds(19, 203, 255, 50);
		textFieldChoice1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldChoice1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		textFieldChoice1.addMouseListener(new Model.multipleChoiceOneMouseListener());
				
				

		textFieldChoice1.setEditable(false);
		textFieldChoice1.setColumns(10);
		
		textFieldChoice2 = new JTextField();
		textFieldChoice2.setBounds(293, 203, 255, 50);
		textFieldChoice2.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldChoice2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		textFieldChoice2.addMouseListener(new Model.multipleChoiceTwoMouseListener());
				

		textFieldChoice2.setEditable(false);
		textFieldChoice2.setColumns(10);
		
		textFieldChoice3 = new JTextField();
		textFieldChoice3.setBounds(567, 203, 255, 50);
		textFieldChoice3.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldChoice3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		textFieldChoice3.addMouseListener(new Model.multipleChoiceThreeMouseListener());
		
		textFieldChoice3.setEditable(false);
		textFieldChoice3.setColumns(10);
		
		JLabel label = new JLabel("Percentage of correct answers");
		label.setBounds(5, 253, 218, 32);
		label.setForeground(SystemColor.menu);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		JLabel lblNewLabel = new JLabel("(A)");
		lblNewLabel.setBounds(120, 160, 56, 34);
		lblNewLabel.setForeground(SystemColor.menu);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("(B)");
		lblNewLabel_1.setBounds(393, 160, 56, 34);
		lblNewLabel_1.setForeground(SystemColor.menu);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel("(C)");
		lblNewLabel_2.setBounds(680, 156, 56, 34);
		lblNewLabel_2.setForeground(SystemColor.menu);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(12, 291, 181, 24);
		
		
		contentPane.setLayout(null);
		contentPane.add(progressBar);
		contentPane.add(textField);
		contentPane.add(lblMultiplechoiceTrainning);
		contentPane.add(buttonNext);
		contentPane.add(button_1);
		contentPane.add(progressBar);
		contentPane.add(textFieldChoice1);
		contentPane.add(textFieldChoice2);
		contentPane.add(textFieldChoice3);
		contentPane.add(label);
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel_2);
		
	}
}
