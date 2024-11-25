package javemarket.interfaces.controladores;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class CorreoService implements javemarket.aplicacion.servicios.CorreoService {
    private final String llaveSendGrid = "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"; // Variable de entorno para la API Key de SendGrid
    private final String remitente = "simondiazmonroy@gmail.com";

    @Override
    public void enviarCorreo(String destinatario, String asunto, String contenido) {
        // Configurar remitente, destinatario y contenido del correo
        Email from = new Email(remitente);  // Dirección de correo remitente
        Email to = new Email(destinatario); // Dirección de correo destinatario
        Content emailContent = new Content("text/plain", contenido); // Contenido del correo
        Mail mail = new Mail(from, asunto, to, emailContent);

        // Configurar SendGrid API
        SendGrid sg = new SendGrid(llaveSendGrid);
        Request request = new Request();

        try {
            // Configurar la solicitud para enviar el correo
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            // Enviar el correo y obtener la respuesta
            Response response = sg.api(request);
            System.out.println("Estado: " + response.getStatusCode());
            System.out.println("Cuerpo de respuesta: " + response.getBody());
            System.out.println("Encabezados: " + response.getHeaders());

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al enviar el correo", ex);
        }
    }
}
