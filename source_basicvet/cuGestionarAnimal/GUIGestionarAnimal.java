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

package cuGestionarAnimal;

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


public class GUIGestionarAnimal extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelFiltro = null;
	private JPanel panelBotones = null;
	private JLabel labelFiltro = null;  
	private JTextField campoFiltro = null;
	
	private JComboBox campoMotivoFiltro = null;
	private JScrollPane panelTabla = null;
	private JTable tablaAnimales = null;
	private JButton botonAgregarAnimal = null;
	private JButton botonModificarAnimal = null;
	private JButton botonEliminarAnimal = null;
	private TableRowSorter<DefaultTableModel> tFiltro;
	private JLabel labelFiltrarPor = null;
	private JButton botonReporte = null;
	
	@SuppressWarnings("unchecked")
	public GUIGestionarAnimal(DefaultTableModel m) {
		super();
		this.tFiltro = new TableRowSorter(m);
		this.tablaAnimales = new JTable(m);
		this.tablaAnimales.getTableHeader().setReorderingAllowed(false);
		this.tablaAnimales.setRowSorter(tFiltro);
		cerrarVentana();
		initialize();
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
	
	private void initialize() {
		this.setSize(800, 600);
		this.setTitle("GESTIONAR ANIMAL");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
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
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.NONE;
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.gridx = 3;
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
			panelFiltro.add(getCampoFiltro(), gridBagConstraints1);
			panelFiltro.add(labelFiltrarPor, gridBagConstraints3);
			panelFiltro.add(getCampoMotivoFiltro(), gridBagConstraints6);
		}
		return panelFiltro;
	}

	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.setSize(new Dimension(678, 40));
			panelBotones.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelBotones.add(getBotonAgregarAnimal(), null);
			panelBotones.add(getBotonModificarAnimal(), null);
			panelBotones.add(getBotonEliminarAnimal(), null);
			panelBotones.add(getBotonReporte(), null);
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
	                	case 1:tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText(), 1));
	                	break;
	                	
	                	case 2:tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toUpperCase(), 2));
	                	break;
	                	
	                	case 3:tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toUpperCase(), 3));
	                	break;
	                	
	                	case 4:tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toUpperCase(), 4));
	                	break;
	                	
	                	case 5:tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toUpperCase(), 4));
	                	break;
	                	
	                	default :tFiltro.setRowFilter(RowFilter.regexFilter(campoFiltro.getText().toUpperCase(), 0));
	                }  
	            }
	        });
		}
		return campoFiltro;
	}

	
	private JComboBox getCampoMotivoFiltro() {
		if (campoMotivoFiltro == null) {
			Object[] opciones = {"ID","NUMERO","NOMBRE","RAZA","ESPECIE","EDAD"};
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
			panelTabla.setViewportView(getTablaAnimales());
		}
		return panelTabla;
	}

	public JTable getTablaAnimales() {
		if (tablaAnimales == null) {
			tablaAnimales = new JTable();
			tablaAnimales.getTableHeader().setReorderingAllowed(false);
		}
		return tablaAnimales;
	}

	public JButton getBotonAgregarAnimal() {
		if (botonAgregarAnimal == null) {
			botonAgregarAnimal = new JButton("INGRESAR ANIMAL");
			botonAgregarAnimal.setMnemonic('I');
			botonAgregarAnimal.registerKeyboardAction(botonAgregarAnimal.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAgregarAnimal.registerKeyboardAction(botonAgregarAnimal.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonAgregarAnimal.setPreferredSize(new Dimension(160, 30));
			botonAgregarAnimal.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
		}
		return botonAgregarAnimal;
	}

	public JButton getBotonModificarAnimal() {
		if (botonModificarAnimal == null) {
			botonModificarAnimal = new JButton("MODIFICAR ANIMAL");
			botonModificarAnimal.setMnemonic('M');
			botonModificarAnimal.registerKeyboardAction(botonModificarAnimal.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonModificarAnimal.registerKeyboardAction(botonModificarAnimal.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonModificarAnimal.setPreferredSize(new Dimension(180, 30));
			botonModificarAnimal.setIcon(new ImageIcon(getClass().getResource("/icono/script_edit.png")));
		}
		return botonModificarAnimal;
	}

	public JButton getBotonEliminarAnimal() {
		if (botonEliminarAnimal == null) {
			botonEliminarAnimal = new JButton("ELIMINAR ANIMAL");
			botonEliminarAnimal.setMnemonic('E');
			botonEliminarAnimal.registerKeyboardAction(botonAgregarAnimal.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonEliminarAnimal.registerKeyboardAction(botonEliminarAnimal.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonEliminarAnimal.setPreferredSize(new Dimension(160, 30));
			botonEliminarAnimal.setIcon(new ImageIcon(getClass().getResource("/icono/delete.png")));
		}
		return botonEliminarAnimal;
	}
	
	public void setListenerButtons(ActionListener al){
		this.botonAgregarAnimal.addActionListener(al);
		this.botonEliminarAnimal.addActionListener(al);
		this.botonModificarAnimal.addActionListener(al);
		this.botonReporte.addActionListener(al);
	}
	
	public void setListenerMouse(MouseAdapter ma){
		this.tablaAnimales.addMouseListener(ma);
	}

	public JButton getBotonReporte() {
		if (botonReporte == null) {
			botonReporte = new JButton();
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
			botonReporte.setIcon(new ImageIcon(getClass().getResource("/icono/iconReport.png")));
			botonReporte.setText("REPORTE");
		}
		return botonReporte;
	}  

	@SuppressWarnings("unchecked")
	public TableRowSorter getTableRowSorter(){
		return this.tFiltro;
	}
}

