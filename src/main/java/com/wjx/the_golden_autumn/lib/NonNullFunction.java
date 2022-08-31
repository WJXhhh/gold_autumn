package com.wjx.the_golden_autumn.lib;

import javax.annotation.Nonnull;
import java.util.function.Function;

/**
 * Equivalent to {@link Function}, except with nonnull contract.
 *
 * @see Function
 */
@FunctionalInterface
public interface NonNullFunction<T, R>
{
    @Nonnull
    R apply(@Nonnull T t);
}
