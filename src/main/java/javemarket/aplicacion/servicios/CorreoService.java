package javemarket.aplicacion.servicios;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface CorreoService {
    void enviarCorreo(String destinatario, String asunto, String contenido) throws MessagingException;
}
