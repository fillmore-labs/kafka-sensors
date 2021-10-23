load("@rules_java//java:defs.bzl", "java_library", "java_test")

java_library(
    name = "tests",
    testonly = True,
    srcs = glob(["*.java"]),
    javacopts = [
        "-Xlint:-cast",
        "-AskipDefs=\\.Dagger",
    ],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/all_serdes",
        "//src/test/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//toolchain:checker",
        "//toolchain:dagger",
        "//toolchain:test",
        "@maven//:com_google_flogger_flogger",
        "@maven//:com_google_guava_guava",
        "@maven//:javax_inject_javax_inject",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)

java_test(
    name = "ConsistencyTest",
    size = "small",
    data = ["//conf:logging"],
    jvm_flags = ["-Djava.util.logging.config.file=$(rootpath //conf:logging)"],
    runtime_deps = [
        ":tests",
        "@maven//:com_google_flogger_flogger_system_backend",
        "@maven//:org_slf4j_slf4j_jdk14",
    ],
)

java_test(
    name = "SerdeTest",
    size = "small",
    data = ["//conf:logging"],
    jvm_flags = ["-Djava.util.logging.config.file=$(rootpath //conf:logging)"],
    runtime_deps = [
        ":tests",
        "@maven//:com_google_flogger_flogger_system_backend",
        "@maven//:org_slf4j_slf4j_jdk14",
    ],
)

java_test(
    name = "SerdeDurationTest",
    size = "small",
    data = ["//conf:logging"],
    jvm_flags = ["-Djava.util.logging.config.file=$(rootpath //conf:logging)"],
    runtime_deps = [
        ":tests",
        "@maven//:com_google_flogger_flogger_system_backend",
        "@maven//:org_slf4j_slf4j_jdk14",
    ],
)
