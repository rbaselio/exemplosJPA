package br.com.dextraining.mediaStore.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CadastroLivro extends CadastrosClientes {

	
	/**
	 * Create the frame.
	 */
	public CadastroLivro() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(103, 117, 70, 15);
		getContentPane().add(lblNewLabel);
		
	}

}
