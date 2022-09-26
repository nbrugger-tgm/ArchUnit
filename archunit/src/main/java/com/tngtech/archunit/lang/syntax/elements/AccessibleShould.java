package com.tngtech.archunit.lang.syntax.elements;

import com.tngtech.archunit.PublicAPI;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.properties.CanAccess;
import com.tngtech.archunit.core.domain.properties.CanBeAccessed;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import static com.tngtech.archunit.PublicAPI.Usage.ACCESS;

/**
 * Assertions for Objects that are {@link CanBeAccessed accessible}
 */
public interface AccessibleShould<CONJUNCTION> {
    /**
     * Asserts that only certain {@link CanAccess accessors} access the selected object<br>
     * <br>E.g.
     * <pre><code>
     * {@link ArchRuleDefinition#methods()}.should().{@link #onlyBeAccessed()}.{@link BeAccessedSpecification#byConstructors() byConstructors()}
     * </code></pre>
     *
     * NOTE: 'access' refers only to violations by real accesses, i.e. accessing a field, and calling a method.
     * Compare with {@link ClassesShould#onlyHaveDependentClassesThat()} that catches a wider variety of violations.
     *
     * @return A syntax element that allows restricting which objects are allowed to access the selected object
     */
    @PublicAPI(usage = ACCESS)
    BeAccessedSpecification<CONJUNCTION> onlyBeAccessed();

    /**
     * Asserts that the selected object is accessed by the further defined {@link CanAccess accessors}.<br>
     * <br>E.g.
     * <pre><code>
     * {@link #beAccessed()}.{@link BeAccessedSpecification#byMethodsThat(DescribedPredicate) byMethodsThat()}.{@link MethodsThat#areStatic() areStatic()})
     * </code></pre>
     *
     * NOTE: 'access' refers only to violations by real accesses, i.e. accessing a field, and calling a method.
     * Compare with {@link ClassesShould#onlyHaveDependentClassesThat()} that catches a wider variety of violations.
     *
     * @return A syntax element that allows forcing how the object has to be accessed
     */
    @PublicAPI(usage = ACCESS)
    BeAccessedSpecification<CONJUNCTION> beAccessed();

    /**
     * Asserts that certain {@link CanAccess accessors} do not access the object selected by this rule.<br>
     * <br>E.g.
     * <pre><code>
     * {@link #notBeAccessed()}.{@link BeAccessedSpecification#byClassesThat()}.{@link ClassesThat#haveSimpleNameEndingWith(String) haveSimpleNameEndingWith("Controller")}
     * </code></pre>
     *
     * NOTE: 'access' refers only to violations by real accesses, i.e. accessing a field, and calling a method.
     * Compare with {@link ClassesShould#onlyHaveDependentClassesThat()} that catches a wider variety of violations.
     *
     * @return A syntax element that allows restricting how the selected object should not be accessed
     */
    @PublicAPI(usage = ACCESS)
    BeAccessedSpecification<CONJUNCTION> notBeAccessed();
}
