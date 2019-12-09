package ifntuog.eschool.service.base;

import java.time.LocalDate;
import java.util.List;

import ifntuog.eschool.dto.DiaryEntryDTO;

public interface DiaryServiceBase {
    List<DiaryEntryDTO> getDiary(LocalDate weekStart, int studentId);
}
