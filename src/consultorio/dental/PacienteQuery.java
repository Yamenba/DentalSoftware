/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import com.google.api.client.util.DateTime;
import datos.Ajustes;
import datos.Citas;
import datos.CostoTratamiento;
import datos.DatosMedicosPaciente;
import datos.Imagen;
import datos.Paciente;
import datos.Pago;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import jfxtras.scene.control.agenda.Agenda.Appointment;

/**
 *
 * @author Ricardo
 */
public class PacienteQuery {
    EntityManager em;
    EntityManagerFactory emf;
    
    public PacienteQuery() {
        JFileChooser fr = new JFileChooser();
        FileSystemView fw = fr.getFileSystemView();
        System.setProperty("derby.system.home", fw.getDefaultDirectory().getAbsolutePath());
        emf = Persistence.createEntityManagerFactory("Consultorio_DentalPU");
        em = emf.createEntityManager();
      
    }
    
    public List<Paciente> listPaciente(){
        
        return em.createNamedQuery("Paciente.findAll",Paciente.class).getResultList();
        
    }
    
    public List<Paciente> pacientePorId(int id){
        return em.createNamedQuery("Paciente.findByIdpaciente").setParameter("idpaciente", id).getResultList();
    }
    
    public List<Citas> citaPorId(int id){
        return em.createNamedQuery("Citas.findByIdcita").setParameter("idcita", id).getResultList();
    }
    
    public List<Citas> citaPorAno(int ano){
        return em.createNamedQuery("Citas.findByAno").setParameter("ano", ano).getResultList();
    }
    
    public List<Citas> listCita(){
        return em.createNamedQuery("Citas.findAll",Citas.class).getResultList();
    }
    
    public List<Citas> citasPorPaciente(int idPaciente){
        Paciente paciente = em.find(Paciente.class, idPaciente);
        return em.createQuery("SELECT d FROM Citas d WHERE d.idpaciente = :idpaciente").setParameter("idpaciente", paciente).getResultList();
    }
    
    public List<DatosMedicosPaciente> datosMedicosPacientePorPaciente(Paciente idPaciente){
        return em.createQuery("SELECT d FROM DatosMedicosPaciente d WHERE d.idpaciente = :idpaciente").setParameter("idpaciente", idPaciente).getResultList();
    }
    
    public List<DatosMedicosPaciente> datosMedicosPaciente(){
        return em.createNamedQuery("DatosMedicosPaciente.findAll",DatosMedicosPaciente.class).getResultList();
    }
    
    public List<Ajustes> ajustes(){
        return em.createNamedQuery("Ajustes.findAll",Ajustes.class).getResultList();
    }
    
    public List<CostoTratamiento> costoTratamientoPorPaciente(int idPaciente){
        return em.createNamedQuery("CostoTratamiento.findByIdPaciente", CostoTratamiento.class).setParameter("idPaciente", idPaciente).getResultList();
    }
    
    public List<Pago> pagosPorPaciente(int idPaciente){
        Paciente paciente = em.find(Paciente.class, idPaciente);
        return em.createQuery("SELECT d FROM Pago d WHERE d.idPaciente = :idpaciente").setParameter("idpaciente", paciente).getResultList();
    }
    
    public Pago pagoPorId(int id_pago){
        return em.createNamedQuery("Pago.findByIdPago", Pago.class).setParameter("idPago", id_pago).getResultList().get(0);
    }
    
    public List<Imagen> imagenesPaciente(int idPaciente){
        Paciente paciente = em.find(Paciente.class, idPaciente);
        return em.createQuery("SELECT d FROM Imagen d WHERE d.idPaciente = :idpaciente").setParameter("idpaciente",paciente).getResultList();
    }
    
    public Imagen imagenPorId(int id_imagen){
        return em.createNamedQuery("Imagen.findByIdImagen", Imagen.class).setParameter("idImagen", id_imagen).getResultList().get(0);
    }
    
    public int save(Paciente paciente){
        em.getTransaction().begin();
        em.persist(paciente);
        em.getTransaction().commit();
        return paciente.getIdpaciente();
    }
    
    
    public void delete(Paciente p){
         Paciente paciente = em.find(Paciente.class, p.getIdpaciente());
        em.getTransaction().begin();
        em.remove(paciente);
        em.getTransaction().commit();
    }
    
    public int saveCita(Appointment a,String grupo, String motivo, Paciente p, String id_calendar){
        Citas cita = new Citas();
        Calendar fecha_inicio = a.getStartTime();
        Calendar fecha_fin = a.getEndTime();
        cita.setSummary(a.getSummary());
        cita.setDescription(motivo);
        cita.setDia(fecha_inicio.get(Calendar.DAY_OF_MONTH));
        cita.setMes(fecha_inicio.get(Calendar.MONTH));
        cita.setAno(fecha_inicio.get(Calendar.YEAR));
        cita.setHoraInicio(fecha_inicio.get(Calendar.HOUR_OF_DAY));
        cita.setMinutoInicio(fecha_inicio.get(Calendar.MINUTE));
        cita.setHoraFin(fecha_fin.get(Calendar.HOUR_OF_DAY));
        cita.setMinutoFin(fecha_fin.get(Calendar.MINUTE));
        cita.setGrupo(grupo);
        cita.setIdpaciente(p);
        cita.setIdCalendar(id_calendar);
        
        em.getTransaction().begin();
        em.persist(cita);
        em.getTransaction().commit();
        return cita.getIdcita();
    }
    
    
    public void saveCita(Citas c){
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }
    
