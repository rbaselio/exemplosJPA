package br.com.roberto.mediaStore.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.roberto.mediaStore.utils.EntityManagerUtil;

public class TelaInicial extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8333409168622197422L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EntityManagerUtil.criarConexao();
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the frame.
	 */
	public TelaInicial() {
		addWindowListener(new WindowAdapter() {
			@Override	
			
			public void windowClosing(WindowEvent e) {
				System.out.println("Encerrando sistema...");
				EntityManagerUtil.fechaConexao();
				System.out.println("Sistema encerrado");
				System.exit(0);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu JMCadastros = new JMenu("Cadastros");
			
		menuBar.add(JMCadastros);
		
		JMenuItem JMIClientes = new JMenuItem("Clientes");
		JMIClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastroClientes().setVisible(true);
			}
		});
		JMCadastros.add(JMIClientes);
		
		JMenu mnNewMenu = new JMenu("Produtos");
		JMCadastros.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Livros");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastroLivro().setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Album");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastroAlbum().setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Filmes");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastroFilme().setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu JMVendas = new JMenu("Vendas");
		menuBar.add(JMVendas);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
