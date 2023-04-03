package com.rungroup.web.service;

import static com.rungroup.web.mapper.EventMapper.mapToEvent;
import static com.rungroup.web.mapper.EventMapper.mapToEventDto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rungroup.web.dto.EventDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.models.Event;
import com.rungroup.web.repository.ClubRepository;
import com.rungroup.web.repository.EventRepository;
@Service
public class EventServiceImpl implements EventService {
	
	private EventRepository eventRepository;
	
	private ClubRepository clubRepository;
	
	

	@Autowired
	public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
		this.eventRepository = eventRepository;
		this.clubRepository = clubRepository;
		
	}
	
	
	@Override
	public void createEvent(Long clubId, EventDto eventDto) {
		Club club = clubRepository.findById(clubId).get();
		
		Event event = mapToEvent(eventDto);
		
		event.setClub(club);
		
		eventRepository.save(event);
		
	}
	
	@Override
	public List<EventDto> findAllEvents() {
		List<Event> events = eventRepository.findAll();
		return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
	}
	
	@Override
	public EventDto findEventById(Long eventId) {
		Event event = eventRepository.findById(eventId).get();
		return mapToEventDto(event);
	}
	
	@Override
	public void updateClub(EventDto eventDto) {
		Event event = mapToEvent(eventDto);
		eventRepository.save(event);
		
	}
	
	
}
