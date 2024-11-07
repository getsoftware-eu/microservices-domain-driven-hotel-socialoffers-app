package eu.getsoftware.hotelico.clients.common.domain;

import java.util.Map;

public interface IDomainEntity {

    void setInitValues(Map<String, String> fieldToValues);

//    boolean enabled();
    
    /**
     *     //this.setFirstName(name);
     *     //this.setTime(LocalDateTime.now());
     *     
     * @return
     * @param <I>
     */
    <I extends EntityIdentifier> I getEntityId();

}
