""" async-profiler dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

def async_profiler_repositories():
    http_archive(
        name = "async_profiler_linux_x64",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "f833908a20a7e626e1f2c8f93fb6679ab139fb38287eca6407101ccc646995a7",
        strip_prefix = "async-profiler-2.6-linux-x64",
        urls = ["https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.6/async-profiler-2.6-linux-x64.tar.gz"],
    )

    http_archive(
        name = "async_profiler_linux_arm64",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "61f240fdca52ccc2eba34acd4134a7af524715fc6a9c452071aac5ddc1fb494d",
        strip_prefix = "async-profiler-2.6-linux-arm64",
        urls = ["https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.6/async-profiler-2.6-linux-arm64.tar.gz"],
    )

    http_archive(
        name = "async_profiler_macos",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "cd046214b5edafd79878211e877a98fea483f7804c6e60f1ae240d3eac02fb86",
        strip_prefix = "async-profiler-2.6-macos",
        urls = ["https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.6/async-profiler-2.6-macos.zip"],
    )
