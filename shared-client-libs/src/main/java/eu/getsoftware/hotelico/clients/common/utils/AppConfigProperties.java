package eu.getsoftware.hotelico.clients.common.utils;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;

import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Eugen on 14.07.2015.
 */
//@ConfigurationProperties(prefix = "buckpal")
public class AppConfigProperties
{
	public static final String SESSION_CUSTOMER_ID = "sessionCustomerId";
	public static final String SESSION_CUSTOMER = "sessionCustomer";
	
	public static final String PARAM_DEFAULT_REALM = "EugenRealm";
	public static final String  FILTER_PATH_MULTITENANT = "auth";
	public static final String  PREFIX_PATH_MULTITENANT = "/$FILTER_PATH_MULTITENANT/{clientRealm}";
		
	public static final boolean RUN_TEST = true;
	public static final boolean RUN_WITH_JETTY = false;

//	public static final boolean USE_LOCAL_DB = false;
	public static final boolean USE_LOCAL_HOST = false;
	public static final boolean USE_ADMIN_MODE = false;
	public static final boolean USE_SSL = true;

	public static String HOST = USE_LOCAL_HOST? (USE_SSL? "https://192.168.2.101/" : "http://192.168.2.100:8080/") : (USE_SSL? "https://hotelico.de/" : "http://hotelico.de/");
	public static int checkinDistanceKm = 5;

	{
		HOST = RUN_WITH_JETTY? USE_LOCAL_HOST ? "http://localhost:8082/" : "http://hotelico.de:8082/" : HOST;
//		HOST = RUN_TEST? HOST + "test32/"  : HOST;
	} 
	
	public static final String HOST_SUFFIX = RUN_TEST? "test32/" : "";
	public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

	public static final boolean SET_AWAY_GUEST_INACTIVE = false;
	public static final boolean SET_AWAY_GUEST_CHECKOUT = false;
	public static final boolean BLOCK_GUESTS_IN_WALL = false;
	
	public static final boolean NO_CHECKOUT_FOR_DEMOHOTEL = true;
	public static final boolean NO_WALL_EXPIRES_FOR_DEMOHOTEL = true;
	
	public static final int AWAY_GUEST_DAYS_TIMEOUT = 7;

	
	public static final boolean SHOW_ONLY_FULL_CHECKIN_USERS = false;
//	public static final String EVENT_CHECKIN = "EVENT_CHECKIN";
//	public static final String EVENT_REGISTER = "EVENT_REGISTER";
//	public static final String EVENT_CHECKOUT = "EVENT_CHECKOUT";
//	public static final String EVENT_LOGIN = "EVENT_LOGIN";
//	public static final String EVENT_WALL_MESSAGE = "EVENT_WALL_MESSAGE";
//	public static final String EVENT_CHAT_MESSAGE = "EVENT_CHAT_MESSAGE";
	public static final boolean ALLOW_INIT_VIRTUAL_HOTEL = false;
	public static final boolean HIDE_DEMO_HOTEL_FROM_HOTEL_LIST = false;
	
	public static final boolean CHAT_DELIEVER_INDIVIDUAL = true;
	public static final boolean USE_UNREAD_ACTIVITY_COUNT = false;
	public static final boolean USE_LAST_SEEN_CUSTOMERS = false;
	public static final boolean CHAT_NOTIFICATE_DELIEVERY_CHATLIST = true;
	public static final boolean CHAT_NOTIFICATE_DELIEVERY_INDIVIDUAL_CHAT = true;
	public static final String HOTEL_DEMO_CODE = "demo";

	public static final String PREVIEW_HOTEL_NOT_AVAILABLE_URL = "angulr/img/build/logo/hotelPreview.png";
	public static final String PREVIEW_ACTIVITY_NOT_AVAILABLE_URL = "angulr/img/build/logo/activityPreview.png";
	public static final String PREVIEW_MENU_NOT_AVAILABLE_URL = "angulr/img/build/logo/photo_not_available.png";


	public static final String SOCKET_WALL_TOPIC = "/walltopic/message/";
	public static final String SOCKET_CHAT_TOPIC = "/chattopic/message/";
	public static final String SOCKET_ACTIVITY_TOPIC = "/activitytopic/message/";
	public static final String SOCKET_NOTIFICATION_TOPIC = "/notificationtopic/message/";
	
	/**
	 * Minutes between FULL Notifications
	 */
	public static final long NOTIFICATION_MIN_DELAY_MINUTES = 1;
	
	/**
	 * every checkin is a full checkin
	 */
	public static final boolean CHECKIN_FULL_ALWAYS = true;
	public static final String PUSH_ICON = "angulr/img/build/logo/logo_push.png";

