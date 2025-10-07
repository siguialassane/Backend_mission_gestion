package com.example.Gestion_mission.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleAutorise {
    String[] roles() default {};
    boolean peutSupprimer() default false;
    boolean peutModifier() default false;
}