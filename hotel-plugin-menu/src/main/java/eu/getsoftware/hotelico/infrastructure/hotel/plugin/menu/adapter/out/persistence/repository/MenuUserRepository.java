package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.repository;

import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.model.MenuUserMappedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuUserRepository extends JpaRepository<MenuUserMappedEntity, Long>
{
	
	MenuUserMappedEntity findByUserId(long userId);
}
