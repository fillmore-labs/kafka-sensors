""" async-profiler dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

def async_profiler_repositories():
    http_archive(
        name = "async_profiler_linux_x64",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "f3a52b167cfd59f740383c57cd9c6da5b0b4d8b0efb7d01510b2af1e0cd5472e",
        strip_prefix = "async-profiler-2.8-linux-x64",
        urls = ["https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.8/async-profiler-2.8-linux-x64.tar.gz"],
    )

    http_archive(
        name = "async_profiler_linux_arm64",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "7b9e9f4516ca5036834c911bed84e8a67b0ddeeb4ad839fb4fd282fc8b13fd05",
        strip_prefix = "async-profiler-2.8-linux-arm64",
        urls = ["https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.8/async-profiler-2.8-linux-arm64.tar.gz"],
    )

    http_archive(
        name = "async_profiler_macos",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "d5bf9758f479e7873e81c61c9cff376bd82202aa56028f851f497b508f177b1e",
        strip_prefix = "async-profiler-2.8-macos",
        urls = ["https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.8/async-profiler-2.8-macos.zip"],
    )
