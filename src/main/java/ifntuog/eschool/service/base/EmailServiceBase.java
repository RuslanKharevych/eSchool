package ifntuog.eschool.service.base;

public interface EmailServiceBase {
    public void sendSimpleMessage(String to, String subject, String text);
    public void sendHtmlMessage(String to, String subject, String text);
}