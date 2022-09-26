package com.tngtech.archunit.lang.syntax.elements;

import com.tngtech.archunit.PublicAPI;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaCall;
import com.tngtech.archunit.core.domain.JavaConstructorCall;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaFieldAccess;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaMethodCall;
import com.tngtech.archunit.core.domain.properties.HasName;

import static com.tngtech.archunit.PublicAPI.Usage.ACCESS;

public interface AccessorShould<CONJUNCTION> {

    /**
     * Matches against getting of a specific field (e.g. <code>return someClass.<b>someField</b>;</code>).
     *
     * @param owner     The class declaring the field
     * @param fieldName The name of the field to match
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION getField(Class<?> owner, String fieldName);

    /**
     * Matches against getting a specific field (e.g. <code>return someClass.<b>someField</b>;</code>).
     *
     * @param ownerName The fully qualified class name of the class declaring the field
     * @param fieldName The name of the field to match
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION getField(String ownerName, String fieldName);

    /**
     * Matches all reading field accesses against the supplied predicate.
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
    CONJUNCTION getFieldThat(DescribedPredicate<? super JavaField> predicate);

    /**
     * Matches against getting of fields, where origin (a method or constructor) and target (a field)
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
    CONJUNCTION getFieldWhere(DescribedPredicate<? super JavaFieldAccess> predicate);

    /**
     * Matches against setting a specific field (e.g. <code>someClass.<b>someField</b> = newValue;</code>).
     *
     * @param owner     The class declaring the field
     * @param fieldName The name of the field to match
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION setField(Class<?> owner, String fieldName);

    /**
     * Matches against setting a specific field (e.g. <code>someClass.<b>someField</b> = newValue;</code>).
     *
     * @param ownerName The fully qualified class name of the class declaring the field
     * @param fieldName The name of the field to match
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION setField(String ownerName, String fieldName);

    /**
     * Matches against setting of fields, where origin (a method or constructor) and target (a field)
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
    CONJUNCTION setFieldWhere(DescribedPredicate<? super JavaFieldAccess> predicate);

    /**
     * Matches all writing field accesses against the supplied predicate.
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
    CONJUNCTION setFieldThat(DescribedPredicate<? super JavaField> predicate);

    @PublicAPI(usage = ACCESS)
    CallSpecification<CONJUNCTION> call();
    @PublicAPI(usage = ACCESS)
    CallSpecification<CONJUNCTION> onlyCall();
    @PublicAPI(usage = ACCESS)
    CallSpecification<CONJUNCTION> notCall();


    /**
     * Asserts that all classes selected by this rule access certain classes (compare {@link #onlyAccessClassesThat()}).<br>
     * NOTE: This usually makes more sense the negated way, e.g.
     * <p>
     * <pre><code>
     * {@link ArchRuleDefinition#noClasses() noClasses()}.{@link GivenClasses#should() should()}.{@link #accessClassesThat()}.{@link ClassesThat#haveFullyQualifiedName(String) haveFullyQualifiedName(String)}
     * </code></pre>
     *
     * NOTE: 'access' refers only to violations by real accesses, i.e. accessing a field, and calling a method.
     * Compare with {@link #dependOnClassesThat()} that catches a wider variety of violations.
     *
     * @return A syntax element that allows choosing which classes should be accessed
     */
    @PublicAPI(usage = ACCESS)
    ClassesThat<CONJUNCTION> accessClassesThat();

    /**
     * Asserts that all classes selected by this rule access certain classes (compare {@link #onlyAccessClassesThat(DescribedPredicate)}.<br>
     * NOTE: This usually makes more sense the negated way, e.g.
     * <p>
     * <pre><code>
     * {@link ArchRuleDefinition#noClasses() noClasses()}.{@link GivenClasses#should() should()}.{@link #accessClassesThat(DescribedPredicate) accessClassesThat(myPredicate)}
     * </code></pre>
     *
     * NOTE: 'access' refers only to violations by real accesses, i.e. accessing a field, and calling a method.
     * Compare with {@link #dependOnClassesThat(DescribedPredicate)} that catches a wider variety of violations.
     * <br><br>
     * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
     * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
     * {@link JavaClass} can be found within {@link JavaClass.Predicates} or one of the respective ancestors like {@link HasName.Predicates}.
     *
     * @param predicate Determines which {@link JavaClass JavaClasses} match the access target
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    AccessSpecification<CONJUNCTION> access();

    /**
     * Asserts that all classes selected by this rule access certain classes (compare {@link #onlyAccessClassesThat(DescribedPredicate)}.<br>
     * NOTE: This usually makes more sense the negated way, e.g.
     * <p>
     * <pre><code>
     * {@link ArchRuleDefinition#noClasses() noClasses()}.{@link GivenClasses#should() should()}.{@link #accessClassesThat(DescribedPredicate) accessClassesThat(myPredicate)}
     * </code></pre>
     *
     * NOTE: 'access' refers only to violations by real accesses, i.e. accessing a field, and calling a method.
     * Compare with {@link #dependOnClassesThat(DescribedPredicate)} that catches a wider variety of violations.
     * <br><br>
     * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
     * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
     * {@link JavaClass} can be found within {@link JavaClass.Predicates} or one of the respective ancestors like {@link HasName.Predicates}.
     *
     * @param predicate Determines which {@link JavaClass JavaClasses} match the access target
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    AccessSpecification<CONJUNCTION> notAccess();

    /**
     * Asserts that all classes selected by this rule ONLY access certain classes (compare {@link #accessClassesThat()}).<br>
     * E.g.
     * <p>
     * <pre><code>
     * {@link ArchRuleDefinition#noClasses() classes()}.{@link GivenClasses#should() should()}.{@link #onlyAccessClassesThat()}.{@link ClassesThat#haveFullyQualifiedName(String) haveFullyQualifiedName(String)}
     * </code></pre>
     *
     * NOTE: 'access' refers only to violations by real accesses, i.e. accessing a field, and calling a method.
     * Compare with {@link #onlyDependOnClassesThat()}) that catches a wider variety of violations.
     *
     * @return A syntax element that allows choosing which classes should only be accessed
     */
    @PublicAPI(usage = ACCESS)
    AccessSpecification<CONJUNCTION> onlyAccess();
}
