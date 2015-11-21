package br.com.roberto.mediaStore.ui.gui.cadastros;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import br.com.roberto.mediaStore.entities.Filme;
import br.com.roberto.mediaStore.services.produto.FilmeService;
import br.com.roberto.mediaStore.ui.gui.CadastroBase;
import br.com.roberto.mediaStore.ui.gui.LerDado;
import br.com.roberto.mediaStore.ui.gui.TelaConsulta;
import br.com.roberto.mediaStore.ui.gui.cadastros.tablemodels.FilmeTableModel;
import br.com.roberto.mediaStore.utils.TamanhoMaximo;

public class CadastroFilme extends CadastroBase {
	private static final long serialVersionUID = -5594069332447084539L;

	private FilmeService filmeService = new FilmeService();
	private Filme filme = new Filme();

	private Integer max = 1;
	private Integer start = 0;

	private JTextField jtfCodigo;
	private JTextField jtfDescricao;
	private JTextField jtfDuracao;
	JFormattedTextField jtfPreco = new JFormattedTextField();
	Integer totalfilme;

	/**
	 * Create the frame.
	 */
	public CadastroFilme() {
		super();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 521, 215);

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

		JLabel lblIsbn = new JLabel("Duração:");
		lblIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsbn.setBounds(22, 131, 70, 15);
		getContentPane().add(lblIsbn);

		jtfDuracao = new JTextField();
		jtfDuracao.setColumns(10);
		jtfDuracao.setBounds(101, 129, 103, 19);
		jtfDuracao.setDocument(new TamanhoMaximo(5, true));
		getContentPane().add(jtfDuracao);
		
		JLabel lblMinutos = new JLabel("minutos");
		lblMinutos.setBounds(212, 133, 70, 15);
		getContentPane().add(lblMinutos);
		
		JLabel lblPreo = new JLabel("Preço:");
		lblPreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreo.setBounds(22, 158, 70, 15);
		getContentPane().add(lblPreo);
		
		
		jtfPreco.setBounds(101, 156, 140, 19);
		jtfPreco.setFormatterFactory(new DefaultFormatterFactory(
                new NumberFormatter(new DecimalFormat("#,##0.00"))));
		
		
		getContentPane().add(jtfPreco);
		

		habilitarCampos(true);
		gotoPrimeiro();
		totalfilme = filmeService.countAll().intValue();
		
		this.setTitle("Filmes");

	}

	private void preencher(Filme filme) {
		if (filme != null) {
			jtfCodigo.setText(filme.getId().toString());
			jtfDescricao.setText(filme.getDescricao());
			jtfDuracao.setText(filme.getDuracao().toString());
			DecimalFormat df = new DecimalFormat("#,##0.00") ;
			jtfPreco.setText(df.format(filme.getPreco()));
		}
		
	}

	@Override
	protected void gotoPrimeiro() {
		start = 0;
		try{
		filme =  filmeService.find(start, max);
		preencher(filme);
		}catch(Exception e){}	
		

	}

	@Override
	protected void gotoProximo() {
		if (start < totalfilme)
			start += 1;
		filme =  filmeService.find(start, max);
		preencher(filme);

	}

	@Override
	protected void gotoAnterior() {
		if (start > 0)
			start -= 1;
		filme =  filmeService.find(start, max);
		preencher(filme);

	}

	@Override
	protected void gotoUltimo() {
		start = totalfilme;
		filme =  filmeService.find(start, max);
		preencher(filme);

	}

	@Override
	protected void pesquisar() {
		FilmeTableModel tableModel = new FilmeTableModel();
		tableModel.setService(filmeService);
		filme = tableModel.getEntidade(TelaConsulta.getEntidade(this, tableModel));
		preencher(filme);
		
	}

	

	@Override
	protected void vaPara() {
		Long id = LerDado.getInteger(this, "Codigo: ").longValue();
		if (id != null) {
			filme = filmeService.findById(id);
			if (filme != null)
				preencher(filme);
			else
				JOptionPane.showMessageDialog(this, "Filme não encontrado");
		}
	}

	@Override
	protected void incluir() {
		habilitarCampos(false);		
		limpar();		
	}

	private void limpar() {
		jtfCodigo.setText("");
		jtfDescricao.setText("");
		jtfDuracao.setText("");
		jtfPreco.setText("");
	}	

	@Override
	protected void remover() {
		habilitarCampos(true);
		limpar();
		int op = JOptionPane.showConfirmDialog(this, "Confirma exclusão do registro?", "EXCLUSÃO", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE );
		if (op == 0) confirmar();
	}

	@Override
	protected void confirmar() {
		switch (operacao) {
		case INCLUIR:
			filme = new Filme();			
			atualizarFilme();
			filmeService.persist(filme);
			break;
		case ALTERAR:
			atualizarFilme();			
			filmeService.update(filme);
			break;
		case REMOVER:
			filmeService.remove(filme);
			gotoAnterior();
			break;		
		default:
			break;
		}
		habilitarCampos(true);
		preencher(filme);
		operacao = 0;		
		totalfilme = filmeService.countAll().intValue() - 1;		

	}

	private void atualizarFilme() {
		filme.setDescricao(jtfDescricao.getText());
		filme.setDuracao(Integer.parseInt(jtfDuracao.getText()));	
		try {
			String numberToFormat = jtfPreco.getText();
	        NumberFormat formatter = NumberFormat.getNumberInstance();
	        Number number = formatter.parse(numberToFormat);
			BigDecimal decimal = BigDecimal.valueOf(number.doubleValue());
			filme.setPreco(decimal);
		} catch (ParseException e) {				
			e.printStackTrace();
		}		
	}

	@Override
	protected void cancelar() {
		habilitarCampos(true);
		preencher(filme);
		
	}
	
	
	@Override
	protected void habilitarCampos(boolean habilita){
		super.habilitarCampos(habilita);
		jtfCodigo.setEditable(false);
		jtfDescricao.setEditable(!habilita);
		jtfDuracao.setEditable(!habilita);
		jtfPreco.setEditable(!habilita);
	}

	@Override
	protected void alterar() {
		habilitarCampos(false);
	}
}
