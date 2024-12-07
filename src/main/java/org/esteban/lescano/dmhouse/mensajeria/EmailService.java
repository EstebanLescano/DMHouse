package org.esteban.lescano.dmhouse.mensajeria;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {
    public void sendWelcomeEmail(String email, String name) {
        String subject = "Bienvenido a DM House";
        String message = "Hola " + name + ",\n\nBienvenido a DM House. Esperamos que disfrutes de nuestra plataforma.\n\nSaludos,\nEl equipo de DM House";
        sendEmail(email, subject, message);
    }

    private void sendEmail(String email, String subject, String message) {

    }
}