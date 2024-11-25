package javemarket.interfaces.controladores;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class CorreoService implements javemarket.aplicacion.servicios.CorreoService {
    private final String remitente = System.getenv("MAIL_SENDER"); // Variable de entorno para el remitente
    private final String smtpHost = System.getenv("SMTP_HOST"); // Variable de entorno para el servidor SMTP
    private final String smtpPort = System.getenv("SMTP_PORT"); // Variable de entorno para el puerto SMTP

    @Override
    public void enviarCorreo(String destinatario, String asunto, String contenido) throws MessagingException {
        // Configurar propiedades del servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "false"); // No requiere autenticación
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost); // Servidor configurado desde variable de entorno
        props.put("mail.smtp.port", smtpPort); // Puerto configurado desde variable de entorno

        // Crear sesión sin autenticación
        Session session = Session.getInstance(props);

        try {
            // Crear mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente)); // Dirección del remitente
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario)); // Destinatario
            message.setSubject(asunto); // Asunto del correo
            message.setText(contenido); // Mensaje

            // Enviar correo
            Transport.send(message);
            System.out.println("Correo enviado exitosamente a " + destinatario);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar el correo", e);
        }
    }
}
