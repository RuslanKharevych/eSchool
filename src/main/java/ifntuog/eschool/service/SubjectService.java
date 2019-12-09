package ifntuog.eschool.service;

import java.util.List;

import ifntuog.eschool.dto.SubjectDTO;

public interface SubjectService {
	List<SubjectDTO> getAllSubjects();

	List<SubjectDTO> getSubjectsByTeacher(int idTeacher);

	List<SubjectDTO> getSubjectsByClass(Integer classId);

	SubjectDTO getSubjectById(int id);

	SubjectDTO addSubject(SubjectDTO subjectDTO);

	SubjectDTO editSubject(int id, SubjectDTO subjectDTO);
}
