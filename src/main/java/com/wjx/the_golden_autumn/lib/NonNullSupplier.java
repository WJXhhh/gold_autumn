package com.wjx.the_golden_autumn.lib;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

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
