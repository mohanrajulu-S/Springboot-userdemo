package com.example.userdemo.repository;

import com.example.userdemo.entity.Attendance;
import com.example.userdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer>
{
    // total attendance by user
    List<Attendance> findByUser(User user);


    // ✅ Prevent duplicate attendance for same day
    boolean existsByUserAndAttendanceDate(User user, LocalDate attendanceDate);

    // attendance between dates
    List<Attendance> findByUserAndAttendanceDateBetween(
            User user,
            LocalDate fromDate,
            LocalDate toDate
    );


}
