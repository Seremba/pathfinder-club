package com.rungroup.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.repository.ClubRepository;

@Service
public class ClubServiceImpl implements ClubService {
    private ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = this.clubRepository.findAll();

        return clubs.stream()
            .map(club -> mapToClubDto(club))
            .collect(Collectors.toList());
    }

    private ClubDto mapToClubDto(Club club) {
        return ClubDto.builder()
            .id(club.getId())
            .title(club.getTitle())
            .photoUrl(club.getPhotoUrl())
            .content(club.getContent())
            .createdOn(club.getCreatedOn())
            .updatedOn(club.getUpdatedOn())
            .build();
        
    }

}

