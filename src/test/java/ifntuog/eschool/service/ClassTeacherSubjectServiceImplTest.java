package ifntuog.eschool.service;

import ifntuog.eschool.dto.TeacherJournalDTO;
import ifntuog.eschool.model.ClassTeacherSubjectLink;
import ifntuog.eschool.model.Clazz;
import ifntuog.eschool.model.Subject;
import ifntuog.eschool.model.Teacher;
import ifntuog.eschool.repository.ClassRepository;
import ifntuog.eschool.repository.ClassTeacherSubjectLinkRepository;
import ifntuog.eschool.repository.SubjectRepository;
import ifntuog.eschool.repository.TeacherRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

/**
 * This is a test class for {@link ClassTeacherSubjectServiceImpl}
 * {@link Mockito} mock framework is used in conjunction with {@link org.junit.runners.JUnit4}
 *
 *  @author Mariana Vorotniak
 */
@RunWith(MockitoJUnitRunner.class)
public class ClassTeacherSubjectServiceImplTest {

    @Mock
    private ClassTeacherSubjectLinkRepository classTeacherSubjectRepository;
    @Mock
    private ClassRepository classRepository;
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private SubjectRepository subjectRepository;
    @InjectMocks
    private ClassTeacherSubjectServiceImpl classTeacherSubjectService;

    private static TeacherJournalDTO teacherJournalDTO;

    @Before
    public void init()
    {
        teacherJournalDTO = new TeacherJournalDTO(1, 1, 1);
    }

    @Test
    public void saveClassTeacherSubject()
    {
        Optional<Object> clazz = Optional.of(new Clazz(1, "8-А", "",2017, false));
        Optional<Object> subject = Optional.of(new Subject(1, "Історія України", "Гуманітарний навчальний предмет. Починає вивчатись із 5-го класу"));
        Mockito.doReturn(clazz).when(classRepository).findById(anyInt());
        Mockito.doReturn(Optional.of(new Teacher())).when(teacherRepository).findById(anyInt());
        Mockito.doReturn(subject).when(subjectRepository).findById(anyInt());
        ClassTeacherSubjectLink classTeacherSubjectLink = new ClassTeacherSubjectLink();
        classTeacherSubjectLink.setClazz(new Clazz(1, "8-А", "",2017, false));
        classTeacherSubjectLink.setClazz_id(1);
        classTeacherSubjectLink.setTeacher(new Teacher());
        classTeacherSubjectLink.setTeacher_id(1);
        classTeacherSubjectLink.setSubject(new Subject(1, "Історія України", "Гуманітарний навчальний предмет. Починає вивчатись із 5-го класу"));
        classTeacherSubjectLink.setSubject_id(1);
        classTeacherSubjectLink.setActive(true);
        assertEquals("Saving teacher-journal connection", classTeacherSubjectLink, classTeacherSubjectService.saveClassTeacherSubject(teacherJournalDTO, true));
    }

    @After
    public void destroy()
    {
        teacherJournalDTO = null;
    }
}
