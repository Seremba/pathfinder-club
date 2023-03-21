package com.rungroup.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.service.ClubService;

@Controller

public class ClubController {
    private ClubService clubService;

    public ClubController(ClubService clubService) {
        super();
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDto> clubs = this.clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }
}
