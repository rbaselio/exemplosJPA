package br.com.roberto.mediaStore.ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public abstract class CadastroBase extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3755959919707941083L;
	private JPanel contentPane;
	
	protected int operacao;
	
	protected final int INCLUIR = 1;
	protected final int ALTERAR = 2;
	protected final int REMOVER = 3;
		
	protected abstract void gotoPrimeiro();
	protected abstract void gotoProximo();
	protected abstract void gotoAnterior();
	protected abstract void gotoUltimo();
	protected abstract void pesquisar();
	protected abstract void vaPara();
	protected abstract void incluir();
	protected abstract void alterar();
	protected abstract void remover();
	protected abstract void confirmar();
	protected abstract void cancelar();
	
	protected JButton btnPrimeiro = new JButton("");
	protected JButton bntAnterior = new JButton("");
	protected JButton btnProximo = new JButton("");
	protected JButton btnUltimo = new JButton("");
	protected JButton btnVaPara = new JButton("");
	protected JButton btnPesquisar = new JButton("");
	protected JButton btnExcluir = new JButton("");
	protected JButton btnAlterar = new JButton("");
	protected JButton btnIncluir = new JButton("");
	protected JButton btnCancelar = new JButton("");
	protected JButton btnSalvar = new JButton("");
	

	/**
	 * Create the frame.
	 */
	protected CadastroBase() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(null, 1, true));
		panel.setBounds(12, 12, 489, 55);
		contentPane.add(panel);

		
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoPrimeiro();				
			}
		});
		btnPrimeiro.setToolTipText("Primeiro Registro");
		btnPrimeiro.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/first.png")));

		
		bntAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoAnterior();
			}
		});
		bntAnterior.setToolTipText("Registro Anterior");
		bntAnterior.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/back.png")));
		
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoProximo();
			}
		});
		btnProximo.setToolTipText("Próximo Registro");
		btnProximo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/forward.png")));

		
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoUltimo();
			}
		});
		btnUltimo.setToolTipText("Último registro");
		btnUltimo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/last.png")));
		
		btnVaPara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaPara();
			}
		});
		btnVaPara.setToolTipText("Vá para");
		btnVaPara.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/goto.png")));
		
		
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setToolTipText("Consultar");
		btnPesquisar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/find.png")));
		
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operacao = REMOVER;
				remover();
				
			}
		});
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/trash.png")));
		
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operacao = ALTERAR;
				alterar();
				
			}
		});
		btnAlterar.setToolTipText("Alterar");
		btnAlterar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/modify.png")));
		
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operacao = INCLUIR;
				incluir();
				
			}
		});
		btnIncluir.setToolTipText("Incluir");
		btnIncluir.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/new.png")));
		
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar();
			}
		});
		btnSalvar.setToolTipText("Confirmar");
		btnSalvar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/confirm.png")));
		
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		btnCancelar.setToolTipText("Cancela");
		btnCancelar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/cancel.png")));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel.createSequentialGroup().addGap(11)
										.addComponent(btnPrimeiro, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(bntAnterior, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnProximo, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnUltimo, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(btnVaPara, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(btnIncluir, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(btnAlterar, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING,
						gl_panel.createSequentialGroup().addGap(11)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnUltimo, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
								.addComponent(btnProximo, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(bntAnterior, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPrimeiro, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(
						gl_panel.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnIncluir, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAlterar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnVaPara, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		panel.setLayout(gl_panel);
	}
	
	
	protected void habilitarCampos(boolean habilita){
		
		btnPrimeiro.setEnabled(habilita);
		bntAnterior.setEnabled(habilita);
		btnProximo.setEnabled(habilita);
		btnUltimo.setEnabled(habilita);
		btnVaPara.setEnabled(habilita);
		btnPesquisar.setEnabled(habilita);
		btnExcluir.setEnabled(habilita);
		btnAlterar.setEnabled(habilita);
		btnIncluir.setEnabled(habilita);
		
		btnCancelar.setEnabled(!habilita);
		btnSalvar.setEnabled(!habilita);
		
		
		
		
	}
	
}
