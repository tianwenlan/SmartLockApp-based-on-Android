package com.example.simsim.local;

/**
 * Created by Qiuge on 2015/7/24.
 */
public interface ServletConstantInterface {

    String URL_SERVER = "http://192.168.0.110/SmartLockBackEnd/";

    // URL for Servlet
    String URL_AUTHENTICATE = URL_SERVER + "AuthenticationServlet";
    String URL_USER_READ = URL_SERVER + "UserReadServlet";
    String URL_USER_CREATE = URL_SERVER + "UserCreateServlet";
    String URL_USER_UPDATE = URL_SERVER + "UserUpdateServlet";
    String URL_USER_DELETE = "";
    String URL_PROPERTY_READ = URL_SERVER + "PropertyReadServlet";
    String URL_PROPERTY_CREATE = URL_SERVER + "PropertyCreateServlet";
    String URL_PROPERTY_UPDATE = URL_SERVER + "PropertyUpdateServlet";
    String URL_PROPERTY_DELETE = URL_SERVER + "PropertyDeleteServlet";
    String URL_LOCK_READ = URL_SERVER + "LockReadServlet";
    String URL_LOCK_CREATE = URL_SERVER + "LockCreateServlet";
    String URL_LOCK_UPDATE = URL_SERVER + "LockUpdateServlet";
    String URL_LOCK_DELETE = URL_SERVER + "LockDeleteServlet";
    String URL_USER_LOCK_READ=URL_SERVER+"GuestLockReadByUserServlet";
    String URL_LOCK_ACTIVITY_READ = URL_SERVER + "LockActivityReadServlet";
    String URL_LOCK_ACTIVITY_CREATE = URL_SERVER + "LockActivityCreateServlet";
    String URL_LOCK_ACTIVITY_UPDATE = URL_SERVER + "LockActivityUpdateServlet";
    String URL_LOCK_ACTIVITY_DELETE = "";

    // Response content
    String RESPONSE_SUCCESS = "success";
    String RESPONSE_FAILURE = "failure";
}
