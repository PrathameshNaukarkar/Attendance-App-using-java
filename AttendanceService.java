package com.example.attendance.service;

import com.example.attendance.model.Attendance;
import com.example.attendance.model.User;
import com.example.attendance.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance markAttendance(User user, String selfie) {
        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setDate(new Date());
        attendance.setSelfie(selfie);
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceHistory(Long userId) {
        return attendanceRepository.findByUserId(userId);
    }
}