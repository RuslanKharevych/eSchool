package ifntuog.eschool.service;


import ifntuog.eschool.dto.HomeworkDTO;
import ifntuog.eschool.dto.HomeworkFileDTO;
import ifntuog.eschool.dto.JournalDTO;
import ifntuog.eschool.dto.JournalMarkDTO;

import java.util.List;

public interface JournalService{
    List<JournalDTO> getJournalsByTeacher(int idTeacher);
    List<JournalDTO> getActiveJournalsByTeacher(int idTeacher);
    List<JournalDTO> getJournals();
    List<JournalDTO> getJournalsByClass(int idClass);
    List<JournalMarkDTO> getJournal(int idSubject, int idClass);
    List<HomeworkDTO> getHomework(int idSubject, int idClass);
    HomeworkFileDTO getFile(int idSubject);
    void saveHomework(HomeworkFileDTO fileDTO);
}
