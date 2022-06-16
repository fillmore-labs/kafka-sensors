load("@io_bazel_rules_avro//avro:avro.bzl", "AVRO_TOOLS_LABEL", "avro_java_library")

AVRO_LIBS_LABELS = {
    "core": "@maven//:org_apache_avro_avro",
    "tools": AVRO_TOOLS_LABEL,
}

avro_java_library(
    name = "avro",
    srcs = [
        # Order is important.
        "Position.avsc",
        "Reading.avsc",
        "SensorState.avsc",
        "StateDuration.avsc",
    ],
    avro_libs = AVRO_LIBS_LABELS,
    strings = True,
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/specific:__subpackages__",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/specific:__subpackages__",
        "//src/test:__subpackages__",
    ],
)
