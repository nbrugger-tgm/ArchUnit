package com.tngtech.archunit.core.domain.properties;

import java.util.Set;

import com.tngtech.archunit.PublicAPI;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaCodeUnit;

import static com.tngtech.archunit.PublicAPI.Usage.ACCESS;

@PublicAPI(usage = ACCESS)
public interface CanBeAccessed {
    @PublicAPI(usage = ACCESS)
    Set<? extends JavaAccess<?>> getAccessesToSelf();

    @PublicAPI(usage = ACCESS)
    final class Predicates {
        @PublicAPI(usage = ACCESS)
        public DescribedPredicate<CanBeAccessed> accessedBy(DescribedPredicate<? super JavaCodeUnit> predicate){
            return new DescribedPredicate<CanBeAccessed>("accessed by %s",predicate.getDescription()) {
                @Override
                public boolean test(CanBeAccessed canBeAccessed) {
                    return canBeAccessed.getAccessesToSelf().stream().anyMatch(access -> predicate.test(access.getOrigin()));
                }
            };
        }
    }
}
