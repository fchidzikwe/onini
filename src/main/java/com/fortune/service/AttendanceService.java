package com.fortune.service;

import com.fortune.model.Attendance;
import com.fortune.model.Case;

import java.util.List;

/**
 * @author fchidzikwe
 */
public interface AttendanceService {

    Attendance findById(Long id);

    List<Attendance> findAllByACase(Case aCase);

    void save(Attendance attendance);

}
