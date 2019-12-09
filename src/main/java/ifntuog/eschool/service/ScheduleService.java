package ifntuog.eschool.service;

import ifntuog.eschool.dto.ScheduleDTO;

/**
 * This is an interface that the {@link ScheduleServiceImpl} implements
 *
 * @author Mariana Vorotniak
 */
public interface ScheduleService {

    ScheduleDTO getScheduleByClassId(int classId);
    void saveSchedule(ScheduleDTO scheduleDTO);
}
