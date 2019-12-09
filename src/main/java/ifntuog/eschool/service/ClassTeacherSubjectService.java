package ifntuog.eschool.service;

import ifntuog.eschool.dto.TeacherJournalDTO;
import ifntuog.eschool.model.ClassTeacherSubjectLink;

/**
 * This is an interface that the {@link ClassTeacherSubjectServiceImpl} implements
 *
 * @author Mariana Vorotniak
 */
public interface ClassTeacherSubjectService {

    ClassTeacherSubjectLink saveClassTeacherSubject(TeacherJournalDTO teacherJournalDTO, boolean isActive);
}
