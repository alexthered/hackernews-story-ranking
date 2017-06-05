package com.alexthered.me.utils.functions;

import br.com.six2six.fixturefactory.base.Range;
import br.com.six2six.fixturefactory.function.AtomicFunction;
import br.com.six2six.fixturefactory.function.impl.RandomFunction;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

/**
 * Created by hd on 05.06.17.
 */
public class LocalDateTimeFunction implements AtomicFunction {

    private RandomFunction random;

    public LocalDateTimeFunction(LocalDateTime referenceDateTime, DateType type) {
        long start, end;

        if (type == DateType.BEFORE) {
            end = referenceDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
            LocalDateTime startDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(end), ZoneOffset.UTC).minus(28, ChronoUnit.YEARS);
            start = startDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        } else {
            start = referenceDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
            LocalDateTime endDateTime = LocalDateTime.ofInstant(Instant.now().ofEpochMilli(start), ZoneOffset.UTC).plus(28, ChronoUnit.YEARS);
            end = endDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        }

        this.random = new RandomFunction(Long.class, new Range(start, end));
    }

    public LocalDateTimeFunction(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isBefore(endDate)) {
            this.random = new RandomFunction(Long.class, new Range(startDate.toInstant(ZoneOffset.UTC).toEpochMilli(), endDate.toInstant(ZoneOffset.UTC).toEpochMilli()));
        } else {
            this.random = new RandomFunction(Long.class, new Range(endDate.toInstant(ZoneOffset.UTC).toEpochMilli(), startDate.toInstant(ZoneOffset.UTC).toEpochMilli()));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T generateValue() {
        return (T) LocalDateTime.ofInstant(Instant.ofEpochMilli(this.random.generateValue()), ZoneOffset.UTC);
    }

    public enum DateType {
        BEFORE, AFTER
    }
}
