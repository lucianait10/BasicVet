/********************************************************
  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *********************************************************/

package cuGestionarProducto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

@SuppressWarnings({ "unchecked", "serial" })
public class GUIGestionarProducto extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelFiltro = null;
	private JPanel panelBotones = null;
	private JLabel labelFiltro = null;
	private JTextField campoFiltro = null;
	private JComboBox campoMotivoFiltro = null;
	private JScrollPane panelTabla = null;
	private JTable tablaProductos = null;
	private JButton botonAgregar = null;
	private JButton botonModificar = null;
	private JButton botonEliminar = null;
	private TableRowSorter<DefaultTableModel> tFiltro;
	private JButton botonReporteProducto = null;
	private JLabel labelFiltrarPor = null;

	public GUIGestionarProducto(DefaultTableModel m) {
		super();
		this.tFiltro = new TableRowSorter(m);
		this.tablaProductos = new JTable(m){
			
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
				Component returnComp = super.prepareRenderer(renderer, row,column);
				Float cant= Float.parseFloat(getValueAt(row,5).toString());
				if(cant==0){
					returnComp.setBackground(Color.RED);
					returnComp.setForeground(Color.BLACK);
				}
				else{
					returnComp.setBackground(Color.GREEN);
					returnComp.setForeground(Color.BLACK);
				}
				if(this.getSelectedRow()==row){
					returnComp.setBackground(Color.BLUE);
					returnComp.setForeground(Color.WHITE);}
				return returnComp;
			}
			
			}; 
		this.tablaProductos.getTableHeader().setReorderingAllowed(false);
		this.tablaProductos.setRowSorter(tFiltro);
		cerrarVentana();
		initialize();
	}

	private void cerrarVentana(){
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
		getRootPane().getActionMap().put("Cancel", new AbstractAction(){
			
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				dispose();
			}
		});
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent evt){
				setVisible(false);
				dispose();
			}
		});
	}
	private void initialize() {
		this.setSize(800, 600);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setTitle("GESTIONAR PRODUCTOS");
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelFiltro(),BorderLayout.NORTH);
			jContentPane.add(getPanelTabla(),BorderLayout.CENTER);
			jContentPane.add(getPanelBotones(),BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	private JPanel getPanelFiltro() {
		if (panelFiltro == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints3.gridx = 3;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 2;
			gridBagConstraints11.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.NONE;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(5, 0, 5, 280);
			gridBagConstraints1.gridheight = 1;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints.fill = GridBagConstraints.NONE;
			gridBagConstraints.gridy = 0;
			labelFiltro = new JLabel();
			labelFiltro.setText("FILTRO:");
			panelFiltro = new JPanel();
			panelFiltro.setLayout(new GridBagLayout());
			panelFiltro.setSize(new Dimension(685, 42));
			panelFiltro.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelFiltro.add(labelFiltro, gridBagConstraints);
			panelFiltro.add(getCampoFiltro(), gridBagConstraints1);
			panelFiltro.add(getLabelFiltrarPor(), gridBagConstraints11);
			panelFiltro.add(getCampoMotivoFiltro(), gridBagConstraints3);
		}
		return panelFiltro;
	}

	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.setSize(new Dimension(678, 40));
			panelBotones.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelBotones.add(getBotonAgregar(), null);
			panelBotones.add(getBotonModificar(), null);
			panelBotones.add(getBotonEliminar(), null);
			panelBotones.add(getBotonReporteProducto(), null);
		}
		return panelBotones;
	}

	private JTextField getCampoFiltro() {
		if (campoFiltro == null) {
			campoFiltro = new JTextField();
			campoFiltro.setPreferredSize(new Dimension(150, 25));
			campoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
				
				public void keyReleased(final java.awt.event.KeyEvent e) {
					switch(campoMotivoFiltro.getSelectedIndex()){
					case 1:tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toUpperCase(), 1));
					break;

					case 2:tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toUpperCase(), 2));
					break;

					case 3:tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toUpperCase(), 3));
					break;

					case 4:tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toUpperCase(), 4));
					break;

					default :tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toString(), 0));
					}

				}
			});
		}
		return campoFiltro;
	}

	private JComboBox getCampoMotivoFiltro() {
		if (campoMotivoFiltro == null) {
			Object[] opciones = {"ID","NOMBRE","TIPO","DESCRIPCION","PRECIO"};
			campoMotivoFiltro = new JComboBox(opciones);
			campoMotivoFiltro.setPreferredSize(new Dimension(150, 25));
		}
		return campoMotivoFiltro;
	}

	private JScrollPane getPanelTabla() {
		if (panelTabla == null) {
			panelTabla = new JScrollPane();
			panelTabla.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelTabla.setSize(new Dimension(125, 88));
			panelTabla.setViewportView(getTablaProductos());
		}
		return panelTabla;
	}

	public JTable getTablaProductos() {
		if (tablaProductos == null) {
			tablaProductos = new JTable();
		}
		return tablaProductos;
	}

	public JButton getBotonAgregar() {
		if (botonAgregar == null) {
			botonAgregar = new JButton();
			botonAgregar.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
			botonAgregar.setText("INGRESAR PRODUCTO");
			botonAgregar.setMnemonic('I');
			botonAgregar.registerKeyboardAction(botonAgregar.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);

			botonAgregar.registerKeyboardAction(botonAgregar.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
			//botonAgregar.setPreferredSize(new Dimension(180, 30));
		}
		return botonAgregar;
	}

	public JButton getBotonModificar() {
		if (botonModificar == null) {
			botonModificar = new JButton();
			botonModificar.setIcon(new ImageIcon(getClass().getResource("/icono/script_edit.png")));
			botonModificar.setText("MODIFICAR PRODUCTO");
			botonModificar.setMnemonic('M');
			botonModificar.registerKeyboardAction(botonModificar.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);

			botonModificar.registerKeyboardAction(botonModificar.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
			//botonModificar.setPreferredSize(new Dimension(190, 30));
		}
		return botonModificar;
	}

	public JButton getBotonEliminar() {
		if (botonEliminar == null) {
			botonEliminar = new JButton();
			botonEliminar.setIcon(new ImageIcon(getClass().getResource("/icono/delete.png")));
			botonEliminar.setText("ELIMINAR PRODUCTO");
			botonEliminar.setMnemonic('E');
			botonEliminar.registerKeyboardAction(botonEliminar.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);

			botonEliminar.registerKeyboardAction(botonEliminar.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
			//botonEliminar.setPreferredSize(new Dimension(180, 30));
		}
		return botonEliminar;
	}

	public void setListenerButtons(ActionListener al){
		this.botonAgregar.addActionListener(al);
		this.botonEliminar.addActionListener(al);
		this.botonModificar.addActionListener(al);
		this.botonReporteProducto.addActionListener(al);
	}

	public void setListenerMouse(MouseAdapter ma){
		this.tablaProductos.addMouseListener(ma);
	}
	
	public JButton getBotonReporteProducto() {
		if (botonReporteProducto == null) {
			botonReporteProducto = new JButton("REPORTE");
			botonReporteProducto.setMnemonic('R');
			botonReporteProducto.setIcon(new ImageIcon(getClass().getResource("/icono/iconReport.png")));
			botonReporteProducto.registerKeyboardAction(botonReporteProducto.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);

			botonReporteProducto.registerKeyboardAction(botonReporteProducto.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
			//botonReporteProducto.setPreferredSize(new Dimension(120, 30));
		}
		return botonReporteProducto;
	}

	public TableRowSorter getTableRowSorter(){
		return this.tFiltro;
	}

	/**
	 * This method initializes labelFiltrarPor	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLabelFiltrarPor() {
		if (labelFiltrarPor == null) {
			labelFiltrarPor = new JLabel();
			labelFiltrarPor.setText("FILTRAR POR:");
		}
		return labelFiltrarPor;
	}

}
