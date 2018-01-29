package com.fortune.repository;

import com.fortune.model.Attendance;
import com.fortune.model.Case;
import com.fortune.model.Expense;
import com.fortune.model.Requisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author fchidzikwe
 */

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    Attendance findById(Long id);

    List<Attendance> findAllByACase(Case aCase);

    @Query("select r from Requisition r where r.expense.attendance=:attendance")
    List<Requisition> getAllAttendanceExpenses(@Param("attendance") Attendance attendance);



}
