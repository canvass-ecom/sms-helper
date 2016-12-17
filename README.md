# sms-helper

SMS helper api to send sms using different providers

## Currently supported providers
 Mvaayoo
 
## Example to build the sms sender
```Java
SmsSender smsSender = SmsHelperBuilder.buildSender(new MvaayooCredentials("xxxxxx", "xxxxxx"));
smsSender.send("1234567890", "Hello this is testing");
