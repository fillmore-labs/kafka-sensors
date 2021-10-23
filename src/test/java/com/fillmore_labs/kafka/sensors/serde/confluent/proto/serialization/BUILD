load("@rules_java//java:defs.bzl", "java_library", "java_test")

java_library(
    name = "tests",
    testonly = True,
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=\\.Dagger"],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/proto/serialization",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/proto/serialization",
        "//src/main/proto/fillmore_labs/kafka/sensors/v1:sensorsv1_java_proto",
        "//src/test/java/com/fillmore_labs/kafka/sensors/serde/confluent/common",
        "//toolchain:checker",
        "//toolchain:dagger",
        "//toolchain:test",
        "@com_google_protobuf//:protobuf_java",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)

java_test(
    name = "SerializationTest",
    size = "small",
    data = ["//conf:logging"],
    jvm_flags = ["-Djava.util.logging.config.file=$(rootpath //conf:logging)"],
    runtime_deps = [
        ":tests",
        "@maven//:org_slf4j_slf4j_jdk14",
    ],
)
