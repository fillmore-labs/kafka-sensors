load("@com_github_bazelbuild_buildtools//buildifier:def.bzl", "buildifier", "buildifier_test")

alias(
    name = "kafka-sensors",
    actual = "//src/main/java/com/fillmore_labs/kafka/sensors/app",
)

alias(
    name = "benchmark",
    actual = "//src/main/java/com/fillmore_labs/kafka/sensors/benchmark",
)

buildifier(
    name = "lint_fix",
    lint_mode = "fix",
    lint_warnings = ["all"],
    mode = "fix",
)

buildifier_test(
    name = "lint_check",
    size = "small",
    lint_mode = "warn",
    lint_warnings = ["all"],
    mode = "check",
    no_sandbox = True,
    workspace = "//:WORKSPACE",
)
