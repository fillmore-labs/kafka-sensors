""" async-profiler dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

def async_profiler_repositories():
    http_archive(
        name = "async_profiler_linux_x64",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "5f2e23fbd94ad2e83d672e17b86949cdc5a54e35c653ba32ac6973664082cb6d",
        strip_prefix = "async-profiler-2.5-linux-x64",
        urls = ["https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.5/async-profiler-2.5-linux-x64.tar.gz"],
    )

    http_archive(
        name = "async_profiler_linux_arm64",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "de73be93de45dcb623ac716227cd926930ec140b966b4dbd78ead361f95db208",
        strip_prefix = "async-profiler-2.5-linux-arm64",
        urls = ["https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.5/async-profiler-2.5-linux-arm64.tar.gz"],
    )

    http_archive(
        name = "async_profiler_macos",
        build_file = "//third_party/async_profiler:BUILD.async_profiler.bazel",
        sha256 = "5ef63b46757a2f95f7c4da25a97fc00929537caf059ed84967f955d836c67abe",
        strip_prefix = "async-profiler-2.5-macos",
        urls = ["https://github.com/jvm-profiling-tools/async-profiler/releases/download/v2.5/async-profiler-2.5-macos.zip"],
    )
