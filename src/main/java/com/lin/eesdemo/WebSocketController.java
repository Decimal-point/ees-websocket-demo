package com.lin.eesdemo;


import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 实现websocket
 */
@ServerEndpoint("/webSocket")
@Component
public class WebSocketController {
    //在连接打开的时候调用,可以作为信息初始化
    private Session session;
    //在线人数统计
    private static Integer count = 0;
    //存储所有的请求
    private static CopyOnWriteArrayList<WebSocketController> allSocket = new CopyOnWriteArrayList();
    @OnOpen
    public void OnOpen(Session session){
        this.session = session;
        allSocket.add(this);
        count ++;
        System.out.println("comming ...");
        System.out.println("当前在线人数："+count);
    }
    //收到消息时执行
    @OnMessage
    public void OnMessage(String message) throws IOException {
        System.out.println("[" + session.getId() + "]" + "消息：" + message);
        for (WebSocketController controller : allSocket) {
            controller.sendMessage(message);
        }
    }

    //连接错误时执行
    @OnError
    public void OnError(Throwable t){
        t.printStackTrace();
        System.out.println(t.getMessage());
    }

    //连接关闭的时候执行
    @OnClose
    public void OnClose(Session session , CloseReason closeReason){
        System.out.println(String.format("Session %s closed because of %s",session.getId(),closeReason));
        allSocket.remove(this);
        count--;
        System.out.println("当前在线人数");
    }
    //发送消息的方法
    private void sendMessage(String msg) throws IOException {
        session.getBasicRemote().sendText(msg);
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
