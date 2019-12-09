package ifntuog.eschool.service.base;

import java.time.LocalDate;
import java.util.List;

import ifntuog.eschool.dto.MarkDTO;
import ifntuog.eschool.dto.MarkDataPointDTO;
import ifntuog.eschool.dto.SubjectAvgMarkDTO;

public interface MarkServiceBase {
    List<MarkDataPointDTO> getFilteredByParams(Integer subjectId, Integer classId, Integer studentId, LocalDate startDate, LocalDate endDate);
    MarkDTO saveMark(MarkDTO dto);
    void updateType(int idLesson, String markType);
    List<SubjectAvgMarkDTO> getAverageMarks(Integer studentId, LocalDate start, LocalDate end);
}
