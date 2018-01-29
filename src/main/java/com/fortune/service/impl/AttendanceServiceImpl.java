package com.fortune.service.impl;

import com.fortune.model.Attendance;
import com.fortune.model.Case;
import com.fortune.model.Expense;
import com.fortune.model.Requisition;
import com.fortune.repository.AttendanceRepository;
import com.fortune.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fchidzikwe
 */
@Service
public class AttendanceServiceImpl implements AttendanceService{

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public Attendance findById(Long id) {
        return attendanceRepository.findById(id);
    }

    @Override
    public List<Attendance> findAllByACase(Case aCase) {
        return attendanceRepository.findAllByACase(aCase);
    }

    @Override
    public void save(Attendance attendance) {
        attendanceRepository.save(attendance);
    }

    @Override
    public List<Requisition> getAllAttendanceExpenses(Attendance attendance) {
        return attendanceRepository.getAllAttendanceExpenses(attendance);
    }
}
