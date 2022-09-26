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

import com.tngtech.archunit.PublicAPI;
import com.tngtech.archunit.base.ChainableFunction;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.*;

import java.util.Set;
import java.util.function.Predicate;

import static com.google.common.collect.Sets.union;
import static com.tngtech.archunit.PublicAPI.Usage.ACCESS;

/**
 * A type that contains (e.g. is able to do) {@link JavaAccess accesses} to other elements such as fields, constructors and methods
 */
@PublicAPI(usage = ACCESS)
public interface CanAccess {
    /**
     * @return All accesses of this object to any members (fields/methods/constructors)
     * @see #getFieldAccessesFromSelf()
     * @see #getCodeUnitAccessesFromSelf()
     * @see #getCodeUnitCallsFromSelf()
     * @see #getConstructorCallsFromSelf()
     * @see #getMethodCallsFromSelf()
     * @see #getCodeUnitReferencesFromSelf()
     * @see #getConstructorReferencesFromSelf()
     * @see #getMethodReferencesFromSelf()
     */
    @PublicAPI(usage = ACCESS)
    default Set<JavaAccess<?>> getAccessesFromSelf() {
        return union(getFieldAccessesFromSelf(), getCodeUnitAccessesFromSelf());
    }

    /**
     * @return All accesses of this object to fields. These can be {@link JavaFieldAccess.AccessType#GET read} accesses
     * (e.g. {@code return this.example}) or {@link JavaFieldAccess.AccessType#SET write} accesses
     * (e.g. {@code this.example = example})
     * @see #getAccessesFromSelf()
     * @see #getCodeUnitAccessesFromSelf()
     * @see #getCodeUnitCallsFromSelf()
     * @see #getConstructorCallsFromSelf()
     * @see #getMethodCallsFromSelf()
     * @see #getCodeUnitReferencesFromSelf()
     * @see #getConstructorReferencesFromSelf()
     * @see #getMethodReferencesFromSelf()
     */
    @PublicAPI(usage = ACCESS)
    Set<JavaFieldAccess> getFieldAccessesFromSelf();

    /**
     * @return All access of this object to other code units. This can be calls to methods/constructors (e.g. {@code someExample.call()})
     * or references of methods/constructors (e.g. {@code SomeExample::call})
     * @see #getAccessesFromSelf()
     * @see #getFieldAccessesFromSelf()
     * @see #getCodeUnitCallsFromSelf()
     * @see #getConstructorCallsFromSelf()
     * @see #getMethodCallsFromSelf()
     * @see #getCodeUnitReferencesFromSelf()
     * @see #getConstructorReferencesFromSelf()
     * @see #getMethodReferencesFromSelf()
     */
    @PublicAPI(usage = ACCESS)
    default Set<JavaCodeUnitAccess<?>> getCodeUnitAccessesFromSelf() {
        return union(getCodeUnitCallsFromSelf(), getCodeUnitReferencesFromSelf());
    }

    /**
     * Returns all calls of this object to methods or constructors.
     *
     * @see #getAccessesFromSelf()
     * @see #getFieldAccessesFromSelf()
     * @see #getCodeUnitAccessesFromSelf()
     * @see #getConstructorCallsFromSelf()
     * @see #getMethodCallsFromSelf()
     * @see #getCodeUnitReferencesFromSelf()
     * @see #getConstructorReferencesFromSelf()
     * @see #getMethodReferencesFromSelf()
     */
    @PublicAPI(usage = ACCESS)
    default Set<JavaCall<?>> getCodeUnitCallsFromSelf() {
        return union(getMethodCallsFromSelf(), getConstructorCallsFromSelf());
    }

