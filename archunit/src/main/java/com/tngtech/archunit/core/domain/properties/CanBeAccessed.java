/*
 * Copyright 2014-2022 TNG Technology Consulting GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tngtech.archunit.core.domain.properties;

import java.util.Set;

import com.tngtech.archunit.PublicAPI;
import com.tngtech.archunit.base.ChainableFunction;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaCodeUnit;

import static com.tngtech.archunit.PublicAPI.Usage.ACCESS;

@PublicAPI(usage = ACCESS)
public interface CanBeAccessed {
    @PublicAPI(usage = ACCESS)
    Set<JavaAccess<?>> getAccessesToSelf();

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

    @PublicAPI(usage = ACCESS)
    final class Functions {
        /**
         * @see #getAccessesToSelf()
         */
        @PublicAPI(usage = ACCESS)
        public static final ChainableFunction<CanBeAccessed, Set<JavaAccess<?>>> GET_ACCESSES_TO_SELF =
                new ChainableFunction<CanBeAccessed, Set<JavaAccess<?>>>() {
                    @Override
                    public Set<JavaAccess<?>> apply(CanBeAccessed input) {
                        return input.getAccessesToSelf();
                    }
                };
    }
}
