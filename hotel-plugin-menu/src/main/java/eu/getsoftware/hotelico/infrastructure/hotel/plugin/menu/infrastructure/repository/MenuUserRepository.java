package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.domain.model.MenuUserEntity;

public interface MenuUserRepository extends JpaRepository<MenuUserEntity, Long>
{
	
	MenuUserEntity findByUserId(long userId);
}
