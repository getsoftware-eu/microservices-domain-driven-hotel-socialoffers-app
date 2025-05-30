package eu.getsoftware.hotelico.clients.common.dto;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;

import java.io.Serializable;

// eu: Serializable — это безопасное и малозатратное расширение интерфейса, которое делает DTO более гибкими, особенно в больших Spring-приложениях. Удалять его имеет смысл только если:
//
//вы уверены, что DTO не будут сериализоваться,
//
//и проект не использует механизмы, которым это может понадобиться (например, сессии, distributed caches и т.д.).

public interface BasicDTO<D extends EntityIdentifier> extends Serializable {

	D getDomainEntityId();
	
	boolean isActive();
	
}