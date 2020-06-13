/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniban.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author koduki
 */
public final class Conveter {

    private Conveter() {
    }

    /**
     * Transform to List.
     *
     * @param <T>
     * @param <R>
     * @param xs
     * @param proc
     * @return
     */
    public static <T, R> List<R> mapToList(List<T> xs, Function<T, R> proc) {
        return xs.stream()
                .map(proc)
                .collect(Collectors.toList());
    }

    /**
     * 
     * @param datetime
     * @return
     * @throws NumberFormatException 
     */
    public static int formatToMonth(ZonedDateTime datetime) throws NumberFormatException {
        return Integer.parseInt(datetime.format(DateTimeFormatter.ofPattern("yyyyMM")));
    }
}
