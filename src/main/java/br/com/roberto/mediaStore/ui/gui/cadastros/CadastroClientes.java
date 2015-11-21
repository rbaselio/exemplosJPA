package br.com.roberto.mediaStore.ui.gui.cadastros;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import br.com.roberto.mediaStore.entities.Cliente;
import br.com.roberto.mediaStore.entities.Endereco;
import br.com.roberto.mediaStore.entities.Estado;
import br.com.roberto.mediaStore.services.ClienteService;
import br.com.roberto.mediaStore.ui.gui.CadastroBase;
import br.com.roberto.mediaStore.ui.gui.LerDado;
import br.com.roberto.mediaStore.ui.gui.TelaConsulta;
import br.com.roberto.mediaStore.ui.gui.cadastros.tablemodels.ClienteTableModel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;

public class CadastroClientes extends CadastroBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClienteService clienteService = new ClienteService();
	private Cliente cliente = new Cliente();

	private Integer max = 1;
	private Integer start = 0;

	private JTextField jtfCodigo;
	private JTextField jtfNome;
	private JFormattedTextField jtfNascimento;
	private JCheckBox chckbxAtivo = new JCheckBox("Ativo");
	

	private Integer totalCliente;
	private JTextField jtfLogradouro;
	private JTextField jtfCidade;
	private JComboBox<Estado> cbEstado = new JComboBox<Estado>(Estado.values());

	/**
	 * Create the frame.
	 */
	public CadastroClientes() {
		super();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 521, 301);

		JLabel lblCdigo = new JLabel("Código:");
		lblCdigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdigo.setBounds(56, 79, 70, 15);
		getContentPane().add(lblCdigo);

		jtfCodigo = new JTextField();
		jtfCodigo.setBounds(130, 77, 114, 19);
		getContentPane().add(jtfCodigo);
		jtfCodigo.setColumns(10);

		JLabel lblDescrio = new JLabel("Nome:");
		lblDescrio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescrio.setBounds(43, 104, 83, 15);
		getContentPane().add(lblDescrio);

		jtfNome = new JTextField();
		jtfNome.setBounds(130, 104, 352, 19);
		getContentPane().add(jtfNome);
		jtfNome.setColumns(10);

		JLabel lblIsbn = new JLabel("Nascimento:");
		lblIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsbn.setBounds(22, 131, 104, 15);
		getContentPane().add(lblIsbn);

		try {
			jtfNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jtfNascimento.setBounds(132, 131, 191, 19);

		getContentPane().add(jtfNascimento);

		chckbxAtivo.setBounds(331, 131, 129, 23);
		getContentPane().add(chckbxAtivo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(43, 158, 440, 92);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		jtfLogradouro = new JTextField();
		jtfLogradouro.setBounds(100, 22, 328, 19);
		panel.add(jtfLogradouro);
		jtfLogradouro.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Rua:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(12, 24, 70, 15);
		panel.add(lblNewLabel);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCidade.setBounds(22, 51, 70, 15);
		panel.add(lblCidade);
		
		jtfCidade = new JTextField();
		jtfCidade.setBounds(100, 51, 114, 19);
		panel.add(jtfCidade);
		jtfCidade.setColumns(10);
		
		
		cbEstado.setBounds(299, 51, 70, 19);
		panel.add(cbEstado);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(232, 51, 70, 15);
		panel.add(lblEstado);

		habilitarCampos(true);
		gotoPrimeiro();
		totalCliente = clienteService.countAll().intValue();

	}

	private void preencher(Cliente cliente) {
		if (cliente != null) {
			jtfCodigo.setText(cliente.getId().toString());
			jtfNome.setText(cliente.getNome());
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			try {
				jtfNascimento.setText(df.format(cliente.getNascimento()));
			} catch (Exception e) {
				jtfNascimento.setText("");
			}
			chckbxAtivo.setSelected(cliente.isAtivo());
			
			cbEstado.setSelectedItem(cliente.getEndereco().getEstado());
			jtfCidade.setText(cliente.getEndereco().getCidade());
			jtfLogradouro.setText(cliente.getEndereco().getLogradouro());
			
			
		}

	}

	@Override
	protected void gotoPrimeiro() {
		start = 0;
		cliente = clienteService.find(start, max);
		preencher(cliente);
	}

	@Override
	protected void gotoProximo() {
		if (start < totalCliente)
			start += 1;
		cliente = clienteService.find(start, max);
		preencher(cliente);

	}

	@Override
	protected void gotoAnterior() {
		if (start > 0)
			start -= 1;
		cliente = clienteService.find(start, max);
		preencher(cliente);

	}

	@Override
	protected void gotoUltimo() {
		start = totalCliente;
		cliente = clienteService.find(start, max);
		preencher(cliente);

	}

	@Override
	protected void pesquisar() {
		ClienteTableModel tableModel = new ClienteTableModel();
		tableModel.setService(clienteService);
		cliente = tableModel.getEntidade(TelaConsulta.getEntidade(this, tableModel));
		if (cliente != null) preencher(cliente);
	}

	@Override
	protected void vaPara() {
		Integer id = LerDado.getInteger(this, "Codigo: ");
		if (id != null) {
			cliente = clienteService.findById(id.longValue());
			if (cliente != null)
				preencher(cliente);
			else
				JOptionPane.showMessageDialog(this, "Livro não encontrado");
		}
	}

	@Override
	protected void incluir() {
		habilitarCampos(false);
		jtfCodigo.setText("");
		jtfNome.setText("");
		jtfNascimento.setText("");
		chckbxAtivo.setSelected(true);		

	}

	@Override
	protected void remover() {
		habilitarCampos(true);
		int op = JOptionPane.showConfirmDialog(this, "Confirma exclusão do registro?", "EXCLUSÃO",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (op == 0)
			confirmar();
	}

	@Override
	protected void confirmar() {
		switch (operacao) {
		case INCLUIR:
			cliente = new Cliente();
			atualizarLivro();
			clienteService.persist(cliente);
			break;
		case ALTERAR:
			atualizarLivro();
			clienteService.update(cliente);
			break;
		case REMOVER:
			clienteService.remove(cliente);
			gotoAnterior();
			break;
		default:
			break;
		}
		habilitarCampos(true);
		preencher(cliente);
		operacao = 0;
		totalCliente = clienteService.countAll().intValue() - 1;

	}

	private void atualizarLivro() {
		cliente.setNome(jtfNome.getText());
		cliente.setAtivo(chckbxAtivo.isSelected());
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			cliente.setNascimento(format.parse(jtfNascimento.getText()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Endereco end = new Endereco();
		end.setCidade(jtfCidade.getText());
		end.setLogradouro(jtfLogradouro.getText());
		end.setEstado((Estado) cbEstado.getSelectedItem());
		cliente.setEndereco(end);

	}

	@Override
	protected void cancelar() {
		habilitarCampos(true);
		preencher(cliente);

	}

	@Override
	protected void habilitarCampos(boolean habilita) {
		super.habilitarCampos(habilita);
		jtfCodigo.setEditable(false);
		jtfNome.setEditable(!habilita);
		jtfNascimento.setEditable(!habilita);
		chckbxAtivo.setEnabled(!habilita);
		cbEstado.setEditable(!habilita);
		jtfCidade.setEditable(!habilita);
		jtfLogradouro.setEditable(!habilita);
	}

	@Override
	protected void alterar() {
		habilitarCampos(false);
	}
}

