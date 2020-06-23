/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniban.fw;

import io.opencensus.trace.Tracer;
import java.util.function.Supplier;
import io.opencensus.exporter.trace.jaeger.JaegerExporterConfiguration;
import io.opencensus.exporter.trace.jaeger.JaegerTraceExporter;
import io.opencensus.trace.Tracing;
import io.opencensus.trace.propagation.SpanContextParseException;
import io.opencensus.trace.propagation.TextFormat;
import io.opencensus.trace.samplers.Samplers;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.microprofile.config.ConfigProvider;

/**
 *
 * @author koduki
 */
public class TelemetryHelper {

    private static final Tracer TRACER = Tracing.getTracer(); // global singletone

    private static final TextFormat textFormat = Tracing.getPropagationComponent().getTraceContextFormat();
    private static final TextFormat.Getter<HttpServletRequest> getter = new TextFormat.Getter<HttpServletRequest>() {
        @Override
        public String get(HttpServletRequest httpRequest, String s) {
            return httpRequest.getHeader(s);
        }
    };

    public static <R> R trace(HttpServletRequest request, Supplier<R> callback) {
        try {
            var spanContext = textFormat.extract(request, getter);

            var depth = 2;
            var className = Thread.currentThread().getStackTrace()[depth].getClassName();
            var methodName = Thread.currentThread().getStackTrace()[depth].getMethodName();

            try (var ss = TRACER
                    .spanBuilderWithRemoteParent(className + "$" + methodName, spanContext)
                    .setRecordEvents(true)
                    .setSampler(Samplers.alwaysSample())
                    .startScopedSpan()) {
                return callback.get();
            }
        } catch (SpanContextParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <R> R trace(Supplier<R> callback) {
        var depth = 2;
        var className = Thread.currentThread().getStackTrace()[depth].getClassName();
        var methodName = Thread.currentThread().getStackTrace()[depth].getMethodName();

        try (var ss = TRACER
                .spanBuilder(className + "$" + methodName)
                .setRecordEvents(true)
                .setSampler(Samplers.alwaysSample())
                .startScopedSpan()) {
            return callback.get();
        }
    }

    public static void trace(Runnable callback) {
        var depth = 2;
        var className = Thread.currentThread().getStackTrace()[depth].getClassName();
        var methodName = Thread.currentThread().getStackTrace()[depth].getMethodName();

        try (var ss = TRACER
                .spanBuilder(className + "$" + methodName)
                .setRecordEvents(true)
                .setSampler(Samplers.alwaysSample())
                .startScopedSpan()) {
            callback.run();
        }
    }

    public static void init(String appName) {
        var url = ConfigProvider.getConfig().getValue("miniban.jaeger.url", String.class);
        JaegerTraceExporter.createAndRegister(
                JaegerExporterConfiguration.builder()
                        .setThriftEndpoint(url)
                        .setServiceName(appName)
                        .build());
    }
}
