package eu.getsoftware.hotelico.clients.api.application.dto.entity.menu;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.MenuDomainEntityId;
import eu.getsoftware.hotelico.clients.common.dto.BasicDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.With;

@With
@Getter
@RequiredArgsConstructor
public class MenuDTO implements BasicDTO<MenuDomainEntityId>
{
	private final MenuDomainEntityId domainEntityId;
	private final long creationTime = System.currentTimeMillis();
	private final boolean active = true;
}
