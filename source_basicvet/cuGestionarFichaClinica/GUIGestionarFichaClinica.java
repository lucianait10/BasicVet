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

package cuGestionarFichaClinica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class GUIGestionarFichaClinica extends JDialog {
  
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelBotones = null;
	private JButton botonIngresarFichaClinica = null;
	private JButton botonEliminarFichaClinica = null;
	private JButton botonModificarFichaClinica = null;
	private JPanel panelBusqueda = null;
	private JTextField campoIdAnimal = null;
	private JTextField campoNombre = null;
	private JButton botonBuscarAnimal = null;
	private JTable tablaTurnos;
	private JPanel panelTabla;
	private JScrollPane listaTurnos;
	private JLabel labelNombre = null;
	private JLabel labelId = null;
	private DefaultTableModel modelo;
	private JButton reporteFichaClinica = null;

	public GUIGestionarFichaClinica(DefaultTableModel m) {
		super();
		this.modelo = m;
		this.tablaTurnos = new JTable(m);
		this.tablaTurnos.getTableHeader().setReorderingAllowed(false);
		cerrarVentana();
		initialize();
	}
	
	public JTable getJTable() {
		return tablaTurnos;
	}

	@SuppressWarnings({ "serial" })
	private void cerrarVentana(){
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); //$NON-NLS-1$
	    getRootPane().getActionMap().put("Cancel", new AbstractAction(){ //$NON-NLS-1$
	        
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
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setContentPane(getJContentPane());
		this.setTitle("GESTIONAR FICHA CLINICA");
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelBotones(), BorderLayout.SOUTH);
			jContentPane.add(getPanelBusqueda(), BorderLayout.NORTH);
			jContentPane.add(getPanelTabla(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 2;
			gridBagConstraints2.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 0;
			panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.add(getBotonIngresarFichaClinica(), null);
			panelBotones.add(getBotonModificarFichaClinica(), null);
			panelBotones.add(getBotonEliminarFichaClinica(), null);
			panelBotones.add(getReporteFichaClinica(), null);
		}
		return panelBotones;
	}

	public JButton getBotonIngresarFichaClinica() {
		if (botonIngresarFichaClinica == null) {
			botonIngresarFichaClinica = new JButton();
			botonIngresarFichaClinica.setText("INGRESAR FICHA CLINICA");
			botonIngresarFichaClinica.setMnemonic('I');
			botonIngresarFichaClinica.registerKeyboardAction(botonIngresarFichaClinica.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonIngresarFichaClinica.registerKeyboardAction(botonIngresarFichaClinica.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonIngresarFichaClinica.setPreferredSize(new Dimension(195, 30));
			botonIngresarFichaClinica.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
		}
		return botonIngresarFichaClinica;
	}

	public JButton getBotonEliminarFichaClinica() {
		if (botonEliminarFichaClinica == null) {
			botonEliminarFichaClinica = new JButton();
			botonEliminarFichaClinica.setText("ELIMINAR FICHA CLINICA");
			botonEliminarFichaClinica.setMnemonic('E');
			botonEliminarFichaClinica.registerKeyboardAction(botonEliminarFichaClinica.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonEliminarFichaClinica.registerKeyboardAction(botonEliminarFichaClinica.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonEliminarFichaClinica.setPreferredSize(new Dimension(195, 30));
			botonEliminarFichaClinica.setIcon(new ImageIcon(getClass().getResource("/icono/delete.png")));
			
		}
		return botonEliminarFichaClinica;
	}

	public JButton getBotonModificarFichaClinica() {
		if (botonModificarFichaClinica == null) {
			botonModificarFichaClinica = new JButton();
			botonModificarFichaClinica.setText("MODIFICAR FICHA CLINICA");
			botonModificarFichaClinica.setMnemonic('M');
			botonModificarFichaClinica.registerKeyboardAction(botonModificarFichaClinica.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonModificarFichaClinica.registerKeyboardAction(botonModificarFichaClinica.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonModificarFichaClinica.setPreferredSize(new Dimension(200, 30));
			botonModificarFichaClinica.setIcon(new ImageIcon(getClass().getResource("/icono/script_edit.png")));
		}
		return botonModificarFichaClinica;
	}

	public JButton getBotonBuscarAnimal() {
		if (botonBuscarAnimal == null) {
			botonBuscarAnimal = new JButton("BUSCAR ANIMAL");
			botonBuscarAnimal.setMnemonic('B');
			botonBuscarAnimal.registerKeyboardAction(botonBuscarAnimal.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonBuscarAnimal.registerKeyboardAction(botonBuscarAnimal.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//botonBuscarAnimal.setPreferredSize(new Dimension(160, 30));
			botonBuscarAnimal.setIcon(new ImageIcon(getClass().getResource("/icono/search.jpg")));
		}
		return botonBuscarAnimal;
	}

	public void setListenerButtons(ActionListener al){
		this.botonIngresarFichaClinica.addActionListener(al);
		this.botonEliminarFichaClinica.addActionListener(al);
		this.botonModificarFichaClinica.addActionListener(al);
		this.botonBuscarAnimal.addActionListener(al);
		this.reporteFichaClinica.addActionListener(al);
	}
	
	public void setListenerMouse(MouseAdapter ma){
		this.tablaTurnos.addMouseListener(ma);
	}

	private JPanel getPanelBusqueda() {
		if (panelBusqueda == null) {
			labelId = new JLabel();
			labelId.setText("ID:");
			labelNombre = new JLabel();
			labelNombre.setText("NOMBRE:");
			panelBusqueda = new JPanel();
			panelBusqueda.setLayout(new FlowLayout());
			panelBusqueda.add(labelId, null);
			panelBusqueda.add(getCampoIdAnimal(), null);
			panelBusqueda.add(labelNombre, null);
			panelBusqueda.add(getCampoNombre(), null);
			panelBusqueda.add(getBotonBuscarAnimal(), null);
		}
		return panelBusqueda;
	}

	public JTextField getCampoIdAnimal() {
		if (campoIdAnimal == null) {
			campoIdAnimal = new JTextField("NUMERO ANIMAL");
			campoIdAnimal.setPreferredSize(new Dimension(200, 20));
			campoIdAnimal.setBackground(new Color(238, 238, 238));
			campoIdAnimal.setText("ID ANIMAL");
			campoIdAnimal.setEditable(false);
		}
		return campoIdAnimal;
	}

	public JTextField getCampoNombre() {
		if (campoNombre == null) {
			campoNombre = new JTextField("NOMBRE ANIMAL");
			campoNombre.setPreferredSize(new Dimension(200, 20));
			campoNombre.setBackground(new Color(238, 238, 238));
			campoNombre.setEditable(false);
		}
		return campoNombre;
	}
	
	private JPanel getPanelTabla() {
		if (panelTabla == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.weighty = 1.0;
			gridBagConstraints3.gridx = 0;
			panelTabla = new JPanel();
			panelTabla.setLayout(new GridBagLayout());
			panelTabla.setSize(new Dimension(556, 234));
			panelTabla.add(getListaTurnos(), gridBagConstraints3);
		}
		return panelTabla;
	}
	
	private JTable getTablaTurnos() {
		if (tablaTurnos == null) {
			tablaTurnos = new JTable();
		}
		return tablaTurnos;
	}

	private JScrollPane getListaTurnos() {
		if (listaTurnos == null) {
			listaTurnos = new JScrollPane();
			listaTurnos.setViewportView(getTablaTurnos());
		}
		return listaTurnos;
	}
	
	public DefaultTableModel getTablaModel(){
		return this.modelo;
	}

	public JButton getReporteFichaClinica() {
		if (reporteFichaClinica == null) {
			reporteFichaClinica = new JButton();
			reporteFichaClinica.setText("REPORTE");
			reporteFichaClinica.setMnemonic('R');
			reporteFichaClinica.registerKeyboardAction(reporteFichaClinica.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			reporteFichaClinica.registerKeyboardAction(reporteFichaClinica.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			//reporteFichaClinica.setPreferredSize(new Dimension(120, 30));
			reporteFichaClinica.setIcon(new ImageIcon(getClass().getResource("/icono/iconReport.png")));
		}
		return reporteFichaClinica;
	}
}
