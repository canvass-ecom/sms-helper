/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnv.smshelper;

import com.cnv.smshelper.mvaayoo.MvaayooCredentials;
import com.cnv.smshelper.mvaayoo.MvaayooSmsProvider;

/**
 *
 * @author Owner
 */
public final class SmsHelperBuilder {

    private SmsHelperBuilder() {
    }

    public static SmsHelper buildProvider(Credentials credentials) throws Exception {
        if (credentials instanceof MvaayooCredentials) {
            return new MvaayooSmsProvider((MvaayooCredentials) credentials);
        }
        throw new Exception("No Implemention available for the given provider");
    }

    public static SmsSender buildSender(Credentials credentials) throws Exception {
        return buildProvider(credentials);
    }
}
