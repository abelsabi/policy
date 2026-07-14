package com.assignment.policy.util;

import java.math.BigDecimal;
import java.util.Set;

public final class AppConstants {

    private AppConstants() {
    }

    public static final int MIN_AGE = 18;
    public static final int MAX_AGE = 65;

    public static final BigDecimal MIN_PREMIUM =
            BigDecimal.valueOf(5000);

    public static final BigDecimal PAN_THRESHOLD =
            BigDecimal.valueOf(50000);

    public static final BigDecimal MIN_SUM_ASSURED =
            BigDecimal.valueOf(100000);

    public static final BigDecimal MAX_SUM_ASSURED =
            BigDecimal.valueOf(50000000);

    public static final Set<Integer> VALID_POLICY_TERMS =
            Set.of(10, 15, 20, 25, 30);

}