package com.wjx.the_golden_autumn.lib;

import java.util.function.Consumer;

import javax.annotation.Nonnull;

/**
 * Equivalent to {@link Consumer}, except with nonnull contract.
 *
 * @see Consumer
 */
@FunctionalInterface
public interface NonNullConsumer<T>
{
    void accept(@Nonnull T t);
}
