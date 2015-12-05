package br.com.roberto.mediaStore.ui.gui.cadastros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.roberto.mediaStore.entities.ItemVenda;
import br.com.roberto.mediaStore.entities.Venda;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdicionarProduto extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private static AdicionarProduto instance;
	
	
	private JTextField jtfIdProduto;
	private JTextField jtfProdutoDescricao;
	private JTextField jtfQuantidade;
	private JTextField jtfValorProduto;
	
	public static ItemVenda getItemVenda(JFrame pai, JTable jtable, Venda venda){
				
		if (instance == null)
			instance = new AdicionarProduto();
		
		instance.setLocationRelativeTo(pai);
		instance.setTitle("Clique sobre a linha desejado");
		instance.setModal(true);
		
		instance.setVisible(true);
		return null;
		
	}
	
	

	
	private AdicionarProduto() {
		setBounds(100, 100, 450, 138);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(12, 12, 70, 15);
		contentPanel.add(lblCodigo);

		jtfIdProduto = new JTextField();
		jtfIdProduto.setBounds(75, 10, 70, 19);
		contentPanel.add(jtfIdProduto);
		jtfIdProduto.setColumns(10);
		{
			JLabel lblDescrio = new JLabel("Descrição:");
			lblDescrio.setBounds(153, 12, 74, 15);
			contentPanel.add(lblDescrio);
		}
		{
			jtfProdutoDescricao = new JTextField();
			jtfProdutoDescricao.setEnabled(false);
			jtfProdutoDescricao.setBounds(233, 10, 205, 19);
			contentPanel.add(jtfProdutoDescricao);
			jtfProdutoDescricao.setColumns(10);
		}
		{
			JLabel lblQuant = new JLabel("Quant.:");
			lblQuant.setBounds(12, 39, 70, 15);
			contentPanel.add(lblQuant);
		}
		{
			jtfQuantidade = new JTextField();
			jtfQuantidade.setBounds(75, 39, 79, 19);
			contentPanel.add(jtfQuantidade);
			jtfQuantidade.setColumns(10);
		}
		{
			JLabel lblValor = new JLabel("Valor:");
			lblValor.setHorizontalAlignment(SwingConstants.RIGHT);
			lblValor.setBounds(163, 39, 64, 15);
			contentPanel.add(lblValor);
		}
		{
			jtfValorProduto = new JTextField();
			jtfValorProduto.setEnabled(false);
			jtfValorProduto.setBounds(233, 37, 114, 19);
			contentPanel.add(jtfValorProduto);
			jtfValorProduto.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAdicionar = new JButton("Adicionar");
				btnAdicionar.setActionCommand("OK");
				buttonPane.add(btnAdicionar);
			}
			{
				JButton okButton = new JButton("Adicionar e Sair");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Sair");
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
}
