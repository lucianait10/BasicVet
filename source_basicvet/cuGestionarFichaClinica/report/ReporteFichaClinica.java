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

package cuGestionarFichaClinica.report;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Hashtable;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import persistencia.Persistencia;
import persistencia.dominio.Animal;
import persistencia.dominio.Circulatorio;
import persistencia.dominio.Digestivo;
import persistencia.dominio.ExploracionGeneral;
import persistencia.dominio.FichaClinica;
import persistencia.dominio.Genital;
import persistencia.dominio.Locomotor;
import persistencia.dominio.Nervioso;
import persistencia.dominio.Ojos;
import persistencia.dominio.Piel;
import persistencia.dominio.Respiratorio;
import persistencia.dominio.Urinario;
import cuGestionarAnimal.ControlAnimal;
import cuGestionarFichaClinica.ControlFichaClinica;
import cuGestionarFichaClinica.GUIGestionarFichaClinica;

@SuppressWarnings({ "unchecked"})
public class ReporteFichaClinica {
	private Persistencia persistencia;
	private ControlFichaClinica controlFichaClinica;
	public GUIGestionarFichaClinica framePrincipal;
	private ControlAnimal controlAnimal;
	  
	public ReporteFichaClinica(GUIGestionarFichaClinica gesFichaClinica) {
		this.framePrincipal=gesFichaClinica;
	}
	
