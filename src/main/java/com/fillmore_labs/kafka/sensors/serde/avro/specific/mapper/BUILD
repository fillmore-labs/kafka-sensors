load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "mapper",
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=MapperImpl$$"],
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/specific:__pkg__",
    ],
    deps = [
        "//src/main/avro/com/fillmore_labs/kafka/sensors/avro",
        "//src/main/java/com/fillmore_labs/kafka/sensors/model",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/mapping",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/mapped",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_guava_guava",
        "@maven//:org_apache_avro_avro",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
