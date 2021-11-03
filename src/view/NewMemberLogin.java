package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.NewMemberDao;
import model.NewMemberVo;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class NewMemberLogin extends JFrame 
	  implements ActionListener {
	
	// Component 부품들
	JPanel          bottomPane, p1, p2, p3, p4, p5;
	JTextField		txtId;
	JPasswordField  txtPwd;
	JButton			btnId, btnPwd, btnMemProc, btnLogin;
	
	// Layout
	GridBagLayout	    gb;
	GridBagConstraints  gbc;
	private JButton button;
	
	NewMemberProc memProc = null; 	// MemberProc 를 조작하기 위한
	NewMemberSearchId memSId = null;
	NewMemberSearchPasswd memSPwd = null;
	
	
	
	// 생성자
	public NewMemberLogin() {
		super("로그인");
		
		initComponent();
		
		// JTable 설정
		this.setLocation(350, 250);
		this.setSize(430, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);	
		
	}
	
	// 화면배치
	private void initComponent() {
		
		// 레이아웃 설정
		gb          =  new GridBagLayout();
		getContentPane().setLayout(gb);
		
		gbc         =  new GridBagConstraints();
		gbc.fill    = GridBagConstraints.VERTICAL;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		// 아이디
		JLabel		lblId  =  new JLabel("아이디");
		txtId			   =  new JTextField(20);
		gbAdd ( lblId, 0, 2, 1, 1 );
		gbAdd ( txtId, 1, 2, 1, 1 );
		
		// 비밀번호
		JLabel     lblPwd  =  new JLabel("비밀번호");
		txtPwd             =  new JPasswordField(20);
		gbAdd ( lblPwd, 0, 3, 1, 1 );
		gbAdd ( txtPwd, 1, 3, 1, 1 );
		
		// 버튼
		bottomPane    =  new JPanel();
		btnLogin = new JButton("로그인");
		bottomPane.add(btnLogin);
		
		btnId  =  new JButton("아이디 찾기");
		bottomPane.add( btnId );
		
		btnPwd =  new JButton("비밀번호 찾기");
		bottomPane.add( btnPwd );
		
		btnMemProc = new JButton("회원가입");
		bottomPane.add(btnMemProc);
		
		gbAdd( bottomPane, 0, 6, 3, 1 );
		
		// 창 추가
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();

		gbAdd( p1, 0, 5, 4, 1 );
		gbAdd( p2, 0, 0, 4, 2 );
		gbAdd( p3, 0, 2, 1, 2 );
	
		this.btnLogin.addActionListener(this);
		this.btnId.addActionListener(this);
		this.btnPwd.addActionListener(this);
		this.btnMemProc.addActionListener(this);
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
		case "로그인" :
			getLogin();
			break;
		case "아이디 찾기" :
			if( memSId != null )
				memSId.dispose();
				memSId = new NewMemberSearchId( this );
			break;
		case "비밀번호 찾기" :
			if( memSPwd != null )
				memSPwd.dispose();
				memSPwd = new NewMemberSearchPasswd( this );
			break;
		case "회원가입":
			if( memProc != null )
				memProc.dispose();
				memProc = new NewMemberProc( this );
			break;
		}
		
	}
	
	private void getLogin() {
		NewMemberDao nDao   = new NewMemberDao();
		NewMemberVo  vo     = getViewData();
		String       uid    = vo.getUserid();
		String       pwd    = vo.getPasswd();
		if( uid.equals(nDao.getId(uid)) && pwd.equals(nDao.getPwd(uid, pwd))) {
			String msg = "로그인하였습니다";
			JOptionPane.showMessageDialog(this, msg , "로그인", JOptionPane.DEFAULT_OPTION);
			return;
		} else {
			String msg = "아이디 또는 비밀번호가 틀립니다";
			JOptionPane.showMessageDialog(this, msg , "로그인 실패", JOptionPane.NO_OPTION);
			return;
		}
		
	}

	private NewMemberVo getViewData() {
		String userid = this.txtId.getText(); 
		String passwd = new String(this.txtPwd.getPassword());
		NewMemberVo vo = new NewMemberVo( userid, passwd );
		return vo;
	}

	public static void main(String[] args) {
		new NewMemberLogin();
		
	}
}




