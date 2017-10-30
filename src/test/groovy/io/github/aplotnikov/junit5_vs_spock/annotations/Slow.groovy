package io.github.aplotnikov.junit5_vs_spock.annotations

import static java.lang.annotation.RetentionPolicy.RUNTIME

import java.lang.annotation.Retention

@Retention(RUNTIME)
@interface Slow { }
