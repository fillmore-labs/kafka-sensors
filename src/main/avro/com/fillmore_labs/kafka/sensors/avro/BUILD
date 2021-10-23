load("//third_party/avro:rules_avro.bzl", "avro_java_library")

avro_java_library(
    name = "avro",
    srcs = glob(["*.avsc"]),
    javacopts = ["-XepDisableAllChecks"],
    strings = True,
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/specific:__subpackages__",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/specific_faster:__subpackages__",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/specific:__subpackages__",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/specific_faster:__subpackages__",
        "//src/test:__subpackages__",
    ],
)
