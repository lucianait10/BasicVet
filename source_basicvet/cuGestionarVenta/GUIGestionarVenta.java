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

package cuGestionarVenta;

import java.awt.BorderLayout;
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
import javax.swing.table.TableRowSorter;

public class GUIGestionarVenta extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelFiltro = null;
	private JPanel panelBotones = null;
	private JLabel labelFiltro = null;
	private JTextField campoFiltro = null;
	private JButton botonAgregar = null;
	private JButton botonModificar = null;
	private JButton botonEliminar = null;
	private TableRowSorter<DefaultTableModel> tFiltro;
	private JPanel panelNorte = null;
	private JScrollPane scrollPanel = null;
	private JTable tablaVentas = null;
	private JComboBox campoMotivoFiltro = null;
	private JButton botonReporte;
	private JLabel labelFiltrarPor;
	
	@SuppressWarnings({"unchecked" })
	public GUIGestionarVenta(DefaultTableModel m) {
		super();
		this.tFiltro = new TableRowSorter(m);
		this.tablaVentas = new JTable(m);
		this.tablaVentas.getTableHeader().setReorderingAllowed(false);
		this.tablaVentas.setRowSorter(tFiltro);
		cerrarVentana();
		initialize();
	}

	
	@SuppressWarnings("serial")
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
		this.setTitle("GESTIONAR VENTA");
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelBotones(),BorderLayout.SOUTH);
			jContentPane.add(getPanelNorte(), BorderLayout.NORTH);
			jContentPane.add(getScrollPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	private JPanel getPanelFiltro() {
		if (panelFiltro == null) {
			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints51.gridy = 0;
			gridBagConstraints51.weightx = 1.0;
			gridBagConstraints51.gridx = 3;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 2;
			gridBagConstraints3.gridy = 0;
			labelFiltrarPor = new JLabel();
			labelFiltrarPor.setText("FILTRAR POR:");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(0, 0, 0, 280);
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints.gridy = 0;
			labelFiltro = new JLabel();
			labelFiltro.setText("FILTRO:");
			panelFiltro = new JPanel();
			panelFiltro.setLayout(new GridBagLayout());
			panelFiltro.setSize(new Dimension(685, 42));
			panelFiltro.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelFiltro.setPreferredSize(new Dimension(487, 40));
			panelFiltro.add(labelFiltro, gridBagConstraints);
			panelFiltro.add(getCampoMotivoFiltro(), gridBagConstraints51);
			panelFiltro.add(labelFiltrarPor, gridBagConstraints3);
			panelFiltro.add(getCampoFiltro(), gridBagConstraints1);
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
			panelBotones.add(getBotonReporte());
		}
		return panelBotones;
	}

	private JTextField getCampoFiltro() {
		if (campoFiltro == null) {
			campoFiltro = new JTextField();
			campoFiltro.setPreferredSize(new Dimension(150, 20));
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
	                	
	                	case 5:tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toUpperCase(), 5));
	                	break;
	                	
	                	case 6:tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toUpperCase(), 6));
	                	break;
	                	
	                	default :tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toString(), 0));
	                }
	                
	            }
	        });
		}
		return campoFiltro;
	}

	public JButton getBotonAgregar() {
		if (botonAgregar == null) {
			botonAgregar = new JButton();
			botonAgregar.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
			botonAgregar.setText("INGRESAR VENTA");
			botonAgregar.setMnemonic('I');
			botonAgregar.registerKeyboardAction(botonAgregar.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);

			botonAgregar.registerKeyboardAction(botonAgregar.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
			//botonAgregar.setPreferredSize(new Dimension(160, 30));
		}
		return botonAgregar;
	}

	public JButton getBotonModificar() {
		if (botonModificar == null) {
			botonModificar = new JButton();
			botonModificar.setIcon(new ImageIcon(getClass().getResource("/icono/script_edit.png")));
			botonModificar.setText("MODIFICAR VENTA");
			botonModificar.setMnemonic('M');
			botonModificar.registerKeyboardAction(botonModificar.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);

			botonModificar.registerKeyboardAction(botonModificar.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
			//botonModificar.setPreferredSize(new Dimension(170, 30));
		}
		return botonModificar;
	}

	public JButton getBotonEliminar() {
		if (botonEliminar == null) {
			botonEliminar = new JButton();
			botonEliminar.setIcon(new ImageIcon(getClass().getResource("/icono/delete.png")));
			botonEliminar.setText("ELIMINAR VENTA");
			botonEliminar.setMnemonic('E');
			botonEliminar.registerKeyboardAction(botonEliminar.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);

			botonEliminar.registerKeyboardAction(botonEliminar.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
			//botonEliminar.setPreferredSize(new Dimension(160, 30));
		}
		return botonEliminar;
	}
	
	

	public void setListenerButtons(ActionListener al){
		this.botonAgregar.addActionListener(al);
		this.botonEliminar.addActionListener(al);
		this.botonModificar.addActionListener(al);
		this.botonReporte.addActionListener(al);
	}
	
	public void setListenerMouse(MouseAdapter ma){
		this.tablaVentas.addMouseListener(ma);
	}

	/**
	 * This method initializes panelNorte	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();
			panelNorte.setLayout(new BorderLayout());
			panelNorte.add(getPanelFiltro(), BorderLayout.SOUTH);
		}
		return panelNorte;
	}

	/**
	 * This method initializes scrollPanel	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollPanel() {
		if (scrollPanel == null) {
			scrollPanel = new JScrollPane();
			scrollPanel.setViewportView(getTablaVentas());
		}
		return scrollPanel;
	}

	/**
	 * This method initializes tablaCompra	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getTablaVentas() {
		if (tablaVentas == null) {
			tablaVentas = new JTable();
		}
		return tablaVentas;
	}

	/**
	 * This method initializes campoMotivoFiltro	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getCampoMotivoFiltro() {
		if (campoMotivoFiltro == null) {
			Object[] opciones = {"ID","FECHA","PROVEEDOR","PRODUCTO","CANTIDAD","P. UNITARIO","TOTAL"};
			campoMotivoFiltro = new JComboBox(opciones);
			campoMotivoFiltro.setPreferredSize(new Dimension(150, 25));
			
		}
		return campoMotivoFiltro;
	}
	
	
	

	@SuppressWarnings("unchecked")
	public TableRowSorter getTableRowSorter(){
		return this.tFiltro;
	}
	
	public JButton getBotonReporte() {
		if (botonReporte == null) {
			botonReporte = new JButton("REPORTE");
			botonReporte.setIcon(new ImageIcon(getClass().getResource("/icono/iconReport.png")));
			botonReporte.setText("REPORTE");
			botonReporte.setMnemonic('R');
			botonReporte.registerKeyboardAction(botonReporte.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);

			botonReporte.registerKeyboardAction(botonReporte.getActionForKeyStroke(
					KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
			//botonReporte.setPreferredSize(new Dimension(120, 30));
		}
		return botonReporte;
	}
}  
