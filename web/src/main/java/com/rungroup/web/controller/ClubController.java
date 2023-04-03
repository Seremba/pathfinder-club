package com.rungroup.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.service.ClubService;



@Controller

public class ClubController {
    private ClubService clubService;

   @Autowired 
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

	@GetMapping("/clubs/{clubId}/delete")
	public String deleteClub(@PathVariable("clubId") Long clubId) {
		clubService.delete(clubId);
		return "redirect:/clubs";
	}
	
	@GetMapping("/clubs/search")
	public String searchClub(@RequestParam(value = "query") String query, Model model) {
		List<ClubDto> clubs = clubService.searchClubs(query);
		model.addAttribute("clubs", clubs);
		
		return "clubs-list";
	}
	
	@GetMapping("/clubs/{clubId}")
	public String clubDetail(@PathVariable("clubId") Long clubId, Model model) {
		ClubDto clubDto = clubService.findClubById(clubId);
		model.addAttribute("club", clubDto);
		
		return "clubs-detail";
	}
	
    @GetMapping("/clubs/new")
    public String createClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @PostMapping("/clubs/new")
	public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("club", clubDto);
			return "clubs-create";
		}
        this.clubService.saveClub(clubDto);
        
        return "redirect:/clubs";
    }
    
    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId, Model model) {
        ClubDto club = this.clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
	public String updateClub(@PathVariable("clubId") Long clubId, @Valid @ModelAttribute("club") ClubDto club,
	        BindingResult result, Model model) {

        if (result.hasErrors()) {
			model.addAttribute("club", club);
            return "clubs-edit";
        }

        club.setId(clubId);
        this.clubService.updateClub(club);
        return "redirect:/clubs";
    }
}
