package site.shug.spring.mvc.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatHandler extends TextWebSocketHandler {
    private Map<String, WebSocketSession> clients = new ConcurrentHashMap<>();

    /**
     * 成功建立连接后执行
     * @param session WebSocket的会话
     * @throws Exception 异常
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        clients.put(session.getId(), session);
        System.out.println("afterConnectionEstablished");
        session.getAttributes().put("client", "Guest1");
    }

    /**
     * 连接关闭后执行
     * @param session WebSocket的会话
     * @param status 关闭状态
     * @throws Exception 异常
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("afterConnectionClosed");
        clients.remove(session.getId());
    }
}
