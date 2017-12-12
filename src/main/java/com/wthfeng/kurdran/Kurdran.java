package com.wthfeng.kurdran;

import com.wthfeng.kurdran.server.netty.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mvc服务启动类
 * @author wangtonghe
 * @date 2017/12/2 12:23
 */
public class Kurdran {

    private final int DEFAULT_PORT = 9000;

    private Logger logger = LoggerFactory.getLogger(Kurdran.class);

    private Kurdran(){

    }

    public static Kurdran me(){
       return new Kurdran();
    }

    /**
     * 服务启动方法
     */
    public void start(Class<?> clazz,int port){

        try {
            NettyServer server = new NettyServer(getPort(port));
            server.start();
        } catch (Exception e) {
           logger.error("服务器启动错误",e);
        }

    }

    public void start(Class<?> clazz){
        start(clazz,DEFAULT_PORT);
    }


    private int getPort(int port)throws Exception{
       if(port<1024||port>65535){
           throw new Exception("port error");
       }else{
           return port;
       }

    }
}
