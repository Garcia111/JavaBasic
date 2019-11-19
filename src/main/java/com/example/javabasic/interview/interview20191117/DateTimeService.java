package com.example.javabasic.interview.interview20191117;

import org.joda.time.DateTime;
import org.springframework.lang.NonNull;

import java.time.temporal.TemporalAccessor;
import java.util.Optional;

/**
 * @author：Cheng.
 * @date：Created in 14:44 2019/11/18
 */
public interface DateTimeService {

    /**
     * Now optional.
     *
     * @return the optional
     */
    @NonNull
    Optional<TemporalAccessor> getNow();

    /**
     * Gets date time now.
     *
     * @return the date time now
     */
    DateTime getDateTimeNow();
}
