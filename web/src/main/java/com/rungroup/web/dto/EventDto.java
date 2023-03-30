package com.rungroup.web.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
	
	private Long id;
	
	private String name;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	private String type;
	
	private String photoUrl;
	
	private LocalDateTime createdOn;
	
	private LocalDateTime updatedOn;
	
}
