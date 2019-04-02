package copyboostYourVoc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;

import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class UpdateWords extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTextField textFieldUpForeign;
	static JTextField textFieldUpNative;
	static JTextField textFieldID;
	static ArrayList<ArrayList<String>> targetWords;
	static ArrayList<String> targetWord;
	static int position;
	
	public UpdateWords() {
		setTitle("Update/Delete");
		addWindowListener(new Model.UpdateWinAdapter());
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 543, 387);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Foreign");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setForeground(SystemColor.menu);
		lblNewLabel.setBounds(12, 69, 75, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Native");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setForeground(SystemColor.menu);
		lblNewLabel_1.setBounds(12, 115, 75, 29);
		contentPane.add(lblNewLabel_1);
		
		textFieldUpForeign = new JTextField();
		textFieldUpForeign.setFont(new Font("Tahoma", Font.BOLD, 17));
		textFieldUpForeign.setBounds(104, 67, 223, 32);
		contentPane.add(textFieldUpForeign);
		textFieldUpForeign.setColumns(10);
		
		textFieldUpNative = new JTextField();
		textFieldUpNative.setFont(new Font("Tahoma", Font.BOLD, 17));
		textFieldUpNative.setBounds(104, 112, 223, 32);
		contentPane.add(textFieldUpNative);
		textFieldUpNative.setColumns(10);
		
		JButton btnFirst = new JButton("");
		btnFirst.addActionListener(Model.firstRecordListener);
		
		btnFirst.setIcon(new ImageIcon(UpdateWords.class.getResource("/copyboostYourVoc/First record.png")));
		btnFirst.setBounds(12, 177, 49, 40);
		contentPane.add(btnFirst);
		
		JButton btnPrevious = new JButton("");
		btnPrevious.addActionListener(Model.previousRecordListener);
		btnPrevious.setIcon(new ImageIcon(UpdateWords.class.getResource("/copyboostYourVoc/Previous_record.png")));
		btnPrevious.setBounds(70, 177, 49, 40);
		contentPane.add(btnPrevious);
		
		JButton btnNext = new JButton("");
		btnNext.addActionListener(Model.nextRecordListener);
		
		btnNext.setIcon(new ImageIcon(UpdateWords.class.getResource("/copyboostYourVoc/Next_track.png")));
		btnNext.setBounds(131, 177, 49, 40);
		contentPane.add(btnNext);
		
		JButton btnLast = new JButton("");
		btnLast.addActionListener(Model.lastRecordListener);
		
		btnLast.setIcon(new ImageIcon(UpdateWords.class.getResource("/copyboostYourVoc/Last_Record.png")));
		btnLast.setBounds(192, 177, 49, 40);
		contentPane.add(btnLast);
		
		JButton btnUpUpdate = new JButton("Update");
		btnUpUpdate.setForeground(SystemColor.activeCaption);
		btnUpUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpUpdate.addActionListener(Model.doUpdateListener);
		
		btnUpUpdate.setBounds(285, 177, 97, 40);
		contentPane.add(btnUpUpdate);
		
		JButton btnUpDelete = new JButton("Delete");
		btnUpDelete.setForeground(SystemColor.activeCaption);
		btnUpDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpDelete.addActionListener(Model.doDeleteListener);
		
		btnUpDelete.setBounds(394, 177, 97, 40);
		contentPane.add(btnUpDelete);
		
		JButton btnUpClose = new JButton("");
		btnUpClose.setIcon(new ImageIcon("D:\\downloads\\Exit.png"));
		btnUpClose.addActionListener(HelperUtil.updateCloseListener);
			
		btnUpClose.setBounds(438, 295, 75, 32);
		contentPane.add(btnUpClose);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(SystemColor.menu);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblId.setBounds(12, 30, 75, 23);
		contentPane.add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Tahoma", Font.BOLD, 17));
		textFieldID.setEditable(false);
		textFieldID.setBounds(104, 28, 137, 32);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
	}
}
