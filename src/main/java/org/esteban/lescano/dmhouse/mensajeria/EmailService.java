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

    enum TipoEnvio {
        SMTP, API
    }

    @Value("${emailSettings.apiKey}")
    private String apiKey;
    @Value("${emailSettings.apiBaseUri}")
    public String apiBaseUri;
    @Value("${emailSettings.requestUri}")
    public String requestUri;
    @Value("${emailSettings.from}")
    public String from;
    @Value("${emailSettings.domain}")
    public String domain;
    @Value("${emailSettings.enabled}")
    public boolean enabled;

    public void sendEmail(String email, String subject, String message) {
        if (!this.enabled) {
            return;
        }

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Construir la URL de la API
            String url = String.format("https://%s/v3/%s/messages", this.apiBaseUri, this.domain);
            HttpPost post = new HttpPost(url);

            // Autenticación básica
            String auth = "api:" + this.apiKey;
            String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
            post.setHeader("Authorization", "Basic " + encodedAuth);

            // Construir los parámetros del formulario de email
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("from", this.from));
            params.add(new BasicNameValuePair("to", email));
            params.add(new BasicNameValuePair("subject", subject));
            params.add(new BasicNameValuePair("text", message));

            post.setEntity(new UrlEncodedFormEntity(params));

            // Enviar la solicitud y manejar la respuesta de email
            try (CloseableHttpResponse response = client.execute(post)) {
                int statusCode = response.getCode();
                if (statusCode == 200) {
                    System.out.println("Email enviado exitosamente");
                } else {
                    System.err.println("Error al enviar email: " + statusCode);
                    System.err.println("Mensaje: " + response.getEntity().getContent());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al enviar email: " + e.getMessage());
        }
    }
}