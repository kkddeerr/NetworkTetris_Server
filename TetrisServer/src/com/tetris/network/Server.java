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
	private JTextField textField; // ����� PORT��ȣ �Է�
	private JButton Start; // ������ �����Ų ��ư
	private GameServer server;
	JTextArea textArea; // Ŭ���̾�Ʈ �� ���� �޽��� ���


	private int Port; // ��Ʈ��ȣ


	public static void main(String[] args)
	{	
			Server frame = new Server();
			frame.setVisible(true);		
			
	}

	public Server() {
		init();
	}

	private void init() { // GUI�� �����ϴ� �޼ҵ�		
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
		Start = new JButton("���� ����");
		
		Myaction action = new Myaction();
		Start.addActionListener(action); // ����Ŭ������ �׼� �����ʸ� ��ӹ��� Ŭ������
		textField.addActionListener(action);
		Start.setBounds(0, 325, 264, 37);
		contentPane.add(Start);
		textArea.setEditable(false); // textArea�� ����ڰ� ���� ���ϰԲ� ���´�.	
	}
	
	class Myaction implements ActionListener // ����Ŭ������ �׼� �̺�Ʈ ó�� Ŭ����
	{
		@Override
		public void actionPerformed(ActionEvent e) {

			// �׼� �̺�Ʈ�� sendBtn�϶� �Ǵ� textField ���� Enter key ġ��
			if (e.getSource() == Start || e.getSource() == textField) 
			{
				if (textField.getText().equals("") || textField.getText().length()==0)// textField�� ���� ������� ������
				{
					textField.setText("��Ʈ��ȣ�� �Է����ּ���");
					textField.requestFocus(); // ��Ŀ���� �ٽ� textField�� �־��ش�
				} 			
				else 
				{
					try
					{
						Port = Integer.parseInt(textField.getText()); // ���ڷ� �Է����� ������ ���� �߻� ��Ʈ�� ���� ����.		
						server_start(); // ����ڰ� ����ε� ��Ʈ��ȣ�� �־����� ���� ���������� �޼ҵ� ȣ��			
					}
					catch(Exception er)
					{
						//����ڰ� ���ڷ� �Է����� �ʾ����ÿ��� ���Է��� �䱸�Ѵ�
						textField.setText("���ڷ� �Է����ּ���"); //��
						textField.requestFocus(); // ��Ŀ���� �ٽ� textField�� �־��ش�
					}	
				}// else �� ��
			}

		}

	}
	private void server_start() {
		server = new GameServer(Port);
		
		
		//socket = new ServerSocket(Port); // ������ ��Ʈ ���ºκ�
		Start.setText("����������");
		Start.setEnabled(false); // ������ ���̻� �����Ű�� �� �ϰ� ���´�
		textField.setEnabled(false); // ���̻� ��Ʈ��ȣ ������ �ϰ� ���´�
		
		if(server!=null) // socket �� ���������� ��������
		{
			server.startServer();
			
		}

	}
	
	public JTextArea getTextarea() {
		return this.textArea;
	}


}
