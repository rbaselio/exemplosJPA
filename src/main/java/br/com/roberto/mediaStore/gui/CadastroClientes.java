package br.com.roberto.mediaStore.gui;

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
import br.com.roberto.mediaStore.gui.TableModels.ClienteTableModel;
import br.com.roberto.mediaStore.services.ClienteService;

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
	JFormattedTextField jtfNascimento;
	JCheckBox chckbxAtivo = new JCheckBox("Ativo");
	
	Integer totallivro;

	/**
	 * Create the frame.
	 */
	public CadastroClientes() {
		super();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 521, 227);

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
		
		
		chckbxAtivo.setBounds(132, 156, 129, 23);
		getContentPane().add(chckbxAtivo);

		habilitarCampos(true);
		gotoPrimeiro();
		totallivro = clienteService.countAll().intValue() - 1;

	}

	private void preencher(Cliente cliente) {
		jtfCodigo.setText(cliente.getId().toString());
		jtfNome.setText(cliente.getNome());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try{
			jtfNascimento.setText(df.format(cliente.getNascimento()));
		} catch(Exception e){jtfNascimento.setText("");}
		chckbxAtivo.setSelected(cliente.isAtivo());		
		
	}

	@Override
	protected void gotoPrimeiro() {
		start = 0;
		cliente =  clienteService.find(start, max);
		preencher(cliente);
		

	}

	@Override
	protected void gotoProximo() {
		if (start < totallivro)
			start += 1;
		cliente =  clienteService.find(start, max);
		preencher(cliente);

	}

	@Override
	protected void gotoAnterior() {
		if (start > 0)
			start -= 1;
		cliente =  clienteService.find(start, max);
		preencher(cliente);

	}

	@Override
	protected void gotoUltimo() {
		start = totallivro;
		cliente =  clienteService.find(start, max);
		preencher(cliente);

	}

	@Override
	protected void pesquisar() {
		buscar(TelaConsulta.getInteger(this, new ClienteTableModel()).longValue());
	}
	
	private void buscar(Long id){
		if (id != null) {
			cliente = clienteService.findById(id);
			if (cliente != null)
				preencher(cliente);
			else
				JOptionPane.showMessageDialog(this, "Livro não encontrado");
		}
	}

	@Override
	protected void vaPara() {
		buscar(LerDado.getInteger(this, "Codigo: ").longValue());
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
		int op = JOptionPane.showConfirmDialog(this, "Confirma exclusão do registro?", "EXCLUSÃO", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE );
		if (op == 0) confirmar();
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
		totallivro = clienteService.countAll().intValue() - 1;		

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
			
		
	}

	@Override
	protected void cancelar() {
		habilitarCampos(true);
		preencher(cliente);
		
	}
	
	
	@Override
	protected void habilitarCampos(boolean habilita){
		super.habilitarCampos(habilita);
		jtfCodigo.setEditable(false);
		jtfNome.setEditable(!habilita);
		jtfNascimento.setEditable(!habilita);
		chckbxAtivo.setEnabled(!habilita);
	}

	@Override
	protected void alterar() {
		habilitarCampos(false);
	}
}
