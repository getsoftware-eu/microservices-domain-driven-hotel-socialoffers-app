package eu.getsoftware.hotelico.hotel.chat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import eu.getsoftware.hotelico.hotel.chat.utils.HibernateUtils;

@Entity
public class ChatCustomerEntity
{
	@Id
	@SequenceGenerator(
			name = "custom_id_sequence",
			sequenceName = "custom_id_sequence"
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "custom_id_sequence"
	)
	private Long id;
	
	@Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
	private boolean active = true;
	
	@Column(name = "logged", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean logged = false;
	
	@Column(name = "showAvatar", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
	private boolean showAvatar = true;
	
	@Column(name = "guestAccount", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean guestAccount = false;
}
