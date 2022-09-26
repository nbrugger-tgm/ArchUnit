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
package com.tngtech.archunit.lang.syntax;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaCodeUnit;
import com.tngtech.archunit.core.domain.JavaConstructor;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.properties.CanAccess;
import com.tngtech.archunit.core.domain.properties.CanBeAccessed;
import com.tngtech.archunit.core.domain.properties.HasSourceCodeLocation;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.conditions.ArchConditions;
import com.tngtech.archunit.lang.syntax.elements.BeAccessedSpecification;
import com.tngtech.archunit.lang.syntax.elements.ClassesShouldConjunction;
import com.tngtech.archunit.lang.syntax.elements.ClassesThat;
import com.tngtech.archunit.lang.syntax.elements.CodeUnitsThat;
import com.tngtech.archunit.lang.syntax.elements.MethodsThat;

import static com.tngtech.archunit.lang.conditions.ArchConditions.onlyBeAccessedByAnyPackage;
import static com.tngtech.archunit.lang.conditions.ArchConditions.onlyBeAccessedByClassesThat;

class BeAccessedSpecificationInternal<CONJUNCTION extends ShouldInternal<CONJUNCTION,  CanBeAccessed>>
        implements BeAccessedSpecification<CONJUNCTION> {
    private final CONJUNCTION classesShould;

    BeAccessedSpecificationInternal(CONJUNCTION classesShould) {
        this.classesShould = classesShould;
    }

    @Override
    public CONJUNCTION byAnyPackage(String... packageIdentifiers) {
        return classesShould.addCondition(onlyBeAccessedByAnyPackage(packageIdentifiers));
    }

    @Override
    public ClassesThat<CONJUNCTION> byClassesThat() {
        return new ClassesThatInternal<>(predicate -> classesShould.addCondition(onlyBeAccessedByClassesThat(predicate)));
    }

    @Override
    public CONJUNCTION byClassesThat(DescribedPredicate<? super JavaClass> predicate) {
        return classesShould.addCondition(onlyBeAccessedByClassesThat(predicate));
    }

    @Override
    public CONJUNCTION byCodeUnitsThat(DescribedPredicate<? super JavaCodeUnit> predicate) {
        return classesShould.addCondition(ArchConditions.onlyBeAccessedByCodeUnitThat(CanBeAccessed.Predicates.accessedBy(predicate)));
    }

    @Override
    public CodeUnitsThat<CONJUNCTION> byCodeUnitsThat() {
        return new CodeUnitsThatInternal<JavaCodeUnit,CONJUNCTION>(classesShould,new PredicateAggregator<JavaCodeUnit>());
    }

    @Override
    public CONJUNCTION byMethodsThat(DescribedPredicate<? super JavaMethod> predicate) {
        return null;
    }

    @Override
    public MethodsThat<CONJUNCTION> byMethodsThat() {
        return null;
    }

    @Override
    public CONJUNCTION byConstructorsThat(DescribedPredicate<? super JavaConstructor> predicate) {
        return null;
    }

    @Override
    public CONJUNCTION byMethods() {
        return null;
    }

    @Override
    public CONJUNCTION byConstructors() {
        return null;
    }

    @Override
    public CONJUNCTION byStaticInitializers() {
        return null;
    }

    @Override
    public CONJUNCTION byAnything() {
        return null;
    }

    @Override
    public CONJUNCTION byAccessorThat(DescribedPredicate<? super CanAccess> predicate) {
        return null;
    }
}
