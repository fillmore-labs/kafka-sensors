load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "xml",
    srcs = glob(["*.java"]),
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/xml/serialization:__pkg__",
        "//src/test:__subpackages__",
    ],
    runtime_deps = [
        "@maven//:org_glassfish_jaxb_jaxb_runtime",
    ],
    deps = [
        "//toolchain:checker",
        "//toolchain:dagger",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_sun_istack_istack_commons_runtime",
        "@maven//:jakarta_xml_bind_jakarta_xml_bind_api",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
