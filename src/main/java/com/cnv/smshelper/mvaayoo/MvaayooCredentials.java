/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnv.smshelper.mvaayoo;

import com.cnv.smshelper.Credentials;

/**
 *
 * @author Owner
 */
public class MvaayooCredentials implements Credentials {

    private String userName;
    private String password;
    private String sender = "TEST SMS"; // setting the default sender

    public MvaayooCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public MvaayooCredentials(String userName, String password, String sender) {
        this.userName = userName;
        this.password = password;
        this.sender = sender;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getSender() {
        return sender;
    }

}
