package ru.job4j.pooh;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QueueServiceTest {

    @Test
    public void whenPostThenGetQueue() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        /* Добавляем данные в очередь weather. Режим queue */
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );
        /* Забираем данные из очереди weather. Режим queue */
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(result.text(), is("temperature=18"));
    }

    @Test
    public void whenPostTwoMessages() {
        QueueService queueService = new QueueService();
        String param1 = "temperature=18";
        String param2 = "temperature=20";
        queueService.process(
                new Req("POST", "queue", "weather", param1)
        );
        queueService.process(
                new Req("POST", "queue", "weather", param2)
        );
        Resp result1 = queueService.process(
                new Req("GET", "queue", "weather", "")
        );
        Resp result2 = queueService.process(
                new Req("GET", "queue", "weather", "")
        );
        assertEquals(result1.text(), "temperature=18");
        assertEquals(result2.text(), "temperature=20");

    }
}