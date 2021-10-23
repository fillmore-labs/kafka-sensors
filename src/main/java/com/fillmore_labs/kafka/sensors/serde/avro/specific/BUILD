load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "specific",
    srcs = glob(["*.java"]),
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro:__pkg__",
        "//src/test:__subpackages__",
    ],
    deps = [
        "//src/main/avro/com/fillmore_labs/kafka/sensors/avro",
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/specific/mapper",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/specific/serialization",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/mapped",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:org_apache_avro_avro",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
