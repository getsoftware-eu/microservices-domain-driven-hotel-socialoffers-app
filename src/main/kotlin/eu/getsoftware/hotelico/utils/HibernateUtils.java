package eu.getsoftware.hotelico.utils;

/**
 * Created by Eugen on 16.07.2015.
 */
public class HibernateUtils
{
	
	//################ COLUMN DEFINITION

	public static class ColumnDefinition
	{
		private ColumnDefinition()
		{
		}

		public static final String VARCHAR_255_DEFAULT_EMPTY = "VARCHAR(255) DEFAULT '' ";

		public static final String VARCHAR_100_DEFAULT_EMPTY = "VARCHAR(100) DEFAULT '' ";

		public static final String VARCHAR_90_DEFAULT_EMPTY = "VARCHAR(90) DEFAULT '' ";

		public static final String BOOL_DEFAULT_TRUE = "BOOL DEFAULT TRUE";

		public static final String BOOL_DEFAULT_FALSE = "BOOL DEFAULT FALSE";

		public static final String TIMESTAMP_DEFAULT_NULL = "TIMESTAMP NULL DEFAULT NULL ";

		public static final String INT_2_DEFAULT_0 = "INT(2) DEFAULT 0";

		public static final String INT_3_DEFAULT_0 = "INT(3) DEFAULT 0";

		public static final String INT_11_DEFAULT_0 = "INT(11) DEFAULT 0";

		public static final String FLOAT_DEFAULT_0 = "FLOAT DEFAULT 0";

		public static final String FLOAT_10_DEFAULT_0 = "FLOAT(10) DEFAULT 0";

		public static final String FLOAT_2_6_DEFAULT_0 = "FLOAT(2,6) DEFAULT 0";

		public static final String MEDIUMINT_UNSIGNED_DEFAULT_0 = "MEDIUMINT UNSIGNED DEFAULT 0";

	}
	
	//################### COLUMN TYPE

	public static class ColumnType
	{
		private ColumnType()
		{
		}

		public static final String JODA_DATE_TIME = "org.jadira.usertype.dateandtime.joda.PersistentDateTime";

		public static final String JODA_LOCAL_DATE = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate";

		public static final String JODA_LOCAL_TIME = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime";
	}
	
}
