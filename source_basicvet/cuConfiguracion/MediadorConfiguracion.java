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


@SuppressWarnings("unused")
public class MediadorConfiguracion {
	private GUIConfiguracion guiConfiguracion;
	private MediadorPanelTurno medPanelTurno;
	private MediadorPanelAnimal medPanelAnimal;
	private MediadorPanelBasicVet medPanelBasicVet;
	private MediadorPanelEstudio medPanelEstudio;


	public MediadorConfiguracion() {
		super();
		this.inicializar();
	}

	private void inicializar() {
		this.guiConfiguracion = new GUIConfiguracion();
		this.medPanelTurno = new MediadorPanelTurno(this.guiConfiguracion.getPanelTurno());
		this.medPanelAnimal = new MediadorPanelAnimal(this.guiConfiguracion.getPanelAnimal());
		this.medPanelBasicVet = new MediadorPanelBasicVet(this.guiConfiguracion.getPanelBasicVet());
		this.medPanelEstudio = new MediadorPanelEstudio(this.guiConfiguracion.getPanelEstudio());
		this.guiConfiguracion.setVisible(true);
	}
}
