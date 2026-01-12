package com.example.userdemo.controller;

import com.example.userdemo.dto.AttendanceRequest;
import com.example.userdemo.entity.Attendance;
import com.example.userdemo.entity.User;
import com.example.userdemo.exception.AttendanceAlreadyMarkedException;
import com.example.userdemo.exception.UserNotFoundException;
import com.example.userdemo.repository.AttendanceRepository;
import com.example.userdemo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController
{
        private final AttendanceRepository attendanceRepository;
        private final UserRepository userRepository;

        public AttendanceController(AttendanceRepository attendanceRepository,
                                    UserRepository userRepository)
        {
            this.attendanceRepository = attendanceRepository;
            this.userRepository = userRepository;
        }

    // ✅ Mark attendance (today)
    @PostMapping("/mark")
    public Attendance markAttendance(@RequestBody AttendanceRequest request)
    {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id " + request.getUserId()));


        // ✅ PREVENT DUPLICATE ENTRY
        if (attendanceRepository.existsByUserAndAttendanceDate(user, LocalDate.now())) {
            throw new AttendanceAlreadyMarkedException("Attendance already marked for today");
        }


        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setStatus(request.getStatus());
        attendance.setAttendanceDate(LocalDate.now());

        return attendanceRepository.save(attendance);
    }

    // ✅ Get total attendance
    @GetMapping("/{userId}/total")
    public List<Attendance> getTotalAttendance(@PathVariable Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id " + userId)
                );

        return attendanceRepository.findByUser(user);
    }

    // ✅ Attendance between dates
    @GetMapping("/{userId}/range")
    public List<Attendance> getAttendanceBetweenDates(
            @PathVariable Integer userId,
            @RequestParam String fromDate,
            @RequestParam String toDate) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id " + userId)
                );

        return attendanceRepository.findByUserAndAttendanceDateBetween(
                user,
                LocalDate.parse(fromDate),
                LocalDate.parse(toDate)
        );
    }


}
