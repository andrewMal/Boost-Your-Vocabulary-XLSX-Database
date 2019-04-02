package copyboostYourVoc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import java.awt.Font;
import javax.swing.JTextArea;

//about window , some details about the app 
public class About extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public About() {
		setResizable(false);
		setTitle("About");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 501);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAboutClose = new JButton("");
		btnAboutClose.addActionListener(HelperUtil.aboutCloseListener);
			
		btnAboutClose.setIcon(new ImageIcon(About.class.getResource("/copyboostYourVoc/Exit.png")));
		btnAboutClose.setBounds(518, 404, 64, 49);
		contentPane.add(btnAboutClose);
		
		JLabel lblRrrr = new JLabel("Version 0.1 ");
		lblRrrr.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblRrrr.setForeground(SystemColor.menu);
		lblRrrr.setBounds(12, 13, 185, 57);
		contentPane.add(lblRrrr);
		
		JTextPane txtpnboostYourVocabulary = new JTextPane();
		txtpnboostYourVocabulary.setEditable(false);
		txtpnboostYourVocabulary.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtpnboostYourVocabulary.setForeground(SystemColor.menu);
		txtpnboostYourVocabulary.setBackground(SystemColor.activeCaption);
		txtpnboostYourVocabulary.setText("\"Boost your vocabulary\" is open source, easy to use application  which help you to train your own choice of words while you can update your database evrerytime you want.");
		txtpnboostYourVocabulary.setBounds(12, 119, 513, 119);
		contentPane.add(txtpnboostYourVocabulary);
		
		JTextArea txtrCreatedByAndreas = new JTextArea();
		txtrCreatedByAndreas.setEditable(false);
		txtrCreatedByAndreas.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 18));
		txtrCreatedByAndreas.setBackground(SystemColor.activeCaption);
		txtrCreatedByAndreas.setLineWrap(true);
		txtrCreatedByAndreas.setText("Created by Andreas Malliotakis  ");
		txtrCreatedByAndreas.setBounds(12, 251, 336, 49);
		contentPane.add(txtrCreatedByAndreas);
		
		JLabel lblLinkedin = new JLabel("Linkedin");
		lblLinkedin.setIcon(new ImageIcon(About.class.getResource("/copyboostYourVoc/in.PNG")));
		lblLinkedin.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblLinkedin.addMouseListener(new Model.AboutMouseListenerlinkedin());//access to linkedin via mouseListener
		
		lblLinkedin.setBounds(12, 313, 145, 34);
		contentPane.add(lblLinkedin);
		
		JLabel lblGithub = new JLabel("GitHub");
		lblGithub.setIcon(new ImageIcon(About.class.getResource("/copyboostYourVoc/git.png")));
		lblGithub.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblGithub.addMouseListener(new Model.AbboutMouseListenerGitHub());//access to GitHub via mouseListener
		
		lblGithub.setBounds(12, 360, 128, 34);
		contentPane.add(lblGithub);
	}
}
