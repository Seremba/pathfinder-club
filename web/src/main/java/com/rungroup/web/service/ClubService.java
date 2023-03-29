package com.rungroup.web.service;

import java.util.List;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;

public interface ClubService {
    List<ClubDto> findAllClubs();

    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(long clubId);

    void updateClub(ClubDto club);
	
	void delete(Long clubId);
}
