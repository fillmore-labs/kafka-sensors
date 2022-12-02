""" async-profiler dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

def async_profiler_repositories():
    http_archive(
        name = "async_profiler_linux_x64",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "b9a094bc480f233f72141b7793c098800054438e0e6cfe5b7f2fe13ef4ad11f0",
        strip_prefix = "async-profiler-2.9-linux-x64",
        url = "https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.9/async-profiler-2.9-linux-x64.tar.gz",
    )

    http_archive(
        name = "async_profiler_linux_arm64",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "00c91439b2fc95fa518e47570c451d92c8670c0cd68d908ee53397bb35a42779",
        strip_prefix = "async-profiler-2.9-linux-arm64",
        url = "https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.9/async-profiler-2.9-linux-arm64.tar.gz",
    )

    http_archive(
        name = "async_profiler_macos",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "7f46398aef41561e77012ecbd07d3cc17eac775c03e895df4fcc174f8af2ecc2",
        strip_prefix = "async-profiler-2.9-macos",
        url = "https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.9/async-profiler-2.9-macos.zip",
    )
