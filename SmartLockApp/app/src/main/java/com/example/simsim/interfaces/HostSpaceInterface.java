package com.example.simsim.interfaces;

import com.example.simsim.entities.Lock;
import com.example.simsim.entities.Property;

import java.util.List;

/**
 * Created by Qiuge on 2015/7/23.
 */
public interface HostSpaceInterface {

    List<Property> getPropertyList();
    int getUserId();
    int getLockNumberOfProperty(Property property);

    void insertProperty(Property property) throws Exception;
    void updateProperty(Property property) throws Exception;
    void deleteProperty(Property property) throws Exception;
    List<Lock> getLockList(Property property);
    void insertLock(Property property, Lock lock) throws Exception;
    void updateLock(Property property, Lock lock) throws Exception;
    void deleteLock(Property property, Lock lock) throws Exception;

}
