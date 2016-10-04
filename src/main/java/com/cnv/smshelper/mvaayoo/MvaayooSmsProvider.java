/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnv.smshelper.mvaayoo;

import com.cnv.smshelper.SmsHelper;
import com.cnv.smshelper.enums.Providers;
import com.cnv.smshelper.SmsSender;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class MvaayooSmsProvider
        implements SmsSender, SmsHelper {

    static final String BASE_URL = "http://api.mVaayoo.com/mvaayooapi/";
    static final String SEND_URL = BASE_URL + "MessageCompose?";
    static final String STATUS_URL = "http://api.mvaayoo.com/apidlvr/APIDlvReport?";
    static final String CHARSET = "UTF-8";
    private static final int TIME_OUT = 45 * 1000; // default timeout for the request

    private MvaayooCredentials credentials;

    public MvaayooSmsProvider(MvaayooCredentials credentials) {
        this.credentials = credentials;
    }

    public MvaayooCredentials getCredentials() {
        return credentials;
    }

    private String buildSendQuery(String senderId, String mobile, String msg) throws UnsupportedEncodingException {
        String query = String.format("user=%s:%s&senderID=%s&receipientno=%s&dcs=0&msgtxt=%s",
                URLEncoder.encode(credentials.getUserName(), CHARSET),
                URLEncoder.encode(credentials.getPassword(), CHARSET),
                URLEncoder.encode(senderId, CHARSET),
                URLEncoder.encode(mobile, CHARSET),
                URLEncoder.encode(msg, CHARSET));
        return SEND_URL + query;
    }

    private String buildSendQuery(String mobile, String msg) throws UnsupportedEncodingException {
        return buildSendQuery("TEST SMS", mobile, msg);
    }

    /**
     *
     */
    private static String sendRequest(String url)
            throws Exception {
        String msg = "";
        System.out.println("SMS Update: Sending Request");
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(TIME_OUT);
            connection.setReadTimeout(TIME_OUT);
            connection.setRequestProperty("Accept-Charset", CHARSET);

            // This automatically fires the request and we can use it to determine
            // the response status
            InputStream response = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(response));
            String line;
            while ((line = br.readLine()) != null) {
                msg += line;
            }
            br.close();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception e) {
                }
            }
        }
        System.out.println("SMS Update: Request Completed");
        //System.out.println(msg);
        return msg;
    }

    @Override
    public Providers getProvider() {
        return Providers.Mvaayoo;
    }

    @Override
    public String send(String receiver, String message) {
        String messageId = null;
        try {
            // if the mobile is valid then only sent the message
            String query = buildSendQuery(receiver, message);
            messageId = sendRequest(query);
            messageId = messageId.substring(messageId.indexOf(",") + 1);
        } catch (Exception ex) {
            Logger.getLogger(MvaayooSmsProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messageId;
    }
}
