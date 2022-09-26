package com.tngtech.archunit.lang.syntax;

import com.tngtech.archunit.lang.ArchCondition;

public interface ShouldInternal<SELF, TARGET> {
    SELF addCondition(ArchCondition<? super TARGET> condition);
}
