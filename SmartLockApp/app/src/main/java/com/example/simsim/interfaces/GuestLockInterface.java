package com.example.simsim.interfaces;

import com.example.simsim.entities.Lock;

import java.util.List;

/**
 * Created by Steven on 7/23/15.
 */
public interface GuestLockInterface {

    public List<Lock> getClientLockList();
    public boolean openLock(Lock lock);
    public boolean closeLock(Lock lock);


}
