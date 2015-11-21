package br.com.roberto.mediaStore.ui.gui.cadastros;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.roberto.mediaStore.entities.Editora;
import br.com.roberto.mediaStore.services.EditoraService;
import br.com.roberto.mediaStore.ui.gui.CadastroBase;
import br.com.roberto.mediaStore.ui.gui.LerDado;
import br.com.roberto.mediaStore.ui.gui.TelaConsulta;
import br.com.roberto.mediaStore.ui.gui.cadastros.tablemodels.EditoraTableModel;

public class CadastroEditora extends CadastroBase {
	private static final long serialVersionUID = -5594069332447084539L;

	private EditoraService editoraService = new EditoraService();
	private Editora editora = new Editora();

	private Integer max = 1;
	private Integer start = 0;

	private JTextField jtfCodigo;
	private JTextField jtfNome;
	Integer totaleditora;

	/**
	 * Create the frame.
	 */
	public CadastroEditora() {
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

		JLabel lblDescrio = new JLabel("Nome:");
		lblDescrio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescrio.setBounds(9, 104, 83, 15);
		getContentPane().add(lblDescrio);

		jtfNome = new JTextField();
		jtfNome.setBounds(101, 102, 388, 19);
		getContentPane().add(jtfNome);
		jtfNome.setColumns(10);

		habilitarCampos(true);
		gotoPrimeiro();
		totaleditora = editoraService.countAll().intValue() - 1;

		this.setTitle("Editoras");

	}

	private void preencher(Editora editora) {
		if (editora != null) {
			jtfCodigo.setText(editora.getId().toString());
			jtfNome.setText(editora.getNomeEditora());

		}
	}

	@Override
	protected void gotoPrimeiro() {
		start = 0;
		editora = editoraService.find(start, max);
		preencher(editora);

	}

	@Override
	protected void gotoProximo() {
		if (start < totaleditora)
			start += 1;
		editora = editoraService.find(start, max);
		preencher(editora);

	}

	@Override
	protected void gotoAnterior() {
		if (start > 0)
			start -= 1;
		editora = editoraService.find(start, max);
		preencher(editora);

	}

	@Override
	protected void gotoUltimo() {
		start = totaleditora;
		editora = editoraService.find(start, max);
		preencher(editora);

	}

	@Override
	protected void pesquisar() {

		EditoraTableModel tableModel = new EditoraTableModel();
		tableModel.setService(editoraService);
		try {
			editora = tableModel.getEntidade(TelaConsulta.getEntidade(this, tableModel));
			preencher(editora);
		} catch (NullPointerException e) {

		}

	}

	@Override
	protected void vaPara() {
		Long id = LerDado.getInteger(this, "Codigo: ").longValue();
		if (id != null) {
			editora = editoraService.findById(id);
			if (editora != null)
				preencher(editora);
			else
				JOptionPane.showMessageDialog(this, "Editora não encontrado");
		}
	}

	@Override
	protected void incluir() {
		habilitarCampos(false);
		jtfCodigo.setText("");
		jtfNome.setText("");

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
			editora = new Editora();
			atualizarEditora();
			editoraService.persist(editora);
			break;
		case ALTERAR:
			atualizarEditora();
			editoraService.update(editora);
			break;
		case REMOVER:
			editoraService.remove(editora);
			gotoAnterior();
			break;
		default:
			break;
		}
		habilitarCampos(true);
		preencher(editora);
		operacao = 0;
		totaleditora = editoraService.countAll().intValue() - 1;

	}

	private void atualizarEditora() {
		editora.setNomeEditora(jtfNome.getText());

	}

	@Override
	protected void cancelar() {
		habilitarCampos(true);
		preencher(editora);

	}

	@Override
	protected void habilitarCampos(boolean habilita) {
		super.habilitarCampos(habilita);
		jtfCodigo.setEditable(false);
		jtfNome.setEditable(!habilita);
	}

	@Override
	protected void alterar() {
		habilitarCampos(false);
	}
}
