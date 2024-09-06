package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.repository;

import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.model.MenuUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuUserRepository extends JpaRepository<MenuUserEntity, Long>
{
	
	MenuUserEntity findByUserId(long userId);
}
