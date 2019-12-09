package ifntuog.eschool.service.base;

public interface CredentialsMailingServiceBase {
    void sendStudentsCredentials(Integer classId);
    void sendTeachersCredentials();
}
