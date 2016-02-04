package com.example.simsim.debug;

import com.example.simsim.database.DatabaseConstantInterface;
import com.example.simsim.entities.EntityAdapter;
import com.example.simsim.entities.Information;
import com.example.simsim.entities.Lock;
import com.example.simsim.entities.LockActivity;
import com.example.simsim.entities.Property;
import com.example.simsim.entities.User;
import com.example.simsim.local.HttpConnection;
import com.example.simsim.local.ServletConstantInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Qiuge on 2015/8/3.
 */
public class Debug implements ServletConstantInterface, DatabaseConstantInterface{

    private static EntityAdapter entityAdapter = new EntityAdapter();

    private static Property property;
    private static Lock lock;
    private static LockActivity lockActivity1;
    private static LockActivity lockActivity2;

    private static void generateData(){
        property = new Property();
        property.setPropertyId(111);
        property.setUserId(0);
        property.setDescription("My Home");
        property.setAddress("5551 Darlington Rd.");
        property.setZipCode(15213);
        property.setCity("Pittsburgh");
        property.setState("PA");
        property.setCountry("US");

        lock = new Lock();
        lock.setLockId(222);
        lock.setPropertyId(111);
        lock.setDescription("Front Door");
        lock.setLockPower(60);
        lock.setLockStartAngle(30);
        lock.setLockEndAngle(120);
        lock.setRotationDirection(LOCK_DIRECTION_COUNTERCLOCKWISE);
        lock.setRotationEndPoints(90);

        lockActivity1 = new LockActivity();
        lockActivity1.setLockActicityId(333);
        lockActivity1.setHostId(0);
        lockActivity1.setGuestId(1);
        lockActivity1.setLockId(222);
        lockActivity1.setAccessStartTime("2015-8-5");
        lockActivity1.setAccessEndTime("2015-8-6");
        lockActivity1.setRequestStatus(LOCK_ACTIVITY_REQUEST_STATUS_PENDING);

        lockActivity2 = new LockActivity();
        lockActivity2.setLockActicityId(444);
        lockActivity2.setHostId(0);
        lockActivity2.setGuestId(1);
        lockActivity2.setLockId(222);
        lockActivity2.setAccessStartTime("2015-8-1");
        lockActivity2.setAccessEndTime("2015-8-2");
        lockActivity2.setRequestStatus(LOCK_ACTIVITY_REQUEST_STATUS_ACCEPT);

    }

    public static void loadDataFromDB(String primaryPhoneNumber) throws Exception {
        generateData();
        Information information = entityAdapter.getInformation();
        information.getUser().setPrimaryPhoneNumber(primaryPhoneNumber);
        User user = (User) HttpConnection.httpPost(URL_USER_READ, information.getUser());

        if(user != null) information.setUser(user);

        if(entityAdapter.getUserState().equals(USER_STATE_HOST)){
            Map<Property, List<Lock>> hostPropLockMap = new HashMap<Property, List<Lock>>();
            List<Lock> lockList = new ArrayList<Lock>();
            lockList.add(lock);
            hostPropLockMap.put(property, lockList);
            information.setHostPropLockMap(hostPropLockMap);
        }
        else{
            List<Lock> guestLock =
                    (List<Lock>) HttpConnection.httpPost(URL_LOCK_READ, information.getUser());
            information.setGuestLock(guestLock);
        }
        Map<Lock, List<LockActivity>> lockLockActivityMap = new HashMap<Lock, List<LockActivity>>();
        List<LockActivity> lockActivitiesList = new ArrayList<LockActivity>();
        lockActivitiesList.add(lockActivity1);
        lockActivitiesList.add(lockActivity2);
        lockLockActivityMap.put(lock, lockActivitiesList);
        information.setLockLockActivityMap(lockLockActivityMap);
    }
}
