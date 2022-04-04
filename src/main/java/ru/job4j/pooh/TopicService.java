package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {
    private final ConcurrentHashMap<String,
            ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> topics = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        Resp rsl = new Resp("", "204");
        if ("GET".equals(req.httpRequestType())) {
            topics.putIfAbsent(req.getSourceName(), new ConcurrentHashMap<>());
            var topic = topics.get(req.getSourceName());
            topic.putIfAbsent(req.getParam(), new ConcurrentLinkedQueue<>());
            String text = topic.get(req.getParam()).poll();
            rsl = text != null ? new Resp(text, "200") : new Resp("", "204");
        } else if ("POST".equals(req.httpRequestType())) {
            var topic = topics.get(req.getSourceName());
               if (topic != null) {
                topic.values().forEach(queue -> queue.add(req.getParam()));
            }
        }
        return rsl;
    }
}
