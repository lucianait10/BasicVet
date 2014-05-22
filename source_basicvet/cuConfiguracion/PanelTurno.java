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

package cuConfiguracion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
  
public class PanelTurno extends JPanel {
  
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JList listaMotivo = null;
	private JPanel panelBoton = null;
	private JButton botonAgregarMotivo = null;
	private JButton botonQuitarMotivo = null;
	private DefaultListModel listaModelo = new DefaultListModel();
	private JScrollPane panelScrollMotivos = null;

	public PanelTurno() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(600,350);
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(600, 200));
		this.setPreferredSize(new Dimension(600, 200));
		this.setMinimumSize(new Dimension(600, 200));
		this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(162,211,255)));
		this.add(getJContentPane(), BorderLayout.NORTH);
	}
	public JList getListaMotivo(){
		if (listaMotivo == null) {
			listaMotivo = new JList(this.listaModelo);
		}
		return listaMotivo;
	}

	private JPanel getPanelBoton() {
		if (panelBoton == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints1.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints.gridy = 0;
			panelBoton = new JPanel();
			panelBoton.setLayout(new GridBagLayout());
			panelBoton.add(getBotonAgregarMotivo(), gridBagConstraints);
			panelBoton.add(getBotonQuitarMotivo(), gridBagConstraints1);
		}
		return panelBoton;
	}

	public JButton getBotonAgregarMotivo() {
		if (botonAgregarMotivo == null) {
			botonAgregarMotivo = new JButton();
			botonAgregarMotivo.setIcon(new ImageIcon(getClass().getResource("/icono/agregar.png")));
			botonAgregarMotivo.registerKeyboardAction(botonAgregarMotivo.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAgregarMotivo.registerKeyboardAction(botonAgregarMotivo.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonAgregarMotivo;
	}

	public JButton getBotonQuitarMotivo() {
		if (botonQuitarMotivo == null) {
			botonQuitarMotivo = new JButton();
			botonQuitarMotivo.setIcon(new ImageIcon(getClass().getResource("/icono/quitar.png")));
			botonQuitarMotivo.registerKeyboardAction(botonQuitarMotivo.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonQuitarMotivo.registerKeyboardAction(botonQuitarMotivo.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonQuitarMotivo;
	}

	public void agregarMotivo(String obj){
		this.listaModelo.addElement(obj);
	}

	public DefaultListModel getModeloLista(){
		return this.listaModelo;		
	}
	public void quitarMotivo(int index){
		this.listaModelo.remove(index);
	}

	public void setListenerButtons(ActionListener al){
		this.botonAgregarMotivo.addActionListener(al);
		this.botonQuitarMotivo.addActionListener(al);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "MOTIVOS", TitledBorder.CENTER, TitledBorder.TOP, new Font("Dialog", Font.BOLD, 12), new Color(5, 13, 152)));
			jContentPane.setName("panelTurnos");
			jContentPane.add(getPanelScrollMotivos(), BorderLayout.CENTER);
			jContentPane.add(getPanelBoton(), BorderLayout.EAST);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelScrollMotivos	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getPanelScrollMotivos() {
		if (panelScrollMotivos == null) {
			panelScrollMotivos = new JScrollPane();
			panelScrollMotivos.setViewportView(getListaMotivo());
		}
		return panelScrollMotivos;
	}
}
