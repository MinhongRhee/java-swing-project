package view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
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

public class NewMemberSearchPasswd extends JFrame
	   implements ActionListener {
	
	// Component
	JPanel			p1, p2, p3, p4, p5, bottomPane;
	JTextField		txtId, txtName, txtNid, txtTel, txtTel2, txtTel3;
	JPasswordField  txtNid2;
	JComboBox		cbTel1;
	JButton			btnSpwd, btnReset;
	
	// Layout
		GridBagLayout	    gb;
		GridBagConstraints  gbc;
		private JButton button;
	
	NewMemberLogin newMemberLogin;

	public NewMemberSearchPasswd() {
		this.setTitle("비밀번호 찾기");
		
		initComponent();
		
		// JTable 설정
		this.setLocation(350, 250);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public NewMemberSearchPasswd(NewMemberLogin newMemberLogin) {
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
		
		// 아이디
		JLabel		lblId  =  new JLabel("아이디");
		JPanel        pId  =  new JPanel( new FlowLayout(FlowLayout.LEFT) );
		txtId			   =  new JTextField(10);
		pId.add(txtId);
		gbAdd ( lblId, 0, 1, 1, 1 );
		gbAdd (   pId, 1, 1, 2, 1 );
		
		// 이름
		JLabel    lblName = new JLabel("이름");
		JPanel      pName =  new JPanel( new FlowLayout(FlowLayout.LEFT) );
		txtName           = new JTextField(10);
		pName.add(txtName);
		gbAdd( lblName, 0, 2, 1, 1 );
		gbAdd(   pName, 1, 2, 2, 1 );
		
		// 주민등록번호
		JLabel      lblNid =  new JLabel("주민등록번호");
		JPanel        pNid =  new JPanel( new FlowLayout(FlowLayout.LEFT) );
		txtNid             =  new JTextField(10);
		txtNid2            =  new JPasswordField(10);
		pNid.add(txtNid);
		pNid.add(new JLabel("-"));
		pNid.add(txtNid2);
		
		gbAdd( lblNid,  0, 3, 1, 1 );
		gbAdd(   pNid,  1, 3, 6, 1 );
		
		// 휴대폰번호
		JLabel     lblTel  =  new JLabel("휴대폰번호");
		String [] arrTel1  = { "선택","(SKT)", "(KT)", "(LGT)", "(알뜰폰)" };
		JPanel       pTel  = new JPanel( new FlowLayout(FlowLayout.LEFT) );
		cbTel1             = new JComboBox(arrTel1);
		txtTel             = new JTextField(3);
		txtTel2            = new JTextField(4);
		txtTel3            = new JTextField(4);
		pTel.add(cbTel1);
		pTel.add(txtTel);
		pTel.add(new JLabel("-"));
		pTel.add(txtTel2);
		pTel.add(new JLabel("-"));
		pTel.add(txtTel3);
		gbAdd ( lblTel, 0, 4, 1, 1 );
		gbAdd (   pTel, 1, 4, 6, 1 );
		
		// 버튼
		bottomPane = new JPanel();
		btnSpwd = new JButton("비밀번호찾기");
		bottomPane.add(btnSpwd);
		
		btnReset  =  new JButton("취소");
		bottomPane.add( btnReset );
		gbAdd( bottomPane, 2, 5, 1, 1);
		
		this.btnSpwd.addActionListener( this );
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
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand() + " 버튼 클릭");
		String btnText = e.getActionCommand();
		switch( btnText ) {
		case "비밀번호찾기" :
			searchPasswd();
			break;
		case "취소" :
			resetInfo();
			break;
		}
		
	}
	
	// 비밀번호 찾기 함수
	public void searchPasswd() {
		NewMemberDao nDao = new NewMemberDao();
		
		
		NewMemberVo  vo   = getViewData();
		
		String   userid   = vo.getUserid();
		String   username = vo.getUsername();
		String   natid    = vo.getNatid();
		String   tel      = vo.getTel();
		if( nDao.checkPwd(userid, username, natid, tel) == null ) {
			this.txtId.setText("");
			this.txtName.setText("");
			this.txtNid.setText("");
			this.txtNid2.setText("");
			this.cbTel1.setSelectedIndex(0);
			this.txtTel.setText("");
			this.txtTel2.setText("");
			this.txtTel3.setText("");
			String msg = "입력하신 정보가 일치하지 않습니다";
			JOptionPane.showMessageDialog(null, msg
					, "일치하지 않는 정보", JOptionPane.ERROR_MESSAGE );
			// 비밀번호 찾기 버튼 클릭시 비밀번호 창 띄우기
		} else if ( nDao.checkPwd(userid, username, natid, tel) != null) {
		String passwd = nDao.checkPwd(userid, username, natid, tel).getPasswd();
		if ( userid.equals( nDao.checkPwd(userid, username, natid, tel).getUserid() ) 
		  && username.equals( nDao.checkPwd(userid, username, natid, tel).getUsername() )
		  && natid.equals( nDao.checkPwd(userid, username, natid, tel).getNatid() )
		  && tel.equals(nDao.checkPwd(userid, username, natid, tel).getTel() ) ) {
			String msg = "회원님의 비밀번호는 " + passwd + " 입니다";
			JOptionPane.showMessageDialog(null, msg
					, "비밀번호", JOptionPane.OK_OPTION);	
			}
		}

	}
	
	// 입력한 아이디, 이름, 주민등록번호, 전화번호를 vo에 담기
	private NewMemberVo getViewData() {
		String userid = this.txtId.getText(); 
		String username = this.txtName.getText(); 
		String natid = this.txtNid.getText() + "-" + this.txtNid2.getText();
		String tel   = (String) this.cbTel1.getSelectedItem() + this.txtTel.getText()
						+ "-" + this.txtTel2.getText() + "-" + this.txtTel3.getText();
		NewMemberVo vo = new NewMemberVo( userid, username, natid, tel);
		return vo;
	}

	private void resetInfo() {
		this.dispose();
		
	}

//	public static void main(String[] args) {
//	new NewMemberSearchPasswd();
//	}
}
