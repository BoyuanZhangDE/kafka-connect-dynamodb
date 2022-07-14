package com.trustpilot.connector.dynamodb.utils.metrics;
import com.codahale.metrics.*;
import com.codahale.metrics.jmx.JmxReporter;
import static com.codahale.metrics.MetricRegistry.name;

public class MetricUtils {
    private MetricUtils() {
    }

    public static final String NAME = "default";
    private static final MetricRegistry REGISTRY = SharedMetricRegistries.getOrCreate(NAME);

    static {
        final JmxReporter reporter = JmxReporter.forRegistry(REGISTRY).build();
        reporter.start();
    }

    public static MetricRegistry get() {
        return REGISTRY;
    }

    public static Gauge gauge(Class<?> klass, String metricName) {
        return REGISTRY.gauge(name(klass, metricName));
    }

    public static Counter counter(Class<?> klass, String metricName) {
        return REGISTRY.counter(name(klass, metricName));
    }

    public static Counter counter(String metricName) {
        return REGISTRY.counter(name(metricName));
    }

    public static Timer timer(Class<?> klass, String metricName) {
        return REGISTRY.timer(name(klass, metricName));
    }

    public static Timer timer(String metricName) {
        return REGISTRY.timer(name(metricName));
    }
}
