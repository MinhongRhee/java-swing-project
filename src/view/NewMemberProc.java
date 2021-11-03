package view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.NewMemberDao;
import model.NewMemberVo;

import javax.swing.JEditorPane;

public class NewMemberProc extends JFrame implements ActionListener {
	
	// Component 부품들
	JPanel          p, p1;
	JTextField      txtId, txtName, txtNid, txtTel, txtTel2, txtTel3, txtIndate, txtEid;
	JPasswordField  txtPwd, txtPwd2, txtNid2;
	JComboBox       cbJob, cbEid, cbTel1;
	JRadioButton    rbMan, rbWoman;
	JTextArea       taIntro;
	JScrollPane     pane;	
	JButton			btnCheck, btnInsert, btnCancel, btnUpdate, btnReset, btnClose;
	
	ButtonGroup   group1;
	
	NewMemberLogin newMemberLogin;
	
	// Layout
	GridBagLayout       gb;
	GridBagConstraints  gbc;
	
	public NewMemberProc() {
		this.setTitle("회원가입");
		initComponent();
	}
	
	public NewMemberProc(NewMemberLogin newMemberLogin) {
		this();
		
		this.newMemberLogin = newMemberLogin;
	}

	// 화면에 component 배치
	private void initComponent() {
		
		// 레이아웃 설정
		gb          =  new GridBagLayout();
		getContentPane().setLayout(gb);
		
		gbc         =  new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		// 아이디
		JLabel		lblId  =  new JLabel("아이디");
		JPanel        pId  =  new JPanel( new FlowLayout(FlowLayout.LEFT) );
		txtId			   =  new JTextField(10);
		pId.add(txtId);
		pId.add(new JLabel("         "));
		gbAdd ( lblId, 0, 0, 1, 1 );
		gbAdd (   pId, 1, 0, 1, 1 );
		
		// 비밀번호
		JLabel     lblPwd  =  new JLabel("비밀번호");
		JLabel     lblPwd2 =  new JLabel("비밀번호확인");
		JPanel        Pwd  =  new JPanel( new FlowLayout(FlowLayout.LEFT) );
		JPanel       Pwd2  =  new JPanel( new FlowLayout(FlowLayout.LEFT) );
		txtPwd             =  new JPasswordField(10);
		txtPwd2            =  new JPasswordField(10);
		Pwd.add(txtPwd);
		Pwd2.add(txtPwd2);
		gbAdd ( lblPwd,  0, 1, 1, 1 );
		gbAdd ( lblPwd2, 0, 2, 1, 1 );
		gbAdd (    Pwd,  1, 1, 2, 1 );
		gbAdd (    Pwd2, 1, 2, 2, 1 );
		
		// 이름
		JLabel     lblName =  new JLabel("이름");
		JPanel       pName =  new JPanel( new FlowLayout(FlowLayout.LEFT) );
		txtName            =  new JTextField(10);
		pName.add(txtName);
		gbAdd ( lblName, 0, 3, 1, 1 );
		gbAdd (   pName, 1, 3, 1, 1 );
		
		// 주민등록번호
		JLabel      lblNid =  new JLabel("주민등록번호");
		JPanel        pNid =  new JPanel( new FlowLayout(FlowLayout.LEFT) );
		txtNid             =  new JTextField(10);
		txtNid2            =  new JPasswordField(10);
		pNid.add(txtNid);
		pNid.add(new JLabel("-"));
		pNid.add(txtNid2);
		gbAdd ( lblNid, 0, 4, 1, 1 );
		gbAdd (   pNid, 1, 4, 4, 1 );
		
		// 이메일
		JLabel      lblEid =  new JLabel("이메일");
		JPanel      pEmail =  new JPanel( new FlowLayout(FlowLayout.LEFT) );
		txtEid             =  new JTextField(10);
		String [] arrEid = { "naver.com", "gmail.com", "daum.net", "nate.com" };
		cbEid            =  new JComboBox(arrEid);
		pEmail.add(txtEid);
		pEmail.add(new JLabel("@"));
		pEmail.add(cbEid);
		gbAdd ( lblEid, 0, 5, 1, 1 );
		gbAdd ( pEmail, 1, 5, 4, 1 );
		
		// 전화번호
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
		gbAdd ( lblTel, 0, 6, 1, 1 );
		gbAdd (   pTel, 1, 6, 3, 1 );
		
		// 직업
		JLabel     lblJob =  new JLabel("직업");
		String [] arrJob = { "-선택-","회사원", "학생", "군인", "무직"};
		JPanel       pJob  = new JPanel( new FlowLayout(FlowLayout.LEFT) );
		cbJob            =  new JComboBox(arrJob);
		pJob.add(cbJob);
		gbAdd ( lblJob, 0, 7, 1, 1 );
		gbAdd (   pJob, 1, 7, 3, 1 );

		// 성별
		JLabel     lblgender =  new JLabel("성별");

		rbMan				 =  new JRadioButton("남자", false);
		rbWoman				 =  new JRadioButton("여자", false);
		
		// RadioButton 그룹지정 : 2개중 하나만 선택
		group1 = new ButtonGroup();
		group1.add( rbMan );
		group1.add( rbWoman);
		
		//패널준비
		JPanel		pGender  =  new JPanel( new FlowLayout(FlowLayout.LEFT) );
		pGender.add( rbMan );
		pGender.add( rbWoman ); 
		
		gbAdd ( lblgender, 0, 8, 1, 1 );
		gbAdd ( pGender,   1, 8, 2, 1 );
		
		// 자기소개
		JLabel    lblIntro =  new JLabel("자기소개");
		taIntro            =  new JTextArea(5, 10); 	// 5rows, 20cols
		pane			   =  new JScrollPane(taIntro);
		
		gbAdd ( lblIntro, 0, 9, 1, 1 );
		gbAdd ( pane, 1, 9, 1, 1 );

		// 가입일
		JLabel   lblIndate =  new JLabel("가입일");
		JPanel	  pIndate  =  new JPanel( new FlowLayout(FlowLayout.LEFT) );
		txtIndate          =  new JTextField(20);
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E");
		String now = sdf.format( today );
		txtIndate.setText(now);
		txtIndate.setEditable( false );
		pIndate.add(txtIndate);
		
		gbAdd ( lblIndate, 0, 12, 1, 1 );
		gbAdd (   pIndate, 1, 12, 3, 1 );
		
		// 버튼들
		JPanel  pButton  =  new JPanel();
		btnInsert		 =  new JButton("가입하기");
		btnInsert.setBounds(65, 5, 60, 28);
		btnCancel		 =  new JButton("취소");
		btnCancel.setBounds(130, 5, 60, 28);
		btnClose         =  new JButton("닫기");
		btnClose.setBounds(195, 5, 66, 28);
		
		pButton.add(btnInsert);
		pButton.add(btnCancel);
		pButton.add(btnClose);
		
		JPanel  pButton2 =  new JPanel();
		btnCheck         =  new JButton("중복확인");
		btnCheck.setBounds(65, 5, 60, 28);

		
		pId.add(btnCheck);
		
		gbAdd( pButton, 0, 14, 0, 1);
		gbAdd( pButton2, 2, 0, 1, 1);
		
		this.btnInsert.addActionListener( this );
		this.btnCancel.addActionListener( this );
		this.btnCheck.addActionListener( this );
		this.btnClose.addActionListener( this );
		
		// 화면 띄우기
		this.setLocation(1000, 200);
		this.setSize(400, 650);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	
	}
	
