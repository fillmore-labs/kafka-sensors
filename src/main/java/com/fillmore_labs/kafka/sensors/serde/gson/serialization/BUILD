load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "serialization",
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=\\.(Immutable|GsonAdapters)[A-Z][A-Za-z0-9]*$$"],
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/gson:__subpackages__",
        "//src/test:__subpackages__",
    ],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/gson",
        "//toolchain:checker",
        "//toolchain:dagger",
        "//toolchain:immutables",
        "@maven//:com_google_code_gson_gson",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_flogger_flogger",
        "@maven//:com_google_guava_guava",
        "@maven//:jakarta_inject_jakarta_inject_api",
        "@maven//:org_apache_kafka_kafka_clients",
        "@maven//:org_immutables_gson",
    ],
)
