load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "serialization",
    srcs = glob(["*.java"]),
    javacopts = ["-AskipDefs=\\.Immutable[A-Z][A-Za-z0-9]*$$"],
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/ion:__subpackages__",
        "//src/test:__subpackages__",
    ],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/ion",
        "//toolchain:checker",
        "//toolchain:dagger",
        "//toolchain:immutables",
        "@maven//:com_amazon_ion_ion_java",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_guava_guava",
        "@maven//:jakarta_inject_jakarta_inject_api",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
