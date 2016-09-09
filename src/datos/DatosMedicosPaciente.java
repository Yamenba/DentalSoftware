/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "DATOS_MEDICOS_PACIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosMedicosPaciente.findAll", query = "SELECT d FROM DatosMedicosPaciente d"),
    @NamedQuery(name = "DatosMedicosPaciente.findByIdDatosMedicosPaciente", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.idDatosMedicosPaciente = :idDatosMedicosPaciente"),
    @NamedQuery(name = "DatosMedicosPaciente.findByHistoriaEnfermedadActual", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.historiaEnfermedadActual = :historiaEnfermedadActual"),
    @NamedQuery(name = "DatosMedicosPaciente.findByHistoriaAccidentes", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.historiaAccidentes = :historiaAccidentes"),
    @NamedQuery(name = "DatosMedicosPaciente.findByHistoriaCirugias", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.historiaCirugias = :historiaCirugias"),
    @NamedQuery(name = "DatosMedicosPaciente.findByHistoriaEnfermedadesInfancia", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.historiaEnfermedadesInfancia = :historiaEnfermedadesInfancia"),
    @NamedQuery(name = "DatosMedicosPaciente.findByHistoriaAntecedentesPatologicos", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.historiaAntecedentesPatologicos = :historiaAntecedentesPatologicos"),
    @NamedQuery(name = "DatosMedicosPaciente.findByHistoriaAlergias", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.historiaAlergias = :historiaAlergias"),
    @NamedQuery(name = "DatosMedicosPaciente.findByExamenCraneofacialProporcionesCefalicas", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.examenCraneofacialProporcionesCefalicas = :examenCraneofacialProporcionesCefalicas"),
    @NamedQuery(name = "DatosMedicosPaciente.findByExamenCraneofacialSimetriaFacial", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.examenCraneofacialSimetriaFacial = :examenCraneofacialSimetriaFacial"),
    @NamedQuery(name = "DatosMedicosPaciente.findByExamenCraneofacialAlturaFacial", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.examenCraneofacialAlturaFacial = :examenCraneofacialAlturaFacial"),
    @NamedQuery(name = "DatosMedicosPaciente.findByExamenCraneofacialConvexividadFacial", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.examenCraneofacialConvexividadFacial = :examenCraneofacialConvexividadFacial"),
    @NamedQuery(name = "DatosMedicosPaciente.findByExamenCraneofacialPerfilInferior", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.examenCraneofacialPerfilInferior = :examenCraneofacialPerfilInferior"),
    @NamedQuery(name = "DatosMedicosPaciente.findByExamenCraneofacialPorturaLabial", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.examenCraneofacialPorturaLabial = :examenCraneofacialPorturaLabial"),
    @NamedQuery(name = "DatosMedicosPaciente.findByExamenCraneofacialSonriraGingival", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.examenCraneofacialSonriraGingival = :examenCraneofacialSonriraGingival"),
    @NamedQuery(name = "DatosMedicosPaciente.findByExamenClinicoHabitos", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.examenClinicoHabitos = :examenClinicoHabitos"),
    @NamedQuery(name = "DatosMedicosPaciente.findByExamenClinicoRespiracion", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.examenClinicoRespiracion = :examenClinicoRespiracion"),
    @NamedQuery(name = "DatosMedicosPaciente.findByExamenClinicoFonacion", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.examenClinicoFonacion = :examenClinicoFonacion"),
    @NamedQuery(name = "DatosMedicosPaciente.findByExamenClinicoFrenilloLingual", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.examenClinicoFrenilloLingual = :examenClinicoFrenilloLingual"),
    @NamedQuery(name = "DatosMedicosPaciente.findByExamenClinicoFrenilloLabial", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.examenClinicoFrenilloLabial = :examenClinicoFrenilloLabial"),
    @NamedQuery(name = "DatosMedicosPaciente.findByColorTexturaGingival", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.colorTexturaGingival = :colorTexturaGingival"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDeshiscencias", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.deshiscencias = :deshiscencias"),
    @NamedQuery(name = "DatosMedicosPaciente.findByBolsasPeriodontales", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.bolsasPeriodontales = :bolsasPeriodontales"),
    @NamedQuery(name = "DatosMedicosPaciente.findByAmigdalas", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.amigdalas = :amigdalas"),
    @NamedQuery(name = "DatosMedicosPaciente.findByOclusionMolares", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.oclusionMolares = :oclusionMolares"),
    @NamedQuery(name = "DatosMedicosPaciente.findByCalseCanina", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.calseCanina = :calseCanina"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySobremordidaHorizontal", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.sobremordidaHorizontal = :sobremordidaHorizontal"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySobremordidaVertical", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.sobremordidaVertical = :sobremordidaVertical"),
    @NamedQuery(name = "DatosMedicosPaciente.findByLineaMediaCorresponde", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.lineaMediaCorresponde = :lineaMediaCorresponde"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMordidaCruzada", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.mordidaCruzada = :mordidaCruzada"),
    @NamedQuery(name = "DatosMedicosPaciente.findByHigieneOral", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.higieneOral = :higieneOral"),
    @NamedQuery(name = "DatosMedicosPaciente.findByCurvaSpee", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.curvaSpee = :curvaSpee"),
    @NamedQuery(name = "DatosMedicosPaciente.findByAusenciaCongenita", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.ausenciaCongenita = :ausenciaCongenita"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySupernumerarios", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.supernumerarios = :supernumerarios"),
    @NamedQuery(name = "DatosMedicosPaciente.findByQuistes", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.quistes = :quistes"),
    @NamedQuery(name = "DatosMedicosPaciente.findByLesionPerapical", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.lesionPerapical = :lesionPerapical"),
    @NamedQuery(name = "DatosMedicosPaciente.findByIncluidos", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.incluidos = :incluidos"),
    @NamedQuery(name = "DatosMedicosPaciente.findByRaizDismorfica", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.raizDismorfica = :raizDismorfica"),
    @NamedQuery(name = "DatosMedicosPaciente.findByResorcionRadicular", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.resorcionRadicular = :resorcionRadicular"),
    @NamedQuery(name = "DatosMedicosPaciente.findByTercerosMolares", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.tercerosMolares = :tercerosMolares"),
    @NamedQuery(name = "DatosMedicosPaciente.findByTrabeculadoOseo", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.trabeculadoOseo = :trabeculadoOseo"),
    @NamedQuery(name = "DatosMedicosPaciente.findByViasAereas", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.viasAereas = :viasAereas"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySenosMaxilares", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.senosMaxilares = :senosMaxilares"),
    @NamedQuery(name = "DatosMedicosPaciente.findByAnalisisRadiograficoOtros", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.analisisRadiograficoOtros = :analisisRadiograficoOtros"),
    @NamedQuery(name = "DatosMedicosPaciente.findByCaracteristicasArcosSuperior", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.caracteristicasArcosSuperior = :caracteristicasArcosSuperior"),
    @NamedQuery(name = "DatosMedicosPaciente.findByCaracteristicasArcosInferior", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.caracteristicasArcosInferior = :caracteristicasArcosInferior"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner11", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner11 = :steiner11"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner12", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner12 = :steiner12"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner13", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner13 = :steiner13"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner14", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner14 = :steiner14"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner21", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner21 = :steiner21"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner22", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner22 = :steiner22"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner23", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner23 = :steiner23"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner24", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner24 = :steiner24"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner31", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner31 = :steiner31"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner32", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner32 = :steiner32"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner33", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner33 = :steiner33"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner34", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner34 = :steiner34"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner41", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner41 = :steiner41"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner42", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner42 = :steiner42"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner43", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner43 = :steiner43"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner44", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner44 = :steiner44"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner51", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner51 = :steiner51"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner52", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner52 = :steiner52"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner53", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner53 = :steiner53"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner54", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner54 = :steiner54"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner61", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner61 = :steiner61"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner62", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner62 = :steiner62"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner63", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner63 = :steiner63"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner64", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner64 = :steiner64"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner71", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner71 = :steiner71"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner72", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner72 = :steiner72"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner73", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner73 = :steiner73"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner74", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner74 = :steiner74"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner81", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner81 = :steiner81"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner82", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner82 = :steiner82"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner83", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner83 = :steiner83"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner84", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner84 = :steiner84"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner91", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner91 = :steiner91"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner92", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner92 = :steiner92"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner93", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner93 = :steiner93"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner94", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner94 = :steiner94"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner101", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner101 = :steiner101"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner102", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner102 = :steiner102"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner103", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner103 = :steiner103"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner104", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner104 = :steiner104"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner111", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner111 = :steiner111"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner112", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner112 = :steiner112"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner113", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner113 = :steiner113"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner114", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner114 = :steiner114"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner121", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner121 = :steiner121"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner122", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner122 = :steiner122"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner123", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner123 = :steiner123"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner124", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner124 = :steiner124"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner131", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner131 = :steiner131"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner132", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner132 = :steiner132"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner133", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner133 = :steiner133"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner134", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner134 = :steiner134"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner141", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner141 = :steiner141"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner142", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner142 = :steiner142"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner143", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner143 = :steiner143"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner144", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner144 = :steiner144"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner151", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner151 = :steiner151"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner152", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner152 = :steiner152"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner153", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner153 = :steiner153"),
    @NamedQuery(name = "DatosMedicosPaciente.findBySteiner154", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.steiner154 = :steiner154"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior11", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior11 = :maxilarInferior11"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior12", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior12 = :maxilarInferior12"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior13", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior13 = :maxilarInferior13"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior14", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior14 = :maxilarInferior14"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior21", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior21 = :maxilarInferior21"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior22", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior22 = :maxilarInferior22"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior23", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior23 = :maxilarInferior23"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior24", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior24 = :maxilarInferior24"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior31", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior31 = :maxilarInferior31"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior32", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior32 = :maxilarInferior32"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior33", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior33 = :maxilarInferior33"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior34", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior34 = :maxilarInferior34"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior41", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior41 = :maxilarInferior41"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior42", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior42 = :maxilarInferior42"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior43", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior43 = :maxilarInferior43"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior44", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior44 = :maxilarInferior44"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior51", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior51 = :maxilarInferior51"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior52", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior52 = :maxilarInferior52"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior53", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior53 = :maxilarInferior53"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarInferior54", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarInferior54 = :maxilarInferior54"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarSuperior11", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarSuperior11 = :maxilarSuperior11"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarSuperior12", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarSuperior12 = :maxilarSuperior12"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarSuperior13", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarSuperior13 = :maxilarSuperior13"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarSuperior14", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarSuperior14 = :maxilarSuperior14"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarSuperior21", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarSuperior21 = :maxilarSuperior21"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarSuperior22", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarSuperior22 = :maxilarSuperior22"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarSuperior23", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarSuperior23 = :maxilarSuperior23"),
    @NamedQuery(name = "DatosMedicosPaciente.findByMaxilarSuperior24", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.maxilarSuperior24 = :maxilarSuperior24"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes11", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes11 = :dientes11"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes12", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes12 = :dientes12"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes13", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes13 = :dientes13"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes14", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes14 = :dientes14"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes21", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes21 = :dientes21"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes22", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes22 = :dientes22"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes23", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes23 = :dientes23"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes24", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes24 = :dientes24"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes31", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes31 = :dientes31"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes32", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes32 = :dientes32"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes33", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes33 = :dientes33"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes34", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes34 = :dientes34"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes41", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes41 = :dientes41"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes42", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes42 = :dientes42"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes43", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes43 = :dientes43"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes44", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes44 = :dientes44"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes51", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes51 = :dientes51"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes52", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes52 = :dientes52"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes53", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes53 = :dientes53"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDientes54", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.dientes54 = :dientes54"),
    @NamedQuery(name = "DatosMedicosPaciente.findByPerfilBlando11", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.perfilBlando11 = :perfilBlando11"),
    @NamedQuery(name = "DatosMedicosPaciente.findByPerfilBlando12", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.perfilBlando12 = :perfilBlando12"),
    @NamedQuery(name = "DatosMedicosPaciente.findByPerfilBlando13", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.perfilBlando13 = :perfilBlando13"),
    @NamedQuery(name = "DatosMedicosPaciente.findByPerfilBlando14", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.perfilBlando14 = :perfilBlando14"),
    @NamedQuery(name = "DatosMedicosPaciente.findByConclusionesEsqueletales", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.conclusionesEsqueletales = :conclusionesEsqueletales"),
    @NamedQuery(name = "DatosMedicosPaciente.findByConclusionesDentales", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.conclusionesDentales = :conclusionesDentales"),
    @NamedQuery(name = "DatosMedicosPaciente.findByRelacionEsqueletal", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.relacionEsqueletal = :relacionEsqueletal"),
    @NamedQuery(name = "DatosMedicosPaciente.findByConResponsivaDe", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.conResponsivaDe = :conResponsivaDe"),
    @NamedQuery(name = "DatosMedicosPaciente.findByTipoDeCrecimiento", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.tipoDeCrecimiento = :tipoDeCrecimiento"),
    @NamedQuery(name = "DatosMedicosPaciente.findByClaseMolar", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.claseMolar = :claseMolar"),
    @NamedQuery(name = "DatosMedicosPaciente.findByClaseCanina", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.claseCanina = :claseCanina"),
    @NamedQuery(name = "DatosMedicosPaciente.findByInclinacionDeIncSup", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.inclinacionDeIncSup = :inclinacionDeIncSup"),
    @NamedQuery(name = "DatosMedicosPaciente.findByInclinacionDeIncInf", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.inclinacionDeIncInf = :inclinacionDeIncInf"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDiagnosticoObjetivos", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.diagnosticoObjetivos = :diagnosticoObjetivos"),
    @NamedQuery(name = "DatosMedicosPaciente.findByDiagnosticoPronostico", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.diagnosticoPronostico = :diagnosticoPronostico"),
    @NamedQuery(name = "DatosMedicosPaciente.findByPlanDeTratamiento", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.planDeTratamiento = :planDeTratamiento"),
    @NamedQuery(name = "DatosMedicosPaciente.findByTiempoEstimadoDeTratamiento", query = "SELECT d FROM DatosMedicosPaciente d WHERE d.tiempoEstimadoDeTratamiento = :tiempoEstimadoDeTratamiento")})
public class DatosMedicosPaciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DATOS_MEDICOS_PACIENTE")
    private Integer idDatosMedicosPaciente;
    @Column(name = "HISTORIA_ENFERMEDAD_ACTUAL")
    private String historiaEnfermedadActual;
    @Column(name = "HISTORIA_ACCIDENTES")
    private String historiaAccidentes;
    @Column(name = "HISTORIA_CIRUGIAS")
    private String historiaCirugias;
    @Column(name = "HISTORIA_ENFERMEDADES_INFANCIA")
    private String historiaEnfermedadesInfancia;
    @Column(name = "HISTORIA_ANTECEDENTES_PATOLOGICOS")
    private String historiaAntecedentesPatologicos;
    @Column(name = "HISTORIA_ALERGIAS")
    private String historiaAlergias;
    @Column(name = "EXAMEN_CRANEOFACIAL_PROPORCIONES_CEFALICAS")
    private String examenCraneofacialProporcionesCefalicas;
    @Column(name = "EXAMEN_CRANEOFACIAL_SIMETRIA_FACIAL")
    private String examenCraneofacialSimetriaFacial;
    @Column(name = "EXAMEN_CRANEOFACIAL_ALTURA_FACIAL")
    private String examenCraneofacialAlturaFacial;
    @Column(name = "EXAMEN_CRANEOFACIAL_CONVEXIVIDAD_FACIAL")
    private String examenCraneofacialConvexividadFacial;
    @Column(name = "EXAMEN_CRANEOFACIAL_PERFIL_INFERIOR")
    private String examenCraneofacialPerfilInferior;
    @Column(name = "EXAMEN_CRANEOFACIAL_PORTURA_LABIAL")
    private String examenCraneofacialPorturaLabial;
    @Column(name = "EXAMEN_CRANEOFACIAL_SONRIRA_GINGIVAL")
    private String examenCraneofacialSonriraGingival;
    @Column(name = "EXAMEN_CLINICO_HABITOS")
    private String examenClinicoHabitos;
    @Column(name = "EXAMEN_CLINICO_RESPIRACION")
    private String examenClinicoRespiracion;
    @Column(name = "EXAMEN_CLINICO_FONACION")
    private String examenClinicoFonacion;
    @Column(name = "EXAMEN_CLINICO_FRENILLO_LINGUAL")
    private String examenClinicoFrenilloLingual;
    @Column(name = "EXAMEN_CLINICO_FRENILLO_LABIAL")
    private String examenClinicoFrenilloLabial;
    @Column(name = "COLOR_TEXTURA_GINGIVAL")
    private String colorTexturaGingival;
    @Column(name = "DESHISCENCIAS")
    private String deshiscencias;
    @Column(name = "BOLSAS_PERIODONTALES")
    private String bolsasPeriodontales;
    @Column(name = "AMIGDALAS")
    private String amigdalas;
    @Column(name = "OCLUSION_MOLARES")
    private String oclusionMolares;
    @Column(name = "CALSE_CANINA")
    private String calseCanina;
    @Column(name = "SOBREMORDIDA_HORIZONTAL")
    private String sobremordidaHorizontal;
    @Column(name = "SOBREMORDIDA_VERTICAL")
    private String sobremordidaVertical;
    @Column(name = "LINEA_MEDIA_CORRESPONDE")
    private String lineaMediaCorresponde;
    @Column(name = "MORDIDA_CRUZADA")
    private String mordidaCruzada;
    @Column(name = "HIGIENE_ORAL")
    private String higieneOral;
    @Column(name = "CURVA_SPEE")
    private String curvaSpee;
    @Column(name = "AUSENCIA_CONGENITA")
    private String ausenciaCongenita;
    @Column(name = "SUPERNUMERARIOS")
    private String supernumerarios;
    @Column(name = "QUISTES")
    private String quistes;
    @Column(name = "LESION_PERAPICAL")
    private String lesionPerapical;
    @Column(name = "INCLUIDOS")
    private String incluidos;
    @Column(name = "RAIZ_DISMORFICA")
    private String raizDismorfica;
    @Column(name = "RESORCION_RADICULAR")
    private String resorcionRadicular;
    @Column(name = "TERCEROS_MOLARES")
    private String tercerosMolares;
    @Column(name = "TRABECULADO_OSEO")
    private String trabeculadoOseo;
    @Column(name = "VIAS_AEREAS")
    private String viasAereas;
    @Column(name = "SENOS_MAXILARES")
    private String senosMaxilares;
    @Column(name = "ANALISIS_RADIOGRAFICO_OTROS")
    private String analisisRadiograficoOtros;
    @Column(name = "CARACTERISTICAS_ARCOS_SUPERIOR")
    private String caracteristicasArcosSuperior;
    @Column(name = "CARACTERISTICAS_ARCOS_INFERIOR")
    private String caracteristicasArcosInferior;
    @Column(name = "STEINER_1_1")
    private String steiner11;
    @Column(name = "STEINER_1_2")
    private String steiner12;
    @Column(name = "STEINER_1_3")
    private String steiner13;
    @Column(name = "STEINER_1_4")
    private String steiner14;
    @Column(name = "STEINER_2_1")
    private String steiner21;
    @Column(name = "STEINER_2_2")
    private String steiner22;
    @Column(name = "STEINER_2_3")
    private String steiner23;
    @Column(name = "STEINER_2_4")
    private String steiner24;
    @Column(name = "STEINER_3_1")
    private String steiner31;
    @Column(name = "STEINER_3_2")
    private String steiner32;
    @Column(name = "STEINER_3_3")
    private String steiner33;
    @Column(name = "STEINER_3_4")
    private String steiner34;
    @Column(name = "STEINER_4_1")
    private String steiner41;
    @Column(name = "STEINER_4_2")
    private String steiner42;
    @Column(name = "STEINER_4_3")
    private String steiner43;
    @Column(name = "STEINER_4_4")
    private String steiner44;
    @Column(name = "STEINER_5_1")
    private String steiner51;
    @Column(name = "STEINER_5_2")
    private String steiner52;
    @Column(name = "STEINER_5_3")
    private String steiner53;
    @Column(name = "STEINER_5_4")
    private String steiner54;
    @Column(name = "STEINER_6_1")
    private String steiner61;
    @Column(name = "STEINER_6_2")
    private String steiner62;
    @Column(name = "STEINER_6_3")
    private String steiner63;
    @Column(name = "STEINER_6_4")
    private String steiner64;
    @Column(name = "STEINER_7_1")
    private String steiner71;
    @Column(name = "STEINER_7_2")
    private String steiner72;
    @Column(name = "STEINER_7_3")
    private String steiner73;
    @Column(name = "STEINER_7_4")
    private String steiner74;
    @Column(name = "STEINER_8_1")
    private String steiner81;
    @Column(name = "STEINER_8_2")
    private String steiner82;
    @Column(name = "STEINER_8_3")
    private String steiner83;
    @Column(name = "STEINER_8_4")
    private String steiner84;
    @Column(name = "STEINER_9_1")
    private String steiner91;
    @Column(name = "STEINER_9_2")
    private String steiner92;
    @Column(name = "STEINER_9_3")
    private String steiner93;
    @Column(name = "STEINER_9_4")
    private String steiner94;
    @Column(name = "STEINER_10_1")
    private String steiner101;
    @Column(name = "STEINER_10_2")
    private String steiner102;
    @Column(name = "STEINER_10_3")
    private String steiner103;
    @Column(name = "STEINER_10_4")
    private String steiner104;
    @Column(name = "STEINER_11_1")
    private String steiner111;
    @Column(name = "STEINER_11_2")
    private String steiner112;
    @Column(name = "STEINER_11_3")
    private String steiner113;
    @Column(name = "STEINER_11_4")
    private String steiner114;
    @Column(name = "STEINER_12_1")
    private String steiner121;
    @Column(name = "STEINER_12_2")
    private String steiner122;
    @Column(name = "STEINER_12_3")
    private String steiner123;
    @Column(name = "STEINER_12_4")
    private String steiner124;
    @Column(name = "STEINER_13_1")
    private String steiner131;
    @Column(name = "STEINER_13_2")
    private String steiner132;
    @Column(name = "STEINER_13_3")
    private String steiner133;
    @Column(name = "STEINER_13_4")
    private String steiner134;
    @Column(name = "STEINER_14_1")
    private String steiner141;
    @Column(name = "STEINER_14_2")
    private String steiner142;
    @Column(name = "STEINER_14_3")
    private String steiner143;
    @Column(name = "STEINER_14_4")
    private String steiner144;
    @Column(name = "STEINER_15_1")
    private String steiner151;
    @Column(name = "STEINER_15_2")
    private String steiner152;
    @Column(name = "STEINER_15_3")
    private String steiner153;
    @Column(name = "STEINER_15_4")
    private String steiner154;
    @Column(name = "MAXILAR_INFERIOR_1_1")
    private String maxilarInferior11;
    @Column(name = "MAXILAR_INFERIOR_1_2")
    private String maxilarInferior12;
    @Column(name = "MAXILAR_INFERIOR_1_3")
    private String maxilarInferior13;
    @Column(name = "MAXILAR_INFERIOR_1_4")
    private String maxilarInferior14;
    @Column(name = "MAXILAR_INFERIOR_2_1")
    private String maxilarInferior21;
    @Column(name = "MAXILAR_INFERIOR_2_2")
    private String maxilarInferior22;
    @Column(name = "MAXILAR_INFERIOR_2_3")
    private String maxilarInferior23;
    @Column(name = "MAXILAR_INFERIOR_2_4")
    private String maxilarInferior24;
    @Column(name = "MAXILAR_INFERIOR_3_1")
    private String maxilarInferior31;
    @Column(name = "MAXILAR_INFERIOR_3_2")
    private String maxilarInferior32;
    @Column(name = "MAXILAR_INFERIOR_3_3")
    private String maxilarInferior33;
    @Column(name = "MAXILAR_INFERIOR_3_4")
    private String maxilarInferior34;
    @Column(name = "MAXILAR_INFERIOR_4_1")
    private String maxilarInferior41;
    @Column(name = "MAXILAR_INFERIOR_4_2")
    private String maxilarInferior42;
    @Column(name = "MAXILAR_INFERIOR_4_3")
    private String maxilarInferior43;
    @Column(name = "MAXILAR_INFERIOR_4_4")
    private String maxilarInferior44;
    @Column(name = "MAXILAR_INFERIOR_5_1")
    private String maxilarInferior51;
    @Column(name = "MAXILAR_INFERIOR_5_2")
    private String maxilarInferior52;
    @Column(name = "MAXILAR_INFERIOR_5_3")
    private String maxilarInferior53;
    @Column(name = "MAXILAR_INFERIOR_5_4")
    private String maxilarInferior54;
    @Column(name = "MAXILAR_SUPERIOR_1_1")
    private String maxilarSuperior11;
    @Column(name = "MAXILAR_SUPERIOR_1_2")
    private String maxilarSuperior12;
    @Column(name = "MAXILAR_SUPERIOR_1_3")
    private String maxilarSuperior13;
    @Column(name = "MAXILAR_SUPERIOR_1_4")
    private String maxilarSuperior14;
    @Column(name = "MAXILAR_SUPERIOR_2_1")
    private String maxilarSuperior21;
    @Column(name = "MAXILAR_SUPERIOR_2_2")
    private String maxilarSuperior22;
    @Column(name = "MAXILAR_SUPERIOR_2_3")
    private String maxilarSuperior23;
    @Column(name = "MAXILAR_SUPERIOR_2_4")
    private String maxilarSuperior24;
    @Column(name = "DIENTES_1_1")
    private String dientes11;
    @Column(name = "DIENTES_1_2")
    private String dientes12;
    @Column(name = "DIENTES_1_3")
    private String dientes13;
    @Column(name = "DIENTES_1_4")
    private String dientes14;
    @Column(name = "DIENTES_2_1")
    private String dientes21;
    @Column(name = "DIENTES_2_2")
    private String dientes22;
    @Column(name = "DIENTES_2_3")
    private String dientes23;
    @Column(name = "DIENTES_2_4")
    private String dientes24;
    @Column(name = "DIENTES_3_1")
    private String dientes31;
    @Column(name = "DIENTES_3_2")
    private String dientes32;
    @Column(name = "DIENTES_3_3")
    private String dientes33;
    @Column(name = "DIENTES_3_4")
    private String dientes34;
    @Column(name = "DIENTES_4_1")
    private String dientes41;
    @Column(name = "DIENTES_4_2")
    private String dientes42;
    @Column(name = "DIENTES_4_3")
    private String dientes43;
    @Column(name = "DIENTES_4_4")
    private String dientes44;
    @Column(name = "DIENTES_5_1")
    private String dientes51;
    @Column(name = "DIENTES_5_2")
    private String dientes52;
    @Column(name = "DIENTES_5_3")
    private String dientes53;
    @Column(name = "DIENTES_5_4")
    private String dientes54;
    @Column(name = "PERFIL_BLANDO_1_1")
    private String perfilBlando11;
    @Column(name = "PERFIL_BLANDO_1_2")
    private String perfilBlando12;
    @Column(name = "PERFIL_BLANDO_1_3")
    private String perfilBlando13;
    @Column(name = "PERFIL_BLANDO_1_4")
    private String perfilBlando14;
    @Column(name = "CONCLUSIONES_ESQUELETALES")
    private String conclusionesEsqueletales;
    @Column(name = "CONCLUSIONES_DENTALES")
    private String conclusionesDentales;
    @Column(name = "RELACION_ESQUELETAL")
    private String relacionEsqueletal;
    @Column(name = "CON_RESPONSIVA_DE")
    private String conResponsivaDe;
    @Column(name = "TIPO_DE_CRECIMIENTO")
    private String tipoDeCrecimiento;
    @Column(name = "CLASE_MOLAR")
    private String claseMolar;
    @Column(name = "CLASE_CANINA")
    private String claseCanina;
    @Column(name = "INCLINACION_DE_INC_SUP")
    private String inclinacionDeIncSup;
    @Column(name = "INCLINACION_DE_INC_INF")
    private String inclinacionDeIncInf;
    @Column(name = "DIAGNOSTICO_OBJETIVOS")
    private String diagnosticoObjetivos;
    @Column(name = "DIAGNOSTICO_PRONOSTICO")
    private String diagnosticoPronostico;
    @Column(name = "PLAN_DE_TRATAMIENTO")
    private String planDeTratamiento;
    @Column(name = "TIEMPO_ESTIMADO_DE_TRATAMIENTO")
    private String tiempoEstimadoDeTratamiento;
    @JoinColumn(name = "IDPACIENTE", referencedColumnName = "IDPACIENTE")
    @ManyToOne(optional = false)
    private Paciente idpaciente;

    public DatosMedicosPaciente() {
    }

    public DatosMedicosPaciente(Integer idDatosMedicosPaciente) {
        this.idDatosMedicosPaciente = idDatosMedicosPaciente;
    }

    public Integer getIdDatosMedicosPaciente() {
        return idDatosMedicosPaciente;
    }

    public void setIdDatosMedicosPaciente(Integer idDatosMedicosPaciente) {
        this.idDatosMedicosPaciente = idDatosMedicosPaciente;
    }

    public String getHistoriaEnfermedadActual() {
        return historiaEnfermedadActual;
    }

    public void setHistoriaEnfermedadActual(String historiaEnfermedadActual) {
        this.historiaEnfermedadActual = historiaEnfermedadActual;
    }

    public String getHistoriaAccidentes() {
        return historiaAccidentes;
    }

    public void setHistoriaAccidentes(String historiaAccidentes) {
        this.historiaAccidentes = historiaAccidentes;
    }

    public String getHistoriaCirugias() {
        return historiaCirugias;
    }

    public void setHistoriaCirugias(String historiaCirugias) {
        this.historiaCirugias = historiaCirugias;
    }

    public String getHistoriaEnfermedadesInfancia() {
        return historiaEnfermedadesInfancia;
    }

    public void setHistoriaEnfermedadesInfancia(String historiaEnfermedadesInfancia) {
        this.historiaEnfermedadesInfancia = historiaEnfermedadesInfancia;
    }

    public String getHistoriaAntecedentesPatologicos() {
        return historiaAntecedentesPatologicos;
    }

    public void setHistoriaAntecedentesPatologicos(String historiaAntecedentesPatologicos) {
        this.historiaAntecedentesPatologicos = historiaAntecedentesPatologicos;
    }

    public String getHistoriaAlergias() {
        return historiaAlergias;
    }

    public void setHistoriaAlergias(String historiaAlergias) {
        this.historiaAlergias = historiaAlergias;
    }

    public String getExamenCraneofacialProporcionesCefalicas() {
        return examenCraneofacialProporcionesCefalicas;
    }

    public void setExamenCraneofacialProporcionesCefalicas(String examenCraneofacialProporcionesCefalicas) {
        this.examenCraneofacialProporcionesCefalicas = examenCraneofacialProporcionesCefalicas;
    }

    public String getExamenCraneofacialSimetriaFacial() {
        return examenCraneofacialSimetriaFacial;
    }

    public void setExamenCraneofacialSimetriaFacial(String examenCraneofacialSimetriaFacial) {
        this.examenCraneofacialSimetriaFacial = examenCraneofacialSimetriaFacial;
    }

    public String getExamenCraneofacialAlturaFacial() {
        return examenCraneofacialAlturaFacial;
    }

    public void setExamenCraneofacialAlturaFacial(String examenCraneofacialAlturaFacial) {
        this.examenCraneofacialAlturaFacial = examenCraneofacialAlturaFacial;
    }

    public String getExamenCraneofacialConvexividadFacial() {
        return examenCraneofacialConvexividadFacial;
    }

    public void setExamenCraneofacialConvexividadFacial(String examenCraneofacialConvexividadFacial) {
        this.examenCraneofacialConvexividadFacial = examenCraneofacialConvexividadFacial;
    }

    public String getExamenCraneofacialPerfilInferior() {
        return examenCraneofacialPerfilInferior;
    }

    public void setExamenCraneofacialPerfilInferior(String examenCraneofacialPerfilInferior) {
        this.examenCraneofacialPerfilInferior = examenCraneofacialPerfilInferior;
    }

    public String getExamenCraneofacialPorturaLabial() {
        return examenCraneofacialPorturaLabial;
    }

    public void setExamenCraneofacialPorturaLabial(String examenCraneofacialPorturaLabial) {
        this.examenCraneofacialPorturaLabial = examenCraneofacialPorturaLabial;
    }

    public String getExamenCraneofacialSonriraGingival() {
        return examenCraneofacialSonriraGingival;
    }

    public void setExamenCraneofacialSonriraGingival(String examenCraneofacialSonriraGingival) {
        this.examenCraneofacialSonriraGingival = examenCraneofacialSonriraGingival;
    }

    public String getExamenClinicoHabitos() {
        return examenClinicoHabitos;
    }

    public void setExamenClinicoHabitos(String examenClinicoHabitos) {
        this.examenClinicoHabitos = examenClinicoHabitos;
    }

    public String getExamenClinicoRespiracion() {
        return examenClinicoRespiracion;
    }

    public void setExamenClinicoRespiracion(String examenClinicoRespiracion) {
        this.examenClinicoRespiracion = examenClinicoRespiracion;
    }

    public String getExamenClinicoFonacion() {
        return examenClinicoFonacion;
    }

    public void setExamenClinicoFonacion(String examenClinicoFonacion) {
        this.examenClinicoFonacion = examenClinicoFonacion;
    }

    public String getExamenClinicoFrenilloLingual() {
        return examenClinicoFrenilloLingual;
    }

    public void setExamenClinicoFrenilloLingual(String examenClinicoFrenilloLingual) {
        this.examenClinicoFrenilloLingual = examenClinicoFrenilloLingual;
    }

    public String getExamenClinicoFrenilloLabial() {
        return examenClinicoFrenilloLabial;
    }

    public void setExamenClinicoFrenilloLabial(String examenClinicoFrenilloLabial) {
        this.examenClinicoFrenilloLabial = examenClinicoFrenilloLabial;
    }

    public String getColorTexturaGingival() {
        return colorTexturaGingival;
    }

    public void setColorTexturaGingival(String colorTexturaGingival) {
        this.colorTexturaGingival = colorTexturaGingival;
    }

    public String getDeshiscencias() {
        return deshiscencias;
    }

    public void setDeshiscencias(String deshiscencias) {
        this.deshiscencias = deshiscencias;
    }

    public String getBolsasPeriodontales() {
        return bolsasPeriodontales;
    }

    public void setBolsasPeriodontales(String bolsasPeriodontales) {
        this.bolsasPeriodontales = bolsasPeriodontales;
    }

    public String getAmigdalas() {
        return amigdalas;
    }

    public void setAmigdalas(String amigdalas) {
        this.amigdalas = amigdalas;
    }

    public String getOclusionMolares() {
        return oclusionMolares;
    }

    public void setOclusionMolares(String oclusionMolares) {
        this.oclusionMolares = oclusionMolares;
    }

    public String getCalseCanina() {
        return calseCanina;
    }

    public void setCalseCanina(String calseCanina) {
        this.calseCanina = calseCanina;
    }

    public String getSobremordidaHorizontal() {
        return sobremordidaHorizontal;
    }

    public void setSobremordidaHorizontal(String sobremordidaHorizontal) {
        this.sobremordidaHorizontal = sobremordidaHorizontal;
    }

    public String getSobremordidaVertical() {
        return sobremordidaVertical;
    }

    public void setSobremordidaVertical(String sobremordidaVertical) {
        this.sobremordidaVertical = sobremordidaVertical;
    }

    public String getLineaMediaCorresponde() {
        return lineaMediaCorresponde;
    }

    public void setLineaMediaCorresponde(String lineaMediaCorresponde) {
        this.lineaMediaCorresponde = lineaMediaCorresponde;
    }

    public String getMordidaCruzada() {
        return mordidaCruzada;
    }

    public void setMordidaCruzada(String mordidaCruzada) {
        this.mordidaCruzada = mordidaCruzada;
    }

    public String getHigieneOral() {
        return higieneOral;
    }

    public void setHigieneOral(String higieneOral) {
        this.higieneOral = higieneOral;
    }

    public String getCurvaSpee() {
        return curvaSpee;
    }

    public void setCurvaSpee(String curvaSpee) {
        this.curvaSpee = curvaSpee;
    }

    public String getAusenciaCongenita() {
        return ausenciaCongenita;
    }

    public void setAusenciaCongenita(String ausenciaCongenita) {
        this.ausenciaCongenita = ausenciaCongenita;
    }

    public String getSupernumerarios() {
        return supernumerarios;
    }

    public void setSupernumerarios(String supernumerarios) {
        this.supernumerarios = supernumerarios;
    }

    public String getQuistes() {
        return quistes;
    }

    public void setQuistes(String quistes) {
        this.quistes = quistes;
    }

    public String getLesionPerapical() {
        return lesionPerapical;
    }

    public void setLesionPerapical(String lesionPerapical) {
        this.lesionPerapical = lesionPerapical;
    }

    public String getIncluidos() {
        return incluidos;
    }

    public void setIncluidos(String incluidos) {
        this.incluidos = incluidos;
    }

    public String getRaizDismorfica() {
        return raizDismorfica;
    }

    public void setRaizDismorfica(String raizDismorfica) {
        this.raizDismorfica = raizDismorfica;
    }

    public String getResorcionRadicular() {
        return resorcionRadicular;
    }

    public void setResorcionRadicular(String resorcionRadicular) {
        this.resorcionRadicular = resorcionRadicular;
    }

    public String getTercerosMolares() {
        return tercerosMolares;
    }

    public void setTercerosMolares(String tercerosMolares) {
        this.tercerosMolares = tercerosMolares;
    }

    public String getTrabeculadoOseo() {
        return trabeculadoOseo;
    }

    public void setTrabeculadoOseo(String trabeculadoOseo) {
        this.trabeculadoOseo = trabeculadoOseo;
    }

    public String getViasAereas() {
        return viasAereas;
    }

    public void setViasAereas(String viasAereas) {
        this.viasAereas = viasAereas;
    }

    public String getSenosMaxilares() {
        return senosMaxilares;
    }

    public void setSenosMaxilares(String senosMaxilares) {
        this.senosMaxilares = senosMaxilares;
    }

    public String getAnalisisRadiograficoOtros() {
        return analisisRadiograficoOtros;
    }

    public void setAnalisisRadiograficoOtros(String analisisRadiograficoOtros) {
        this.analisisRadiograficoOtros = analisisRadiograficoOtros;
    }

    public String getCaracteristicasArcosSuperior() {
        return caracteristicasArcosSuperior;
    }

    public void setCaracteristicasArcosSuperior(String caracteristicasArcosSuperior) {
        this.caracteristicasArcosSuperior = caracteristicasArcosSuperior;
    }

    public String getCaracteristicasArcosInferior() {
        return caracteristicasArcosInferior;
    }

    public void setCaracteristicasArcosInferior(String caracteristicasArcosInferior) {
        this.caracteristicasArcosInferior = caracteristicasArcosInferior;
    }

    public String getSteiner11() {
        return steiner11;
    }

    public void setSteiner11(String steiner11) {
        this.steiner11 = steiner11;
    }

    public String getSteiner12() {
        return steiner12;
    }

    public void setSteiner12(String steiner12) {
        this.steiner12 = steiner12;
    }

    public String getSteiner13() {
        return steiner13;
    }

    public void setSteiner13(String steiner13) {
        this.steiner13 = steiner13;
    }

    public String getSteiner14() {
        return steiner14;
    }

    public void setSteiner14(String steiner14) {
        this.steiner14 = steiner14;
    }

    public String getSteiner21() {
        return steiner21;
    }

    public void setSteiner21(String steiner21) {
        this.steiner21 = steiner21;
    }

    public String getSteiner22() {
        return steiner22;
    }

    public void setSteiner22(String steiner22) {
        this.steiner22 = steiner22;
    }

    public String getSteiner23() {
        return steiner23;
    }

    public void setSteiner23(String steiner23) {
        this.steiner23 = steiner23;
    }

    public String getSteiner24() {
        return steiner24;
    }

    public void setSteiner24(String steiner24) {
        this.steiner24 = steiner24;
    }

    public String getSteiner31() {
        return steiner31;
    }

    public void setSteiner31(String steiner31) {
        this.steiner31 = steiner31;
    }

    public String getSteiner32() {
        return steiner32;
    }

    public void setSteiner32(String steiner32) {
        this.steiner32 = steiner32;
    }

    public String getSteiner33() {
        return steiner33;
    }

    public void setSteiner33(String steiner33) {
        this.steiner33 = steiner33;
    }

    public String getSteiner34() {
        return steiner34;
    }

    public void setSteiner34(String steiner34) {
        this.steiner34 = steiner34;
    }

    public String getSteiner41() {
        return steiner41;
    }

    public void setSteiner41(String steiner41) {
        this.steiner41 = steiner41;
    }

    public String getSteiner42() {
        return steiner42;
    }

    public void setSteiner42(String steiner42) {
        this.steiner42 = steiner42;
    }

    public String getSteiner43() {
        return steiner43;
    }

    public void setSteiner43(String steiner43) {
        this.steiner43 = steiner43;
    }

    public String getSteiner44() {
        return steiner44;
    }

    public void setSteiner44(String steiner44) {
        this.steiner44 = steiner44;
    }

    public String getSteiner51() {
        return steiner51;
    }

    public void setSteiner51(String steiner51) {
        this.steiner51 = steiner51;
    }

    public String getSteiner52() {
        return steiner52;
    }

    public void setSteiner52(String steiner52) {
        this.steiner52 = steiner52;
    }

    public String getSteiner53() {
        return steiner53;
    }

    public void setSteiner53(String steiner53) {
        this.steiner53 = steiner53;
    }

    public String getSteiner54() {
        return steiner54;
    }

    public void setSteiner54(String steiner54) {
        this.steiner54 = steiner54;
    }

    public String getSteiner61() {
        return steiner61;
    }

    public void setSteiner61(String steiner61) {
        this.steiner61 = steiner61;
    }

    public String getSteiner62() {
        return steiner62;
    }

    public void setSteiner62(String steiner62) {
        this.steiner62 = steiner62;
    }

    public String getSteiner63() {
        return steiner63;
    }

    public void setSteiner63(String steiner63) {
        this.steiner63 = steiner63;
    }

    public String getSteiner64() {
        return steiner64;
    }

    public void setSteiner64(String steiner64) {
        this.steiner64 = steiner64;
    }

    public String getSteiner71() {
        return steiner71;
    }

    public void setSteiner71(String steiner71) {
        this.steiner71 = steiner71;
    }

    public String getSteiner72() {
        return steiner72;
    }

    public void setSteiner72(String steiner72) {
        this.steiner72 = steiner72;
    }

    public String getSteiner73() {
        return steiner73;
    }

    public void setSteiner73(String steiner73) {
        this.steiner73 = steiner73;
    }

    public String getSteiner74() {
        return steiner74;
    }

    public void setSteiner74(String steiner74) {
        this.steiner74 = steiner74;
    }

    public String getSteiner81() {
        return steiner81;
    }

    public void setSteiner81(String steiner81) {
        this.steiner81 = steiner81;
    }

    public String getSteiner82() {
        return steiner82;
    }

    public void setSteiner82(String steiner82) {
        this.steiner82 = steiner82;
    }

    public String getSteiner83() {
        return steiner83;
    }

    public void setSteiner83(String steiner83) {
        this.steiner83 = steiner83;
    }

    public String getSteiner84() {
        return steiner84;
    }

    public void setSteiner84(String steiner84) {
        this.steiner84 = steiner84;
    }

    public String getSteiner91() {
        return steiner91;
    }

    public void setSteiner91(String steiner91) {
        this.steiner91 = steiner91;
    }

    public String getSteiner92() {
        return steiner92;
    }

    public void setSteiner92(String steiner92) {
        this.steiner92 = steiner92;
    }

    public String getSteiner93() {
        return steiner93;
    }

    public void setSteiner93(String steiner93) {
        this.steiner93 = steiner93;
    }

    public String getSteiner94() {
        return steiner94;
    }

    public void setSteiner94(String steiner94) {
        this.steiner94 = steiner94;
    }

    public String getSteiner101() {
        return steiner101;
    }

    public void setSteiner101(String steiner101) {
        this.steiner101 = steiner101;
    }

    public String getSteiner102() {
        return steiner102;
    }

    public void setSteiner102(String steiner102) {
        this.steiner102 = steiner102;
    }

    public String getSteiner103() {
        return steiner103;
    }

    public void setSteiner103(String steiner103) {
        this.steiner103 = steiner103;
    }

    public String getSteiner104() {
        return steiner104;
    }

    public void setSteiner104(String steiner104) {
        this.steiner104 = steiner104;
    }

    public String getSteiner111() {
        return steiner111;
    }

    public void setSteiner111(String steiner111) {
        this.steiner111 = steiner111;
    }

    public String getSteiner112() {
        return steiner112;
    }

    public void setSteiner112(String steiner112) {
        this.steiner112 = steiner112;
    }

    public String getSteiner113() {
        return steiner113;
    }

    public void setSteiner113(String steiner113) {
        this.steiner113 = steiner113;
    }

    public String getSteiner114() {
        return steiner114;
    }

    public void setSteiner114(String steiner114) {
        this.steiner114 = steiner114;
    }

    public String getSteiner121() {
        return steiner121;
    }

    public void setSteiner121(String steiner121) {
        this.steiner121 = steiner121;
    }

    public String getSteiner122() {
        return steiner122;
    }

    public void setSteiner122(String steiner122) {
        this.steiner122 = steiner122;
    }

    public String getSteiner123() {
        return steiner123;
    }

    public void setSteiner123(String steiner123) {
        this.steiner123 = steiner123;
    }

    public String getSteiner124() {
        return steiner124;
    }

    public void setSteiner124(String steiner124) {
        this.steiner124 = steiner124;
    }

    public String getSteiner131() {
        return steiner131;
    }

    public void setSteiner131(String steiner131) {
        this.steiner131 = steiner131;
    }

    public String getSteiner132() {
        return steiner132;
    }

    public void setSteiner132(String steiner132) {
        this.steiner132 = steiner132;
    }

    public String getSteiner133() {
        return steiner133;
    }

    public void setSteiner133(String steiner133) {
        this.steiner133 = steiner133;
    }

    public String getSteiner134() {
        return steiner134;
    }

    public void setSteiner134(String steiner134) {
        this.steiner134 = steiner134;
    }

    public String getSteiner141() {
        return steiner141;
    }

    public void setSteiner141(String steiner141) {
        this.steiner141 = steiner141;
    }

    public String getSteiner142() {
        return steiner142;
    }

    public void setSteiner142(String steiner142) {
        this.steiner142 = steiner142;
    }

    public String getSteiner143() {
        return steiner143;
    }

    public void setSteiner143(String steiner143) {
        this.steiner143 = steiner143;
    }

    public String getSteiner144() {
        return steiner144;
    }

    public void setSteiner144(String steiner144) {
        this.steiner144 = steiner144;
    }

    public String getSteiner151() {
        return steiner151;
    }

    public void setSteiner151(String steiner151) {
        this.steiner151 = steiner151;
    }

    public String getSteiner152() {
        return steiner152;
    }

    public void setSteiner152(String steiner152) {
        this.steiner152 = steiner152;
    }

    public String getSteiner153() {
        return steiner153;
    }

    public void setSteiner153(String steiner153) {
        this.steiner153 = steiner153;
    }

    public String getSteiner154() {
        return steiner154;
    }

    public void setSteiner154(String steiner154) {
        this.steiner154 = steiner154;
    }

    public String getMaxilarInferior11() {
        return maxilarInferior11;
    }

    public void setMaxilarInferior11(String maxilarInferior11) {
        this.maxilarInferior11 = maxilarInferior11;
    }

    public String getMaxilarInferior12() {
        return maxilarInferior12;
    }

    public void setMaxilarInferior12(String maxilarInferior12) {
        this.maxilarInferior12 = maxilarInferior12;
    }

    public String getMaxilarInferior13() {
        return maxilarInferior13;
    }

    public void setMaxilarInferior13(String maxilarInferior13) {
        this.maxilarInferior13 = maxilarInferior13;
    }

    public String getMaxilarInferior14() {
        return maxilarInferior14;
    }

    public void setMaxilarInferior14(String maxilarInferior14) {
        this.maxilarInferior14 = maxilarInferior14;
    }

    public String getMaxilarInferior21() {
        return maxilarInferior21;
    }

    public void setMaxilarInferior21(String maxilarInferior21) {
        this.maxilarInferior21 = maxilarInferior21;
    }

    public String getMaxilarInferior22() {
        return maxilarInferior22;
    }

    public void setMaxilarInferior22(String maxilarInferior22) {
        this.maxilarInferior22 = maxilarInferior22;
    }

    public String getMaxilarInferior23() {
        return maxilarInferior23;
    }

    public void setMaxilarInferior23(String maxilarInferior23) {
        this.maxilarInferior23 = maxilarInferior23;
    }

    public String getMaxilarInferior24() {
        return maxilarInferior24;
    }

    public void setMaxilarInferior24(String maxilarInferior24) {
        this.maxilarInferior24 = maxilarInferior24;
    }

    public String getMaxilarInferior31() {
        return maxilarInferior31;
    }

    public void setMaxilarInferior31(String maxilarInferior31) {
        this.maxilarInferior31 = maxilarInferior31;
    }

    public String getMaxilarInferior32() {
        return maxilarInferior32;
    }

    public void setMaxilarInferior32(String maxilarInferior32) {
        this.maxilarInferior32 = maxilarInferior32;
    }

    public String getMaxilarInferior33() {
        return maxilarInferior33;
    }

    public void setMaxilarInferior33(String maxilarInferior33) {
        this.maxilarInferior33 = maxilarInferior33;
    }

    public String getMaxilarInferior34() {
        return maxilarInferior34;
    }

    public void setMaxilarInferior34(String maxilarInferior34) {
        this.maxilarInferior34 = maxilarInferior34;
    }

    public String getMaxilarInferior41() {
        return maxilarInferior41;
    }

    public void setMaxilarInferior41(String maxilarInferior41) {
        this.maxilarInferior41 = maxilarInferior41;
    }

    public String getMaxilarInferior42() {
        return maxilarInferior42;
    }

    public void setMaxilarInferior42(String maxilarInferior42) {
        this.maxilarInferior42 = maxilarInferior42;
    }

    public String getMaxilarInferior43() {
        return maxilarInferior43;
    }

    public void setMaxilarInferior43(String maxilarInferior43) {
        this.maxilarInferior43 = maxilarInferior43;
    }

    public String getMaxilarInferior44() {
        return maxilarInferior44;
    }

    public void setMaxilarInferior44(String maxilarInferior44) {
        this.maxilarInferior44 = maxilarInferior44;
    }

    public String getMaxilarInferior51() {
        return maxilarInferior51;
    }

    public void setMaxilarInferior51(String maxilarInferior51) {
        this.maxilarInferior51 = maxilarInferior51;
    }

    public String getMaxilarInferior52() {
        return maxilarInferior52;
    }

    public void setMaxilarInferior52(String maxilarInferior52) {
        this.maxilarInferior52 = maxilarInferior52;
    }

    public String getMaxilarInferior53() {
        return maxilarInferior53;
    }

    public void setMaxilarInferior53(String maxilarInferior53) {
        this.maxilarInferior53 = maxilarInferior53;
    }

    public String getMaxilarInferior54() {
        return maxilarInferior54;
    }

    public void setMaxilarInferior54(String maxilarInferior54) {
        this.maxilarInferior54 = maxilarInferior54;
    }

    public String getMaxilarSuperior11() {
        return maxilarSuperior11;
    }

    public void setMaxilarSuperior11(String maxilarSuperior11) {
        this.maxilarSuperior11 = maxilarSuperior11;
    }

    public String getMaxilarSuperior12() {
        return maxilarSuperior12;
    }

    public void setMaxilarSuperior12(String maxilarSuperior12) {
        this.maxilarSuperior12 = maxilarSuperior12;
    }

    public String getMaxilarSuperior13() {
        return maxilarSuperior13;
    }

    public void setMaxilarSuperior13(String maxilarSuperior13) {
        this.maxilarSuperior13 = maxilarSuperior13;
    }

    public String getMaxilarSuperior14() {
        return maxilarSuperior14;
    }

    public void setMaxilarSuperior14(String maxilarSuperior14) {
        this.maxilarSuperior14 = maxilarSuperior14;
    }

    public String getMaxilarSuperior21() {
        return maxilarSuperior21;
    }

    public void setMaxilarSuperior21(String maxilarSuperior21) {
        this.maxilarSuperior21 = maxilarSuperior21;
    }

    public String getMaxilarSuperior22() {
        return maxilarSuperior22;
    }

    public void setMaxilarSuperior22(String maxilarSuperior22) {
        this.maxilarSuperior22 = maxilarSuperior22;
    }

    public String getMaxilarSuperior23() {
        return maxilarSuperior23;
    }

    public void setMaxilarSuperior23(String maxilarSuperior23) {
        this.maxilarSuperior23 = maxilarSuperior23;
    }

    public String getMaxilarSuperior24() {
        return maxilarSuperior24;
    }

    public void setMaxilarSuperior24(String maxilarSuperior24) {
        this.maxilarSuperior24 = maxilarSuperior24;
    }

    public String getDientes11() {
        return dientes11;
    }

    public void setDientes11(String dientes11) {
        this.dientes11 = dientes11;
    }

    public String getDientes12() {
        return dientes12;
    }

    public void setDientes12(String dientes12) {
        this.dientes12 = dientes12;
    }

    public String getDientes13() {
        return dientes13;
    }

    public void setDientes13(String dientes13) {
        this.dientes13 = dientes13;
    }

    public String getDientes14() {
        return dientes14;
    }

    public void setDientes14(String dientes14) {
        this.dientes14 = dientes14;
    }

    public String getDientes21() {
        return dientes21;
    }

    public void setDientes21(String dientes21) {
        this.dientes21 = dientes21;
    }

    public String getDientes22() {
        return dientes22;
    }

    public void setDientes22(String dientes22) {
        this.dientes22 = dientes22;
    }

    public String getDientes23() {
        return dientes23;
    }

    public void setDientes23(String dientes23) {
        this.dientes23 = dientes23;
    }

    public String getDientes24() {
        return dientes24;
    }

    public void setDientes24(String dientes24) {
        this.dientes24 = dientes24;
    }

    public String getDientes31() {
        return dientes31;
    }

    public void setDientes31(String dientes31) {
        this.dientes31 = dientes31;
    }

    public String getDientes32() {
        return dientes32;
    }

    public void setDientes32(String dientes32) {
        this.dientes32 = dientes32;
    }

    public String getDientes33() {
        return dientes33;
    }

    public void setDientes33(String dientes33) {
        this.dientes33 = dientes33;
    }

    public String getDientes34() {
        return dientes34;
    }

    public void setDientes34(String dientes34) {
        this.dientes34 = dientes34;
    }

    public String getDientes41() {
        return dientes41;
    }

    public void setDientes41(String dientes41) {
        this.dientes41 = dientes41;
    }

    public String getDientes42() {
        return dientes42;
    }

    public void setDientes42(String dientes42) {
        this.dientes42 = dientes42;
    }

    public String getDientes43() {
        return dientes43;
    }

    public void setDientes43(String dientes43) {
        this.dientes43 = dientes43;
    }

    public String getDientes44() {
        return dientes44;
    }

    public void setDientes44(String dientes44) {
        this.dientes44 = dientes44;
    }

    public String getDientes51() {
        return dientes51;
    }

    public void setDientes51(String dientes51) {
        this.dientes51 = dientes51;
    }

    public String getDientes52() {
        return dientes52;
    }

    public void setDientes52(String dientes52) {
        this.dientes52 = dientes52;
    }

    public String getDientes53() {
        return dientes53;
    }

    public void setDientes53(String dientes53) {
        this.dientes53 = dientes53;
    }

    public String getDientes54() {
        return dientes54;
    }

    public void setDientes54(String dientes54) {
        this.dientes54 = dientes54;
    }

    public String getPerfilBlando11() {
        return perfilBlando11;
    }

    public void setPerfilBlando11(String perfilBlando11) {
        this.perfilBlando11 = perfilBlando11;
    }

    public String getPerfilBlando12() {
        return perfilBlando12;
    }

    public void setPerfilBlando12(String perfilBlando12) {
        this.perfilBlando12 = perfilBlando12;
    }

    public String getPerfilBlando13() {
        return perfilBlando13;
    }

    public void setPerfilBlando13(String perfilBlando13) {
        this.perfilBlando13 = perfilBlando13;
    }

    public String getPerfilBlando14() {
        return perfilBlando14;
    }

    public void setPerfilBlando14(String perfilBlando14) {
        this.perfilBlando14 = perfilBlando14;
    }

    public String getConclusionesEsqueletales() {
        return conclusionesEsqueletales;
    }

    public void setConclusionesEsqueletales(String conclusionesEsqueletales) {
        this.conclusionesEsqueletales = conclusionesEsqueletales;
    }

    public String getConclusionesDentales() {
        return conclusionesDentales;
    }

    public void setConclusionesDentales(String conclusionesDentales) {
        this.conclusionesDentales = conclusionesDentales;
    }

    public String getRelacionEsqueletal() {
        return relacionEsqueletal;
    }

    public void setRelacionEsqueletal(String relacionEsqueletal) {
        this.relacionEsqueletal = relacionEsqueletal;
    }

    public String getConResponsivaDe() {
        return conResponsivaDe;
    }

    public void setConResponsivaDe(String conResponsivaDe) {
        this.conResponsivaDe = conResponsivaDe;
    }

    public String getTipoDeCrecimiento() {
        return tipoDeCrecimiento;
    }

    public void setTipoDeCrecimiento(String tipoDeCrecimiento) {
        this.tipoDeCrecimiento = tipoDeCrecimiento;
    }

    public String getClaseMolar() {
        return claseMolar;
    }

    public void setClaseMolar(String claseMolar) {
        this.claseMolar = claseMolar;
    }

    public String getClaseCanina() {
        return claseCanina;
    }

    public void setClaseCanina(String claseCanina) {
        this.claseCanina = claseCanina;
    }

    public String getInclinacionDeIncSup() {
        return inclinacionDeIncSup;
    }

    public void setInclinacionDeIncSup(String inclinacionDeIncSup) {
        this.inclinacionDeIncSup = inclinacionDeIncSup;
    }

    public String getInclinacionDeIncInf() {
        return inclinacionDeIncInf;
    }

    public void setInclinacionDeIncInf(String inclinacionDeIncInf) {
        this.inclinacionDeIncInf = inclinacionDeIncInf;
    }

    public String getDiagnosticoObjetivos() {
        return diagnosticoObjetivos;
    }

    public void setDiagnosticoObjetivos(String diagnosticoObjetivos) {
        this.diagnosticoObjetivos = diagnosticoObjetivos;
    }

    public String getDiagnosticoPronostico() {
        return diagnosticoPronostico;
    }

    public void setDiagnosticoPronostico(String diagnosticoPronostico) {
        this.diagnosticoPronostico = diagnosticoPronostico;
    }

    public String getPlanDeTratamiento() {
        return planDeTratamiento;
    }

    public void setPlanDeTratamiento(String planDeTratamiento) {
        this.planDeTratamiento = planDeTratamiento;
    }

    public String getTiempoEstimadoDeTratamiento() {
        return tiempoEstimadoDeTratamiento;
    }

    public void setTiempoEstimadoDeTratamiento(String tiempoEstimadoDeTratamiento) {
        this.tiempoEstimadoDeTratamiento = tiempoEstimadoDeTratamiento;
    }

    public Paciente getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(Paciente idpaciente) {
        this.idpaciente = idpaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDatosMedicosPaciente != null ? idDatosMedicosPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosMedicosPaciente)) {
            return false;
        }
        DatosMedicosPaciente other = (DatosMedicosPaciente) object;
        if ((this.idDatosMedicosPaciente == null && other.idDatosMedicosPaciente != null) || (this.idDatosMedicosPaciente != null && !this.idDatosMedicosPaciente.equals(other.idDatosMedicosPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.DatosMedicosPaciente[ idDatosMedicosPaciente=" + idDatosMedicosPaciente + " ]";
    }
    
}
