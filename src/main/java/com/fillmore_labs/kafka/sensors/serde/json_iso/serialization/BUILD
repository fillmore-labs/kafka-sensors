load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "serialization",
    srcs = glob(["*.java"]),
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/json_iso:__subpackages__",
        "//src/test:__subpackages__",
    ],
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/json/serialization",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/json",
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_fasterxml_jackson_core_jackson_databind",
        "@maven//:com_fasterxml_jackson_datatype_jackson_datatype_guava",
        "@maven//:com_fasterxml_jackson_datatype_jackson_datatype_jdk8",
        "@maven//:com_fasterxml_jackson_datatype_jackson_datatype_jsr310",
        "@maven//:com_fasterxml_jackson_module_jackson_module_blackbird",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:jakarta_inject_jakarta_inject_api",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
