package com.black.trade.core.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataUtil {

    public static <T, UUID> void matchNewDataAndThen(Collection<T> oldData, Collection<T> newData, Function<T, UUID> buildUUID, BiConsumer<T, T> oldAndNewDataConsumer) {
        Objects.requireNonNull(buildUUID);
        Objects.requireNonNull(oldAndNewDataConsumer);
        if (null == oldData || null == newData) {
            return;
        }
        Map<UUID, List<T>> groupByUUID = newData.stream()
                .collect(Collectors.groupingBy(buildUUID));
        groupByUUID.values()
                .stream()
                .filter(list -> list.size() > 1)
                .findAny()
                .ifPresent(list -> {
                    throw new IllegalArgumentException(String.format("id:%s more than one", buildUUID.apply(list.get(0))));
                });
        oldData.forEach(data -> {
            List<T> matchNewDataList = groupByUUID.get(buildUUID.apply(data));
            if (Objects.nonNull(matchNewDataList)) {
                oldAndNewDataConsumer.accept(data, matchNewDataList.get(0));
            }
        });
    }

    public static <T, UUID> void matchListDataAndThen(Collection<T> oldData, Collection<T> newData, Function<T, UUID> buildUUID, BiConsumer<T, List<T>> then) {
        Objects.requireNonNull(buildUUID);
        Objects.requireNonNull(then);
        if (null == oldData || null == newData) {
            return;
        }
        Map<UUID, List<T>> groupByUUID = newData.stream()
                .collect(Collectors.groupingBy(buildUUID));
        oldData.forEach(data -> {
            List<T> matchNewDataList = groupByUUID.get(buildUUID.apply(data));
            if (Objects.nonNull(matchNewDataList)) {
                then.accept(data, matchNewDataList);
            }
        });
    }
}
