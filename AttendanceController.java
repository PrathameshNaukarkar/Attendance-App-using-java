package com.example.attendance.controller;

import com.example.attendance.model.Attendance;
import com.example.attendance.model.User;
import com.example.attendance.service.AttendanceService;
import com.example.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserService userService;

    @GetMapping("/attendance")
    public String showAttendancePage(Model model, Principal principal) {
        User user = userService.getUserById(Long.parseLong(principal.getName()));
        model.addAttribute("user", user);
        return "attendance";
    }

    @PostMapping("/attendance/mark")
    public String markAttendance(@RequestParam String selfie, Principal principal) {
        User user = userService.getUserById(Long.parseLong(principal.getName()));
        attendanceService.markAttendance(user, selfie);
        return "redirect:/attendance";
    }

    @GetMapping("/attendance/history")
    public String viewAttendanceHistory(Model model, Principal principal) {
        User user = userService.getUserById(Long.parseLong(principal.getName()));
        List<Attendance> history = attendanceService.getAttendanceHistory(user.getId());
        model.addAttribute("history", history);
        return "attendance-history";
    }
}