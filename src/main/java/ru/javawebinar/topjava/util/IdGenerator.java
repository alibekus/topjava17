package ru.javawebinar.topjava.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static IdGenerator ourInstance = new IdGenerator();

    public static IdGenerator getInstance() {
        return ourInstance;
    }

    private AtomicLong atomicLong = new AtomicLong();

    private IdGenerator() {
    }

    public long generateId() {
        return atomicLong.addAndGet(1);
    }
}
