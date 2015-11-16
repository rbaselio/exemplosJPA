package br.com.roberto.mediaStore.gui;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import br.com.roberto.mediaStore.entities.Livro;
import br.com.roberto.mediaStore.services.produto.LivroService;
import javax.swing.JFormattedTextField;

public class CadastroLivro extends CadastroBase {
	private static final long serialVersionUID = -5594069332447084539L;

	private LivroService livroService = new LivroService();
	private Livro livro = new Livro();

	private Integer max = 1;
	private Integer start = 0;

	private JTextField jtfCodigo;
	private JTextField jtfDescricao;
	private JTextField jtfISBN;
	JFormattedTextField jtfPreco = new JFormattedTextField();
	Integer totallivro;

	/**
	 * Create the frame.
	 */
	public CadastroLivro() {
		super();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 521, 227);

		JLabel lblCdigo = new JLabel("Código:");
		lblCdigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdigo.setBounds(22, 77, 70, 15);
		getContentPane().add(lblCdigo);

		jtfCodigo = new JTextField();
		jtfCodigo.setBounds(101, 75, 114, 19);
		getContentPane().add(jtfCodigo);
		jtfCodigo.setColumns(10);

		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescrio.setBounds(9, 104, 83, 15);
		getContentPane().add(lblDescrio);

		jtfDescricao = new JTextField();
		jtfDescricao.setBounds(101, 102, 388, 19);
		getContentPane().add(jtfDescricao);
		jtfDescricao.setColumns(10);

		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsbn.setBounds(22, 131, 70, 15);
		getContentPane().add(lblIsbn);

		jtfISBN = new JTextField();
		jtfISBN.setColumns(10);
		jtfISBN.setBounds(101, 129, 191, 19);
		getContentPane().add(jtfISBN);
		
		JLabel lblPreo = new JLabel("Preço:");
		lblPreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreo.setBounds(22, 158, 70, 15);
		getContentPane().add(lblPreo);
		
		
		jtfPreco.setBounds(101, 156, 191, 19);
		jtfPreco.setFormatterFactory(new DefaultFormatterFactory(
                new NumberFormatter(new DecimalFormat("#,##0.00"))));
		
		
		getContentPane().add(jtfPreco);

		habilitarCampos(true);
		gotoPrimeiro();
		totallivro = livroService.countAll().intValue() - 1;

	}

	private void preencher(Livro livro) {
		jtfCodigo.setText(livro.getId().toString());
		jtfDescricao.setText(livro.getDescricao());
		jtfISBN.setText(livro.getISBN());
		DecimalFormat df = new DecimalFormat("#,##0.00") ;
		jtfPreco.setText(df.format(livro.getPreco()));
	}

	@Override
	protected void gotoPrimeiro() {
		start = 0;
		livro =  livroService.find(start, max);
		preencher(livro);
		

	}

	@Override
	protected void gotoProximo() {
		if (start < totallivro)
			start += 1;
		livro =  livroService.find(start, max);
		preencher(livro);

	}

	@Override
	protected void gotoAnterior() {
		if (start > 0)
			start -= 1;
		livro =  livroService.find(start, max);
		preencher(livro);

	}

	@Override
	protected void gotoUltimo() {
		start = totallivro;
		livro =  livroService.find(start, max);
		preencher(livro);

	}

	@Override
	protected void pesquisar() {
		buscar(TelaConsulta.getInteger(this).longValue());
	}
	
	private void buscar(Long id){
		if (id != null) {
			livro = livroService.findById(id);
			if (livro != null)
				preencher(livro);
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
		jtfDescricao.setText("");
		jtfISBN.setText("");
		jtfPreco.setText("");
		operacao = REMOVER;
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
			livro = new Livro();			
			atualizarLivro();
			livroService.persist(livro);
			break;
		case ALTERAR:
			atualizarLivro();			
			livroService.update(livro);
			break;
		case REMOVER:
			livroService.remove(livro);
			gotoAnterior();
			break;		
		default:
			break;
		}
		habilitarCampos(true);
		preencher(livro);
		operacao = 0;		
		totallivro = livroService.countAll().intValue() - 1;		

	}

	private void atualizarLivro() {
		livro.setDescricao(jtfDescricao.getText());
		livro.setISBN(jtfISBN.getText());
		try {
			String numberToFormat = jtfPreco.getText();
	        NumberFormat formatter = NumberFormat.getNumberInstance();
	        Number number = formatter.parse(numberToFormat);
			BigDecimal decimal = BigDecimal.valueOf(number.doubleValue());
			livro.setPreco(decimal);
		} catch (ParseException e) {				
			e.printStackTrace();
		}			
		
	}

	@Override
	protected void cancelar() {
		habilitarCampos(true);
		preencher(livro);
		
	}
	
	
	@Override
	protected void habilitarCampos(boolean habilita){
		super.habilitarCampos(habilita);
		jtfCodigo.setEditable(false);
		jtfDescricao.setEditable(!habilita);
		jtfISBN.setEditable(!habilita);
		jtfPreco.setEditable(!habilita);		
	}

	@Override
	protected void alterar() {
		habilitarCampos(false);
	}
}
