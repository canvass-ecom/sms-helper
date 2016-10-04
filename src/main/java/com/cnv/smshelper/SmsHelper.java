/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnv.smshelper;

import com.cnv.smshelper.enums.Providers;

/**
 *
 * @author Owner
 */
public interface SmsHelper extends SmsSender {

    Providers getProvider();
}
