load("@google_bazel_common//testing:test_defs.bzl", "gen_java_tests")

gen_java_tests(
    name = "tests",
    srcs = glob(["*.java"]),
    deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/serializer/xml",
        "//toolchain:checker",
        "//toolchain:mockito",
        "//toolchain:test",
        "@maven//:jakarta_xml_bind_jakarta_xml_bind_api",
        "@maven//:org_apache_kafka_kafka_clients",
    ],
)