	public void viewReport(Object[][] param, String[] fieldXml, String nameReport, Object[]values) {
		try {
			// parametros
			/*HashMap parameters = new HashMap(); 
			for (int i = 0; i <param.length; i++) {
				System.out.println(param.length);
				parameters.put(param[i][0], param[i][1]);
			}*/
			Hashtable ht = new Hashtable();
			for (int i = 0; i < fieldXml.length; i++) {
				ht.put(fieldXml[i], new Integer(i));
			}
			DataSourceJasper data = new DataSourceJasper(values, ht);
			String fileNameReport = MakeReportFichaClinica.PATH_OUTPUT_REPORT + nameReport + ".jasper";
			JasperPrint jasperprint= JasperFillManager.fillReport(fileNameReport,new HashMap(),data);
			JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("Ficha Clinica");
            visor.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){framePrincipal.setVisible(true);}            	}
            						);

            visor.setVisible(true);
		} catch (JRException exc) {
			exc.printStackTrace(System.out);
		}
	}
	public void viewFicha(int idFicha, int nroAnimal) {
		this.persistencia = new Persistencia();
		this.controlFichaClinica = new ControlFichaClinica();
		this.controlAnimal = new ControlAnimal();
		try{
			persistencia.iniciarTransaccion();
			Animal animal = controlAnimal.obtenerAnimal(nroAnimal);
			FichaClinica ficha = controlFichaClinica.obtenerFichaClinica(idFicha+"");
			ExploracionGeneral expGral = controlFichaClinica.obtenerExploracionGeneral(ficha.getId());
			Circulatorio cir = controlFichaClinica.obtenerCirculatorio(ficha.getId());
			Digestivo dig = controlFichaClinica.obtenerDigestivo(ficha.getId());
			Respiratorio res = controlFichaClinica.obtenerRespiratorio(ficha.getId());
			Genital gen = controlFichaClinica.obtenerGenital(ficha.getId());
			Locomotor loc = controlFichaClinica.obtenerLocomotor(ficha.getId());
			Nervioso ner = controlFichaClinica.obtenerNervioso(ficha.getId());
			Ojos ojos = controlFichaClinica.obtenerOjos(ficha.getId());
			Piel piel = controlFichaClinica.obtenerPiel(ficha.getId());
			Urinario uri = controlFichaClinica.obtenerUrinario(ficha.getId());
			String nFicha = ficha.getId()+"";
			String idAnimal = Integer.toString(animal.getId());
			String nomAnimal = animal.getNombre().toUpperCase();
			String fecha = ficha.getFecha().toString();
			String raza = animal.getRaza().toUpperCase();
			String edad = animal.getEdad()+"";
			String anamnesis = ficha.getAnamnesis().firstElement();
			String diagDiferencial = ficha.getDiagnosticoDiferencial();
			String diagPresuntivo = ficha.getDiagnosticoPresuntivo();
			String pronostico = ficha.getPronostico();
			String manCliTerap = ficha.getManejoClinicoTerapeutico();
			String temp = expGral.getTemperatura();
			String fc = expGral.getFc();
			String fr = expGral.getFr();
			String estGral = expGral.getEstadoGral();
			String mucOcular = expGral.getMucosaOcular();
			String mucGenital = expGral.getMucosaGenital();
			String mucBucal = expGral.getMucosaBucal();
			String mucOtros = expGral.getMucosaOtros();
			String hidratacion = expGral.getHidratacion();
			String ganLinfaticos = expGral.getGangliosLinfaticos();
			String cavOral = dig.getCavidadOral();
			String palAbdominal = dig.getPalpacionAbd();
			String vomitos = dig.getVomitos();
			String disfagia = dig.getDisfagia();
			String materiaFecal = dig.getMateriaFecal();
			String obsD = dig.getObservaciones().firstElement();
			String ritmo = cir.getRitmo();
			String ascultacion = cir.getAscultacion();
			String pulso = cir.getPulso();
			String soplo = cir.getSoplo();
			String ecg = cir.getEcg();
			String otrosCir = cir.getOtros();
			String obsC = cir.getObservaciones().firstElement();
			String respiracion = res.getRepiracion();
			String tos = res.getTos();
			String auscultacion = res.getAuscultacion();
			String refTusigneo = res.getReflejoTusigeno();
			String obsR = res.getObservaciones().firstElement();
			String expM = gen.getMachoExp();
			String secrecionM = gen.getMachoSecreciones();
			String tactoRectal = gen.getMachoTactoRectal();
			String expH = gen.getHembraExp();
			String secrecionH = gen.getHembraSecreciones();
			String glaMamarias = gen.getHembraGlanMamarias();
			String obsG = gen.getObservaciones().firstElement();
			String lesion = loc.getLesion();
			String ubicacion = loc.getUbicacion();
			String obsL = loc.getObservaciones().firstElement();
			String paralisis = ner.getParalisis();
			String convulsiones = ner.getConvulsiones();
			String ataxia = ner.getAtaxia();
			String reflejos = ner.getReflejos();
			String sensibilidad = ner.getSensibilidad();
			String conducta = ner.getConducta();
			String sensorio = ner.getSensorio();
			String otrosN = ner.getOtros();
			String obsN = ner.getObservaciones().firstElement();
			String izquierdo = ojos.getIzquierdo();
			String derecho = ojos.getDerecho();
			String obsO = ojos.getObservaciones().firstElement();
			String tipoLesion = piel.getTipoLesion();
			String forma = piel.getForma();
			String ubicacionP = piel.getUbicacion();
			String simetria = piel.getSimetrica();
			String olorP = piel.getOlor();
			String prurito = piel.getPrurito();
			String mantoPiloso = piel.getMantoPiloso();
			String ectoparasitos = piel.getEctoparasitos();
			String oidoIzq = piel.getOidoIzq();
			String oidoDer = piel.getOidoDer();
			String obsP = piel.getObservaciones().firstElement();
			String vejiga = uri.getVejiga();
			String orina = uri.getOrina();
			String otroU = uri.getOtros();
			String obsU = uri.getObservaciones().firstElement();
			Object[]values = {nFicha,idAnimal,nomAnimal,fecha,raza,edad,
							anamnesis,diagDiferencial,diagPresuntivo,pronostico,manCliTerap,
							temp,fc,fr,estGral,mucOcular,mucGenital,mucBucal,mucOtros,hidratacion,ganLinfaticos,
							cavOral,palAbdominal,vomitos,disfagia,materiaFecal,obsD,ritmo,ascultacion,pulso,soplo,ecg,otrosCir,obsC,
							respiracion,tos,auscultacion,refTusigneo,obsR,expM,secrecionM,tactoRectal,expH,secrecionH,glaMamarias,obsG,
							lesion,ubicacion,obsL,paralisis,convulsiones,ataxia,reflejos,sensibilidad,conducta,sensorio,otrosN,obsN,
							izquierdo,derecho,obsO,tipoLesion,forma,ubicacionP,simetria,olorP,prurito,mantoPiloso,ectoparasitos,oidoIzq,oidoDer,obsP,
							vejiga,orina,otroU,obsU};
			persistencia.concretarTransaccion();
			String[] filedXml = { "nFicha", "idAnimal", "nomAnimal","fecha","raza","edad","anamnesis","diagDiferencial","diagPresuntivo","pronostico","manCliTerap",//11
     						  "temp","frecCardiaca","frecRespiratoria","estGral","mucOcular","mucGenital","mucBucal","mucOtros","hidratacion","ganLinfaticos","cavOral","palAbdominal",//12
     						  "vomitos","disfagia","materiaFecal","observacionD","ritmo","ascultacion","pulso","soplo","ecg","otrosCir","observacionC",//11
     						  "respiracion","tos","auscultacion","refTusigneo","observacionR","expM","secrecionesM","tactoRectal","expH","secrecionesH","glaMamarias","observacionG",//12
     						  "lesion","ubicacion","observacionL","paralisis","convulsiones","ataxia","reflejo","sensibilidad","conducta","sensorio","otrosN","observacionN",//12
     						  "izquierdo","derecho","observacionO","tipoLesion","forma","ubicacionP","simetria","olorP","prurito","mantoPiloso","ectoparasitos","oidoIzq","oidoDer","observacionP",//14
     						  "vejiga","orina","otrosU","observacionU"};//4
 
			String nameReport = "FichaClinica";
			this.viewReport(new Object[][]{}, filedXml, nameReport, values);
		}catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
}