    /**
     * @return All method calls (e.g. a call to {@code SomeExample.someMethod()})
     * @see #getAccessesFromSelf()
     * @see #getFieldAccessesFromSelf()
     * @see #getCodeUnitAccessesFromSelf()
     * @see #getCodeUnitCallsFromSelf()
     * @see #getConstructorCallsFromSelf()
     * @see #getCodeUnitReferencesFromSelf()
     * @see #getConstructorReferencesFromSelf()
     * @see #getMethodReferencesFromSelf()
     */
    @PublicAPI(usage = ACCESS)
    Set<JavaMethodCall> getMethodCallsFromSelf();

    /**
     * @return All constructor calls (e.g. a call to {@code SomeExample()})
     * @see #getAccessesFromSelf()
     * @see #getFieldAccessesFromSelf()
     * @see #getCodeUnitAccessesFromSelf()
     * @see #getCodeUnitCallsFromSelf()
     * @see #getMethodCallsFromSelf()
     * @see #getCodeUnitReferencesFromSelf()
     * @see #getConstructorReferencesFromSelf()
     * @see #getMethodReferencesFromSelf()
     */
    @PublicAPI(usage = ACCESS)
    Set<JavaConstructorCall> getConstructorCallsFromSelf();

    /**
     * @return All references of this object to {@link #getMethodReferencesFromSelf() method} or {@link #getConstructorReferencesFromSelf() constructor references}.
     * @see #getAccessesFromSelf()
     * @see #getFieldAccessesFromSelf()
     * @see #getCodeUnitAccessesFromSelf()
     * @see #getCodeUnitCallsFromSelf()
     * @see #getConstructorCallsFromSelf()
     * @see #getMethodCallsFromSelf()
     * @see #getConstructorReferencesFromSelf()
     * @see #getMethodReferencesFromSelf()
     */
    @PublicAPI(usage = ACCESS)
    default Set<JavaCodeUnitReference<?>> getCodeUnitReferencesFromSelf() {
        return union(getMethodReferencesFromSelf(), getConstructorReferencesFromSelf());
    }

    /**
     * @return All method references (e.g. {@code SomeExample::someMethod})
     * @see #getAccessesFromSelf()
     * @see #getFieldAccessesFromSelf()
     * @see #getCodeUnitAccessesFromSelf()
     * @see #getCodeUnitCallsFromSelf()
     * @see #getConstructorCallsFromSelf()
     * @see #getMethodCallsFromSelf()
     * @see #getCodeUnitReferencesFromSelf()
     * @see #getConstructorReferencesFromSelf()
     */
    @PublicAPI(usage = ACCESS)
    Set<JavaMethodReference> getMethodReferencesFromSelf();

    /**
     * @return All constructor references (e.g. {@code SomeExample::new})
     * @see #getAccessesFromSelf()
     * @see #getFieldAccessesFromSelf()
     * @see #getCodeUnitAccessesFromSelf()
     * @see #getCodeUnitCallsFromSelf()
     * @see #getConstructorCallsFromSelf()
     * @see #getMethodCallsFromSelf()
     * @see #getCodeUnitReferencesFromSelf()
     * @see #getMethodReferencesFromSelf()
     */
    @PublicAPI(usage = ACCESS)
    Set<JavaConstructorReference> getConstructorReferencesFromSelf();

    @PublicAPI(usage = ACCESS)
    final class Functions {

        /**
         * @see #getAccessesFromSelf()
         */
        @PublicAPI(usage = ACCESS)
        public static final ChainableFunction<CanAccess, Set<JavaAccess<?>>> GET_ACCESSES_FROM_SELF =
                new ChainableFunction<CanAccess, Set<JavaAccess<?>>>() {
                    @Override
                    public Set<JavaAccess<?>> apply(CanAccess input) {
                        return input.getAccessesFromSelf();
                    }
                };

    }