    public void deleteCita(int a) throws Exception{
        Citas cita = em.find(Citas.class, a);
        RecordatorioMail recordatorio = new RecordatorioMail();
        recordatorio.deleteRecordatorio(cita.getIdCalendar());
        em.getTransaction().begin();
        em.remove(cita);
        em.getTransaction().commit();
    }
    
    public void updateCita(Appointment a, int id, String grupo, String motivo, Paciente p) throws Exception {
        Citas cita = em.find(Citas.class, id);
        String id_calendar = cita.getIdCalendar();
        Calendar fecha_inicio = a.getStartTime();
        Calendar fecha_fin = a.getEndTime();
        em.getTransaction().begin();
        cita.setSummary(a.getSummary());
        cita.setDescription(motivo);
        cita.setDia(fecha_inicio.get(Calendar.DAY_OF_MONTH));
        cita.setMes(fecha_inicio.get(Calendar.MONTH));
        cita.setAno(fecha_inicio.get(Calendar.YEAR));
        cita.setHoraInicio(fecha_inicio.get(Calendar.HOUR_OF_DAY));
        cita.setMinutoInicio(fecha_inicio.get(Calendar.MINUTE));
        cita.setHoraFin(fecha_fin.get(Calendar.HOUR_OF_DAY));
        cita.setMinutoFin(fecha_fin.get(Calendar.MINUTE));
        cita.setGrupo(grupo);
        cita.setIdpaciente(p);
        em.getTransaction().commit();
        //////aqui se edita la cita en google calendar
       List<Ajustes> lista_ajustes = new ArrayList<>();
       lista_ajustes = ajustes();
       Ajustes ajustes = lista_ajustes.get(0);
       if(ajustes.getRecordatorioActivado() && cita.getIdCalendar() != null){
        RecordatorioMail recordatorio = new RecordatorioMail();
        recordatorio.updateRecordatorio(id_calendar, new DateTime(fecha_inicio.getTime()),new DateTime(fecha_fin.getTime()),p.getMail());
       }
    }
    
    public void updateCita(int id_cita, LocalDate fecha, String tratamiento){
        Citas cita = em.find(Citas.class, id_cita);
        em.getTransaction().begin();
        cita.setAno(fecha.getYear());
        cita.setMes(fecha.getMonthValue()-1);
        cita.setDia(fecha.getDayOfMonth());
        cita.setTratamientoDeLaSesion(tratamiento);
        em.getTransaction().commit();
    }
    
    
    public int saveDatosMedicosPaciente(DatosMedicosPaciente datos){
        em.getTransaction().begin();
        em.persist(datos);
        em.getTransaction().commit();
        return datos.getIdDatosMedicosPaciente();
    }
    
    public void savePago(Pago pago){
        em.getTransaction().begin();
        em.persist(pago);
        em.getTransaction().commit();
    }
    
    public void deletePago(int id_pago){
        Pago pago = em.find(Pago.class, id_pago);
        em.getTransaction().begin();
        em.remove(pago);
        em.getTransaction().commit();
    }
    
    public void updatePago(int id_pago, double cantidad, LocalDate fecha, String concepto){
        Pago pago = em.find(Pago.class, id_pago);
        em.getTransaction().begin();
        pago.setCantidad(cantidad);
        pago.setAnoPago(fecha.getYear());
        pago.setMesPago(fecha.getMonthValue());
        pago.setDiaPago(fecha.getDayOfMonth());
        pago.setDescripcion(concepto);
        em.getTransaction().commit();
    }
    
    public void nuevosAjustes(Boolean act,String correo){
        Boolean activado = act;
        String titulo = "Cita con el dentista";
        String descripcion = "Tienes cita con el dentista";
        Ajustes ajustes = new Ajustes();
        ajustes.setRecordatorioActivado(activado);
        ajustes.setRecordatorioTitulo(titulo);
        ajustes.setRecordatorioDescripcion(descripcion);
        ajustes.setIdAjustes(1);
        ajustes.setCorreo(correo);
        em.getTransaction().begin();
        em.persist(ajustes);
        em.getTransaction().commit();
    }
    
    public void updateAjustes(Boolean activado, String titulo, String descripcion,String correo){
       Ajustes ajustes = em.find(Ajustes.class, 1);
       em.getTransaction().begin();
       ajustes.setRecordatorioActivado(activado);
       ajustes.setRecordatorioTitulo(titulo);
       ajustes.setRecordatorioDescripcion(descripcion);
       ajustes.setCorreo(correo);
       em.getTransaction().commit();
    }
    
    public void updateAjustesCorreo(String correo){
        Ajustes ajustes = em.find(Ajustes.class, 1);
        em.getTransaction().begin();
        ajustes.setCorreo(correo);
        em.getTransaction().commit();
    }
    
    public void saveCostoTratamiento(CostoTratamiento costo){
        em.getTransaction().begin();
        em.persist(costo);
        em.getTransaction().commit();
    }
    
    public void updateCostoTratamiento(int id,double pagoInicial,double numeroMensualidades,double montoMensualidad){
        CostoTratamiento costo = em.find(CostoTratamiento.class, id);
        em.getTransaction().begin();
        costo.setPagoInicial(pagoInicial);
        costo.setNumeroMensualidades(numeroMensualidades);
        costo.setMontoMensualidad(montoMensualidad);
        em.getTransaction().commit();
    }
    
    public void saveImagen(Imagen imagen){
        em.getTransaction().begin();
        em.persist(imagen);
        em.getTransaction().commit();
    }
    
    public void deleteImagen(int id_imagen){
        Imagen imagen = em.find(Imagen.class, id_imagen);
        em.getTransaction().begin();
        em.remove(imagen);
        em.getTransaction().commit();
    }
}
