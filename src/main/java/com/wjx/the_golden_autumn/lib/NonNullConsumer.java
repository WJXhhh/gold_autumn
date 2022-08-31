package com.wjx.the_golden_autumn.lib;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

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
