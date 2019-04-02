package copyboostYourVoc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Words extends JFrame {

	private JPanel contentPane;
	static JTable table;
	static DefaultTableModel tableModel;
	String[] columnNames;
	static int countHideN=0;
	static int countHideF=0;
	

	public Words() {
		addWindowListener(new Model.WordsWinAdapter());

		setTitle("List of Words");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 923);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(HelperUtil.wordsCloseListener);
			
		mnFile.add(mntmExit);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenu mnSort = new JMenu("Sort");
		mnTools.add(mnSort);
		
		JMenuItem mntmSortByNative = new JMenuItem("Sort by Native Word");
		mntmSortByNative.addActionListener(Model.SortByNativeListener);		
		mnSort.add(mntmSortByNative);
		
		JMenuItem mntmSortByForeign = new JMenuItem("Sort by Foreign Word");
		mntmSortByForeign.addActionListener(Model.SortByForeignListener);
		mnSort.add(mntmSortByForeign);
		
		JMenuItem mntmSortById = new JMenuItem("Sort by ID");
		mntmSortById.addActionListener(Model.SortByIDListener);
		
		mnSort.add(mntmSortById);
		
		JMenu mnHide = new JMenu("Hide");
		mnTools.add(mnHide);
		
		JMenuItem mntmHideNativeWords = new JMenuItem("Hide Native words");
		mntmHideNativeWords.addActionListener(Model.HideNativeListener);
		mnHide.add(mntmHideNativeWords);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Hide Foreign Words");
		mntmNewMenuItem.addActionListener(Model.HideForeignListener);
		
		mnHide.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String col[]={"ID","Foreign","Native"};
		tableModel = new DefaultTableModel(col,0){
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }

			@Override
			public void removeRow(int row) {
				// TODO Auto-generated method stub
				super.removeRow(row);
			}
			
			
		};	
		table = new JTable(tableModel);

		table.setToolTipText("If you want to modify or delete a word, do double click.");
		table.setForeground(SystemColor.textHighlight);
		table.setFont(new Font("Tahoma", Font.BOLD, 15));
		table.addMouseListener(new Model.wordsDoubleClickMouseListener());
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(0).setMaxWidth(101);
		table.getColumnModel().getColumn(0).setMinWidth(101);
		table.getColumnModel().getColumn(0).setWidth(101);



		table.setBounds(12, 13, 549, 900);
		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(-2, 0, 573, 822);
		contentPane.add(scrollPane);
		
		JButton closeButton = new JButton("");
		closeButton.addActionListener(HelperUtil.wordsCloseListener);
				
		closeButton.setIcon(new ImageIcon(Words.class.getResource("/copyboostYourVoc/Exit.png")));
		closeButton.setBounds(476, 824, 97, 25);
		contentPane.add(closeButton);
		
		JButton btnHideNatives = new JButton("Hide Natives");
		btnHideNatives.addActionListener(Model.HideNativeListener);
		
		btnHideNatives.setForeground(SystemColor.activeCaption);
		btnHideNatives.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnHideNatives.setBounds(350, 824, 114, 25);
		contentPane.add(btnHideNatives);
		
		JButton btnHideForeigns = new JButton("Hide Foreigns");
		btnHideForeigns.addActionListener(Model.HideForeignListener);
		
		btnHideForeigns.setForeground(SystemColor.activeCaption);
		btnHideForeigns.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnHideForeigns.setBounds(210, 824, 128, 25);
		contentPane.add(btnHideForeigns);
	}
}
