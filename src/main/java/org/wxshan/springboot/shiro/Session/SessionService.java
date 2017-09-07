package org.wxshan.springboot.shiro.Session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/9/6 0006.
 * session持久化操作
 */
public class SessionService extends EnterpriseCacheSessionDAO{

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void doUpdate(Session session) throws UnknownSessionException{

        System.out.println("update()");
        if (session == null || session.getId() == null){
            System.out.println("session or session id is null");
            return;
        }
        if (session instanceof ValidatingSession && ((ValidatingSession) session).isValid()){
            // 如果会话过期或者停止 就没有必要更新了
            return;
        }
        super.doUpdate(session);
        //设置缓存值并设置有效期
        redisTemplate.opsForValue().set(session.getId().toString(), SerializationUtils.serialize(session));
        redisTemplate.expire(session.getId().toString(),2592000, TimeUnit.SECONDS);
    }

    public  void doDelete(Session session){
        if (session == null || session.getId() == null){
            System.out.println("session or session id is null");
            return;
        }
        //删除key值
        super.doDelete(session);
        redisTemplate.delete(session.getId().toString());
        System.out.println("delete()");
    }

    protected Serializable doCreate(Session session){
        System.out.println("doCreate()");
        Serializable sessionId = super.doCreate(session);
        //设置缓存值并设置有效期
        redisTemplate.opsForValue().set(sessionId.toString(), SerializationUtils.serialize(session));
        redisTemplate.expire(sessionId.toString(),2592000,TimeUnit.SECONDS);
        return sessionId;
    }

    protected Session doReadSession(Serializable sessionId){
        if (sessionId == null){
            System.out.println("session id is null");
            return null;
        }
        System.out.println("doReadSession()");
        Session session = super.doReadSession(sessionId);
        if (session == null){
            session = (Session) SerializationUtils.deserialize((byte[]) redisTemplate.opsForValue().get(sessionId));
        }
        return session;
    }
}
