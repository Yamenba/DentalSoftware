/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio.dental;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.Message;
import datos.Ajustes;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Ricardo
 */
public class RecordatorioMail {
    
    /** Application name. */
    private static final String APPLICATION_NAME =
        "Dental Software";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/gmail-java-quickstart.json");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/gmail-java-quickstart.json
     */
    private static final List<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/calendar","https://www.googleapis.com/auth/userinfo.email ");

    

    public RecordatorioMail() throws IOException, Exception{
       
    }
    
     /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException, Exception {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
        // Load client secrets.
        InputStream in =
            RecordatorioMail.class.getResourceAsStream("client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }
    
    public static Credential renovar() throws Exception{
        Credential vieja = authorize();
        borrarArchivo();
        sendGET("https://accounts.google.com/o/oauth2/revoke?token="+vieja.getAccessToken());
        return authorize();
    }
    
    private static void sendGET(String url) throws ProtocolException, MalformedURLException, IOException{
         URL obj = new URL(url);
         HttpURLConnection con = (HttpURLConnection) obj.openConnection();
         // optional default is GET
         con.setRequestMethod("GET");
         int responseCode = con.getResponseCode();
          System.out.println("Response Code : " + responseCode);
     }
    
    private static void borrarArchivo(){
        String path = System.getProperty("user.home")+ "/.credentials/gmail-java-quickstart.json/StoredCredential";
        try{
            Files.delete(Paths.get(path));
        }catch(Exception e){
            e.printStackTrace();
        }

    }
   

    /**
     * Build and return an authorized Gmail client service.
     * @return an authorized Gmail client service
     * @throws IOException
     */
    
    
    public static com.google.api.services.calendar.Calendar
        getCalendarService() throws IOException, Exception {
        Credential credential = authorize();
        return new com.google.api.services.calendar.Calendar.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
        
        public String agregarRecordatorio(String correo, String resumen, String descripcion, DateTime hora_inicio, DateTime hora_fin, String timeZone) throws Exception{
            Event event = new Event()
                .setSummary(resumen)
                .setDescription(descripcion);

            
            EventDateTime start = new EventDateTime()
                .setDateTime(hora_inicio)
                .setTimeZone(timeZone);
            event.setStart(start);

            
            EventDateTime end = new EventDateTime()
                .setDateTime(hora_fin)
                .setTimeZone(timeZone);
            event.setEnd(end);

    
            EventAttendee[] attendees = new EventAttendee[] {
                new EventAttendee().setEmail(correo),
            };
            event.setAttendees(Arrays.asList(attendees));

            EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(72 * 60),
                new EventReminder().setMethod("email").setMinutes(5 * 60)
            };
            Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
            event.setReminders(reminders);
            

            String calendarId = "primary";
            event = getCalendarService().events().insert(calendarId, event).setSendNotifications(true).execute();
            System.out.printf("Event created: %s\n", event.getId());
            return event.getId();
        }
        
        public void updateRecordatorio(String id_calendar, DateTime hora_inicio, DateTime hora_fin,String correo) throws Exception{
            
            // Retrieve the event from the API
            Event event = getCalendarService().events().get("primary", id_calendar).execute();
            

            // Make a change
            TimeZone tz = Calendar.getInstance().getTimeZone();
            EventDateTime start = new EventDateTime()
                .setDateTime(hora_inicio).setTimeZone(tz.getID());
            event.setStart(start);
            EventDateTime end = new EventDateTime()
                .setDateTime(hora_fin).setTimeZone(tz.getID());
            event.setEnd(end);
            EventAttendee[] attendees = new EventAttendee[] {
                new EventAttendee().setEmail(correo),
            };
            event.setAttendees(Arrays.asList(attendees));

            // Update the event
            Event updatedEvent = new Event();
            PacienteQuery query = new PacienteQuery();
            List<Ajustes> lista_ajustes = new ArrayList<>();
            lista_ajustes = query.ajustes();
            Ajustes ajustes = lista_ajustes.get(0);
            if(ajustes.getRecordatorioActivado() && event.getId() != null)
                updatedEvent = getCalendarService().events().update("primary", event.getId(), event).setSendNotifications(true).execute();
            System.out.printf("Event updated: %s\n", updatedEvent.getId());
        }
        
        public void deleteRecordatorio(String id_calendar) throws Exception{
            PacienteQuery query = new PacienteQuery();
            List<Ajustes> lista_ajustes = new ArrayList<>();
            lista_ajustes = query.ajustes();
            Ajustes ajustes = lista_ajustes.get(0);
            if(ajustes.getRecordatorioActivado() && getCalendarService().events().get("primary", id_calendar).execute().getId() != null){
                    getCalendarService().events().delete("primary", id_calendar).setSendNotifications(true).execute();          
            }
        }
}
