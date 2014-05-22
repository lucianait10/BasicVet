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

package cuGestionarPropietario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
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
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class GUIPropietario extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel labelDni = null;
	private JLabel labelNombre = null;
	private JLabel labelApellido = null;
	private JLabel labelTelefono = null;
	private JLabel labelCuil = null;
	private JLabel labelDireccion = null;
	private JTextField campoDni = null;
	private JTextField campoNombre = null;
	private JTextField campoApellido = null;
	private JTextField campoTelefono = null;
	private JTextField campoCuil = null;
	private JTextField campoDireccion = null;
	private JLabel labelDatos = null;
	private JPanel panelDatos = null;
	private JScrollPane panelListaAnimal = null;
	private JTable tablaAnimales = null;
	private JPanel panelAnimal = null;
	private JButton botonAgregarAnimal = null;
	private JPanel panelBuscar = null;  //  @jve:decl-index=0:visual-constraint="803,120"
	private JPanel panelBotones = null;
	private JButton botonAgregar = null;
	private JButton botonModificar = null;
	private DefaultTableModel modelo;
	private JLabel labelAsterisco1 = null;
	private JPanel panelDni = null;
	private JPanel panelTelefono = null;
	private JLabel labelAsterisco11 = null;
	private JPanel panelApellido = null;
	private JLabel labelAsterisco111 = null;
	private JPanel panelCuil = null;
	private JPanel panelNombre = null;
	private JPanel panelDireccion = null;
	public GUIPropietario(DefaultTableModel m) {	
		super();
		this.modelo = m;
		cerrarVentana();
		initialize();
	}

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
	    	
			public void windowClosing(WindowEvent evt){
	    		setVisible(false);
	    		dispose();
	    	}
	    });
	}
	
	private void initialize() {
		this.setSize(800, 600);
		this.setTitle("PROPIETARIO");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setContentPane(getJContentPane());
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelDatos(),BorderLayout.NORTH);
			jContentPane.add(getPanelAnimal(), BorderLayout.CENTER);
			jContentPane.add(getPanelBotones(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	private JLabel getLabelDni() {
		if (labelDni == null) {
			labelDni = new JLabel();
			labelDni.setText("DNI:");
		}
		return labelDni;
	}

	private JLabel getLabelNombre() {
		if (labelNombre == null) {
			labelNombre = new JLabel();
			labelNombre.setText("NOMBRE:");
		}
		return labelNombre;
	}

	private JLabel getLabelApellido() {
		if (labelApellido == null) {
			labelApellido = new JLabel();
			labelApellido.setText("APELLIDO:");
		}
		return labelApellido;
	}

	private JLabel getLabelTelefono() {
		if (labelTelefono == null) {
			labelTelefono = new JLabel();
			labelTelefono.setText("TELEFONO:");
		}
		return labelTelefono;
	}

	private JLabel getLabelCuil() {
		if (labelCuil == null) {
			labelCuil = new JLabel();
			labelCuil.setText("CUIL:");
		}
		return labelCuil;
	}

	private JLabel getLabelDireccion() {
		if (labelDireccion == null) {
			labelDireccion = new JLabel();
			labelDireccion.setText("DIRECCION:");
		}
		return labelDireccion;
	}

	public JTextField getCampoDni() {
		if (campoDni == null) {
			campoDni = new JTextField();
			campoDni.setMinimumSize(new Dimension(150, 20));
			campoDni.setPreferredSize(new Dimension(150, 20));
			campoDni.addKeyListener(new KeyAdapter(){
				
				public void keyTyped(KeyEvent ev){if(!(ev.getKeyChar()>='0' && ev.getKeyChar()<='9')&& !(ev.getKeyChar()=='m'||ev.getKeyChar()=='M')&& !(ev.getKeyChar()=='f'||ev.getKeyChar()=='F')) ev.consume();}
				
			});
		}
		return campoDni;
	}

	public JTextField getCampoNombre() {
		if (campoNombre == null) {
			campoNombre = new JTextField();
			campoNombre.setPreferredSize(new Dimension(150, 20));
			campoNombre.addKeyListener(new KeyAdapter(){
				
				public void keyTyped(KeyEvent ev){if(ev.getKeyChar()>='0' && ev.getKeyChar()<='9') ev.consume();}
			});
		}
		return campoNombre;
	}

	public JTextField getCampoApellido() {
		if (campoApellido == null) {
			campoApellido = new JTextField();
			campoApellido.setPreferredSize(new Dimension(150, 20));
			campoApellido.addKeyListener(new KeyAdapter(){
				
				public void keyTyped(KeyEvent ev){if(ev.getKeyChar()>='0' && ev.getKeyChar()<='9') ev.consume();}
			});
		}
		return campoApellido;
	}

	public JTextField getCampoTelefono() {
		if (campoTelefono == null) {
			campoTelefono = new JTextField();
			campoTelefono.setPreferredSize(new Dimension(150, 20));
			campoTelefono.addKeyListener(new KeyAdapter(){
				
				public void keyTyped(KeyEvent ev){if(!(ev.getKeyChar()>='0' && ev.getKeyChar()<='9')&& !(ev.getKeyChar()=='(' || ev.getKeyChar()==')' || ev.getKeyChar()=='+' || ev.getKeyChar()=='-')) ev.consume();}
			});
		}
		return campoTelefono;
	}

	public JTextField getCampoCuil() {
		if (campoCuil == null) {
			campoCuil = new JTextField();
			campoCuil.setPreferredSize(new Dimension(150, 20));
			campoCuil.addKeyListener(new KeyAdapter(){
				
				public void keyTyped(KeyEvent ev){if(!(ev.getKeyChar()>='0' && ev.getKeyChar()<='9')&& !(ev.getKeyChar()=='-')) ev.consume();}
			});
		}
		return campoCuil;
	}

	public JTextField getCampoDireccion() {
		if (campoDireccion == null) {
			campoDireccion = new JTextField();
			campoDireccion.setPreferredSize(new Dimension(150, 20));
		}
		return campoDireccion;
	}

	private JLabel getLabelDatos() {
		if (labelDatos == null) {
			labelDatos = new JLabel();
			labelDatos.setText("DATOS PROPIETARIO");
			labelDatos.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.gray));
		}
		return labelDatos;
	}

	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints.gridy = 2;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.gridy = 0;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 3;
			gridBagConstraints11.gridy = 2;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 2;
			gridBagConstraints10.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.gridy = 1;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 2;
			gridBagConstraints9.gridy = 2;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 3;
			gridBagConstraints8.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints8.gridy = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 3;
			gridBagConstraints13.gridy = 1;
			panelDatos = new JPanel();
			panelDatos.setLayout(new GridBagLayout());
			panelDatos.setSize(new Dimension(661, 90));
			panelDatos.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelDatos.add(getPanelDni(), gridBagConstraints2);
			panelDatos.add(getPanelApellido(), gridBagConstraints8);
			panelDatos.add(getPanelCuil(), gridBagConstraints9);
			panelDatos.add(getPanelNombre(), gridBagConstraints10);
			panelDatos.add(getPanelDireccion(), gridBagConstraints11);
			panelDatos.add(getLabelDatos(), gridBagConstraints12);
			panelDatos.add(getPanelTelefono(), gridBagConstraints);
		}
		return panelDatos;
	}

	private JScrollPane getPanelListaAnimal() {
		if (panelListaAnimal == null) {
			panelListaAnimal = new JScrollPane();
			panelListaAnimal.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelListaAnimal.setViewportView(getTablaAnimales());
		}
		return panelListaAnimal;
	}

	private JTable getTablaAnimales() {
		if (tablaAnimales == null) {
			tablaAnimales = new JTable(this.modelo);
			tablaAnimales.getTableHeader().setReorderingAllowed(false);
			tablaAnimales.setMaximumSize(new Dimension(600, 600));
		}
		return tablaAnimales;
	}

	private JPanel getPanelAnimal() {
		if (panelAnimal == null) {
			panelAnimal = new JPanel();
			panelAnimal.setLayout(new BorderLayout());
			panelAnimal.add(getPanelListaAnimal(), BorderLayout.CENTER);
			panelAnimal.add(getPanelBuscar(), BorderLayout.EAST);
		}
		return panelAnimal;
	}

	public JButton getBotonAgregarAnimal(){
		if (botonAgregarAnimal == null) {
			botonAgregarAnimal = new JButton("AGREGAR ANIMAL");
			botonAgregarAnimal.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
			//botonAgregarAnimal.setPreferredSize(new Dimension(130, 30));
			botonAgregarAnimal.setMnemonic('G');
			botonAgregarAnimal.registerKeyboardAction(botonAgregarAnimal.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);
			botonAgregarAnimal.registerKeyboardAction(botonAgregarAnimal.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonAgregarAnimal;
	}

	private JPanel getPanelBuscar() {
		if (panelBuscar == null) {
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridx = 0;
			gridBagConstraints14.gridy = 0;
			panelBuscar = new JPanel();
			panelBuscar.setLayout(new BorderLayout());
			panelBuscar.setPreferredSize(new Dimension(200, 200));
			panelBuscar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelBuscar.add(getBotonAgregarAnimal(), BorderLayout.NORTH);
		}
		return panelBuscar;
	}

	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			panelBotones.add(getBotonAgregar(), null);
			panelBotones.add(getBotonModificar(), null);
		}
		return panelBotones;
	}

	public JButton getBotonAgregar() {
		if (botonAgregar == null) {
			botonAgregar = new JButton("AGREGAR");
			botonAgregar.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
			//botonAgregar.setPreferredSize(new Dimension(109, 30));
			botonAgregar.setMnemonic('A');
			botonAgregar.registerKeyboardAction(botonAgregar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);
			botonAgregar.registerKeyboardAction(botonAgregar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonAgregar;
	}

	public JButton getBotonModificar() {
		if (botonModificar == null) {
			botonModificar = new JButton("MODIFICAR");
			botonModificar.setIcon(new ImageIcon(getClass().getResource("/icono/script_edit.png")));
			//botonModificar.setPreferredSize(new Dimension(117, 30));
			botonModificar.setMnemonic('M');
			botonModificar.registerKeyboardAction(botonModificar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);
			botonModificar.registerKeyboardAction(botonModificar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonModificar;
	}
	
	public void setListenerButtons(ActionListener al){
		this.botonModificar.addActionListener(al);
		this.botonAgregar.addActionListener(al);
		this.botonAgregarAnimal.addActionListener(al);
	}

	private JLabel getLabelAsterisco1() {
		if (labelAsterisco1 == null) {
			labelAsterisco1 = new JLabel();
			labelAsterisco1.setText("*");
			labelAsterisco1.setForeground(Color.red);
			labelAsterisco1.setFont(new Font("Dialog", Font.BOLD, 24));
		}
		return labelAsterisco1;
	}

	private JPanel getPanelDni() {
		if (panelDni == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.RIGHT);
			flowLayout.setHgap(4);
			panelDni = new JPanel();
			panelDni.setLayout(flowLayout);
			panelDni.setPreferredSize(new Dimension(218, 42));
			panelDni.add(getLabelDni(), null);
			panelDni.add(getCampoDni(), null);
			panelDni.add(getLabelAsterisco1(), null);
		}
		return panelDni;
	}

	private JPanel getPanelTelefono() {
		if (panelTelefono == null) {
			FlowLayout flowLayout5 = new FlowLayout();
			flowLayout5.setAlignment(java.awt.FlowLayout.RIGHT);
			flowLayout5.setHgap(4);
			labelAsterisco11 = new JLabel();
			labelAsterisco11.setForeground(Color.red);
			labelAsterisco11.setFont(new Font("Dialog", Font.BOLD, 24));
			labelAsterisco11.setText("*");
			panelTelefono = new JPanel();
			panelTelefono.setLayout(flowLayout5);
			panelTelefono.add(getLabelTelefono(), null);
			panelTelefono.add(getCampoTelefono(), null);
			panelTelefono.add(labelAsterisco11, null);
		}
		return panelTelefono;
	}

	private JPanel getPanelApellido() {
		if (panelApellido == null) {
			FlowLayout flowLayout4 = new FlowLayout();
			flowLayout4.setHgap(6);
			flowLayout4.setAlignment(java.awt.FlowLayout.RIGHT);
			labelAsterisco111 = new JLabel();
			labelAsterisco111.setForeground(Color.red);
			labelAsterisco111.setFont(new Font("Dialog", Font.BOLD, 24));
			labelAsterisco111.setText("*");
			panelApellido = new JPanel();
			panelApellido.setLayout(flowLayout4);
			panelApellido.add(getLabelApellido(), null);
			panelApellido.add(getCampoApellido(), null);
			panelApellido.add(labelAsterisco111, null);
		}
		return panelApellido;
	}

	private JPanel getPanelCuil() {
		if (panelCuil == null) {
			FlowLayout flowLayout2 = new FlowLayout();
			flowLayout2.setAlignment(java.awt.FlowLayout.RIGHT);
			flowLayout2.setVgap(10);
			flowLayout2.setHgap(18);
			panelCuil = new JPanel();
			panelCuil.setPreferredSize(new Dimension(242, 42));
			panelCuil.setLayout(flowLayout2);
			panelCuil.add(getLabelCuil(), null);
			panelCuil.add(getCampoCuil(), null);
		}
		return panelCuil;
	}

	private JPanel getPanelNombre() {
		if (panelNombre == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setAlignment(java.awt.FlowLayout.CENTER);
			flowLayout1.setVgap(10);
			flowLayout1.setHgap(1);
			panelNombre = new JPanel();
			panelNombre.setPreferredSize(new Dimension(218, 42));
			panelNombre.setLayout(flowLayout1);
			panelNombre.add(getLabelNombre(), null);
			panelNombre.add(getCampoNombre(), null);
		}
		return panelNombre;
	}

	private JPanel getPanelDireccion() {
		if (panelDireccion == null) {
			FlowLayout flowLayout3 = new FlowLayout();
			flowLayout3.setAlignment(java.awt.FlowLayout.LEFT);
			flowLayout3.setVgap(10);
			flowLayout3.setHgap(3);
			panelDireccion = new JPanel();
			panelDireccion.setLayout(flowLayout3);
			panelDireccion.setPreferredSize(new Dimension(239, 42));
			panelDireccion.add(getLabelDireccion(), null);
			panelDireccion.add(getCampoDireccion(), null);
		}
		return panelDireccion;
	}
}
