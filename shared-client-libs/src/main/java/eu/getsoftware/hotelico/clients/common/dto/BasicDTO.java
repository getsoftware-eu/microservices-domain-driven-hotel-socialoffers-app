package eu.getsoftware.hotelico.clients.common.dto;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;

import java.io.Serializable;

public interface BasicDTO<D extends EntityIdentifier> extends Serializable {

	D getDomainEntityId();
	
	Boolean isActive();
	
}