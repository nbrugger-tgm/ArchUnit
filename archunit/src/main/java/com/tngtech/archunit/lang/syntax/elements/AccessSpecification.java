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
package com.tngtech.archunit.lang.syntax.elements;

import com.tngtech.archunit.PublicAPI;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaFieldAccess;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.domain.PackageMatcher;
import com.tngtech.archunit.core.domain.properties.HasName;

import static com.tngtech.archunit.PublicAPI.Usage.ACCESS;

@PublicAPI(usage = ACCESS)
public interface AccessSpecification<CONJUNCTION> extends CallSpecification<CONJUNCTION> {
    /**
     * Matches classes residing in a package matching any of the supplied package identifiers.
     *
     * @param packageIdentifiers Strings identifying packages, for details see {@link PackageMatcher}
     * @return A syntax conjunction element, which can be completed to form a full rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION allPackages(String... packageIdentifiers);

    /**
     * Matches classes residing in a package matching any of the supplied package identifiers.
     *
     * @param packageIdentifiers Strings identifying packages, for details see {@link PackageMatcher}
     * @return A syntax conjunction element, which can be completed to form a full rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION anyPackage(String... packageIdentifiers);

    /**
     * @return A syntax element that allows restricting which classes the access should be from
     */
    @PublicAPI(usage = ACCESS)
    ClassesThat<ClassesShouldConjunction> classThat();

    /**
     * Allows to restrict the access origins by matching them against the supplied {@link DescribedPredicate}.
     * <br><br>
     * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
     * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
     * {@link JavaClass} can be found within {@link JavaClass.Predicates} or one of the respective ancestors like {@link HasName.Predicates}.
     *
     * @param predicate Restricts which classes the access should be from
     * @return A syntax conjunction element, which can be completed to form a full rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION classThat(DescribedPredicate<? super JavaClass> predicate);



    /**
     * Matches against all accesses (setting or getting) of a specific field.
     *
     * @param owner     The class declaring the field
     * @param fieldName The name of the field to match
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION field(Class<?> owner, String fieldName);

    /**
     * Matches against all accesses (setting or getting) of a specific field.
     *
     * @param ownerName The fully qualified class name of the class declaring the field
     * @param fieldName The name of the field to match
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION field(String ownerName, String fieldName);

    /**
     * Matches against accessing fields, where origin (a method or constructor) and target (a field)
     * can be freely restricted by the supplied predicate.
     * <br><br>
     * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
     * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
     * {@link JavaFieldAccess} can be found within {@link JavaFieldAccess.Predicates} or one of the respective ancestors
     * like {@link JavaAccess.Predicates}.
     *
     * @param predicate Determines which {@link JavaFieldAccess JavaFieldAccesses} match the rule
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION fieldsWhere(DescribedPredicate<? super JavaFieldAccess> predicate);

    /**
     * Matches against accessing fields, where origin (a method or constructor) and target (a field)
     * can be freely restricted by the supplied predicate.
     * <br><br>
     * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
     * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
     * {@link JavaFieldAccess} can be found within {@link JavaFieldAccess.Predicates} or one of the respective ancestors
     * like {@link JavaAccess.Predicates}.
     *
     * @param predicate Determines which {@link JavaFieldAccess JavaFieldAccesses} match the rule
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    FieldsThat<CONJUNCTION> fieldsThat();

    /**
     * Matches all field accesses against the supplied predicate.
     * <br><br>
     * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
     * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
     * {@link JavaField} can be found within {@link JavaMember.Predicates} or one of the respective ancestors
     * like {@link HasName.Predicates}.
     *
     * @param predicate Determines which {@link JavaField JavaFields} match the rule
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION fieldsThat(DescribedPredicate<? super JavaField> predicate);

    /**
     * Matches against access of arbitrary targets (compare {@link AccessTarget})
     * where origin (a method or constructor) and target (a field, method or constructor) can be freely restricted
     * by the supplied predicate.
     * <br><br>
     * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
     * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
     * {@link JavaAccess} can be found within {@link JavaAccess.Predicates} or one of the respective ancestors
     * like {@link HasName.Predicates}.
     *
     * @param predicate Determines which {@link JavaAccess JavaAccesses} match the rule
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION targetsWhere(DescribedPredicate<? super JavaAccess<?>> predicate);


    /**
     * Matches all members calls against the supplied predicate.
     * <br><br>
     * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
     * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
     * {@link JavaMember} can be found within {@link JavaMember.Predicates} or one of the respective ancestors
     * like {@link HasName.Predicates}.
     *
     * @param predicate Determines which {@link JavaMember JavaMembers} match the rule
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION membersThat(DescribedPredicate<? super JavaMember> predicate);

    /**
     * Matches all members calls against the supplied predicate.
     * <br><br>
     * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
     * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
     * {@link JavaMember} can be found within {@link JavaMember.Predicates} or one of the respective ancestors
     * like {@link HasName.Predicates}.
     *
     * @param predicate Determines which {@link JavaMember JavaMembers} match the rule
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    MembersThat<CONJUNCTION> membersThat();
}
