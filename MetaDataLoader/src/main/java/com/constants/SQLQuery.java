package com.constants;

public class SQLQuery {


	public final static String GET_DETAILS_FOR_SCHEMA	=
			" select nsp.nspname as object_schema, " 
					 + "      cls.relname as table_name,   "
					 + "      cols.column_name   ,  "
					 + "      cols.data_type     "
					 + " from pg_class cls "
					 + "  join pg_roles rol on rol.oid = cls.relowner  "
					 + "  join pg_namespace nsp on nsp.oid = cls.relnamespace  "
					 + " join information_schema.columns cols on cols.table_name=  cls.relname  " 
					 + " where nsp.nspname not in ('information_schema', 'pg_catalog')   "
					 + "   and nsp.nspname not like 'pg_toast%'   "
					 + "   and rol.rolname = 'ue618148f48394ed3aaa1d238496f70f9'  --- remove this if you want to see all objects  "
					 + "  order by nsp.nspname, cls.relname";
	
	
	
/*	public final static String GET_OBECT_DETAILS	=
			"  SELECT  POST_DB_QRY_OBJ_NM,POST_DB_QUERY_STRNG  " + 
 " FROM POST_DB_SQL_QRY";*/
	


	public final static String GET_OBECT_DETAILS	=			

			////"SELECT \"post_db_query_strng\",\"post_db_qry_obj_nm\" FROM \"public\".\"post_db_sql_qry\"";

			"SELECT POST_DB_QUERY_STRNG   , POST_DB_QRY_OBJ_NM    FROM PUBLIC.POST_DB_SQL_QRY2  ";

			
}
