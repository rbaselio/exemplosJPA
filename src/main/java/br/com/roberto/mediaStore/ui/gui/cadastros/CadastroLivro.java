package br.com.roberto.mediaStore.ui.gui.cadastros;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import br.com.roberto.mediaStore.entities.Editora;
import br.com.roberto.mediaStore.entities.Livro;
import br.com.roberto.mediaStore.services.EditoraService;
import br.com.roberto.mediaStore.services.produto.LivroService;
import br.com.roberto.mediaStore.ui.gui.CadastroBase;
import br.com.roberto.mediaStore.ui.gui.LerDado;
import br.com.roberto.mediaStore.ui.gui.TelaConsulta;
import br.com.roberto.mediaStore.ui.gui.cadastros.tablemodels.EditoraTableModel;
import br.com.roberto.mediaStore.ui.gui.cadastros.tablemodels.LivroTableModel;
import br.com.roberto.mediaStore.utils.TamanhoMaximo;

public class CadastroLivro extends CadastroBase {
	private static final long serialVersionUID = -5594069332447084539L;

	private LivroService livroService = new LivroService();
	private Livro livro = new Livro();
	private EditoraService editoraService = new EditoraService();
	private Editora editora = new Editora();

	private Integer max = 1;
	private Integer start = 0;

	private JTextField jtfCodigo;
	private JTextField jtfDescricao;
	private JTextField jtfISBN;
	private JFormattedTextField jtfPreco = new JFormattedTextField();
	private Integer totallivro;
	private JTextField jtfEditoraID;
	private JTextField jtfEditora;
	private CadastroLivro estajanela = this;

	/**
	 * Create the frame.
	 */
	public CadastroLivro() {
		super();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 521, 324);

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
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Editora", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel.setBounds(22, 203, 476, 90);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCdigo_1 = new JLabel("Código:");
		lblCdigo_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdigo_1.setBounds(12, 22, 70, 15);
		panel.add(lblCdigo_1);
		
		jtfEditoraID = new JTextField();
		jtfEditoraID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (jtfEditoraID.isEditable() || e.getClickCount() == 2 ) {
					EditoraTableModel tableModel = new EditoraTableModel();
					tableModel.setService(editoraService);
					editora = tableModel.getEntidade(TelaConsulta.getEntidade(estajanela, tableModel, null));
					livro.setEditora(editora);
					preencher(livro);
				}
			}
		});
		jtfEditoraID.setDocument(new TamanhoMaximo(5, true));
		jtfEditoraID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				editora =  editoraService.findById(Long.parseLong(jtfEditoraID.getText()));
					
				if (editora != null){
					livro.setEditora(editora);
					preencher(editora);
				}
				else{
					JOptionPane.showMessageDialog(null, "Editora não encontrado");
				}
			}

			
		});
		jtfEditoraID.setBounds(88, 20, 79, 19);
		panel.add(jtfEditoraID);
		jtfEditoraID.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(185, 22, 45, 15);
		panel.add(lblNome);
		
		jtfEditora = new JTextField();
		jtfEditora.setEditable(false);
		jtfEditora.setBounds(240, 20, 224, 19);
		panel.add(jtfEditora);
		jtfEditora.setColumns(10);
		
		JButton btnMaisLivrosDesta = new JButton("Livros desta editora?");
		btnMaisLivrosDesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editora = editoraService.findById(Long.parseLong(jtfEditoraID.getText()));
				
				if (editora != null){
					Map<String,Object> dados = new HashMap<String,Object>();
					dados.put( "editora", editora);
					LivroTableModel tableModel = new LivroTableModel();
					tableModel.setService(livroService);
					try {
						livro = tableModel.getEntidade(TelaConsulta.getEntidade(estajanela, tableModel, dados));
						preencher(livro);
					} catch (Exception e2) {

					}
												
				}	
				
			}
		});
		btnMaisLivrosDesta.setBounds(240, 51, 224, 25);
		btnMaisLivrosDesta.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/find.png")));
		panel.add(btnMaisLivrosDesta);

		habilitarCampos(true);
		//gotoPrimeiro();
		totallivro = livroService.countAll().intValue();
		
		this.setTitle("Livros");
		gotoPrimeiro();

	}

	private void preencher(Livro livro) {
		limpar();
		if (livro != null) {
			jtfCodigo.setText(livro.getId().toString());
			jtfDescricao.setText(livro.getDescricao());
			jtfISBN.setText(livro.getISBN());
			DecimalFormat df = new DecimalFormat("#,##0.00");
			jtfPreco.setText(df.format(livro.getPreco()));
			preencher(livro.getEditora());
			
		}
	}
	
	private void preencher(Editora editora) {
		if (editora != null) {
			jtfEditora.setText(livro.getEditora().getNomeEditora());
			jtfEditoraID.setText(livro.getEditora().getId().toString());
		}
		
	}

	private void limpar() {
		jtfCodigo.setText("");
		jtfDescricao.setText("");
		jtfISBN.setText("");
		jtfPreco.setText("");
		jtfEditora.setText("");
		jtfEditoraID.setText("");

	}

	@Override
	protected void gotoPrimeiro() {
		start = 0;
		livro = livroService.find(start, max);
		preencher(livro);

	}

	@Override
	protected void gotoProximo() {
		if (start < totallivro)
			start += 1;
		livro = livroService.find(start, max);
		preencher(livro);

	}

	@Override
	protected void gotoAnterior() {
		if (start > 0)
			start -= 1;
		livro = livroService.find(start, max);
		preencher(livro);

	}

	@Override
	protected void gotoUltimo() {
		start = totallivro - 1;
		livro = livroService.find(start, max);
		preencher(livro);

	}

	@Override
	protected void pesquisar() {
		LivroTableModel tableModel = new LivroTableModel();
		tableModel.setService(livroService);

		livro = tableModel.getEntidade(TelaConsulta.getEntidade(this, tableModel, null));
		preencher(livro);
	}

	@Override
	protected void vaPara() {
		Long id = LerDado.getInteger(this, "Codigo: ").longValue();
		if (id != null) {
			livro = livroService.findById(id);
			if (livro != null)
				preencher(livro);
			else
				JOptionPane.showMessageDialog(this, "Livro não encontrado");
		}
	}

	@Override
	protected void incluir() {
		habilitarCampos(false);
		limpar();

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
		if (editora != null) livro.setEditora(editora);

	}

	@Override
	protected void cancelar() {
		habilitarCampos(true);
		preencher(livro);

	}

	@Override
	protected void habilitarCampos(boolean habilita) {
		super.habilitarCampos(habilita);
		jtfCodigo.setEditable(false);
		jtfDescricao.setEditable(!habilita);
		jtfISBN.setEditable(!habilita);
		jtfPreco.setEditable(!habilita);
		jtfEditoraID.setEditable(!habilita);
	}

	@Override
	protected void alterar() {
		habilitarCampos(false);
	}
}
