package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        Resp resp = new Resp("", "204");
        if ("POST".equals(req.httpRequestType())) {
            queue.putIfAbsent(req.getSourceName(), new ConcurrentLinkedQueue<>());
            queue.get(req.getSourceName()).add(req.getParam());
        }
        if ("GET".equals(req.httpRequestType())) {
            String name = queue.getOrDefault(req.getSourceName(), new ConcurrentLinkedQueue<>()).poll();
            if (name != null) {
                resp = new Resp(name, "200");
            }
        }
        return resp;
    }
}

