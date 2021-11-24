load("@rules_java//java:defs.bzl", "java_binary", "java_library")

java_library(
    name = "schema_demo",
    srcs = glob(["*.java"]),
    resources = [
        "//src/main/resources/com/fillmore_labs/kafka/sensors/serde/avro/schema_demo:schemata",
    ],
    visibility = ["//visibility:public"],
    deps = [
        "//toolchain:checker",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_flogger_flogger",
        "@maven//:com_google_guava_guava",
        "@maven//:org_apache_avro_avro",
    ],
)

java_binary(
    name = "main",
    data = ["//conf:shortlog"],
    jvm_flags = [
        "-Djava.util.logging.config.file=$(rootpath //conf:shortlog)",
        "-XX:+CrashOnOutOfMemoryError",
    ],
    main_class = "com.fillmore_labs.kafka.sensors.serde.avro.schema_demo.Main",
    visibility = ["//visibility:public"],
    runtime_deps = [
        ":schema_demo",
        "@maven//:com_google_flogger_flogger_system_backend",
        "@maven//:org_slf4j_slf4j_jdk14",
    ],
)
