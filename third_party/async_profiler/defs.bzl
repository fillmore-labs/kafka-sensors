""" async-profiler dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

def async_profiler_repositories():
    http_archive(
        name = "async_profiler_linux_x64",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "bb41cda5a3b529c023f36d6a1d33439b786b9957a64971e553d0b22bd14dc13d",
        strip_prefix = "async-profiler-2.8.3-linux-x64",
        url = "https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.8.3/async-profiler-2.8.3-linux-x64.tar.gz",
    )

    http_archive(
        name = "async_profiler_linux_arm64",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "45e9e878d77636d53adaefd89ca754721c61737454b07b95796cabdba8eb18aa",
        strip_prefix = "async-profiler-2.8.3-linux-arm64",
        url = "https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.8.3/async-profiler-2.8.3-linux-arm64.tar.gz",
    )

    http_archive(
        name = "async_profiler_macos",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "d30aeaf2b0d71a99ca28ae903c11431d1a62c39ca31e07efea234f43428fe8d5",
        strip_prefix = "async-profiler-2.8.3-macos",
        url = "https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.8.3/async-profiler-2.8.3-macos.zip",
    )
