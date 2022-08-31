package com.wjx.the_golden_autumn.lib;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

/**
 * Equivalent to {@link Predicate}, except with nonnull contract.
 *
 * @see Predicate
 */
@FunctionalInterface
public interface NonNullPredicate<T>
{
    boolean test(@Nonnull T t);
}
