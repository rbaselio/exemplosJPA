package br.com.roberto.mediaStore.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.roberto.mediaStore.utils.EntityManagerUtil;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaInicial extends JFrame {

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
		JMCadastros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
		
		menuBar.add(JMCadastros);
		
		JMenuItem JMIClientes = new JMenuItem("Clientes");
		JMIClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastrosClientes().setVisible(true);
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
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Filmes");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu JMVendas = new JMenu("Vendas");
		menuBar.add(JMVendas);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
