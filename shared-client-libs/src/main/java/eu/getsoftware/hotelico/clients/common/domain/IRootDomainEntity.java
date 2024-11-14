package eu.getsoftware.hotelico.clients.common.domain;

import java.util.Map;

public interface IRootDomainEntity {

    /**
     * One setter of internal state!
     *     //this.setFirstName(name);
     *     //this.setTime(LocalDateTime.now());
     */
    void setInitValues(Map<String, String> fieldToValues);

    <I extends EntityIdentifier> I getDomainEntityId();
}
