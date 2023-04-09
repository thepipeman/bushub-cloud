package com.bushub.security.common.method;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@PreAuthorize("hasAuthority('SCOPE_admin')")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AdminScope {
}
