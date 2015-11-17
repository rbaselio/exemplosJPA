package br.com.roberto.mediaStore.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaConsulta extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3863066712292676755L;
	private final JPanel contentPanel = new JPanel();
	private static JTable table = new JTable();
	private static String retorno;
	private static AbstractTableModel model;

	private static TelaConsulta instance;

	public static Integer getInteger(JFrame pai, AbstractTableModel models) {
		model = models;
		
		if (instance == null)
			instance = new TelaConsulta();
		
		instance.setLocationRelativeTo(pai);
		instance.setTitle("Clique sobre a linha desejado");
		instance.setModal(true);
		instance.setVisible(true);

		try {
			return Integer.parseInt(retorno);
		} catch (Exception e1) {
			return null;
		}

	}

	/**
	 * Create the dialog.
	 */
	private TelaConsulta() {
		setBounds(100, 100, 550, 271);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		table.setModel(model);
		alinhar();
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					retorno = table.getValueAt(table.getSelectedRow(), 0).toString();
					instance.setVisible(false);
				}
			}
		});

		scrollPane.setViewportView(table);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retorno = table.getValueAt(table.getSelectedRow(), 0).toString();
				instance.setVisible(false);
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retorno = null;
				instance.setVisible(false);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}

	private void alinhar() {		
		for (int column = 0; column < table.getColumnModel().getColumnCount(); column++) {
			int width = 50; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			table.getColumnModel().getColumn(column).setPreferredWidth(width);			
		}

	}

}
