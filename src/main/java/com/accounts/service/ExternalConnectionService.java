package com.accounts.service;

import com.accounts.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

/**
 * Created by alex on 3/10/18.
 */
@Service
public class ExternalConnectionService {
    @Value("${external.connection.service.url}")
    private String url;
    @Value("${external.connection.service.port}")
    private int port;

    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    @PostConstruct
    private void init() {
        try {
            SSLSocketFactory sslsocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslsocketFactory.createSocket(url, port);

            OutputStream outputStream = sslSocket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            InputStream inputStream = sslSocket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAccountDetail(Account account) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String sendPacket = objectMapper.writeValueAsString(new RequestModel(account.getId())) + "\r\n";

            bufferedWriter.write(sendPacket);
            bufferedWriter.flush();
            String string = bufferedReader.readLine();

            return objectMapper.readValue(string, ResponseModel.class).getAccountDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static class RequestModel {
        private long id;

        RequestModel(long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }

    private static class ResponseModel {
        private long id;
        private String accountDetails;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        String getAccountDetails() {
            return accountDetails;
        }

        public void setAccountDetails(String accountDetails) {
            this.accountDetails = accountDetails;
        }
    }
}
