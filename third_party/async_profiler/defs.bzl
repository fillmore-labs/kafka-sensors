""" async-profiler dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

def async_profiler_repositories():
    http_archive(
        name = "async_profiler_linux_x64",
        build_file = "//third_party/async_profiler:BUILD.linux.bazel",
        sha256 = "5ce4aed3d7e941f39ea11f31e25c89d14ee7c09e71abae6ecc847cc4a4fd8c32",
        strip_prefix = "async-profiler-2.10-linux-x64",
        url = "https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.10/async-profiler-2.10-linux-x64.tar.gz",
    )

    http_archive(
        name = "async_profiler_linux_arm64",
        build_file = "//third_party/async_profiler:BUILD.linux.bazel",
        sha256 = "8d3b407055f66756eb9525a814cbcf119951a8fb82055df9471b6559326f7607",
        strip_prefix = "async-profiler-3.0-linux-arm64",
        url = "https://github.com/jvm-profiling-tools/async-profiler/releases/download/v3.0/async-profiler-3.0-linux-arm64.tar.gz",
    )

    http_archive(
        name = "async_profiler_macos",
        build_file = "//third_party/async_profiler:BUILD.darwin.bazel",
        sha256 = "7f46398aef41561e77012ecbd07d3cc17eac775c03e895df4fcc174f8af2ecc2",
        strip_prefix = "async-profiler-2.9-macos",
        url = "https://github.com/async-profiler/async-profiler/releases/download/v2.9/async-profiler-2.9-macos.zip",
    )
