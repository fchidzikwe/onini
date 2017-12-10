package com.fortune.repository;

import com.fortune.model.Attendance;
import com.fortune.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fchidzikwe
 */

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    Attendance findById(Long id);

    List<Attendance> findAllByACase(Case aCase);

}
