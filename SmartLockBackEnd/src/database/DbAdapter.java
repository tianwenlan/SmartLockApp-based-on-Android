package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.simsim.entities.*;

public class DbAdapter {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/SMARTLOCK";

	//  Database credentials
	static final String USER = "pbpublic";
	static final String PASS = "pbpublic";
	static Connection conn = null;
	static Statement stmt = null;
	
	public static void main(String[] args) {

		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			System.out.println("Creating database...");
			stmt = conn.createStatement();

			String sql = "CREATE DATABASE IF NOT EXISTS SMARTLOCK;";
			stmt.executeUpdate(sql);
			sql = "use SMARTLOCK;";
			stmt.executeUpdate(sql);

			String sqlUser = "CREATE TABLE IF NOT EXISTS USER ( " + 
					" USER_ID int NOT NULL AUTO_INCREMENT primary key, " + // auto generated user_id
					" NAME varchar(255) NOT NULL, " +
					" PRIMARY_PHONE_NUMBER varchar(255) NOT NULL UNIQUE, " +
					" IS_MOBILE_VERIFIED varchar(255) , " +
					" COUNTRY varchar(255) NOT NULL, " +
					" PASSWORD varchar(255) NOT NULL , " +
					" USER_STATE varchar(255) , " +
					" DATE_OF_BIRTH varchar(255) , " +
					" GENDER varchar(255) , " +
					" EMAIL_ADDRESS varchar(255) , " +
					" ZIP_CODE int, " +
					" ICON varchar(255));" ;

			//      System.out.println(sqlUser);

			String sqlProperty =" CREATE TABLE IF NOT EXISTS PROPERTY (" +
					"PROPERTY_ID int    NOT NULL AUTO_INCREMENT primary key, " +
					"USER_ID int		NOT NULL," +
					"DESCRIPTION varchar(255)," +
					"ADDRESS varchar(255)," +
					"CITY varchar(255)," +
					"ZIP_CODE int," +
					"STATE varchar(255)," +
					"COUNTRY varchar(255)," +
					"OWNERSHIP varchar(255), " + 
					//			    		   "PRIMARY KEY PROPERTY_ID, " +
					"FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID));";
			//			    
			String sqlLock = "CREATE TABLE IF NOT EXISTS LOCKS(" +
					"LOCK_ID int NOT NULL primary key," +
					"DESCRIPTION varchar(255)," +
					"IS_LOCKED boolean," +
					"LOCK_POWER double," +
					"LOCK_START_ANGLE double," +
					"LOCK_END_ANGLE double," +
					"ROTATION_DIRECTION varchar(255)," +
					"ROTATION_END_POINTS double, " + 
					"FOREIGN KEY (LOCK_ID) REFERENCES PROPERTY(PROPERTY_ID));";
			//	  System.out.println(sqlLock);			  
			String sqlLockActivity = "CREATE TABLE IF NOT EXISTS LOCK_ACTIVITY(" +
					"LOCK_ID int    NOT NULL ," + 
					"GUEST_ID int," + 
					"HOST_ID int," +
					"ACCESS_START_TIME varchar(255)," + 
					"ACCESS_END_TIME varchar(255)," + 
					"REQUEST_ACCESS_TIMESTAMP varchar(255)," + 
					"REQUEST_STATUS varchar(255),"  +
					"ALERT int, " +
					"FOREIGN KEY (LOCK_ID) REFERENCES LOCKS(LOCK_ID));";
			//	  System.out.println(sqlLockActivity);		
			String sqlGuestLock = "CREATE TABLE IF NOT EXISTS GUEST_LOCK (" +
					"USER_ID int    NOT NULL ," + 
					"LOCK_ID int    NOT NULL ," +
					"FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID), " +
					"FOREIGN KEY (LOCK_ID) REFERENCES LOCKS(LOCK_ID));";

			stmt.executeUpdate(sqlUser);
			stmt.executeUpdate(sqlProperty);
			stmt.executeUpdate(sqlLock);
			stmt.executeUpdate(sqlLockActivity);
			stmt.executeUpdate(sqlGuestLock);
			System.out.println("Database created successfully...");

			//user test part
			User testUser = new User();
			testUser.setName("xiaokaisun");
			testUser.setPrimaryPhoneNumber("1233mdddxxx38");
			testUser.setPassword("xxxxx");
			testUser.setCountry("USA");
			testUser.setUserState("guest");
//			testUser.setDataOfBirth("091390");
//			testUser.setEmailAddress("xiaokaisun@gmail.com");
//			testUser.setGender("Male");
//			testUser.setIsMobileVerified("No");
//			testUser.setZipCode(12345);
//			testUser.setIcon("www.google.com");
//			String info = createUser(testUser);
//			updateUser(testUser);

			User user3 = readUser(testUser);
			
//			System.out.println("read user_id: " + user3.getUserId());
			//      System.out.println(user3.getIcon());
			//      deleteUser(testUser);
			//property test part
			Property testP = new Property();
			testP.setUserId(1);
			testP.setDescription("Beautiful room");
			testP.setAddress("Hobart st. ");
			testP.setCity("Columbus");
			testP.setZipCode(43210);
			testP.setState("Ohio");
			testP.setCountry("USABS");
			testP.setOwnership("Host");
			testP.setPropertyId(2);
//			List<Property> prop = readProperty(user3);
//			System.out.println(createProperty(testP));

			//      String info = updateProperty(testP);
			//      System.out.println(info);

//			List<Property> p = readProperty(user3);
			
//			System.out.println("readProperty id: " + p.get(0).getCity());

			//      System.out.println(deleteProperty(testP));

			// lock test part
			Lock testLock = new Lock();
			testLock.setLockId(7);
			testLock.setDescription("cool lock");
			testLock.setIsLocked(false);
			testLock.setLockPower(100);
			testLock.setLockStartAngle(36.0);
			testLock.setLockEndAngle(90.5);
			testLock.setRotationDirection("E");
			testLock.setRotationEndPoints(67.5);
//			createLock(testLock);
//			Map<Property, List<Lock>> map = readLock(user3);
//			for(Property p : map.keySet()) 
//				System.out.println("read host lock map: " + p.getPropertyId());
			//      System.out.println(createLock(testLock));
			//      updateLock(testLock);
			//      System.out.println("read lock: " + readLock(testLock).getLockId());
			//      deleteLock(testLock);

			LockActivity testLA = new LockActivity();
			testLA.setLockId(7);
			testLA.setHostId(user3.getUserId());
//			testLA.setGuestId(3);
			testLA.setAccessStartTime("2355xx");
			testLA.setAccessEndTime("09425");
			testLA.setRequestAccessTimestamp("2xxx434");
			testLA.setRequestStatus("pending");
			testLA.setAlert(30);
			
			User testUser2 = new User();
			testUser2.setName("xiaokaisun");
			testUser2.setPrimaryPhoneNumber("1233456768");
			testUser2.setPassword("xxxxx");
			testUser2.setCountry("USA");
			testUser2.setUserState("guest");
//			User xxuser = readUser(testUser2);
////			System.out.println(xxuser.getUserId());
//			Map<Property, List<Lock>> lockMap = readLock(xxuser);
////			System.out.println("testreadlock: "+lockMap.size());
//			List<Lock> listLock = new ArrayList<Lock>();
////			int count = 0;
//			for(Property p: lockMap.keySet()) {
//				listLock.addAll(lockMap.get(p));
//			}
////			System.out.println(listLock.size());
////			for (int j = 0; j < listLock.size(); j ++) {
////				createGuestLock(xxuser,listLock.get(j));
////			}
//			List<Lock> haha = readGuestLock(xxuser);
////			for(Lock l : haha) 
//					System.out.println("finally: " + l.getLockId());
//			Map<Lock, List<LockActivity>> LAmap = readLockActivity(user3);
//			for(Lock p : LAmap.keySet()) {
//				System.out.println("lock activity number: " + LAmap.get(p).size());
//			}
//			      createLockActivity(testLA);
			//      updateLockActivity(testLA);
			//      System.out.println("read lockactivity: " + readLockActivity(testLA).get(0).getAccessEndTime());
			//      deleteLockActivity(testLA);

			//      createGuestLock(readUser(testUser), readLock(testLock));
			//      System.out.println("guest lock: "+ readGuestLock(readLock(testLock)).size());
//			deleteGuestLock(user3);
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(Exception se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(Exception se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");
	}//end main

	public static String createUser(User user) {
		   String query = "INSERT INTO USER(NAME, PRIMARY_PHONE_NUMBER, IS_MOBILE_VERIFIED, COUNTRY, PASSWORD, USER_STATE, "
		   		+ "DATE_OF_BIRTH, GENDER, EMAIL_ADDRESS, ZIP_CODE, ICON) " + 
				   		"VALUES(" +
				   		"'" + user.getName() + "'," +
				   		"'" + user.getPrimaryPhoneNumber() +"'," +
				   		"'" + user.getIsMobileVerified() + "'," + 
						"'" + user.getCountry() + "'," +
				   		"'" + user.getPassword() + "'," + 
				   		"'" + user.getUserState() + "'," +
		   				"'" + user.getDataOfBirth() + "'," +
		   				"'" + user.getGender() + "'," +
		   				"'" + user.getEmailAddress() + "'," +
				   		user.getZipCode() + "," +
				   		"'" + user.getIcon() + "');";
		   System.out.println(query);
		   try {
			   open();
			stmt.executeUpdate(query);
			close();
			return "success";
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failure";
		}
	   }
	   
	public static String updateUser(User user) {
		String query = "UPDATE USER" + 
				" SET NAME=" + "'" + user.getName() + "'," +
				"IS_MOBILE_VERIFIED=" + "'" + user.getIsMobileVerified() + "'," +
				"COUNTRY=" + "'" + user.getCountry() + "'," +
				"DATE_OF_BIRTH=" +"'" +  user.getDataOfBirth() + "'," +
				"GENDER=" + "'" + user.getGender() + "'," +
				"EMAIL_ADDRESS=" + "'" + user.getEmailAddress() + "'," +
				"ZIP_CODE=" + user.getZipCode() + "," +
				"ICON=" + "'" + user.getIcon() + "'" + 
				" WHERE PRIMARY_PHONE_NUMBER=" + "'" + user.getPrimaryPhoneNumber() + "';";
		System.out.println(query);
		 try {
			 open();
				stmt.executeUpdate(query);
				close();
				return "success";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "failure";
			}
	}

	public static User readUser(User user) {
		String query = "SELECT * FROM USER " +
				"WHERE PRIMARY_PHONE_NUMBER='" + user.getPrimaryPhoneNumber() + "';";
		 try {
			 open();
			 ResultSet rs = stmt.executeQuery(query);
			 User userInfo = null;
			 while(rs.next()){ 
//				 System.out.println(rs.getString("USER_ID"));
				 userInfo = new User();
				 userInfo.setPrimaryPhoneNumber(user.getPrimaryPhoneNumber());
				 userInfo.setUserId(rs.getInt("USER_ID"));
				 userInfo.setName(rs.getString("NAME"));
				 userInfo.setPassword(rs.getString("PASSWORD"));
				 userInfo.setIsMobileVerified(rs.getString("IS_MOBILE_VERIFIED"));
				 userInfo.setCountry(rs.getString("COUNTRY"));
				 userInfo.setUserState(rs.getString("USER_STATE"));
				 userInfo.setDataOfBirth(rs.getString("DATE_OF_BIRTH"));
				 userInfo.setGender(rs.getString("GENDER"));
				 userInfo.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
				 userInfo.setZipCode(rs.getInt("ZIP_CODE"));
				 userInfo.setIcon(rs.getString("ICON"));
				 
			 }
			 rs.close();
			 close();
//			 System.out.println(userInfo.getUserId());
			 return userInfo;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
	}

	public static String deleteUser(User user) {
		String query = "DELETE FROM USER" + 
						" WHERE PRIMARY_PHONE_NUMBER='" + user.getPrimaryPhoneNumber() + "';";
		System.out.println(query);
		try {
			open();
			stmt.executeUpdate(query);
			close();
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failure";
		}
	}

	public static String createProperty(Property property) {
		String query = "INSERT INTO PROPERTY(USER_ID, DESCRIPTION, ADDRESS, CITY, ZIP_CODE, STATE, COUNTRY, OWNERSHIP) " + 
				"VALUES( " +
				property.getUserId() + "," +
				"'"+property.getDescription() + "'," +
				"'"+property.getAddress() + "'," + 
				"'"+property.getCity() + "'," +
				property.getZipCode() + "," +
				"'"+property.getState()+ "',"  +
				"'"+property.getCountry() + "'," +
				"'"+property.getOwnership()+ "');";
		String isAccepted;
		try {
			open();
			stmt.executeUpdate(query);
			close();
			isAccepted =  "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isAccepted = "failure";
		}
		return isAccepted;
	}

	public static String updateProperty(Property property) {
		String query = "UPDATE PROPERTY" + 
				" SET USER_ID=" + property.getUserId() + "," +
				"DESCRIPTION=" + "'"+ property.getDescription() + "'," +
				"ADDRESS=" +"'"+ property.getAddress() + "'," +
				"CITY=" + "'"+property.getCity() + "'," +
				"ZIP_CODE=" + property.getZipCode() + "," +
				"STATE=" +"'"+ property.getState() + "'," +
				"COUNTRY=" + "'"+property.getCountry() + "'," +
				"OWNERSHIP=" + "'"+property.getOwnership() +"'" + 
				" WHERE PROPERTY_ID=" + property.getPropertyId() + ";";
		String isAccepted;
		try {
			open();
			stmt.executeUpdate(query);
			close();
			isAccepted = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isAccepted = "failure";
		}
		return isAccepted;
	}

	//public static Property readProperty(Property property) {
	//		String query = "SELECT * FROM PROPERTY " +
	//				"WHERE PROPERTY_ID=" + property.getPropertyId() + ";";
	//		 try {
	//			 ResultSet rs = stmt.executeQuery(query);
	//			 Property propertyInfo = new Property();
	//			 while(rs.next()){ 
	//				 propertyInfo.setPropertyId(rs.getInt("PROPERTY_ID"));
	//				 propertyInfo.setUserId(rs.getInt("USER_ID"));
	//				 propertyInfo.setDescription(rs.getString("DESCRIPTION"));
	//				 propertyInfo.setAddress(rs.getString("ADDRESS"));
	//				 propertyInfo.setCity(rs.getString("CITY"));
	//				 propertyInfo.setZipCode(rs.getInt("ZIP_CODE"));
	//				 propertyInfo.setState(rs.getString("STATE"));
	//				 propertyInfo.setCountry(rs.getString("COUNTRY"));
	//				 propertyInfo.setOwnership(rs.getString("OWNERSHIP"));
	//			 }
	//			 rs.close();
	//			 return propertyInfo;
	//			} catch (Exception e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//				return null;
	//			}
	//	}
	
	public static List<Property> readProperty(User user) {
		String query = "SELECT * FROM PROPERTY " +
				"WHERE USER_ID=" + user.getUserId() + ";";
		try {
			open();
			ResultSet rs = stmt.executeQuery(query);
			List<Property> list = new ArrayList<Property>();
			while(rs.next()){ 
				Property propertyInfo = new Property();
				propertyInfo.setPropertyId(rs.getInt("PROPERTY_ID"));
				propertyInfo.setUserId(rs.getInt("USER_ID"));
				propertyInfo.setDescription(rs.getString("DESCRIPTION"));
				propertyInfo.setAddress(rs.getString("ADDRESS"));
				propertyInfo.setCity(rs.getString("CITY"));
				propertyInfo.setZipCode(rs.getInt("ZIP_CODE"));
				propertyInfo.setState(rs.getString("STATE"));
				propertyInfo.setCountry(rs.getString("COUNTRY"));
				propertyInfo.setOwnership(rs.getString("OWNERSHIP"));
				list.add(propertyInfo);
			}
			rs.close();
			close();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String deleteProperty(Property property) {
		String query = "DELETE FROM PROPERTY" + 
				" WHERE PROPERTY_ID=" + property.getPropertyId() + ";";
		String isAccepted;
		try {
			open();
			stmt.executeUpdate(query);
			close();
			isAccepted = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isAccepted =  "failure";
		}
		return isAccepted;
	}
	
	public static String createLock(Lock lock) {
		String query = "INSERT INTO LOCKS(LOCK_ID, DESCRIPTION, IS_LOCKED, LOCK_POWER, LOCK_START_ANGLE, LOCK_END_ANGLE, ROTATION_DIRECTION, ROTATION_END_POINTS ) " + 
				"VALUES(" + lock.getLockId() +"," +
				"'"+lock.getDescription()+ "'," +
				lock.isLocked()+ "," +
				lock.getLockPower() + "," + 
				lock.getLockStartAngle()+ "," +
				lock.getLockEndAngle() + "," +
				"'"+ lock.getRotationDirection()+ "',"  +
				lock.getRotationEndPoints() + ");";
		String isAccepted;
		try {
			open();
			//		   System.out.println(query);
			stmt.executeUpdate(query);
			close();
			isAccepted = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isAccepted =  "failure";
		}
		return isAccepted;
	}
	
	public static String updateLock(Lock lock) {
		String query = "UPDATE LOCKS" + 
				" SET DESCRIPTION=" + "'"+ lock.getDescription() + "'," +
				"IS_LOCKED=" + lock.isLocked() + "," +
				"LOCK_POWER=" + lock.getLockPower() + "," +
				"LOCK_START_ANGLE=" + lock.getLockStartAngle() + "," +
				"LOCK_END_ANGLE=" + lock.getLockEndAngle() + "," +
				"ROTATION_DIRECTION='" + lock.getRotationDirection() + "'," +
				"ROTATION_END_POINTS=" + lock.getRotationEndPoints() + 
				" WHERE LOCK_ID=" + lock.getLockId() + ";";
		String isAccepted;
		try {
			open();
			System.out.println(query);
			stmt.executeUpdate(query);
			close();
			isAccepted = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isAccepted = "failure";
		}
		return isAccepted;
	}

	//public static Lock readLock(Lock lock) {
	//	String query = "SELECT * FROM LOCKS" + 
	//				" WHERE LOCK_ID =" + lock.getLockId() + ";";
	//	 try {
	//		 ResultSet rs = stmt.executeQuery(query);
	//		 Lock lockInfo = new Lock();
	//		 while(rs.next()){ 
	//			lockInfo.setLockId(rs.getInt("LOCK_ID"));
	//			lockInfo.setDescription(rs.getString("DESCRIPTION"));
	//			lockInfo.setIsLocked(rs.getBoolean("IS_LOCKED"));
	//			lockInfo.setLockPower(rs.getDouble("LOCK_POWER"));
	//			lockInfo.setLockStartAngle(rs.getDouble("LOCK_START_ANGLE"));
	//			lockInfo.setLockEndAngle(rs.getDouble("LOCK_END_ANGLE"));
	//			lockInfo.setRotationDirection(rs.getString("ROTATION_DIRECTION"));
	//			lockInfo.setRotationEndPoints(rs.getDouble("ROTATION_END_POINTS"));
	//		 }
	//		 rs.close();
	//		 return lockInfo;
	//		} catch (Exception e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//			return null;
	//		}
	//}
	// read host locks
	
	public static Map<Property, List<Lock>> readLock(User user) {
		List<Property> listOfProperty = readProperty(user);
		Map<Property, List<Lock>> map = new HashMap<Property, List<Lock>>();
		for(int i = 0; i < listOfProperty.size(); i ++) {
			String query = "SELECT * FROM LOCKS" + 
					" WHERE LOCK_ID =" + listOfProperty.get(i).getPropertyId() + ";";
			List<Lock> listOfLock = new ArrayList<Lock>();
			try {
				open();
				ResultSet rs = stmt.executeQuery(query);
				Lock lockInfo = null;
				while(rs.next()){ 
					lockInfo = new Lock();
					lockInfo.setLockId(rs.getInt("LOCK_ID"));
					lockInfo.setDescription(rs.getString("DESCRIPTION"));
					lockInfo.setIsLocked(rs.getBoolean("IS_LOCKED"));
					lockInfo.setLockPower(rs.getDouble("LOCK_POWER"));
					lockInfo.setLockStartAngle(rs.getDouble("LOCK_START_ANGLE"));
					lockInfo.setLockEndAngle(rs.getDouble("LOCK_END_ANGLE"));
					lockInfo.setRotationDirection(rs.getString("ROTATION_DIRECTION"));
					lockInfo.setRotationEndPoints(rs.getDouble("ROTATION_END_POINTS"));
					listOfLock.add(lockInfo);
				}
				rs.close();
				close();				
				map.put(listOfProperty.get(i), listOfLock);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return map;
	}
	
	public static String deleteLock(Lock lock) {
		String query = "DELETE FROM LOCKS" + 
				" WHERE LOCK_ID=" + lock.getLockId() + ";";
		String isAccepted;
		try {
			open();
			stmt.executeUpdate(query);
			close();
			isAccepted = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isAccepted = "failure";
		}
		return isAccepted;
	}

	public static String createLockActivity(LockActivity lockActivity) {
		String query = "INSERT INTO LOCK_ACTIVITY " + 
				"VALUES(" + lockActivity.getLockId() + "," +
				lockActivity.getGuestId()+ "," +
				lockActivity.getHostId()+ "," +
				"'" + lockActivity.getAccessStartTime() + "'," + 
				"'" + lockActivity.getAccessEndTime()+ "'," +
				"'" + lockActivity.getRequestAccessTimestamp() + "'," +
				"'" + lockActivity.getRequestStatus()+ "',"  +
				lockActivity.getAlert() + ");";
		String isAccepted;
		try {
			open();
			stmt.executeUpdate(query);
			close();
			isAccepted= "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isAccepted= "failure";
		}
		return isAccepted;
	}
	
	public static String updateLockActivity(LockActivity lockActivity) {
		String query = "UPDATE LOCK_ACTIVITY" + 
				" SET GUEST_ID=" + lockActivity.getGuestId() + "," +
				"HOST_ID=" + lockActivity.getHostId() + "," +
				"ACCESS_START_TIME=" + "'" +lockActivity.getAccessStartTime() + "'," +
				"ACCESS_END_TIME=" + "'" +lockActivity.getAccessEndTime() + "'," +
				"REQUEST_ACCESS_TIMESTAMP=" + "'" +lockActivity.getRequestAccessTimestamp() + "'," +
				"REQUEST_STATUS=" + "'" +lockActivity.getRequestStatus()+"',"+ 
				"ALERT=" + lockActivity.getAlert() + 
				" WHERE LOCK_ID=" + lockActivity.getLockId() + "," + 
				" GUEST_ID=" + lockActivity.getGuestId() + "," + 
				" HOST_ID=" + lockActivity.getHostId() + ";";
		System.out.println(query);
		String isAccepted;
		try {
			open();
			stmt.executeUpdate(query);
			close();
			isAccepted = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isAccepted = "failure";
		}
		return isAccepted;
	}
	
	//public static List<LockActivity> readLockActivity(LockActivity lockActivity) {
	//	String query = "SELECT * FROM LOCK_ACTIVITY" + 
	//				" WHERE LOCK_ID =" + lockActivity.getLockId() + ";";
	//	 try {
	//		 ResultSet rs = stmt.executeQuery(query);
	//		 List<LockActivity> list = new ArrayList<LockActivity>();
	//		 while(rs.next()){ 
	//			 LockActivity lockActivityInfo = new LockActivity();
	//			 lockActivityInfo.setLockId(rs.getInt("LOCK_ID"));
	//			 lockActivityInfo.setGuestId(rs.getInt("GUEST_ID"));
	//			 lockActivityInfo.setHostId(rs.getInt("HOST_ID"));
	//			 lockActivityInfo.setAccessStartTime(rs.getString("ACCESS_START_TIME"));
	////			 System.out.println("ACCESS_END_TIME: " + rs.getString("ACCESS_END_TIME"));
	//			 lockActivityInfo.setAccessEndTime(rs.getString("ACCESS_END_TIME"));
	//			 lockActivityInfo.setRequestAccessTimestamp(rs.getString("REQUEST_ACCESS_TIMESTAMP"));
	//			 lockActivityInfo.setRequestStatus(rs.getString("REQUEST_STATUS"));
	//			 lockActivityInfo.setAlert(rs.getInt("ALERT"));
	//			 list.add(lockActivityInfo);
	//		 }
	//		 rs.close();
	//		 return list;
	//		} catch (Exception e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//			return null;
	//		}
	//}
	
	public static Map<Lock, List<LockActivity>> readLockActivity(User user) {
		String userState = user.getUserState().toLowerCase();
		List<Lock> listOfLock = new ArrayList<Lock>();
		if (userState.equals("guest")) {
			listOfLock = readGuestLock(user);
		}
		if (userState.equals("host")) {
			Map<Property, List<Lock>> map = readLock(user);
			for (List<Lock> subList : map.values()) 
				listOfLock.addAll(subList);
		}
		Map<Lock, List<LockActivity>> map = new HashMap<Lock, List<LockActivity>>();
		for (int i = 0; i < listOfLock.size(); i ++) {
			String query = "SELECT * FROM LOCK_ACTIVITY" + 
					" WHERE LOCK_ID =" + listOfLock.get(i).getLockId() + ";";
			try {
				open();
				ResultSet rs = stmt.executeQuery(query);
				List<LockActivity> list = new ArrayList<LockActivity>();
				while(rs.next()){ 
					LockActivity lockActivityInfo = new LockActivity();
					lockActivityInfo.setLockId(rs.getInt("LOCK_ID"));
					lockActivityInfo.setGuestId(rs.getInt("GUEST_ID"));
					lockActivityInfo.setHostId(rs.getInt("HOST_ID"));
					lockActivityInfo.setAccessStartTime(rs.getString("ACCESS_START_TIME"));
					lockActivityInfo.setAccessEndTime(rs.getString("ACCESS_END_TIME"));
					lockActivityInfo.setRequestAccessTimestamp(rs.getString("REQUEST_ACCESS_TIMESTAMP"));
					lockActivityInfo.setRequestStatus(rs.getString("REQUEST_STATUS"));
					lockActivityInfo.setAlert(rs.getInt("ALERT"));
					list.add(lockActivityInfo);
				}
				rs.close();
				close();
				map.put(listOfLock.get(i), list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return map;
	}
	
	public static String deleteLockActivity(LockActivity lockActivity) {
		String query = "DELETE FROM LOCK_ACTIVITY" + 
				" WHERE LOCK_ID=" + lockActivity.getLockId();
		String isAccepted;
		try {
			open();
			stmt.executeUpdate(query);
			close();
			isAccepted = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isAccepted = "failure";
		}
		return isAccepted;
	}
	
	public static String createGuestLock(User user, Lock lock) {
		String query = "INSERT INTO GUEST_LOCK " + 
				"VALUES(" + user.getUserId() + "," +
				lock.getLockId() + ");";
		System.out.println(query);
		String isAccepted;
		try {
			open();
			stmt.executeUpdate(query);
			close();
			isAccepted = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isAccepted = "failure";
		}
		return isAccepted;
	}

	public static String updateGuestLock(User user, Lock lock)  {
		String isAccepted = null;
		return isAccepted;
	}
	
	public static List<Lock> readGuestLock(User user) {
		String query = "SELECT * FROM GUEST_LOCK" + 
				" WHERE USER_ID =" + user.getUserId() + ";";
		List<Lock> listOfLock = new ArrayList<Lock>();
		List<Integer> lockIdList = new ArrayList<Integer>();
		try {
			open();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){ 
				lockIdList.add(rs.getInt("LOCK_ID"));
			}
			rs.close();
			close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	for(int i = 0; i < lockIdList.size(); i ++) {
		String query2 = "SELECT * FROM LOCKS" + 
				" WHERE LOCK_ID =" + lockIdList.get(i)+ ";";
		try {
			open();
			ResultSet rs = stmt.executeQuery(query2);			
			while(rs.next()){ 
				Lock lockInfo = new Lock();
				lockInfo.setLockId(rs.getInt("LOCK_ID"));
				lockInfo.setDescription(rs.getString("DESCRIPTION"));
				lockInfo.setIsLocked(rs.getBoolean("IS_LOCKED"));
				lockInfo.setLockPower(rs.getDouble("LOCK_POWER"));
				lockInfo.setLockStartAngle(rs.getDouble("LOCK_START_ANGLE"));
				lockInfo.setLockEndAngle(rs.getDouble("LOCK_END_ANGLE"));
				lockInfo.setRotationDirection(rs.getString("ROTATION_DIRECTION"));
				lockInfo.setRotationEndPoints(rs.getDouble("ROTATION_END_POINTS"));
				listOfLock.add(lockInfo);
			}
			rs.close();
			close();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	return listOfLock;
}

	//	public static List<User> readGuestLock(Lock lock) {
//		String query = "SELECT * FROM GUEST_LOCK" + 
//				" WHERE LOCK_ID =" + lock.getLockId() + ";";
//		try {
//			ResultSet rs = stmt.executeQuery(query);
//			List<User> userList = new ArrayList<User>();
//			while(rs.next()){ 
//				User userInfo = new User();
//				userInfo.setUserId(rs.getInt("USER_ID"));
//				userList.add(userInfo);
//			}
//			rs.close();
//			return userList;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	public static String deleteGuestLock(Lock lock) {
		String query = "DELETE FROM GUEST_LOCK" + 
				" WHERE LOCK_ID=" + lock.getLockId()+ ";";
		String isAccepted;
		try {
			open();
			stmt.executeUpdate(query);
			close();
			isAccepted = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isAccepted = "failure";
		}
		return isAccepted;
	}

	public static String deleteGuestLock(User user) {
		String query = "DELETE FROM GUEST_LOCK" + 
				" WHERE USER_ID=" + user.getUserId() + ";";
		String isAccepted;
		try {
			open();
			stmt.executeUpdate(query);
			close();
			isAccepted = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isAccepted = "failure";
		}
		return isAccepted;
	}
	
	public static void open() throws ClassNotFoundException, Exception{
		System.out.println("Connecting to database...");
		Class.forName(JDBC_DRIVER);	      
	    conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    stmt = conn.createStatement();
	}

	public static void close() throws Exception {
	    System.out.println("Closing db connection");
	    stmt.close();
	    conn.close();
	}
}