    /**
     * Predefined predicates to test objects that can access other objects
     */
    @PublicAPI(usage = ACCESS)
    final class Predicates {
        @PublicAPI(usage = ACCESS)
        public static DescribedPredicate<CanAccess> accessesTargetThat(DescribedPredicate<? super AccessTarget> targetPredicate) {
            return new DescribedPredicate<CanAccess>(
                    "accesses target, that " + targetPredicate.getDescription()
            ) {
                @Override
                public boolean test(CanAccess canAccess) {
                    return canAccess.getAccessesFromSelf().stream()
                            .map(JavaAccess::getTarget)
                            .anyMatch(targetPredicate);
                }
            };
        }

        @PublicAPI(usage = ACCESS)
        public static DescribedPredicate<CanAccess> accessesCodeUnitsThat(DescribedPredicate<? super JavaCodeUnit> codeUnitPredicate) {
            return new DescribedPredicate<CanAccess>("accesses code units, that %s", codeUnitPredicate.getDescription()) {
                @Override
                public boolean test(CanAccess canAccess) {
                    return canAccess.getCodeUnitAccessesFromSelf().stream()
                            .anyMatch(access -> codeUnitThat(codeUnitPredicate).test(access.getTarget()));
                }
            };
        }

        @PublicAPI(usage = ACCESS)
        public static DescribedPredicate<CanAccess> accessesFieldThat(DescribedPredicate<? super JavaField> fieldPredicate) {
            return new DescribedPredicate<CanAccess>("accesses fields, that %s", fieldPredicate.getDescription()) {
                @Override
                public boolean test(CanAccess canAccess) {
                    return canAccess.getFieldAccessesFromSelf().stream()
                            .anyMatch(access -> fieldThat(fieldPredicate).test(access.getTarget()));
                }
            };
        }

        /**
         * This does not include calls to getter methods, use {@link #accessesCodeUnitsThat(DescribedPredicate)} for that
         */
        @PublicAPI(usage = ACCESS)
        public static DescribedPredicate<CanAccess> readsFieldThat(DescribedPredicate<? super JavaField> fieldPredicate) {
            return new DescribedPredicate<CanAccess>("accesses fields, that %s", fieldPredicate.getDescription()) {
                @Override
                public boolean test(CanAccess canAccess) {
                    return canAccess.getFieldAccessesFromSelf().stream()
                            .filter(a -> a.getAccessType() == JavaFieldAccess.AccessType.GET)
                            .anyMatch(access -> fieldThat(fieldPredicate).test(access.getTarget()));
                }
            };
        }

        /**
         * This does not include calls to setter methods, use {@link #accessesCodeUnitsThat(DescribedPredicate)} for that
         */
        @PublicAPI(usage = ACCESS)
        public static DescribedPredicate<CanAccess> setsFieldThat(DescribedPredicate<? super JavaField> fieldPredicate) {
            return new DescribedPredicate<CanAccess>("accesses fields, that %s", fieldPredicate.getDescription()) {
                @Override
                public boolean test(CanAccess canAccess) {
                    return canAccess.getFieldAccessesFromSelf().stream()
                            .filter(a -> a.getAccessType() == JavaFieldAccess.AccessType.SET)
                            .anyMatch(access -> fieldThat(fieldPredicate).test(access.getTarget()));
                }
            };
        }

        private static Predicate<? super AccessTarget.CodeUnitAccessTarget> codeUnitThat(
                DescribedPredicate<? super JavaCodeUnit> codeUnitPredicate) {
            return (AccessTarget.CodeUnitAccessTarget codeUnitAccessTarget) -> {
                return codeUnitAccessTarget.resolveMember()
                        .map(codeUnitPredicate::test)
                        .orElse(false);//how can a called thing not be imported? False is the right way then i guess
            };
        }

        private static Predicate<AccessTarget.FieldAccessTarget> fieldThat(DescribedPredicate<? super JavaField> fieldPredicate) {
            return (AccessTarget.FieldAccessTarget codeUnitAccessTarget) -> {
                return codeUnitAccessTarget.resolveMember()
                        .map(fieldPredicate::test)
                        .orElse(false);//how can a called thing not be imported? False is the right way then i guess
            };
        }
    }
}
