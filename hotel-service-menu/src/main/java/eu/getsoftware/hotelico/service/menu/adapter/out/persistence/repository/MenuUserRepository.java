package eu.getsoftware.hotelico.service.menu.adapter.out.persistence.repository;

import eu.getsoftware.hotelico.service.menu.adapter.out.persistence.model.MenuUserMappedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuUserRepository extends JpaRepository<MenuUserMappedEntity, Long>
{
	
	MenuUserMappedEntity findByUserId(long userId);
}
