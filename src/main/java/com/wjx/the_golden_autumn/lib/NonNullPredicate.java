package com.wjx.the_golden_autumn.lib;

import java.util.function.Predicate;

import javax.annotation.Nonnull;

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
