/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnv.smshelper;

/**
 *
 * @author Owner
 */
public interface SmsSender {

    String send(String receiver, String message);
}
