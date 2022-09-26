package com.tngtech.archunit.lang.syntax.elements;

import com.tngtech.archunit.PublicAPI;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaCall;
import com.tngtech.archunit.core.domain.JavaCodeUnit;
import com.tngtech.archunit.core.domain.JavaConstructor;
import com.tngtech.archunit.core.domain.JavaConstructorCall;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaMethodCall;

import static com.tngtech.archunit.PublicAPI.Usage.ACCESS;

public interface CallSpecification<CONJUNCTION> {
    /**
     * Restricts allowed origins of calls to code units matching the supplied {@link DescribedPredicate}.
     * <br><br>
     * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
     * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
     * {@link JavaCodeUnit} can be found within {@link JavaCodeUnit.Predicates} or one of the respective ancestors
     * like {@link JavaMember.Predicates}.
     *
     * @param predicate Restricts which code units the call should originate from
     * @return A syntax conjunction element, which can be completed to form a full rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION codeUnitThat(DescribedPredicate<? super JavaCodeUnit> predicate);

    /**
     * Restricts allowed origins of calls to code units matching the supplied {@link DescribedPredicate}.
     * <br><br>
     * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
     * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
     * {@link JavaCodeUnit} can be found within {@link JavaCodeUnit.Predicates} or one of the respective ancestors
     * like {@link JavaMember.Predicates}.
     *
     * @param predicate Restricts which code units the call should originate from
     * @return A syntax conjunction element, which can be completed to form a full rule
     */
    @PublicAPI(usage = ACCESS)
    CodeUnitsThat<CONJUNCTION> codeUnitThat();

    /**
     * @return A syntax element that allows restricting which classes the access should be from
     */
    @PublicAPI(usage = ACCESS)
    MethodsThat<CONJUNCTION> methodsThat();

    /**
     * Matches all method calls against the supplied predicate.
     * <br><br>
     * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
     * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
     * {@link JavaMethod} can be found within {@link JavaMethod.Predicates} or one of the respective ancestors
     * like {@link JavaMember.Predicates}.
     *
     * @param predicate Determines which {@link JavaMethod JavaMethods} match the rule
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION methodsThat(DescribedPredicate<? super JavaMethod> predicate);

    /**
     * Matches against a method call to a specific method (e.g. <code>someClass.<b>call()</b>;</code>).
     *
     * @param owner          Class declaring the method
     * @param methodName     The method name to match against
     * @param parameterTypes The parameter types of the respective method
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION method(Class<?> owner, String methodName, Class<?>... parameterTypes);

    /**
     * Matches against method call to a specific method (e.g. <code>someClass.<b>call()</b>;</code>).
     *
     * @param ownerName          The fully qualified class name declaring the method
     * @param methodName         The method name to match against
     * @param parameterTypeNames The fully qualified parameter type names
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION method(String ownerName, String methodName, String... parameterTypeNames);

    /**
     * Matches against method calls where origin (a method or constructor) and target (a method)
     * can be freely restricted by the supplied predicate.
     * <br><br>
     * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
     * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
     * {@link JavaMethodCall} can be found within {@link JavaCall.Predicates} or one of the respective ancestors
     * like {@link JavaAccess.Predicates}.
     *
     * @param predicate Determines which {@link JavaMethodCall JavaMethodCalls} match the rule
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION methodWhere(DescribedPredicate<? super JavaMethodCall> predicate);

    /**
     * Matches against any method calls
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    CONJUNCTION methods();

    /**
     * Restricts allowed origins of calls to constructors matching the supplied {@link DescribedPredicate}.
     * <br><br>
     * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
     * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
     * {@link JavaConstructor} can be found within {@link JavaConstructor.Predicates} or one of the respective ancestors
     * like {@link JavaMember.Predicates}.
     *
     * @param predicate Restricts which constructors the call should originate from. Calls from methods are treated as mismatch.
     * @return A syntax conjunction element, which can be completed to form a full rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION constructorsThat(DescribedPredicate<? super JavaConstructor> predicate);

    /**
     * Matches against a constructor call to a specific constructor (e.g. <code><b>new SomeClass()</b>;</code>).
     *
     * @param owner          Class declaring the constructor
     * @param parameterTypes The parameter types of the respective constructor
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION constructor(Class<?> owner, Class<?>... parameterTypes);

    /**
     * Matches against constructor call to a specific constructor (e.g. <code><b>new SomeClass()</b>;</code>).
     *
     * @param ownerName          The fully qualified class name declaring the constructor
     * @param parameterTypeNames The fully qualified parameter type names
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION constructor(String ownerName, String... parameterTypeNames);

    /**
     * Matches against constructor calls where origin (a method or constructor) and target (a constructor)
     * can be freely restricted by the supplied predicate.
     * <br><br>
     * Note that many predefined {@link DescribedPredicate predicates} can be found within a subclass {@code Predicates} of the
     * respective domain object or a common ancestor. For example, {@link DescribedPredicate predicates} targeting
     * {@link JavaConstructorCall} can be found within {@link JavaCall.Predicates} or one of the respective ancestors
     * like {@link JavaAccess.Predicates}.
     *
     * @param predicate Determines which {@link JavaConstructorCall JavaConstructorCalls} match the rule
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    @PublicAPI(usage = ACCESS)
    CONJUNCTION constructorsWhere(DescribedPredicate<? super JavaConstructorCall> predicate);

    /**
     * Matches against any constructor calls
     * @return A syntax element that can either be used as working rule, or to continue specifying a more complex rule
     */
    CONJUNCTION constructors();
}
