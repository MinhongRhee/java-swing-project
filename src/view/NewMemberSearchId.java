package view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.NewMemberDao;
import model.NewMemberVo;

public class NewMemberSearchId extends JFrame
	  implements ActionListener {
	
	// Component
	JPanel      p1, p2, p3, p4, p5, bottomPane;
	JTextField  txtName, txtEid, txtNid, txtNid2;
	JComboBox   cbEid;
	JButton     btnSearch, btnReset;
	
	NewMemberLogin newMemberLogin;
	
	// Layout
	GridBagLayout	    gb;
	GridBagConstraints  gbc;
	private JButton button;
	
	public NewMemberSearchId() {
		this.setTitle("아이디 찾기");
		
		initComponent();
		
		// JTable 설정
		this.setLocation(350, 250);
		this.setSize(430, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public NewMemberSearchId(NewMemberLogin newMemberLogin) {
		this();
		
		this.newMemberLogin = newMemberLogin;
	}
	
	private void initComponent() {
		// 레이아웃 설정
		gb = new GridBagLayout();
		getContentPane().setLayout(gb);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		// 패널 배치
		p1 = new JPanel();
		gbAdd( p1, 0, 0, 6, 1);

		// 이름
		JLabel     lblName =  new JLabel("이름");
		JPanel       pName =  new JPanel( new FlowLayout(FlowLayout.LEFT) );
		txtName            =  new JTextField(10);
		pName.add(txtName);
		gbAdd( lblName, 1, 1, 1, 1 );
		gbAdd(   pName, 2, 1, 1, 1 );
		
		// 주민등록번호
		JLabel      lblNid =  new JLabel("주민등록번호");
		JPanel        pNid =  new JPanel( new FlowLayout(FlowLayout.LEFT) );
		txtNid             =  new JTextField(10);
		txtNid2            =  new JPasswordField(10);
		pNid.add(txtNid);
		pNid.add(new JLabel("-"));
		pNid.add(txtNid2);
		gbAdd( lblNid, 1, 2, 1, 1 );
		gbAdd(   pNid, 2, 2, 1, 1 );

		// 이메일
		JLabel      lblEid =  new JLabel("이메일");
		JPanel      pEmail =  new JPanel( new FlowLayout(FlowLayout.LEFT) );
		txtEid             =  new JTextField(10);
		String [] arrEid = { "naver.com", "gmail.com", "daum.net", "nate.com" };
		cbEid            =  new JComboBox(arrEid);
		pEmail.add(txtEid);
		pEmail.add(new JLabel("@"));
		pEmail.add(cbEid);
		gbAdd( lblEid, 1, 3, 1, 1);
		gbAdd( pEmail, 2, 3, 1, 1);
		
		// 버튼
		bottomPane = new JPanel();
		btnSearch = new JButton("인증하기");
		bottomPane.add(btnSearch);
		
		btnReset  =  new JButton("취소");
		bottomPane.add( btnReset );
		gbAdd( bottomPane, 2, 5, 1, 1);
		
		this.btnSearch.addActionListener( this );
		this.btnReset.addActionListener( this );
	}

	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx	    = x;
		gbc.gridy	    = y;
		gbc.gridwidth	= w;
		gbc.gridheight  = h;
		gb.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		getContentPane().add( c, gbc );
		
	}

	
	// 기능부여
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand() + " 버튼 클릭");
		String btnText = e.getActionCommand();
		switch( btnText ) {
		case "인증하기" :
			searchId();
			break;
		case "취소" :
			resetInfo();
			break;
		}
		
	}
	
	private void searchId() {
		NewMemberDao nDao = new NewMemberDao();
		NewMemberVo  vo   = getViewData();

		String   username = vo.getUsername();
		String   natid    = vo.getNatid();
		String   email    = vo.getEmail();
		
		if( nDao.getSId( username, natid, email ) != null ) {
			JOptionPane.showMessageDialog( null, "아이디 : " + nDao.getSId( username, natid, email )
					, "찾은 아이디", JOptionPane.INFORMATION_MESSAGE);
		} else {
			this.txtName.setText("");
			this.txtNid.setText("");
			this.txtNid2.setText("");
			this.txtEid.setText("");
			String msg = "입력하신 정보가 일치하지 않습니다";
			JOptionPane.showMessageDialog(null, msg
					, "일치하지 않는 정보", JOptionPane.ERROR_MESSAGE );
		}
		nDao.close();
		
	}

	private NewMemberVo getViewData() {
		String username = this.txtName.getText(); 
		String natid = this.txtNid.getText() + "-" + this.txtNid2.getText();
		String email = this.txtEid.getText() + "@" + (String) this.cbEid.getSelectedItem();
		NewMemberVo vo = new NewMemberVo( username, natid, email );
		return vo;
	}

	private void resetInfo() {
		this.dispose();
		
	}

//	public static void main(String[] args) {
//		new NewMemberSearchId();
}

