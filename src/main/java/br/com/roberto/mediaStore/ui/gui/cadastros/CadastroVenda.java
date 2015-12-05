package br.com.roberto.mediaStore.ui.gui.cadastros;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;

import br.com.roberto.mediaStore.entities.Cliente;
import br.com.roberto.mediaStore.entities.Venda;
import br.com.roberto.mediaStore.services.ClienteService;
import br.com.roberto.mediaStore.services.VendaService;
import br.com.roberto.mediaStore.ui.gui.CadastroBase;
import br.com.roberto.mediaStore.ui.gui.LerDado;
import br.com.roberto.mediaStore.ui.gui.TelaConsulta;
import br.com.roberto.mediaStore.ui.gui.cadastros.tablemodels.ClienteTableModel;
import br.com.roberto.mediaStore.ui.gui.cadastros.tablemodels.ItemVendaTableModel;
import br.com.roberto.mediaStore.ui.gui.cadastros.tablemodels.VendaTableModel;
import br.com.roberto.mediaStore.utils.TamanhoMaximo;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroVenda extends CadastroBase {

	private static final long serialVersionUID = 7878012768321645380L;

	private Venda venda;
	private VendaService vendaService = new VendaService();

	private ItemVendaTableModel tableModel;

	private Cliente cliente;
	private ClienteService clienteService = new ClienteService();

	private Integer max = 1;
	private Integer start = 0;
	Integer totalVenda;

	private JTextField jtfVendaID;

	private JTextField jtfClienteCodigo;
	private JTextField jtfClienteNome;
	private JButton btnAdicionarItem;
	private JButton btnRemoverItem;
	private JTable table;

	private JFrame estajanela = this;
	private JTextField jtfData;
	private JTextField jtfValor;

	/**
	 * Create the frame.
	 */
	public CadastroVenda() {
		super();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 521, 390);

		JLabel lblVendaId = new JLabel("Venda id:");
		lblVendaId.setBounds(12, 81, 70, 15);
		getContentPane().add(lblVendaId);

		jtfVendaID = new JTextField();
		jtfVendaID.setBounds(92, 79, 74, 19);
		getContentPane().add(jtfVendaID);
		jtfVendaID.setColumns(10);

		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(184, 81, 49, 15);
		getContentPane().add(lblData);

		jtfData = new JTextField();
		jtfData.setBounds(236, 79, 90, 19);
		getContentPane().add(jtfData);
		jtfData.setColumns(10);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(344, 81, 49, 15);
		getContentPane().add(lblValor);

		jtfValor = new JTextField();
		jtfValor.setBounds(395, 79, 100, 19);
		getContentPane().add(jtfValor);
		jtfValor.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cliente:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 108, 493, 51);
		getContentPane().add(panel);
		panel.setLayout(null);

		jtfClienteCodigo = new JTextField();
		jtfClienteCodigo.setDocument(new TamanhoMaximo(5, true));
		jtfClienteCodigo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (jtfClienteCodigo.isEditable() && e.getClickCount() == 2) {
					ClienteTableModel tableModel = new ClienteTableModel();
					tableModel.setService(clienteService);
					cliente = tableModel.getEntidade(TelaConsulta.getEntidade(estajanela, tableModel, null));
					jtfClienteCodigo.setText(cliente.getId().toString());
					jtfClienteNome.setText(cliente.getNome());
				}

			}
		});

		jtfClienteCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				cliente = clienteService.findById(Long.parseLong(jtfClienteCodigo.getText()));
				if (cliente != null) {
					jtfClienteCodigo.setText(cliente.getId().toString());
					jtfClienteNome.setText(cliente.getNome());
				} else {
					JOptionPane.showMessageDialog(null, "Editora não encontrado");
				}
			}
		});

		jtfClienteCodigo.setBounds(72, 22, 76, 19);
		panel.add(jtfClienteCodigo);
		jtfClienteCodigo.setColumns(10);

		jtfClienteNome = new JTextField();
		jtfClienteNome.setBounds(201, 22, 280, 19);
		panel.add(jtfClienteNome);
		jtfClienteNome.setColumns(10);

		JLabel lblCdigo = new JLabel("Código:");
		lblCdigo.setBounds(12, 24, 61, 15);
		panel.add(lblCdigo);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(153, 24, 55, 15);
		panel.add(lblNome);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 160, 493, 190);
		getContentPane().add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Produtos", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 430, 139);
		panel_1.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnAdicionarItem = new JButton("");
		btnAdicionarItem.setToolTipText("Adicionar Itens");
		btnAdicionarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarProduto.getItemVenda(estajanela, table, venda);
				preencher(venda);				
				
			}
		});
		btnAdicionarItem.setEnabled(false);
		btnAdicionarItem.setBounds(454, 12, 34, 34);
		btnAdicionarItem.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/plus.png")));
		panel_1.add(btnAdicionarItem);
		
		
		btnRemoverItem = new JButton("");
		
		btnRemoverItem.setToolTipText("Remover Itens");
		btnRemoverItem.setEnabled(false);
		btnRemoverItem.setBounds(454, 42, 34, 34);
		btnRemoverItem.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/minus.png")));
		panel_1.add(btnRemoverItem);
		totalVenda = vendaService.countAll().intValue();
		habilitarCampos(true);
		gotoPrimeiro();

	}

	private void preencher(Venda venda) {
		try {
			jtfVendaID.setText(venda.getId().toString());
		} catch (Exception e) {
		}
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		jtfData.setText(dateFormat.format(venda.getData()));

		DecimalFormat df = new DecimalFormat("#,##0.00");
		jtfValor.setText(df.format(venda.getValortotal()));	
		

		jtfClienteCodigo.setText(venda.getCliente().getId().toString());
		jtfClienteNome.setText(venda.getCliente().getNome());

		tableModel = new ItemVendaTableModel(venda.getItemVendas());
		alinharTabela();

	}

	@Override
	protected void gotoPrimeiro() {
		start = 0;
		try {
			venda = vendaService.find(start, max);
			preencher(venda);
		} catch (Exception e) {
		}

	}

	@Override
	protected void gotoProximo() {
		if (start < totalVenda - 1)
			start += 1;
		try {
			venda = vendaService.find(start, max);
			preencher(venda);
		} catch (Exception e) {
		}

	}

	@Override
	protected void gotoAnterior() {
		if (start > 0)
			start -= 1;
		try {
			venda = vendaService.find(start, max);
			preencher(venda);
		} catch (Exception e) {
		}

	}

	@Override
	protected void gotoUltimo() {
		start = totalVenda - 1;
		try {
			venda = vendaService.find(start, max);
			preencher(venda);
		} catch (Exception e) {
		}

	}

	@Override
	protected void pesquisar() {
		VendaTableModel tableModel = new VendaTableModel();
		tableModel.setService(vendaService);
		venda = tableModel.getEntidade(TelaConsulta.getEntidade(this, tableModel, null));
		preencher(venda);

	}

	@Override
	protected void vaPara() {
		Long id = LerDado.getInteger(this, "Codigo: ").longValue();
		if (id != null) {
			venda = vendaService.findById(id);
			if (venda != null)
				preencher(venda);
			else
				JOptionPane.showMessageDialog(this, "Venda não encontrado");
		}

	}

	@Override
	protected void incluir() {
		habilitarCampos(false);

	}

	@Override
	protected void alterar() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void remover() {
		habilitarCampos(true);
		int op = JOptionPane.showConfirmDialog(this, "Confirma exclusão do Venda?", "EXCLUSÃO",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (op == 0)
			confirmar();

	}

	@Override
	protected void confirmar() {
		switch (operacao) {
		case INCLUIR:
			venda = new Venda();
			atualizarVenda();
			// vendaService.persist(venda);
			break;
		case ALTERAR:
			atualizarVenda();
			// vendaService.update(venda);
			break;
		case REMOVER:
			vendaService.remove(venda);
			gotoAnterior();
			break;
		default:
			break;
		}
		habilitarCampos(true);
		preencher(venda);
		operacao = 0;
		totalVenda = vendaService.countAll().intValue();

	}

	private void atualizarVenda() {
		venda.setData(new Date(System.currentTimeMillis()));
		venda.setCliente(cliente);
		preencher(venda);

	}

	@Override
	protected void cancelar() {
		gotoPrimeiro();
	}

	protected void habilitarCampos(boolean habilita) {
		super.habilitarCampos(habilita);
		jtfClienteCodigo.setEditable(!habilita);
		table.setEnabled(!habilita);
		btnAdicionarItem.setEnabled(!habilita);
		btnRemoverItem.setEnabled(!habilita);
		

		jtfVendaID.setEditable(false);
		jtfClienteNome.setEditable(false);
		jtfData.setEditable(false);
		jtfValor.setEditable(false);
	}

	private void alinharTabela() {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(tableModel);
		table.updateUI();

		FontMetrics fm = getFontMetrics(new Font(null));
		int width = 0; // Min width

		for (int column = 0; column < table.getColumnModel().getColumnCount(); column++) {

			String columnName = table.getColumnName(column);
			width = fm.stringWidth(columnName) + 20;

			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 10, width);
			}

			table.getColumnModel().getColumn(column).setPreferredWidth(width);
		}

	}
}
