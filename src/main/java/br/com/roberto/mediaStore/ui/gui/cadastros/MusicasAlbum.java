package br.com.roberto.mediaStore.ui.gui.cadastros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.roberto.mediaStore.entities.Musica;
import br.com.roberto.mediaStore.utils.TamanhoMaximo;

public class MusicasAlbum extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTextField jtfNome;
	private static JTextField jtfduracao;
		
	private static MusicasAlbum instance;
	private static Musica musica;
	
	
	
	private MusicasAlbum() {
		setBounds(100, 100, 450, 125);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 22, 52, 15);
		contentPanel.add(lblNome);
		
		jtfNome = new JTextField();
		jtfNome.setBounds(64, 20, 206, 19);
		contentPanel.add(jtfNome);
		jtfNome.setColumns(10);
		
		JLabel lblDurao = new JLabel("Duração:");
		lblDurao.setBounds(288, 22, 70, 15);
		contentPanel.add(lblDurao);
		
		jtfduracao = new JTextField();
		jtfduracao.setBounds(376, 20, 62, 19);
		contentPanel.add(jtfduracao);
		jtfduracao.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Adicionar e Sair");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						musica = new Musica();
						musica.setDuracao(Integer.parseInt(jtfduracao.getText()));
						musica.setNome(jtfNome.getText());	
						instance.setVisible(false);
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						instance.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	


	public static Musica getMusicas(JFrame pai) {			
		
		if (instance == null) instance = new MusicasAlbum();
		
		jtfduracao.setDocument(new TamanhoMaximo(5, true));
		jtfduracao.setText("0");
		instance.setLocationRelativeTo(pai);
		instance.setTitle("Digite o valor a ser pesquisado");
		instance.setModal(true);
		instance.setVisible(true);				
		
		return musica;
	}
}
