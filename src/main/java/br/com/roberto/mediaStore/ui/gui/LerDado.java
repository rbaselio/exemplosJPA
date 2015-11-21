package br.com.roberto.mediaStore.ui.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.roberto.mediaStore.utils.TamanhoMaximo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class LerDado extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTextField jtfDado;
	private static JLabel lblLabel = new JLabel("New label");
	private static String retorno;

	private static LerDado instance;

	public static Integer getInteger(JFrame pai, String dado) {
		if (instance == null)
			instance = new LerDado();
		lblLabel.setText(dado);
		jtfDado.setText("");

		jtfDado.setDocument(new TamanhoMaximo(5, true));
		instance.setLocationRelativeTo(pai);
		instance.setTitle("Digite o valor a ser pesquisado");
		instance.setModal(true);
		instance.setVisible(true);
		
		try{
			return Integer.parseInt(retorno);
		} catch(Exception e1){
			return null;
		}	
		

	}

	/**
	 * Create the dialog.
	 */
	private LerDado() {
		setBounds(100, 100, 346, 115);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		lblLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		lblLabel.setBounds(12, 24, 122, 15);
		contentPanel.add(lblLabel);

		jtfDado = new JTextField();
		jtfDado.setBounds(152, 22, 169, 19);
		contentPanel.add(jtfDado);
		jtfDado.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						retorno = jtfDado.getText();
						instance.setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						retorno = null;
						instance.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
