package com.example.simsim.interfaces;

import com.example.simsim.entities.LockActivity;

import java.util.List;

/**
 * Created by Qiuge on 2015/7/23.
 */
public interface HostEventInterface {

    List<LockActivity> getFutureLockActivity();
    void updateLockActivity(LockActivity lockActivity) throws Exception;
}
