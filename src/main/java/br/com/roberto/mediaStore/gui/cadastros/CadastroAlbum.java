package br.com.roberto.mediaStore.gui.cadastros;

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

import br.com.roberto.mediaStore.entities.Album;
import br.com.roberto.mediaStore.gui.CadastroBase;
import br.com.roberto.mediaStore.gui.LerDado;
import br.com.roberto.mediaStore.gui.TelaConsulta;
import br.com.roberto.mediaStore.gui.TableModels.AlbumTableModel;
import br.com.roberto.mediaStore.services.produto.AlbumService;
import br.com.roberto.mediaStore.utils.TamanhoMaximo;

public class CadastroAlbum extends CadastroBase {
	private static final long serialVersionUID = -5594069332447084539L;

	private AlbumService albumService = new AlbumService();
	private Album album = new Album();

	private Integer max = 1;
	private Integer start = 0;

	private JTextField jtfCodigo;
	private JTextField jtfDescricao;
	private JTextField jtfFaixas;
	JFormattedTextField jtfPreco = new JFormattedTextField();
	Integer totalalbum;

	/**
	 * Create the frame.
	 */
	public CadastroAlbum() {
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

		jtfFaixas = new JTextField();
		jtfFaixas.setColumns(10);
		jtfFaixas.setBounds(101, 129, 103, 19);
		jtfFaixas.setDocument(new TamanhoMaximo(2, true));
		getContentPane().add(jtfFaixas);
		
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
		totalalbum = albumService.countAll().intValue();
		
		this.setTitle("Albums");

	}

	private void preencher(Album album) {
		if (album != null){
			jtfCodigo.setText(album.getId().toString());
			jtfDescricao.setText(album.getDescricao());
			jtfFaixas.setText(album.getFaixas().toString());
			DecimalFormat df = new DecimalFormat("#,##0.00") ;
			jtfPreco.setText(df.format(album.getPreco()));
		}
		
	}

	@Override
	protected void gotoPrimeiro() {
		start = 0;
		try{
		album =  albumService.find(start, max);
		preencher(album);
		}catch(Exception e){}
		
		

	}

	@Override
	protected void gotoProximo() {
		if (start < totalalbum)
			start += 1;
		album =  albumService.find(start, max);
		preencher(album);

	}

	@Override
	protected void gotoAnterior() {
		if (start > 0)
			start -= 1;
		album =  albumService.find(start, max);
		preencher(album);

	}

	@Override
	protected void gotoUltimo() {
		start = totalalbum;
		album =  albumService.find(start, max);
		preencher(album);

	}

	@Override
	protected void pesquisar() {		
		AlbumTableModel tableModel = new AlbumTableModel();
		tableModel.setService(albumService);
		album = tableModel.getEntidade(TelaConsulta.getEntidade(this, tableModel));
		preencher(album);
	}
	

	@Override
	protected void vaPara() {
		Long id = LerDado.getInteger(this, "Codigo: ").longValue();
		if (id != null) {
			album = albumService.findById(id);
			if (album != null)
				preencher(album);
			else
				JOptionPane.showMessageDialog(this, "Album não encontrado");
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
		jtfFaixas.setText("");
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
			album = new Album();			
			atualizarAlbum();
			albumService.persist(album);
			break;
		case ALTERAR:
			atualizarAlbum();			
			albumService.update(album);
			break;
		case REMOVER:
			albumService.remove(album);
			gotoAnterior();
			break;		
		default:
			break;
		}
		habilitarCampos(true);
		preencher(album);
		operacao = 0;		
		totalalbum = albumService.countAll().intValue() - 1;		

	}

	private void atualizarAlbum() {
		album.setDescricao(jtfDescricao.getText());
		album.setFaixas(Integer.parseInt(jtfFaixas.getText()));	
		try {
			String numberToFormat = jtfPreco.getText();
	        NumberFormat formatter = NumberFormat.getNumberInstance();
	        Number number = formatter.parse(numberToFormat);
			BigDecimal decimal = BigDecimal.valueOf(number.doubleValue());
			album.setPreco(decimal);
		} catch (ParseException e) {				
			e.printStackTrace();
		}		
	}

	@Override
	protected void cancelar() {
		habilitarCampos(true);
		preencher(album);
		
	}
	
	
	@Override
	protected void habilitarCampos(boolean habilita){
		super.habilitarCampos(habilita);
		jtfCodigo.setEditable(false);
		jtfDescricao.setEditable(!habilita);
		jtfFaixas.setEditable(!habilita);
		jtfPreco.setEditable(!habilita);
	}

	@Override
	protected void alterar() {
		habilitarCampos(false);
	}
}
