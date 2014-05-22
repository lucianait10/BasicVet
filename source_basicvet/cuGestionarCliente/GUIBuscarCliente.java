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

package cuGestionarCliente;

import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class GUIBuscarCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelBotones = null;
	private JButton botonAceptar = null;
	private JButton botonCancelar = null;
	private JScrollPane scrollPanel = null;  
	private JTable tablaBusqueda = null;
	private JButton botonAgregar = null;
	private JTextField textoDni = null;
	private JTextField textoNombre = null;
	private JPanel datosBusqueda = null;
	private JPanel panelBusqueda = null;
	private JLabel labelDni = null;
	private JLabel labelNombre = null;
	private TableRowSorter<DefaultTableModel> tFiltro;
	private JLabel  labelApellido= null;
	private JTextField textoApellido = null;
	/**
	 * This is the default constructor
	 */
	@SuppressWarnings({ "unchecked" })
	public GUIBuscarCliente(DefaultTableModel t) {
		super();
		tFiltro = new TableRowSorter(t);
		this.tablaBusqueda=new JTable(t);
		this.tablaBusqueda.setRowSorter(tFiltro);
		initialize();
		cerrarVentana();
	}
	
	@SuppressWarnings({ "serial" })
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
	    	
			public void windowClosing(java.awt.event.WindowEvent evt){
	    		setVisible(false);
	    		dispose();
	    	}
	    });
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(800, 600);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setContentPane(getJContentPane());
		this.setTitle("BUSCAR CLIENTES");
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelBotones(), BorderLayout.SOUTH);
			jContentPane.add(getDatosBusqueda(), BorderLayout.NORTH);
			jContentPane.add(getPanelBusqueda(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelBotones	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 3;
			gridBagConstraints3.gridy = 2;
			panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.add(getBotonAceptar(), null);
			panelBotones.add(getBotonCancelar(), null);
			panelBotones.add(getBotonAgregar(), null);
		}
		return panelBotones;
	}

	/**
	 * This method initializes botonAceptar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonAceptar() {
		if (botonAceptar == null) {
			botonAceptar = new JButton();
			botonAceptar.setIcon(new ImageIcon(getClass().getResource("/icono/accept.png")));
			botonAceptar.setMnemonic('A');
			botonAceptar.setText("ACEPTAR");
		}
		return botonAceptar;
	}

	/**
	 * This method initializes botonCancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonCancelar() {
		if (botonCancelar == null) {
			botonCancelar = new JButton();
			botonCancelar.setIcon(new ImageIcon(getClass().getResource("/icono/cancel.png")));
			botonCancelar.setMnemonic('C');
			botonCancelar.setText("CANCELAR");
		}
		return botonCancelar;
	}

	/**
	 * This method initializes scrollPanel	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollPanel() {
		if (scrollPanel == null) {
			scrollPanel = new JScrollPane();
			scrollPanel.setViewportView(getTablaBusqueda());
		}
		return scrollPanel;
	}

	/**
	 * This method initializes tablaBusqueda	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getTablaBusqueda() {
		if (tablaBusqueda == null) {
			tablaBusqueda = new JTable();
		}
		return tablaBusqueda;
	}

	/**
	 * This method initializes botonAgregar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonAgregar() {
		if (botonAgregar == null) {
			botonAgregar = new JButton();
			botonAgregar.setText("NUEVO CLIENTE");
			botonAgregar.setMnemonic('N');
			botonAgregar.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
		}
		return botonAgregar;
	}

	/**
	 * This method initializes textoId	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextoDni() {
		if (textoDni == null) {
			textoDni = new JTextField();
			textoDni.setBackground(Color.CYAN);
			textoDni.setText("");
			textoDni.addKeyListener(new java.awt.event.KeyAdapter() {
				
				public void keyReleased(final java.awt.event.KeyEvent e) {
	                int columna = 0;
	                tFiltro.setRowFilter(RowFilter.regexFilter(textoDni.getText().toUpperCase(), columna));
	            }
	        });
		}
		textoDni.selectAll();
		return textoDni;
	}

	/**
	 * This method initializes textoNombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextoNombre() {
		if (textoNombre == null) {
			textoNombre = new JTextField();
			textoNombre.setBackground(Color.CYAN);
			textoNombre.setText("");
			textoNombre.addKeyListener(new java.awt.event.KeyAdapter() {
				
				public void keyReleased(final java.awt.event.KeyEvent e) {
	                int columna = 1;
	                tFiltro.setRowFilter(RowFilter.regexFilter(textoNombre.getText().toUpperCase(), columna));
	            }
	        }); 
		}
		textoNombre.selectAll();
		return textoNombre;
	}

	/**
	 * This method initializes datosBusqueda	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDatosBusqueda() {
		if (datosBusqueda == null) {
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.fill = GridBagConstraints.NONE;
			gridBagConstraints31.gridy = 0;
			gridBagConstraints31.weightx = 1.0;
			gridBagConstraints31.ipadx = 180;
			gridBagConstraints31.insets = new Insets(10, 5, 10, 5);
			gridBagConstraints31.anchor = GridBagConstraints.WEST;
			gridBagConstraints31.gridx = 5;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 4;
			gridBagConstraints11.gridy = 0;
			labelApellido = new JLabel();
			labelApellido.setText("APELLIDO:");
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 2;
			gridBagConstraints10.gridy = 0;
			labelNombre = new JLabel();
			labelNombre.setText("NOMBRE:");
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints9.gridy = 0;
			labelDni = new JLabel();
			labelDni.setText("DNI:");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.NONE;
			gridBagConstraints1.gridx = 3;
			gridBagConstraints1.insets = new Insets(10, 5, 10, 5);
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.ipadx = 180;
			gridBagConstraints1.weightx = 1.0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints.gridx = 1;
			gridBagConstraints.insets = new Insets(10, 5, 10, 5);
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.ipadx = 180;
			gridBagConstraints.weightx = 1.0;
			datosBusqueda = new JPanel();
			datosBusqueda.setLayout(new GridBagLayout());
			datosBusqueda.add(getTextoDni(), gridBagConstraints);
			datosBusqueda.add(getTextoNombre(), gridBagConstraints1);
			datosBusqueda.add(labelDni, gridBagConstraints9);
			datosBusqueda.add(labelNombre, gridBagConstraints10);
			datosBusqueda.add(labelApellido, gridBagConstraints11);
			datosBusqueda.add(getTextoApellido(), gridBagConstraints31);
		}
		return datosBusqueda;
	}

	/**
	 * This method initializes panelBusqueda	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelBusqueda() {
		if (panelBusqueda == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.gridx = 0;
			panelBusqueda = new JPanel();
			panelBusqueda.setLayout(new GridBagLayout());
			panelBusqueda.add(getScrollPanel(), gridBagConstraints2);
		}
		return panelBusqueda;
	}
	
	public void setListenerButtons(ActionListener al){
		this.botonAceptar.addActionListener(al);
		this.botonCancelar.addActionListener(al);
		this.botonAgregar.addActionListener(al);
	}

	/**
	 * This method initializes textoTipo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextoApellido() {
		if (textoApellido == null) {
			textoApellido = new JTextField();
			textoApellido.setBackground(Color.CYAN);
			textoApellido.setText("");
			textoApellido.addKeyListener(new java.awt.event.KeyAdapter() {
				
				public void keyTyped(java.awt.event.KeyEvent e) {
					int columna = 2;
	                tFiltro.setRowFilter(RowFilter.regexFilter(textoApellido.getText().toUpperCase(), columna));
				}
			});
		}
		textoApellido.selectAll();
		return textoApellido;
	}

	public void setListenerMouse(MouseAdapter ma){
		this.tablaBusqueda.addMouseListener(ma);
	}
	
	@SuppressWarnings("unchecked")
	public TableRowSorter getTableRowSorter(){
		return this.tFiltro;
	}

}
