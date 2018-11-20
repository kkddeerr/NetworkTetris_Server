package com.tetris.network;
// Java Chatting Server

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Server extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField; // 사용할 PORT번호 입력
	private JButton Start; // 서버를 실행시킨 버튼
	private GameServer server;
	JTextArea textArea; // 클라이언트 및 서버 메시지 출력


	private int Port; // 포트번호


	public static void main(String[] args)
	{	
			Server frame = new Server();
			frame.setVisible(true);		
			
	}

	public Server() {
		init();
	}

	private void init() { // GUI를 구성하는 메소드		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JScrollPane js = new JScrollPane();				

		textArea = new JTextArea();
		textArea.setColumns(20);
		textArea.setRows(5);
		js.setBounds(0, 0, 264, 254);
		contentPane.add(js);
		js.setViewportView(textArea);

		textField = new JTextField();
		textField.setBounds(98, 264, 154, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText("9500");

		JLabel lblNewLabel = new JLabel("Port Number");
		lblNewLabel.setBounds(12, 264, 98, 37);
		contentPane.add(lblNewLabel);
		Start = new JButton("서버 실행");
		
		Myaction action = new Myaction();
		Start.addActionListener(action); // 내부클래스로 액션 리스너를 상속받은 클래스로
		textField.addActionListener(action);
		Start.setBounds(0, 325, 264, 37);
		contentPane.add(Start);
		textArea.setEditable(false); // textArea를 사용자가 수정 못하게끔 막는다.	
	}
	
	class Myaction implements ActionListener // 내부클래스로 액션 이벤트 처리 클래스
	{
		@Override
		public void actionPerformed(ActionEvent e) {

			// 액션 이벤트가 sendBtn일때 또는 textField 에세 Enter key 치면
			if (e.getSource() == Start || e.getSource() == textField) 
			{
				if (textField.getText().equals("") || textField.getText().length()==0)// textField에 값이 들어있지 않을때
				{
					textField.setText("포트번호를 입력해주세요");
					textField.requestFocus(); // 포커스를 다시 textField에 넣어준다
				} 			
				else 
				{
					try
					{
						Port = Integer.parseInt(textField.getText()); // 숫자로 입력하지 않으면 에러 발생 포트를 열수 없다.		
						server_start(); // 사용자가 제대로된 포트번호를 넣었을때 서버 실행을위헤 메소드 호출			
					}
					catch(Exception er)
					{
						//사용자가 숫자로 입력하지 않았을시에는 재입력을 요구한다
						textField.setText("숫자로 입력해주세요"); //밑
						textField.requestFocus(); // 포커스를 다시 textField에 넣어준다
					}	
				}// else 문 끝
			}

		}

	}
	private void server_start() {
		server = new GameServer(Port);
		
		
		//socket = new ServerSocket(Port); // 서버가 포트 여는부분
		Start.setText("서버실행중");
		Start.setEnabled(false); // 서버를 더이상 실행시키지 못 하게 막는다
		textField.setEnabled(false); // 더이상 포트번호 수정못 하게 막는다
		
		if(server!=null) // socket 이 정상적으로 열렸을때
		{
			server.startServer();
			
		}

	}
	
	public JTextArea getTextarea() {
		return this.textArea;
	}


}