	//	public static int chatListMessageLength = 25;
	public static final String PUSH_CHROME_ID = "chromePushRegistrationId";

	public static String getTimeFormatted(Timestamp timeStamp)
	{
		return timeStamp!=null? getTimeFormatted(new Date(timeStamp.getTime())) : "";
	}
	
	public static Date convertToDate(LocalDateTime localDateTime)
	{
		return java.sql.Timestamp.valueOf(localDateTime);
	}
	
	public static LocalDateTime convertToLocalDateTime(Date dateToConvert)
	{
		return new java.sql.Timestamp(
				dateToConvert.getTime()).toLocalDateTime();
	}
	
	public static String getTimeFormatted(Date date)
	{
		String time = "";

		if(date==null)
			return time;
		
		if(date.after(convertToDate(LocalDateTime.now().withHour(0).withMinute(0))))
			time = AppConfigProperties.timeFormat.format(date.getTime());
		else
//			time = ControllerUtils.dateTimeFormat.format(date.getTime());
			time = AppConfigProperties.dateFormat.format(date.getTime());
		return time;
	}

	public static String getRelativePath(Path fileAdded)
	{
		String result = "";
		
		if(fileAdded==null)
		return result;

		String pathString = fileAdded.toAbsolutePath().toString();
		pathString = pathString.replaceAll("\\\\","/");
		String[] split = pathString.split("/img/");
		
		if(split.length>1)
			result =  "/" + HOST_SUFFIX + "img/" + split[1] + "";
		
		return result.toString();
	}



	private static boolean checkEmptyness(final String str)
	{
		for (int i = 0, len = str.length(); i < len; i++)
		{
			char chr = str.charAt(i);

			if (!isWhitespace(chr))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Trim whitespace + non-breaking space (hexa code : A0 and unicode 160)<br />
	 * In der DB lautet dieser Befehl: <br />
	 * <code>update scans set scans.serial = REPLACE(scans.serial, char(0xc2a0), '');</code>
	 *
	 * @param val
	 * @return val without leading and trailing whitespace or non-breaking space
	 */
	public static String trimString(String val)
	{
		if (val != null)
		{
			String trimmedValue = val.replace("\u00A0", " ");
			trimmedValue = trimmedValue.replace("\uFEFF", " ");
			trimmedValue = trimmedValue.trim();
			return trimmedValue;
		}
		return "";
	}


	/**
	 * Contains only whitespace + non-breaking space (hexa code : A0 and unicode 160)
	 *
	 * @param str
	 * @return true if is empty else false.
	 */
	public static boolean isEmptyString(final String str)
	{
		return str == null || str.isEmpty() || checkEmptyness(str);
	}
	
	public static String trimString(String val, String emptyValue)
	{
		String trimmedValue = trimString(val);
		if (isEmptyString(trimmedValue))
		{
			return emptyValue;
		}
		return trimmedValue;
	}

	/**
	 * @param val
	 * @return null if val is null
	 */
	public static String trimStringIfNotNull(String val)
	{
		if (val != null)
		{
			return trimString(val);
		}
		return null;
	}

	public static boolean isWhitespace(char c)
	{
		return c == '\u00A0' || Character.isWhitespace(c);
	}

	/**
	 * COALESCE
	 *
	 * @param values
	 * @return
	 */
	public static String getFirstNotEmptyString(String... values)
	{
		if (values != null)
		{
			for (String value : values)
			{
				if (!isEmptyString(value))
				{
					return trimString(value);
				}
			}
		}
		return null;
	}
	
	public static CustomerDomainEntityId getTryEntityId(CustomerDomainEntityId requesterInitId)
	{
		int customerId = 0;
		
		throw new UnsupportedOperationException("mapping from initId to entityId is not implemented");
	}

	public static String addHostPrefixOnDemand(String pictureUrl)
	{
		if(isEmptyString(HOST_SUFFIX)  || isEmptyString(pictureUrl) ||  pictureUrl.startsWith("angulr/") || pictureUrl.startsWith(HOST_SUFFIX) || pictureUrl.startsWith("/"+HOST_SUFFIX) || pictureUrl.startsWith("http"))
		{
			return pictureUrl;
		}
		
		return (pictureUrl.startsWith("/")? "/" + HOST_SUFFIX.replace("/","") : HOST_SUFFIX) + pictureUrl;
	}

	public static String generateCode()
	{
		return String.valueOf(ThreadLocalRandom.current().nextInt(1, 999999));
	}
	
	
	
	
}
