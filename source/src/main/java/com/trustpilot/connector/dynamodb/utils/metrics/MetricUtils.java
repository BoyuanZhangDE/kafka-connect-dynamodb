package com.trustpilot.connector.dynamodb.utils.metrics;
import com.codahale.metrics.*;
import com.codahale.metrics.jmx.JmxReporter;
import static com.codahale.metrics.MetricRegistry.name;

public class MetricUtils {
    private static String NAME = "default";
    private final static MetricRegistry REGISTRY = SharedMetricRegistries.getOrCreate(NAME);

    static {
        final JmxReporter reporter = JmxReporter.forRegistry(REGISTRY).build();
        reporter.start();
    }
    public static MetricRegistry getRegistry() {
        return REGISTRY;
    }
    public static Gauge gauge(Class<?> kclass, String metricName, int metricsValue){
        return REGISTRY.register(name(kclass, metricName), new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return metricsValue;
            }
        });
    };
    public static Counter counter(Class<?> klass, String metricName) {
        return REGISTRY.counter(name(klass, metricName));
    };
}
