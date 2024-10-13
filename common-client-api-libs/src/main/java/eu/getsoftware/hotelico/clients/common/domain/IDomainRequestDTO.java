package eu.getsoftware.hotelico.clients.common.domain;

/**
 * Interface for all boundary user-dto
 * 
 * EU: no getMethods, because RECORD implements it!!!
 */
public interface IDomainRequestDTO
{
    long requesterId();
    String name();
}
