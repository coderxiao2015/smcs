package com.tianwen.common.session.listener;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.tianwen.common.session.ShiroSessionRepository;
/**
 * 
 * shiro 回话 监听
 * 
 */
public class ShiroSessionListener implements SessionListener {

    private ShiroSessionRepository shiroSessionRepository;

    /**
     * 一个回话的生命周期开始
     */
    @Override
    public void onStart(Session session) {
        System.out.println("on start");
    }
    /**
     * 一个回话的生命周期结束
     */
    @Override
    public void onStop(Session session) {
    	getShiroSessionRepository().delSession(session.getId());
        System.out.println("on stop");
    }

    @Override
    public void onExpiration(Session session) {
       // shiroSessionRepository.deleteSession(session.getId());
    }

    public ShiroSessionRepository getShiroSessionRepository() {
        return shiroSessionRepository;
    }

    public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }

}

