load("@com_github_bazelbuild_buildtools//buildifier:def.bzl", "buildifier")

buildifier(
    name = "lint_check",
    lint_mode = "warn",
    lint_warnings = ["all"],
    mode = "check",
)

buildifier(
    name = "lint_fix",
    lint_mode = "fix",
    lint_warnings = ["all"],
    mode = "fix",
)

alias(
    name = "kafka-sensors",
    actual = "//src/main/java/com/fillmore_labs/kafka/sensors/app",
)

alias(
    name = "classcheck",
    actual = "//src/main/java/com/fillmore_labs/kafka/sensors/classcheck",
)

alias(
    name = "benchmark",
    actual = "//src/main/java/com/fillmore_labs/kafka/sensors/benchmark",
)
