load("@io_bazel_rules_docker//container:container.bzl", "container_image", "container_push")
load("@io_bazel_rules_docker//java:image.bzl", "java_image")
load("@rules_java//java:defs.bzl", "java_binary", "java_library")

java_library(
    name = "benchmark_library",
    srcs = glob(["*.java"]),
    javacopts = [
        "-Xlint:-cast",
        "-AskipDefs=\\.(jmh_generated\\.|Dagger)",
    ],
    visibility = ["//visibility:public"],
    runtime_deps = [
        "@maven//:com_google_flogger_flogger_system_backend",
        "@maven//:org_slf4j_slf4j_jdk14",
    ],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/all_serdes",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//toolchain:checker",
        "//toolchain:dagger",
        "//toolchain:jmh",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:jakarta_inject_jakarta_inject_api",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)

DATA = ["//conf:logging"]

JVM_FLAGS = [
    "--add-opens",
    "java.base/java.io=ALL-UNNAMED",
    "-Djava.util.logging.config.file=$(rootpath //conf:logging)",
    "-Dorg.apache.avro.fastread=true",  # https://issues.apache.org/jira/browse/AVRO-2247
    "-Dorg.apache.avro.specific.use_custom_coders=true",  # https://issues.apache.org/jira/browse/AVRO-2090
    "-XX:+CrashOnOutOfMemoryError",
]

PROFILER_DATA = select({
    "//third_party/async_profiler:supports_profiling": [
        "//third_party/async_profiler",
        "//third_party/async_profiler:async_profiler_ext",
    ],
    "//conditions:default": [],
})

PROFILER_PATH = select({
    "//third_party/async_profiler:supports_profiling": [
        "-Djava.library.path=$$(dirname $(rootpath //third_party/async_profiler))",
    ],
    "//conditions:default": [],
})

java_binary(
    name = "benchmark",
    data = DATA + PROFILER_DATA,
    jvm_flags = JVM_FLAGS + PROFILER_PATH,
    main_class = "org.openjdk.jmh.Main",
    visibility = ["//visibility:public"],
    runtime_deps = [":benchmark_library"],
)

# Docker images

ARCHITECTURES = [
    "amd64",
    "arm64",
]

[
    java_image(
        name = "benchmark_java_image_" + architecture,
        base = "@java17_%s//image" % architecture,
        data = DATA,
        jvm_flags = JVM_FLAGS,
        main_class = "org.openjdk.jmh.Main",
        runtime_deps = [":benchmark_library"],
    )
    for architecture in ARCHITECTURES
]

[
    container_image(
        name = "benchmark_image_" + architecture,
        architecture = architecture,
        base = ":benchmark_java_image_" + architecture,
        operating_system = "linux",
        visibility = ["//visibility:public"],
    )
    for architecture in ARCHITECTURES
]

[
    container_push(
        name = "push_benchmark_" + architecture,
        format = "Docker",
        image = ":benchmark_image_" + architecture,
        registry = "index.docker.io",
        repository = "fillmorelabs/serialization-benchmark",
        tag = "latest-" + architecture,
    )
    for architecture in ARCHITECTURES
]
