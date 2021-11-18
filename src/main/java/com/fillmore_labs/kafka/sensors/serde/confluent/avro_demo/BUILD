load("@rules_java//java:defs.bzl", "java_binary", "java_library")

java_library(
    name = "avro_demo",
    srcs = glob(["*.java"]),
    visibility = ["//visibility:public"],
    deps = [
        "//src/main/avro/com/fillmore_labs/kafka/sensors/avro",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/reflect/serialization",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/confluent",
        "//toolchain:checker",
        "@confluent_schema_registry//:kafka_schema_registry_client",
        "@confluent_schema_registry//:kafka_schema_serializer",
        "@confluent_schema_registry//:kafka_streams_avro_serde",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_flogger_flogger",
        "@maven//:com_google_guava_guava",
        "@maven//:org_apache_avro_avro",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)

java_binary(
    name = "main",
    data = ["//conf:logging"],
    jvm_flags = [
        "-Djava.util.logging.config.file=$(rootpath //conf:logging)",
        "-XX:+CrashOnOutOfMemoryError",
    ],
    main_class = "com.fillmore_labs.kafka.sensors.serde.confluent.avro_demo.Main",
    visibility = ["//visibility:public"],
    runtime_deps = [
        ":avro_demo",
        "@maven//:com_google_flogger_flogger_system_backend",
        "@maven//:org_slf4j_slf4j_jdk14",
    ],
)
