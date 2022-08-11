package com.wjx.the_golden_autumn.lib;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

/**
 * Equivalent to {@link Supplier}, except with nonnull contract.
 *
 * @see Supplier
 */
@FunctionalInterface
public interface NonNullSupplier<T>
{
    @Nonnull T get();
}
