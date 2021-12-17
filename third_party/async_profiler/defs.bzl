""" async-profiler dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

def async_profiler_repositories():
    http_archive(
        name = "async_profiler_linux_x64",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "f533c1dcdcc7820f8e86f888f1becffabbe134ede97965df73026944bf0c7c2e",
        strip_prefix = "async-profiler-2.5.1-linux-x64",
        urls = ["https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.5.1/async-profiler-2.5.1-linux-x64.tar.gz"],
    )

    http_archive(
        name = "async_profiler_linux_arm64",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "7834cbfe3957b82d235abc7def73f67279ab84d23cdcd63a4f74549948b5db81",
        strip_prefix = "async-profiler-2.5.1-linux-arm64",
        urls = ["https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.5.1/async-profiler-2.5.1-linux-arm64.tar.gz"],
    )

    http_archive(
        name = "async_profiler_macos",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "ebd7409f15fa579e91fb1ef1263d285e526b2e87c3f0354975c2477889db6f02",
        strip_prefix = "async-profiler-2.5.1-macos",
        urls = ["https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.5.1/async-profiler-2.5.1-macos.zip"],
    )
