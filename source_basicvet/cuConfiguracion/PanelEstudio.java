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
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
    
public class PanelEstudio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelTipoEstudios;
	private JPanel panelBotonTipoEstudios = null;
	private JButton botonAgregarTipoEstudio = null;
	private JButton botonQuitarTipoEstudio = null;
	private JList listaTipoEstudios = null;
	private DefaultListModel modeloTipoEstudio = new DefaultListModel();
	private JScrollPane panelScrollTipoEstudios = null; 

	public PanelEstudio() {
		super();
		initialize();
	}

	private void initialize(){
		this.setSize(600,350);
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(600, 200));
		this.setPreferredSize(new Dimension(600, 200));
		this.setMinimumSize(new Dimension(600, 200));
		this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(162,211,255)));
		this.add(getPanelTipoEstudios(), BorderLayout.NORTH);
	}
	
	private JPanel getPanelTipoEstudios() {
		if (panelTipoEstudios == null) {
			panelTipoEstudios = new JPanel();
			panelTipoEstudios.setLayout(new BorderLayout());
			panelTipoEstudios.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "TIPO ESTUDIOS", TitledBorder.CENTER, TitledBorder.TOP, new Font("Dialog", Font.BOLD, 12), new Color(5, 13, 152)));
			panelTipoEstudios.setName("panelRazas");
			panelTipoEstudios.add(getPanelScrollTipoEstudios(), BorderLayout.CENTER);
			panelTipoEstudios.add(getPanelBotonTipoEstudios(), BorderLayout.EAST);
		}
		return panelTipoEstudios;
	}
	
	private JPanel getPanelBotonTipoEstudios() {
		if (panelBotonTipoEstudios == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.gridx = 0;
			panelBotonTipoEstudios = new JPanel();
			panelBotonTipoEstudios.setLayout(new GridBagLayout());
			panelBotonTipoEstudios.add(getBotonAgregarTipoEstudio(), gridBagConstraints2);
			panelBotonTipoEstudios.add(getBotonQuitarTipoEstudio(), gridBagConstraints11);
		}
		return panelBotonTipoEstudios;
	}

	public JButton getBotonAgregarTipoEstudio() {
		if (botonAgregarTipoEstudio == null) {
			botonAgregarTipoEstudio = new JButton();
			botonAgregarTipoEstudio.setIcon(new ImageIcon(getClass().getResource("/icono/agregar.png")));
			botonAgregarTipoEstudio.registerKeyboardAction(botonAgregarTipoEstudio.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAgregarTipoEstudio.registerKeyboardAction(botonAgregarTipoEstudio.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonAgregarTipoEstudio;
	}

	public JButton getBotonQuitarTipoEstudio() {
		if (botonQuitarTipoEstudio == null) {
			botonQuitarTipoEstudio = new JButton();
			botonQuitarTipoEstudio.setIcon(new ImageIcon(getClass().getResource("/icono/quitar.png")));
			botonQuitarTipoEstudio.registerKeyboardAction(botonQuitarTipoEstudio.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonQuitarTipoEstudio.registerKeyboardAction(botonQuitarTipoEstudio.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonQuitarTipoEstudio;
	}


	public JList getListaTipoEstudios() {
		if (listaTipoEstudios == null) {
			listaTipoEstudios = new JList(this.modeloTipoEstudio);
		}
		return listaTipoEstudios;
	}
	
	public void setListenerButtons(ActionListener al){
		botonAgregarTipoEstudio.addActionListener(al);
		botonQuitarTipoEstudio.addActionListener(al);
	}
	
	public void agregarEstudio(String obj){
		this.modeloTipoEstudio.addElement(obj);
	}
	
	public void setListSelectionListener(ListSelectionListener lsl){
		ListSelectionModel listSelectionModel = this.listaTipoEstudios.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(lsl);
	}
	
	public DefaultListModel getModeloTipoEstudio(){
		return this.modeloTipoEstudio;		
	}

	public void quitarEstudio(int fila) {
		this.modeloTipoEstudio.remove(fila);
	}

	/**
	 * This method initializes panelScrollTipoEstudios	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getPanelScrollTipoEstudios() {
		if (panelScrollTipoEstudios == null) {
			panelScrollTipoEstudios = new JScrollPane();
			panelScrollTipoEstudios.setSize(new Dimension(276, 113));
			panelScrollTipoEstudios.setViewportView(getListaTipoEstudios());
		}
		return panelScrollTipoEstudios;
	}
}