package interview.interview20191117;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

/**
 * @author：Cheng.
 * @date：Created in 14:44 2019/11/18
 */
@Slf4j
@Service
public class DateTimeServiceImpl implements DateTimeService {

    @Override
    @NonNull
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(LocalDateTime.now());
    }

    @Override
    public DateTime getDateTimeNow() {
        return DateTime.now();
    }
}
