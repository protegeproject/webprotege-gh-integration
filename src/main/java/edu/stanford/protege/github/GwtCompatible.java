package edu.stanford.protege.github;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2023-10-19
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface GwtCompatible {

    boolean serializable();
}
