package com.trustpilot.connector.dynamodb.utils.metrics;

import com.codahale.metrics.*;
import com.codahale.metrics.jmx.JmxReporter;

import static com.codahale.metrics.MetricRegistry.name;

public class MetricUtils {
    private MetricUtils() {
    }

    private static final String NAME = "default";
    private final static MetricRegistry REGISTRY = SharedMetricRegistries.getOrCreate(NAME);

    static {
        final JmxReporter reporter = JmxReporter.forRegistry(REGISTRY).build();
        reporter.start();
    }

    public static MetricRegistry getRegistry() {
        return REGISTRY;
    }

    public static Gauge gauge(Class<?> kclass, String metricName, long metricsValue) {
        return REGISTRY.register(name(kclass, metricName), new Gauge<Long>() {
            @Override
            public Long getValue() {
                return metricsValue;
            }
        });
    }
}
