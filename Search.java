package copyboostYourVoc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Search extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTextField textFieldForeignS;
	static JTextField textFieldNativeS;
	static String foreignWord;
	static String nativeWord;
	static boolean byForeign;

	
	public Search() {
		addWindowListener(new Model.SearchWindowAdapter());
		
		setResizable(false);
		setTitle("Search");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 649, 405);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSearchByForeign = new JLabel("Search by Foreign word");
		lblSearchByForeign.setForeground(SystemColor.menu);
		lblSearchByForeign.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSearchByForeign.setBounds(167, 52, 239, 25);
		contentPane.add(lblSearchByForeign);
		
		JLabel lblSearchByNative = new JLabel("Search by Native word");
		lblSearchByNative.setForeground(SystemColor.menu);
		lblSearchByNative.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSearchByNative.setBounds(167, 168, 204, 25);
		contentPane.add(lblSearchByNative);
		
		textFieldForeignS = new JTextField();
		textFieldForeignS.setFont(new Font("Tahoma", Font.BOLD, 17));
		textFieldForeignS.setBounds(97, 90, 354, 41);
		contentPane.add(textFieldForeignS);
		textFieldForeignS.setColumns(10);
		
		textFieldNativeS = new JTextField();
		textFieldNativeS.setFont(new Font("Tahoma", Font.BOLD, 17));
		textFieldNativeS.setBounds(101, 206, 350, 41);
		contentPane.add(textFieldNativeS);
		textFieldNativeS.setColumns(10);
		
		JButton btnSearchF = new JButton("");
		btnSearchF.addActionListener(Model.searchFListener);
				
		btnSearchF.setIcon(new ImageIcon(Search.class.getResource("/copyboostYourVoc/Find.png")));
		btnSearchF.setBounds(560, 90, 59, 41);
		contentPane.add(btnSearchF);
		
		JButton btnSearchN = new JButton("");
		btnSearchN.addActionListener(Model.searchNListener);
				
		btnSearchN.setIcon(new ImageIcon(Search.class.getResource("/copyboostYourVoc/Find.png")));
		btnSearchN.setBounds(560, 206, 59, 41);
		contentPane.add(btnSearchN);
		
		JButton buttonExitSearch = new JButton("");
		buttonExitSearch.addActionListener(HelperUtil.searchCloseListener);
			
		buttonExitSearch.setIcon(new ImageIcon("D:\\downloads\\Exit.png"));
		buttonExitSearch.setBounds(542, 304, 77, 41);
		contentPane.add(buttonExitSearch);
	}
}
