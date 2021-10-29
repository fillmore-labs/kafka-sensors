load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "serialization",
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=\\.(Immutable|GsonAdapters)[A-Z][A-Za-z0-9]*$$"],
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/gson_faster:__subpackages__",
        "//src/test:__subpackages__",
    ],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/gson/serialization",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/gson_faster",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_google_code_gson_gson",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_guava_guava",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