	// frame jtextfield <- vo
	protected void setViewData(NewMemberVo vo) {
		this.txtId.setText( vo.getUserid() );
		this.txtPwd.setText( vo.getPasswd() );
		this.txtName.setText( vo.getUsername() );
		this.txtNid.setText( vo.getNatid() ); 
		this.txtNid2.setText( vo.getNatid() );
		this.txtEid.setText( vo.getEmail() );
		this.cbEid.setSelectedItem( vo.getEmail() );
		this.cbTel1.setSelectedItem( vo.getTel() );
		this.txtTel.setText( vo.getTel() );
		this.txtTel2.setText( vo.getTel() );
		this.txtTel3.setText( vo.getTel() );
		this.cbJob.setSelectedItem( vo.getJob() );
		
		if(vo.getGender().equals("남") ) this.rbMan.setSelected(true); 
		if(vo.getGender().equals("여") ) this.rbWoman.setSelected(true);
		
		this.taIntro.setText( vo.getIntro() );
		this.txtIndate.setText( vo.getIndate() );
		
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

	// 이벤트처리
	// 기능부여
	@Override
	public void actionPerformed(ActionEvent e) {
		switch( e.getActionCommand() ) {
		case "가입하기":
			NewMemberDao nDao = new NewMemberDao();
			NewMemberVo  vo   = getViewData();
			String passwd = new String(this.txtPwd.getPassword());
			String passwd2 = new String(this.txtPwd2.getPassword());
			if( passwd.equals(passwd2) ) {
			insertMember();
			resetMember(); 
			} else {
				String msg = "비밀번호가 서로 일치하지 않습니다";
				JOptionPane.showMessageDialog(null, msg
						, "비밀번호 확인", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "취소":
			resetMember();
			break;
		case "중복확인":
			checkMember();
			break;
		case "닫기":
			this.dispose();
			break;
		}
		
	}

	private void checkMember() {
		
		NewMemberDao nDao = new NewMemberDao();
		NewMemberVo  vo   = getViewData();
		String    userid  = vo.getUserid();
		
		if( userid.equals(nDao.getId(userid) ) ) {
			String msg = "중복된 아이디입니다";
			JOptionPane.showMessageDialog(this, msg , "사용불가", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			String msg = "사용 가능한 아이디입니다";
			JOptionPane.showMessageDialog(this, msg, "사용가능", JOptionPane.DEFAULT_OPTION);
			return;
		}
		
	}

	private void insertMember() {
		
		NewMemberDao nDao = new NewMemberDao();
		NewMemberVo  vo   = getViewData();
		nDao.insertMember( vo );
		
		nDao.close();
		
	}


	NewMemberVo getViewData() {
		String userid = this.txtId.getText(); 
		String passwd = new String(this.txtPwd.getPassword()); 
		String passwd2 = new String(this.txtPwd2.getPassword());
		String username = this.txtName.getText();
		String natid = this.txtNid.getText() + "-" + this.txtNid2.getText();
		String email = this.txtEid.getText() + "@" + (String) this.cbEid.getSelectedItem();
		String tel   = (String) this.cbTel1.getSelectedItem() + this.txtTel.getText()
		+ "-" + this.txtTel2.getText() + "-" + this.txtTel3.getText();
		String job = (String) this.cbJob.getSelectedItem();
		
		String gender = ""; // "남", "여", null
		if(this.rbMan.isSelected()) gender = "남";
		if(this.rbWoman.isSelected()) gender = "여";
		
		String intro = this.taIntro.getText(); 
		String indate = this.txtIndate.getText();
		NewMemberVo vo = new NewMemberVo(
				userid, passwd, passwd2, username, natid, email, tel, job, gender, intro, indate);
		return vo;
	}
		
	private void resetMember() {
		this.txtId.setText("");
		this.txtPwd.setText("");
		this.txtPwd2.setText("");
		this.txtName.setText("");
		this.cbJob.setSelectedIndex(0);
		this.txtNid.setText("");
		this.txtNid2.setText("");
		this.txtEid.setText("");
		this.cbEid.setSelectedIndex(0);
		this.cbTel1.setSelectedIndex(0);
		this.txtTel.setText("");
		this.group1.clearSelection();
		this.taIntro.setText("");
		this.txtIndate.setText("");
		this.txtId.grabFocus();
		
	}
	// test main 추가
//	public static void main(String[] args) {
//		new NewMemberProc();
//		
}

