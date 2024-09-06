package eu.getsoftware.hotelico.customer.adapter.out.persistence.repository

import eu.getsoftware.hotelico.customer.adapter.out.persistence.model.UserRole
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRolesRepository : CrudRepository<UserRole, Long> {

    companion object {

        const val QUERY_ROLE_STRING_BY_USERNAME = " SELECT role.role " +
                " FROM UserRole role, User user " +
                " WHERE user.userName = ?1 " +
                " AND role.user_id = user.id " +
                " AND user.enabled = 1 "

        const val QUERY_ROLE_BY_USERNAME = " SELECT role " +
                " FROM UserRole role, User user " +
                " WHERE user.userName = ?1 " +
                " AND role.user_id = user.id " +
                " AND user.enabled = 1 " 
    }
    
	@Query(QUERY_ROLE_STRING_BY_USERNAME)
    fun getStringRoleByUserName(username: String) : List<String>    
    
    
	@Query(QUERY_ROLE_BY_USERNAME)
    fun getRoleByUserName(username: String) : List<UserRole>
	
}