package br.com.roberto.mediaStore.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CadastrosClientes extends JFrame {

	protected JPanel contentPane;
	protected JTextField textField;

	/**
	 * Launch the application.
	

	/**
	 * Create the frame.
	 */
	public CadastrosClientes() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(100, 12, 169, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(12, 14, 70, 15);
		contentPane.add(lblNewLabel);
	}
}
